package com.jxyj.delivery.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author xieDan
 * @Classname LoginForm
 * @Description 登录form
 * @Date 2022/4/5 9:37
 * @Created by mr_xie
 */
@Data
@ApiModel("登录form")
public class LoginForm {
    /**
     * 账号
     */
    @ApiModelProperty("账号")
    @NotBlank(message = "账号不能为空")
    @Length(min = 4, max = 20, message = "账号的长度为4-20")
    private String account;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 50, message = "密码的最大长度为6-50")
    private String password;
}
