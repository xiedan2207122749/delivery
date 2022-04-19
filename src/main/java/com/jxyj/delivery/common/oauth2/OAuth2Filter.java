package com.jxyj.delivery.common.oauth2;

import com.jxyj.delivery.common.util.HttpContextUtils;
import com.jxyj.delivery.common.util.JsonUtil;
import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.common.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * oauth2过滤器
 *
 * @author dell
 */
@Slf4j
public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        // 获取请求token
        String token = getToken(request);
        if(StringUtils.isBlank(token)){
            return null;
        }
        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }
        return false;
    }
    private String getToken(ServletRequest request) {
        String token;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            token = httpServletRequest.getParameter("Authorization");
        }
        return token;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 获取请求token，如果token不存在，直接返回401
        String token = getToken(request);
        if(StringUtils.isBlank(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            Result result = Result.error(369, "token为空");
            String json = JsonUtil.objectToJson(result);
            httpResponse.getWriter().print(json);
            return false;
        } else {
            RedisTemplate<String, Object> redisTemplate = SpringContextUtils.getBean("redisTemplate", RedisTemplate.class);
            // 判断这个token是否存在
            if (!redisTemplate.hasKey(token)) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setContentType("application/json;charset=utf-8");
                httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
                httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
                Result result = Result.error(369, "token无效");
                String json = JsonUtil.objectToJson(result);
                httpResponse.getWriter().print(json);
                return false;
            }
        }
        // 不为空就拿token去自动登录
        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        try {
            Result result = Result.error(369, "token不存在或已过期");
            String json = JsonUtil.objectToJson(result);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {
            log.error(e.getMessage());
        }
        return false;
    }

}
