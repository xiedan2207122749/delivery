package com.jxyj.delivery.app.entity;

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
 * 订单表
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Data
@ApiModel("订单表")
@TableName("tb_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
    private Long id;
	/**
	 * 客户id
	 */
	@ApiModelProperty("客户id")
    private Long customerId;
	/**
	 * 总费用
	 */
	@ApiModelProperty("总费用")
    private BigDecimal totalExpense;
	/**
	 * 配送时间
	 */
	@ApiModelProperty("配送时间")
    private Date deliveryTime;
	/**
	 * 备注
	 */
	@ApiModelProperty("备注")
    private String remark;
	/**
	 * 状态
	 */
	@ApiModelProperty("状态")
    private Integer status;
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
