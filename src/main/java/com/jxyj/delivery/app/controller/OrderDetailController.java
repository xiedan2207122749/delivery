package com.jxyj.delivery.app.controller;

import com.jxyj.delivery.app.service.OrderDetailService;
import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.sys.vo.OrderDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 订单详情
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="订单详情")
@RestController
@RequestMapping("applet/orderDetail")
@Validated
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/getOrderDetail")
    @ApiModelProperty("获取订单详情")
    public Result<List<OrderDetailVO>> getOrderDetail(@RequestParam Long orderId) {
        return Result.ok(orderDetailService.getOrderDetail(orderId));
    }

}
