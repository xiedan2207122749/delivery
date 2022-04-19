package com.jxyj.delivery.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 *
 * @author dell
 */
public class ShiroUtils {

	public static boolean hasRole(String roleName) {
		return SecurityUtils.getSubject().hasRole(roleName);
	}
	
	public static boolean hasRole(String... roleName) {
		Subject subject = SecurityUtils.getSubject();
		for (String item : roleName) {
			if (subject.hasRole(item)) {
				return true;
			}
		}
		return false;
	}

	public static Long getUserId() {
		return Long.parseLong(SecurityUtils.getSubject().getPrincipal().toString());
	}

	public static boolean isLogin() {
		Subject subject = SecurityUtils.getSubject();
		return subject.getPrincipal() != null && subject.isAuthenticated();
	}
}
