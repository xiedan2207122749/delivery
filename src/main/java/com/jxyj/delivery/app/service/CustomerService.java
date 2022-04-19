package com.jxyj.delivery.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxyj.delivery.app.entity.CustomerEntity;
import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.sys.form.LoginForm;
import com.jxyj.delivery.sys.form.SaveCustomerForm;
import com.jxyj.delivery.sys.form.SearchCustomerForm;

/**
 * 客户表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
public interface CustomerService extends IService<CustomerEntity> {

    /**
     * 客户登录接口
     * @param form
     * @return
     */
    Result<String> login(LoginForm form);

    /**
     * 添加客户
     * @param form
     */
    void updateOrSave(SaveCustomerForm form);

    /**
     * 获取客户分页数据
     * @param form
     * @return
     */
    IPage<CustomerEntity> pageCustomer(SearchCustomerForm form);
}

