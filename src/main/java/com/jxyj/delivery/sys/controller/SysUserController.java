
package com.jxyj.delivery.sys.controller;

import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.sys.form.LoginForm;
import com.jxyj.delivery.sys.service.SysUserService;
import com.jxyj.delivery.sys.vo.LoginInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 公司员工表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="公司员工表")
@RestController
@RequestMapping("/sys/user")
@Validated
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Result<LoginInfoVO> login(@RequestBody @Valid LoginForm form) {
        return Result.ok(sysUserService.login(form));
    }

    /**
     * 登出
     */
    @GetMapping("/logOut")
    @ApiOperation("登出")
    public Result logOut() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            currentUser.logout();
        }
        return Result.ok("登出成功");
    }
}
