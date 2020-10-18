package com.robot.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.robot.api.enums.RedisKeyEnum;
import com.robot.api.middleware.BaseRedis;
import com.robot.api.pojo.*;
import com.robot.api.request.CategoryRequest;
import com.robot.api.response.*;
import com.robot.product.dao.*;
import com.robot.product.service.ConvertService;
import com.robot.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author robot
 * @date 2019/12/10 17:38
 */
@Service("productService")
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Resource
    private ProductSkuDao productSkuDao;

    @Resource
    private ProductImgDao productImgDao;

    @Resource
    private ProductRightsDao rightsDao;

    @Resource
    private ProductAttributeDao productAttributeDao;

    @Resource
    private ProductAttributeOptionDao productAttributeOptionDao;


    @Resource
    private BaseRedis baseRedis;

    @Override
    public List<ProductResponse> productList(Integer optionId, Integer page, Integer limit) throws Exception {
        PageBounds pageBounds = new PageBounds();
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }
        pageBounds.setLimit(limit);
        pageBounds.setPage(page);
        List<Product> products = productDao.findProductList(optionId, pageBounds);
        return ConvertService.INSTANCE.convertProduct(products);
    }


    @Override
    public ProductAttrResponse ProductNorms(CategoryRequest categoryRequest) {
        //查询商品的类别
        //查询商品
        List<ProductSku> productByProductIdSimple = productSkuDao.findProductByProductIdSimple(categoryRequest.getProductId());
        List<ProductAttribute> one = productAttributeDao.findAttributeList(productByProductIdSimple);
        List<ProductTreeResponse> tree = new ArrayList<>();
        List<ProductListResponse> list = new ArrayList<>();
        List<ProductAttributeOption> options = new ArrayList<>();
        ProductAttrResponse productAttrResponse = new ProductAttrResponse();
        for (ProductSku productSku : productByProductIdSimple) {
            ProductListResponse listResponse = new ProductListResponse();
            listResponse.setId(productSku.getSku());
            listResponse.setS1(productSku.getAttrOne());
            listResponse.setS2(productSku.getAttrTwo());
            listResponse.setPrice(productSku.getPrice().multiply(new BigDecimal(100)));
            listResponse.setStock_num(productSku.getStock());
            list.add(listResponse);
            //循环，然后将选项图放进去
            for (ProductAttributeOption option : one.get(0).getOptions()) {
                if (productSku.getAttrOne() == option.getId()) {
                    option.setImgUrl(productSku.getPictureUrl());
                    options.add(option);
                }
            }
        }
        //去重，利用java特性
        options = options.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(ProductAttributeOption :: getId))), ArrayList::new));
        one.get(0).setOptions(options);
        //是否有规格
        if (CollectionUtils.isEmpty(one)) {
            productAttrResponse.setCollection_id(categoryRequest.getProductId());
            productAttrResponse.setNone_sku(true);
        } else {
            //组装前端需要的规格
            for (int i = 0; i < one.size(); i++) {
                ProductTreeResponse treeResponse = new ProductTreeResponse();
                treeResponse.setK(one.get(i).getAttrName());
                if (i == 0) {
                    treeResponse.setK_s("s1");
                } else {
                    treeResponse.setK_s("s2");
                }
                treeResponse.setV(one.get(i).getOptions());
                productAttrResponse.setNone_sku(false);
                tree.add(treeResponse);
            }
        }

        productAttrResponse.setPrice(productByProductIdSimple.get(0).getPrice());
        productAttrResponse.setStock_num(productByProductIdSimple.get(0).getStock());
        productAttrResponse.setList(list);
        productAttrResponse.setTree(tree);
        return productAttrResponse;
    }

    //从mongodb查询商品信息
    public void setProductMongoDb(String productSku) {

    }

    @Override
    public ProductDetailResponse productDetail(String productId) throws Exception {
        return getProductSkuByDB(productId);
    }

    @Override
    public ProductSku productSku(String sku) throws Exception {
        return productSkuDao.findProductSkuDetail(sku);
    }

    @Override
    public ProductSku getProductByRedis(String sku) {
        ProductSku productSku = new ProductSku();
        if (StringUtils.isBlank(sku)) {
            return null;
        }
        String result = baseRedis.get(String.format(RedisKeyEnum.PRODUCT_DETAIL_KEY.getKey(), sku));
        //如果redis存在就直接返回
        if (StringUtils.isNotBlank(result)) {
            productSku = JSON.parseObject(result, new TypeReference<ProductSku>() {
            });
            return productSku;
        }
        //如果不存在就查库
        try {
            baseRedis.set(String.format(RedisKeyEnum.PRODUCT_DETAIL_KEY.getKey(), sku), JSON.toJSONString(productSku));
            return productSku;
        } catch (Exception e) {
            log.error("getProductByRedis error", e);
        }
        return null;
    }


    private ProductDetailResponse getProductSkuByDB(String productId) throws Exception {
        Product product = productDao.findProductOne(productId);
        ProductDetailResponse productDetailResponse = ConvertService.INSTANCE.convertProductSku(product);
        if (StringUtils.isNotBlank(product.getRights())) {
            String[] arr = product.getRights().split(",");
            List<String> strings = Arrays.asList(arr);
            List<ProductRights> rights = rightsDao.selectProductRights(strings);
            productDetailResponse.setProductRightsList(rights);
        }
        ProductImg productImg = productImgDao.findProductImg(product.getProductId());
        productDetailResponse.setBrand(product.getBrand());
        productDetailResponse.setProductImg(productImg);
        return productDetailResponse;
    }
}
