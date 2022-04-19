package com.jxyj.delivery.app.interceptor;

import com.jxyj.delivery.common.exception.BaseException;
import com.jxyj.delivery.common.exception.GlobalErrorEnum;
import com.jxyj.delivery.common.util.UserContext;
import com.jxyj.delivery.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dell
 * @Classname AuthorizationInterceptor
 * @Description
 * @Date 2020/10/14 13:37
 * @Created mr_xie
 */
@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //放行 OPTION 请求 options请求是浏览器自主发起的一个试探性请求  是浏览器对服务器的支持情况 以及一些资源的试探性请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            log.debug("放行OPTION请求");
            return true;
        }
        //获取请求中的token 令牌
        String token = request.getHeader("Authorization");
        if (token == null) {
            token = request.getParameter("token");
        }
        judgeTokenValidity(token);
        return true;
    }

    /**
     * 判断token是否还有效
     *
     * @param token 凭证
     */
    private void judgeTokenValidity(String token) {
        if (StringUtils.isBlank(token)) {
            throw new BaseException(GlobalErrorEnum.TOKEN_EMPTY);
        }
        long userId;
        Object userIdObj = redisTemplate.opsForValue().get(token);
        if (userIdObj == null) {
            throw new BaseException(GlobalErrorEnum.TOKEN_NO_EXISTS);
        }
        userId = Long.parseLong(userIdObj.toString());
        UserContext.setUserId(userId);
    }
}
