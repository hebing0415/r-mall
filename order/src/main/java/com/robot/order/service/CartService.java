package com.robot.order.service;

import com.robot.api.response.CartResponse;
import com.robot.api.response.Message;

public interface CartService {


    /**
     * 第一次 添加购物车，
     * 这里添加会多两次查询
     *
     * @param uid
     * @param sku
     * @param quantity
     * @return
     */
    Message addCartEntry(String uid, String sku, int quantity) throws Exception;

    /**
     * 减少购物车数量
     *
     * @param uid
     * @param sku
     * @param quantity
     * @return
     * @throws Exception
     */
    Message cartEntryHandle(String uid, String sku, int quantity) throws Exception;


    /**
     * 选择购物车商品购物车
     *
     * @param uid
     * @return
     */
    Message selectCart(String uid, String sku, boolean checked) throws Exception;

    /**
     * 全选购物车
     *
     * @param uid
     * @param selectAll
     * @return
     */
    Message selectAll(String uid,boolean selectAll);

    /**
     * 删除一行购物车
     *
     * @param uid
     * @param sku
     * @return
     */
    Message deleteCartEntry(String uid, String sku);

    /**
     * 查找购物车
     *
     * @param uid
     * @return
     */
    Message findCart(String uid);

    /**
     * 清空购物车
     *
     * @param uid
     * @return
     */
    Message cleanCart(String uid);

    /**
     * 查询check的商品
     * @param uid
     * @return
     */
    CartResponse checkCartEntry(String uid);
}
