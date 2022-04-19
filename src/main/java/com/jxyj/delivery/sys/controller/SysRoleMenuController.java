package com.jxyj.delivery.sys.controller;

import com.jxyj.delivery.sys.service.SysRoleMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 角色与菜单对应关系
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="角色与菜单对应关系")
@RestController
@RequestMapping("user/sysrolemenu")
public class SysRoleMenuController {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

}
