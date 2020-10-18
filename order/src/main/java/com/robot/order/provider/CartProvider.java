package com.robot.order.provider;

import com.robot.api.response.Message;

public interface CartProvider {

    /**
     * 添加购物车
     * @param uid
     * @param sku
     * @param quantity
     * @return
     */
    Message addCartEntry(String uid, String sku, int quantity,boolean oneAdd) throws Exception;

    /**
     * 删除一行购物车
     * @param uid
     * @param sku
     * @return
     */
    Message deleteCartEntry(String uid, String sku);

    /**
     * 查找购物车
     * @param uid
     * @return
     */
    Message findCart(String uid);

    /**
     * 清空购物车
     * @param uid
     * @return
     */
    Message cleanCart(String uid);

    Message selectCart(String sku,String uid,boolean checked) throws Exception;

    Message selectAll(String uid, boolean selectAll);
}
