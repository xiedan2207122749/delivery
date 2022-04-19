package com.jxyj.delivery.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司员工表
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Data
@ApiModel("公司员工表")
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
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
	 * 用户名
	 */
	@ApiModelProperty("用户名")
    private String username;
	/**
	 * 电话
	 */
	@ApiModelProperty("电话")
    private String phone;
	/**
	 * 备注
	 */
	@ApiModelProperty("备注")
    private String remark;
	/**
	 * 角色id
	 */
	@ApiModelProperty("角色id")
    private Long roleId;
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
    private Integer deleted;

}
