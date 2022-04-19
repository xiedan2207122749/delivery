package com.jxyj.delivery.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xieDan
 * @Classname SaveCustomerForm
 * @Description 保存客户form
 * @Date 2022/4/6 22:32
 * @Created by mr_xie
 */
@Data
@ApiModel("保存客户form")
public class SaveCustomerForm {

    /**
     *
     */
    @ApiModelProperty("")
    private Long id;
    /**
     * 账号
     */
    @ApiModelProperty("账号")
    @NotBlank(message = "账号不能为空")
    private String account;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 客户名称
     */
    @ApiModelProperty("客户名称")
    @NotBlank(message = "客户名称不能为空")
    private String name;
    /**
     * 电话
     */
    @ApiModelProperty("电话")
    @NotBlank(message = "电话不能为空")
    private String phone;
    /**
     * 地址
     */
    @ApiModelProperty("地址")
    @NotBlank(message = "地址不能为空")
    private String address;
    /**
     * 公司名称
     */
    @ApiModelProperty("公司名称")
    private String companyName;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;


}
