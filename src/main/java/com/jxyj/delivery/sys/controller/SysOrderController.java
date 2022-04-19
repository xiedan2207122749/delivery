package com.jxyj.delivery.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxyj.delivery.app.enums.OrderStatusEnum;
import com.jxyj.delivery.app.service.OrderService;
import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.sys.form.SearchOrderForm;
import com.jxyj.delivery.sys.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * 订单表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="订单表(后台)")
@RestController
@RequestMapping("/sys/order")
@Validated
public class SysOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/pageOrder")
    @ApiOperation("分页order")
    public Result<IPage<OrderVO>> pageOrder(@RequestBody SearchOrderForm form) {
        return Result.ok(orderService.pageOrder(form));
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除订单")
    public Result delete(@PathVariable Long id) {
        orderService.delete(id);
        return Result.ok();
    }

    @PostMapping("/updateStatus/{id}")
    @ApiOperation("修改状态")
    public Result updateStatus(@PathVariable Long id) {
        orderService.update().set("status", OrderStatusEnum.YET_OUT.getValue()).eq("id", id).update();
        return Result.ok();
    }

    @GetMapping("/exportOrder")
    @ApiModelProperty("导出订单")
    public void exportOrder(@RequestParam Long id, HttpServletResponse response) {
        orderService.exportOrder(id, response);
    }

}
