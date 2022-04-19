package com.jxyj.delivery.sys.controller;

import com.jxyj.delivery.sys.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 菜单管理
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="菜单管理")
@RestController
@RequestMapping("user/sysmenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;


}
