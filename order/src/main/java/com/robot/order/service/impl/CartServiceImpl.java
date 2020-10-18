package com.robot.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.robot.api.enums.RedisKeyEnum;
import com.robot.api.middleware.BaseRedis;
import com.robot.api.pojo.CartEntry;
import com.robot.api.pojo.ProductSku;
import com.robot.api.response.CartResponse;
import com.robot.api.response.ErrorType;
import com.robot.api.response.Message;
import com.robot.order.service.CartService;
import com.robot.product.provider.ProductProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author robot
 * @date 2020/7/2
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private static final String selectAll = "selectAll";

    @Resource
    private BaseRedis baseRedis;

    @Reference(version = "1.0.0", check = false)
    private ProductProvider productProvider;


    public Message addCartEntry(String uid, String sku, int quantity) throws Exception {
        //判断是否存在
        if (baseRedis.hexists(buildUserCart(uid), sku)) {
            return cartEntryHandle(uid, sku, quantity);
        } else {
            ProductSku product = productProvider.productSku(sku);
            if (product == null) {
                return Message.error(ErrorType.PRODUCT_NOT_EXIT);
            }
            CartEntry cartEntry = CartEntry.convertCartEntry(sku, quantity, product, true);
            baseRedis.hset(buildUserCart(uid), sku, JSONObject.toJSONString(cartEntry));
            return Message.success(ErrorType.SUCCESS);
        }
    }


    @Override
    public Message cartEntryHandle(String uid, String sku, int addQuantity) throws Exception {
        Map<String, String> cartMap = baseRedis.hgetAll(buildUserCart(uid));
        boolean selectAll = true;
        int totalNum = 0;
        CartResponse cartResponse = new CartResponse();
        BigDecimal total = BigDecimal.ZERO;
        List<CartEntry> cartEntries = new ArrayList<>();
        if (CollectionUtils.isEmpty(cartMap)) {
            return null;
        }
        for (String key : cartMap.keySet()) {
            CartEntry cartEntry = JSON.parseObject(cartMap.get(key), new TypeReference<CartEntry>() {
            });
            if (StringUtils.equals(sku, cartEntry.getSku())) {
                {
                    int quantity = cartEntry.getQuantity() + addQuantity;
                    if (quantity <= 0) {
                        baseRedis.hdel(buildUserCart(uid), sku);
                        continue;
                    } else {
                        cartEntry.setQuantity(quantity);
                        baseRedis.hset(buildUserCart(uid), sku, JSONObject.toJSONString(cartEntry));
                    }
                }
            }
            int quantity = cartEntry.getQuantity();
            BigDecimal price = cartEntry.getPrice();
            if (cartEntry.isChecked()) {
                totalNum += cartEntry.getQuantity();
                total = total.add(price.multiply(new BigDecimal(quantity)));
            } else {
                selectAll = false;
            }
            cartEntries.add(cartEntry);
        }
        cartResponse.setCartEntries(cartEntries);
        cartResponse.setTotal(total);
        cartResponse.setSelectAll(selectAll);
        cartResponse.setTotalNum(totalNum);
        return Message.success(cartResponse);
    }

    @Override
    public Message selectCart(String uid, String sku, boolean checked) throws Exception {
        Map<String, String> cartMap = baseRedis.hgetAll(buildUserCart(uid));
        CartResponse cartResponse = convertCartEntry(cartMap, uid, sku, checked);
        return Message.success(cartResponse);
    }

    public Message deleteCartEntry(String uid, String sku) {
        baseRedis.hdel(buildUserCart(uid), sku);
        Map<String, String> cartMap = baseRedis.hgetAll(buildUserCart(uid));
        CartResponse cartResponse = convertCartEntry(cartMap, uid, null, null);
        return Message.success(cartResponse);
    }

    public Message findCart(String uid) {
        Map<String, String> cartMap = baseRedis.hgetAll(buildUserCart(uid));
        CartResponse cartResponse = convertCartEntry(cartMap, uid, null, null);
        return Message.success(cartResponse);

    }

    public Message cleanCart(String uid) {
        baseRedis.del(buildUserCart(uid));
        return Message.success(ErrorType.SUCCESS);

    }

    public Message selectAll(String uid, boolean all) {
        Map<String, String> cartMap = baseRedis.hgetAll(buildUserCart(uid));
        Map<String, String> hash = new HashMap<>();
        boolean selectAll = true;
        int totalNum = 0;
        CartResponse cartResponse = new CartResponse();
        BigDecimal total = BigDecimal.ZERO;
        List<CartEntry> cartEntries = new ArrayList<>();
        if (CollectionUtils.isEmpty(cartMap)) {
            return Message.success(ErrorType.SUCCESS);
        }
        for (String key : cartMap.keySet()) {
            CartEntry cartEntry = JSON.parseObject(cartMap.get(key), new TypeReference<CartEntry>() {
            });
            cartEntry.setChecked(all);
            int quantity = cartEntry.getQuantity();
            if (cartEntry.isChecked()) {
                totalNum += quantity;
            }
            if (all) {
                BigDecimal price = cartEntry.getPrice();
                total = total.add(price.multiply(new BigDecimal(quantity)));
            } else {
                selectAll = false;
            }
            cartEntries.add(cartEntry);
            hash.put(cartEntry.getSku(), JSONObject.toJSONString(cartEntry));
        }
        baseRedis.hmset(buildUserCart(uid), hash);
        cartResponse.setCartEntries(cartEntries);
        cartResponse.setTotal(total);
        cartResponse.setTotalNum(totalNum);
        cartResponse.setSelectAll(selectAll);
        return Message.success(cartResponse);
    }

    private static String buildUserCart(String uid) {
        return String.format(RedisKeyEnum.CART_KEY.getKey(), uid);
    }

    private static String buildUserSelectAll(String uid) {
        return String.format(RedisKeyEnum.SELECT_ALL_KEY.getKey(), uid);
    }


    public CartResponse convertCartEntry(Map<String, String> cartMap, String uid, String sku, Boolean check) {
        boolean selectAll = true;
        CartResponse cartResponse = new CartResponse();
        BigDecimal total = BigDecimal.ZERO;
        int totalNum = 0;
        List<CartEntry> cartEntries = new ArrayList<>();
        if (CollectionUtils.isEmpty(cartMap)) {
            return null;
        }
        for (String key : cartMap.keySet()) {
            CartEntry cartEntry = JSON.parseObject(cartMap.get(key), new TypeReference<CartEntry>() {
            });
            if (StringUtils.equals(sku, cartEntry.getSku())) {
                cartEntry.setChecked(check);
                baseRedis.hset(buildUserCart(uid), sku, JSONObject.toJSONString(cartEntry));
            }
            int quantity = cartEntry.getQuantity();
            BigDecimal price = cartEntry.getPrice();
            if (cartEntry.isChecked()) {
                total = total.add(price.multiply(new BigDecimal(quantity)));
                totalNum += quantity;
            } else {
                selectAll = false;
            }

            cartEntries.add(cartEntry);
        }
        cartResponse.setCartEntries(cartEntries);
        cartResponse.setTotal(total);
        cartResponse.setTotalNum(totalNum);
        cartResponse.setSelectAll(selectAll);
        return cartResponse;
    }

    public CartResponse checkCartEntry(String uid) {
        Map<String, String> cartMap = baseRedis.hgetAll(buildUserCart(uid));
        List<CartEntry> cartEntries = new ArrayList<>();
        CartResponse cartResponse = new CartResponse();
        BigDecimal total = BigDecimal.ZERO;
        int totalNum = 0;

        if (CollectionUtils.isEmpty(cartMap)) {
            return null;
        }
        for (String key : cartMap.keySet()) {
            CartEntry cartEntry = JSON.parseObject(cartMap.get(key), new TypeReference<CartEntry>() {
            });
            if (cartEntry.isChecked()) {
                cartEntries.add(cartEntry);
                total = total.add(cartEntry.getPrice().multiply(new BigDecimal(cartEntry.getQuantity())));
                totalNum += cartEntry.getQuantity();
            }
        }
        cartResponse.setCartEntries(cartEntries);
        cartResponse.setTotal(total);
        cartResponse.setTotalNum(totalNum);
        return cartResponse;
    }
}
