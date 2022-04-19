package com.jxyj.delivery.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色与菜单对应关系
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Data
@ApiModel("角色与菜单对应关系")
@TableName("sys_role_menu")
public class SysRoleMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
    private Long id;
	/**
	 * 角色ID
	 */
	@ApiModelProperty("角色ID")
    private Long roleId;
	/**
	 * 菜单ID
	 */
	@ApiModelProperty("菜单ID")
    private Long menuId;

}
