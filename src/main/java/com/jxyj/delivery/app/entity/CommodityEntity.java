package com.jxyj.delivery.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品表
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Data
@ApiModel("商品表")
@TableName("tb_commodity")
public class CommodityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
    private Long id;
	/**
	 * 商品名称
	 */
	@ApiModelProperty("商品名称")
    private String name;
	/**
	 * 价格
	 */
	@ApiModelProperty("价格")
    private BigDecimal price;
	/**
	 * 介绍图片
	 */
	@ApiModelProperty("介绍图片")
    private String introduceImage;
	/**
	 * 类型id
	 */
	@ApiModelProperty("类型id")
    private Integer typeId;
	/**
	 * 备注
	 */
	@ApiModelProperty("备注")
    private String remark;
	/**
	 * 单位
	 */
	@ApiModelProperty("单位")
    private String unit;
	/**
	 * 状态
	 */
	@ApiModelProperty("状态")
	private Integer status;
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

	/**
	 * 数量
	 */
	@ApiModelProperty("数量")
	@TableField(exist = false)
	private BigDecimal quantity = new BigDecimal("0.0");

}
