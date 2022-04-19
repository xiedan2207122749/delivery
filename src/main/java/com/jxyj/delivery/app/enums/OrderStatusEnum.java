package com.jxyj.delivery.app.enums;

/**
 * @author xieDan
 * @Classname OrderStatusEnum
 * @Description
 * @Date 2022/4/9 11:47
 * @Created by mr_xie
 */
public enum OrderStatusEnum {
    /**
     * 没出货
     */
    NO_OUT(1),
    /**
     * 已出货
     */
    YET_OUT(2);

    private final int value;

    OrderStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
