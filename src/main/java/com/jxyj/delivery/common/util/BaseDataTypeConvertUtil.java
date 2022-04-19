package com.jxyj.delivery.common.util;

import com.jxyj.delivery.common.exception.BaseException;
import com.jxyj.delivery.common.exception.GlobalErrorEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

/**
 * @author dell
 * @Classname BaseDataTypeConvertUtil
 * @Description 基本数据类型之间的转换和异常抛售
 * @Date 2020/12/8 10:46
 * @Created by mr_xie
 */
public class BaseDataTypeConvertUtil {

    public static void judgeThrowException(String value, GlobalErrorEnum globalErrorEnum) throws BaseException {
        if (StringUtils.isBlank(value)) {
            throw new BaseException(globalErrorEnum);
        }
    }

    public static String filterStringFromCVS(String field, GlobalErrorEnum globalErrorEnum) throws BaseException {
        if (StringUtils.isBlank(field)) {
            throw new BaseException(globalErrorEnum);
        }
        return field;
    }

    public static Double filterDoubleFromCVS(String field) throws BaseException {
        if (StringUtils.isBlank(field)) {
            return null;
        } else {
            try {
                return Double.parseDouble(field);
            } catch (NumberFormatException e) {
                throw new BaseException(GlobalErrorEnum.STRING_CONVERT_NUMBER_ERROR);
            }
        }
    }

    public static Double filterDoubleFromCVS(String field, GlobalErrorEnum globalErrorEnum) throws BaseException {
        Double fieldValue = filterDoubleFromCVS(field);
        judgeThrowException(fieldValue, globalErrorEnum);
        return fieldValue;
    }

    public static void judgeThrowException(Double value, GlobalErrorEnum globalErrorEnum) throws BaseException {
        if (Objects.isNull(value) || value <= 0) {
            throw new BaseException(globalErrorEnum);
        }
    }

    public static void judgeThrowException(Integer value, GlobalErrorEnum globalErrorEnum) throws BaseException {
        if (Objects.isNull(value) || value <= 0) {
            throw new BaseException(globalErrorEnum);
        }
    }

    public static Integer filterIntegerFromCVS(String field) throws BaseException {
        if (StringUtils.isBlank(field)) {
            return null;
        } else {
            try {
                return Integer.parseInt(field);
            } catch (Exception e) {
                throw new BaseException(GlobalErrorEnum.STRING_CONVERT_NUMBER_ERROR);
            }
        }
    }

    public static Integer filterIntegerFromCVS(String field, GlobalErrorEnum globalErrorEnum) throws BaseException {
        Integer fieldValue = filterIntegerFromCVS(field);
        judgeThrowException(fieldValue, globalErrorEnum);
        return fieldValue;
    }

}
