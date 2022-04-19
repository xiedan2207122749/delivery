package com.jxyj.delivery.sys.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xieDan
 * @Classname ExportOrderDetailBO
 * @Description
 * @Date 2022/4/12 0:01
 * @Created by mr_xie
 */
@Data
public class ExportOrderDetailBO {

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 商品名字
     */
    private String commodityName;
    /**
     * 下单数量
     */
    private BigDecimal quantity;
    /**
     * 单位
     */
    private String unit;
}
