package com.jxyj.delivery.app.enums;

/**
 * @author xieDan
 * @Classname WhetherEnum
 * @Description
 * @Date 2021/9/10 10:54
 * @Created by mr_xie
 */
public enum WhetherEnum {
    /**
     * 不是
     */
    NO(0),
    /**
     * 是
     */
    IS(1);
    
    private final int value;
    
    WhetherEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
