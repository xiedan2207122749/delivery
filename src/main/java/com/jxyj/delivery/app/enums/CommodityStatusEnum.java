package com.jxyj.delivery.app.enums;

/**
 * @author xieDan
 * @Classname OrderStatusEnum
 * @Description
 * @Date 2022/4/9 11:47
 * @Created by mr_xie
 */
public enum CommodityStatusEnum {
    /**
     * 上架
     */
    UP(1),
    /**
     * 下架
     */
    DOWN(2);

    private final int value;

    CommodityStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
