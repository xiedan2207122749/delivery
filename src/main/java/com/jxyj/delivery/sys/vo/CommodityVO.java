package com.jxyj.delivery.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xieDan
 * @Classname CommodityVO
 * @Description 商品vo
 * @Date 2022/4/8 23:53
 * @Created by mr_xie
 */
@Data
@ApiModel("商品vo")
public class CommodityVO {

    /**
     *
     */
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
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;
    /**
     * 类型的id
     */
    @ApiModelProperty("类型的id")
    private Integer typeId;
    /**
     * 类型的值
     */
    @ApiModelProperty("类型的值")
    private String typeValue;
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
     *
     */
    @ApiModelProperty("创建者")
    private String creatorUsername;
    /**
     *
     */
    @ApiModelProperty("")
    private Date createTime;
}
