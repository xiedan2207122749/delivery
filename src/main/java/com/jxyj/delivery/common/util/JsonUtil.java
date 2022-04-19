package com.jxyj.delivery.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxyj.delivery.common.exception.BaseException;
import com.jxyj.delivery.common.exception.GlobalErrorEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author xieDan
 * @Classname JsonUtil
 * @Description
 * @Date 2021/4/30 16:44
 * @Created by mr_xie
 */
@Slf4j
public class JsonUtil {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("对象转strJson失败{}", e);
            throw new BaseException(GlobalErrorEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToObject(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            log.error("strJson转对象失败{}", e);
            throw new BaseException(GlobalErrorEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            log.error("strJson转对象失败{}", e);
            throw new BaseException(GlobalErrorEnum.UNKNOWN_ERROR);
        }
    }
}
