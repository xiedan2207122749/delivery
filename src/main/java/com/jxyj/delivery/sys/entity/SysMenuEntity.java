package com.jxyj.delivery.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单管理
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Data
@ApiModel("菜单管理")
@TableName("sys_menu")
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
    private Long menuId;
	/**
	 * 父菜单ID，一级菜单为0
	 */
	@ApiModelProperty("父菜单ID，一级菜单为0")
    private Long parentId;
	/**
	 * 菜单名称
	 */
	@ApiModelProperty("菜单名称")
    private String name;
	/**
	 * 菜单URL
	 */
	@ApiModelProperty("菜单URL")
    private String url;
	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	@ApiModelProperty("授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;
	/**
	 * 类型   0：目录   1：菜单   2：按钮
	 */
	@ApiModelProperty("类型   0：目录   1：菜单   2：按钮")
    private Integer type;
	/**
	 * 菜单图标
	 */
	@ApiModelProperty("菜单图标")
    private String icon;
	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
    private Integer orderNum;

}
