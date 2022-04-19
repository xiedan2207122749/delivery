package com.jxyj.delivery.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Data
@ApiModel("角色")
@TableName("sys_role")
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
    private Long roleId;
	/**
	 * 角色名称
	 */
	@ApiModelProperty("角色名称")
    private String roleName;
	/**
	 * 备注
	 */
	@ApiModelProperty("备注")
    private String remark;
	/**
	 * 创建者ID
	 */
	@ApiModelProperty("创建者ID")
    private Long createUserId;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
    private Date createTime;

}
