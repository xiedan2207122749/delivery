package com.jxyj.delivery.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxyj.delivery.sys.entity.SysUserEntity;
import com.jxyj.delivery.sys.form.LoginForm;
import com.jxyj.delivery.sys.vo.LoginInfoVO;

/**
 * 公司员工表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 登录的回调
     * @return
     */
    LoginInfoVO login(LoginForm form);
}

