package com.jxyj.delivery.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xieDan
 * @Classname OrderDetailVO
 * @Description 订单详情vo
 * @Date 2022/4/9 11:58
 * @Created by mr_xie
 */
@Data
@ApiModel("订单详情vo")
public class OrderDetailVO {

    /**
     *
     */
    @ApiModelProperty("")
    private Long id;
    /**
     * 商品名字
     */
    @ApiModelProperty("商品名字")
    private String commodityName;
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
     * 图片
     */
    @ApiModelProperty("图片")
    private String introduceImage;
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
}
