package com.jxyj.delivery.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xieDan
 * @Classname OrderVO
 * @Description 订单vo
 * @Date 2022/4/9 15:45
 * @Created by mr_xie
 */
@Data
@ApiModel("订单vo")
public class OrderVO {
    /**
     *
     */
    @ApiModelProperty("")
    private Long id;
    /**
     * 客户id
     */
    @ApiModelProperty("客户id")
    private String customerName;
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
}
