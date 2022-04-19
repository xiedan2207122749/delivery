package com.jxyj.delivery.common.util;

/**
 * @author Mr_xie
 * @Classname UserContext
 * @Description
 * @Date 2022/03/15 9:43
 * @Created mr_xie
 */
public class UserContext {
    private static ThreadLocal<Long> currentUserId = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        currentUserId.set(userId);
    }

    public static Long getUserId() {
        return currentUserId.get();
    }

    public static void remove() {
        currentUserId.remove();
    }
}
