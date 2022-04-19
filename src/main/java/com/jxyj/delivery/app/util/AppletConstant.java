package com.jxyj.delivery.app.util;

/**
 * @author xieDan
 * @Classname AppletConstant
 * @Description
 * @Date 2022/4/5 17:09
 * @Created by mr_xie
 */
public class AppletConstant {
    /**
     * 用户token
     */
    public static final String USER_TOKEN = "appletUserToken";

    /**
     * 用户ip
     */
    public static String GET_USER_IP(Long userId) {
        return "appletUserIp" + userId;
    }


    /**
     * 获取被挤下去的人的token
     *
     * @param token
     * @return
     */
    public static String GET_SQUEEZED_TOKEN(String token) {
        return "appletSqueezedToken" + token;
    }
}
