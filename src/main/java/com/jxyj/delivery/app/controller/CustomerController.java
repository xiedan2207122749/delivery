package com.jxyj.delivery.app.controller;

import com.jxyj.delivery.app.service.CustomerService;
import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.sys.form.LoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 客户表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="客户表")
@RestController
@RequestMapping("/applet/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result<String> login(@RequestBody @Valid LoginForm form) {
        return customerService.login(form);
    }



}
