package com.jxyj.delivery.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 类型表
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Data
@ApiModel("类型表")
@TableName("tb_type")
public class TypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.INPUT)
	@ApiModelProperty("")
    private Long id;
	/**
	 * 值
	 */
	@ApiModelProperty("值")
    private String value;
	/**
	 * 创建者id
	 */
	@ApiModelProperty("创建者id")
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
