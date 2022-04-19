package com.jxyj.delivery.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxyj.delivery.app.entity.CustomerEntity;
import com.jxyj.delivery.app.service.CustomerService;
import com.jxyj.delivery.common.annotation.CurrentLimiting;
import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.sys.form.SaveCustomerForm;
import com.jxyj.delivery.sys.form.SearchCustomerForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * 客户表(后台)
 *
 * @author
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="客户表(后台)")
@RestController
@RequestMapping("/sys/customer")
@Validated
public class SysCustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/updateOrSave")
    @ApiOperation("修改或添加客户")
    @CurrentLimiting
    public Result updateOrSave(@RequestBody @Valid SaveCustomerForm form) {
        customerService.updateOrSave(form);
        return Result.ok();
    }

    @PostMapping("/pageCustomer")
    @ApiOperation("获取分页客户数据")
    public Result<IPage<CustomerEntity>> pageCustomer(@RequestBody SearchCustomerForm form) {
        return Result.ok(customerService.pageCustomer(form));
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除客户")
    @CurrentLimiting
    public Result deleteCustomer(@PathVariable @NotNull(message = "id不能为空") @Range(min = 1, message = "id不在取值范围内") Long id) {
        customerService.removeById(id);
        return Result.ok();
    }

}
