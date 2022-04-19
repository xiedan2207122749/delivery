package com.jxyj.delivery.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xieDan
 * @Classname LoginInfoVO
 * @Description 登录信息vo
 * @Date 2022/4/6 21:10
 * @Created by mr_xie
 */
@Data
@ApiModel("登录信息")
public class LoginInfoVO {
    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    private String username;
    /**
     * token
     */
    @ApiModelProperty("token")
    private String token;
}


