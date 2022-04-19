package com.jxyj.delivery.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxyj.delivery.app.entity.OrderEntity;
import com.jxyj.delivery.app.form.SaveOrderForm;
import com.jxyj.delivery.app.service.OrderService;
import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.common.util.UserContext;
import com.jxyj.delivery.sys.form.SearchOrderForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 订单表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="订单表")
@RestController
@RequestMapping("/applet/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    @ApiOperation("添加订单")
    public Result addOrder(@RequestBody SaveOrderForm form) {
        orderService.addOrder(form);
        return Result.ok();
    }

    @PostMapping("/pageOrder")
    @ApiOperation("查看历史订单")
    public Result<IPage<OrderEntity>> pageOrder(@RequestBody SearchOrderForm form) {
        IPage<OrderEntity> page = form.getPage();
        orderService.page(page, new QueryWrapper<OrderEntity>().select("id, total_expense, create_time, remark, status").eq("customer_id", UserContext.getUserId()).orderByDesc("id"));
        return Result.ok(page);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除订单")
    public Result delete(@PathVariable Long id) {
        orderService.deleteOfCustomer(id);
        return Result.ok();
    }


}
