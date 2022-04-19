package com.jxyj.delivery.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户表
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Data
@ApiModel("客户表")
@TableName("tb_customer")
public class CustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *  
	 */
	@TableId
	@ApiModelProperty(" ")
    private Long id;
	/**
	 * 账号
	 */
	@ApiModelProperty("账号")
    private String account;
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
    private String password;
	/**
	 * 客户名称
	 */
	@ApiModelProperty("客户名称")
    private String name;
	/**
	 * 电话
	 */
	@ApiModelProperty("电话")
    private String phone;
	/**
	 * 地址
	 */
	@ApiModelProperty("地址")
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
	/**
	 * 
	 */
	@ApiModelProperty("")
    private Long creatorUserId;
	/**
	 * 
	 */
	@ApiModelProperty("")
    private Date createTime;
	/**
	 * 
	 */
	@ApiModelProperty("")
    private Date updateTime;
	/**
	 * 
	 */
	@ApiModelProperty("")
	@TableLogic
    private Integer deleted;

}
