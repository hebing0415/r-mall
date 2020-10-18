package com.robot.order.provider.impl;

import com.robot.api.response.Message;
import com.robot.order.provider.CartProvider;
import com.robot.order.service.CartService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service(version = "1.0.0", timeout = 5000)
public class CartProviderImpl implements CartProvider {

    @Resource
    private CartService cartService;

    public Message addCartEntry(String uid, String sku, int quantity, boolean oneAdd) throws Exception {
        if (oneAdd) {
            return cartService.addCartEntry(uid, sku, quantity);
        } else {
            return cartService.cartEntryHandle(uid, sku, quantity);
        }
    }

    @Override
    public Message deleteCartEntry(String uid, String sku) {
        return cartService.deleteCartEntry(uid, sku);
    }

    @Override
    public Message findCart(String uid) {
        return cartService.findCart(uid);
    }

    @Override
    public Message cleanCart(String uid) {
        return cartService.cleanCart(uid);
    }

    @Override
    public Message selectCart(String sku, String uid, boolean checked) throws Exception {
        return cartService.selectCart(uid,sku,checked);
    }

    @Override
    public Message selectAll(String uid, boolean selectAll) {
        return cartService.selectAll(uid,selectAll);
    }
}
