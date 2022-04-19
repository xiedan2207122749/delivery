package com.jxyj.delivery.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单详情
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Data
@ApiModel("订单详情")
@TableName("tb_order_detail")
public class OrderDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
    private Long id;
	/**
	 * 大订单id
	 */
	@ApiModelProperty("大订单id")
    private Long orderId;
	/**
	 * 商品id
	 */
	@ApiModelProperty("商品id")
    private Long commodityId;
	/**
	 * 下单数量
	 */
	@ApiModelProperty("下单数量")
    private BigDecimal quantity;
	/**
	 * 单位
	 */
	@ApiModelProperty("单位")
    private String unit;
	/**
	 * 单价
	 */
	@ApiModelProperty("单价")
    private BigDecimal price;
	/**
	 * 费用
	 */
	@ApiModelProperty("费用")
    private BigDecimal expense;
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
