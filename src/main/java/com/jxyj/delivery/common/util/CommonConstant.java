package com.jxyj.delivery.common.util;

/**
 * @author xieDan
 * @Classname SysConstant
 * @Description 通用常量
 * @Date 2021/7/6 11:16
 * @Created by mr_xie
 */
public class CommonConstant {

    public final static long TOKEN_EXPIRE_TIME = 1800L;

    
    /**
     * 用户ip
     */
    public static String GET_USER_IP(Long userId) {
        return "userIp" + userId;
    }
    
    /**
     * 获取被挤下去的人的token
     *
     * @param token
     * @return
     */
    public static String GET_SQUEEZED_TOKEN(String token) {
        return "squeezedToken" + token;
    }





}
