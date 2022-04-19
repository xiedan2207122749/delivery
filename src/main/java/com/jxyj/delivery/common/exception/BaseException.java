package com.jxyj.delivery.common.exception;

import lombok.Data;

/**
 * @author dell
 * @Classname BaseException
 * @Description
 * @Date 2020/11/20 11:26
 * @Created by mr_xie
 * */
@Data
public class BaseException extends RuntimeException {

    private String message;
    private int code;

    public BaseException(String message) {
        super();
        this.message = message;
    }

    public BaseException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BaseException(GlobalErrorEnum globalErrorEnum) {
        super();
        this.code = globalErrorEnum.getCode();
        this.message = globalErrorEnum.getMessage();
    }

    
}
