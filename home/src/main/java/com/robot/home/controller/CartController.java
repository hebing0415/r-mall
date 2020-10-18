package com.robot.home.controller;


import com.robot.api.request.CartRequest;
import com.robot.api.request.SelectRequest;
import com.robot.api.response.Message;
import com.robot.api.util.LocalUserUtil;
import com.robot.home.annotation.ControllerLog;
import com.robot.order.provider.CartProvider;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cart")
@CrossOrigin
public class CartController {


    @Reference(version = "1.0.0", check = false)
    private CartProvider cartProvider;


    @RequestMapping("/addCart")
    @ResponseBody
    @ControllerLog(description = "添加购物车")
    public Message addCartEntry(@RequestBody CartRequest cartRequest) throws Exception {
        String uid = LocalUserUtil.getUid();
        return cartProvider.addCartEntry(uid, cartRequest.getSku(), cartRequest.getQuantity(), true);
    }

    @RequestMapping("/cartNum")
    @ResponseBody
    @ControllerLog(description = "增减商品")
    public Message cartEntryNum(@RequestBody CartRequest cartRequest) throws Exception {
        String uid = LocalUserUtil.getUid();
        return cartProvider.addCartEntry(uid, cartRequest.getSku(), cartRequest.getQuantity(), false);
    }


    @RequestMapping("/deleteCart")
    @ResponseBody
    @ControllerLog(description = "删除购物车商品")
    public Message deleteCartEntry(@RequestBody CartRequest cartRequest) {
        String uid = LocalUserUtil.getUid();
        return cartProvider.deleteCartEntry(uid, cartRequest.getSku());
    }


    @RequestMapping("/findCart")
    @ResponseBody
    @ControllerLog(description = "查询购物车")
    public Message findCart() {
        String uid = LocalUserUtil.getUid();
        return cartProvider.findCart(uid);
    }

    @RequestMapping("/cleanCart")
    @ResponseBody
    @ControllerLog(description = "清空购物车")
    public Message cleanCart() {
        String uid = LocalUserUtil.getUid();
        return cartProvider.cleanCart(uid);
    }

    @RequestMapping("/selectCart")
    @ResponseBody
    @ControllerLog(description = "选择购物车商品")
    public Message selectCart(@RequestBody SelectRequest selectRequest) throws Exception {
        String uid = LocalUserUtil.getUid();
        return cartProvider.selectCart(selectRequest.getSku(), uid, selectRequest.isCheck());
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    @ControllerLog(description = "选择购物车商品")
    public Message selectAll(@RequestBody SelectRequest selectRequest) throws Exception {
        String uid = LocalUserUtil.getUid();
        return cartProvider.selectAll(uid, selectRequest.isSelectAll());
    }
}
