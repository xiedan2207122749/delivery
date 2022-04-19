package com.jxyj.delivery.common.oauth2;

import com.jxyj.delivery.common.util.CommonConstant;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class OAuth2Realm extends AuthorizingRealm {

    /**
     * 不重写这个的话 OAuth2Token这个就不能使用 会包异常： Realm [com.smcx.xxx.common.oauth2.OAuth2Realm@3bc225c9]
     *      does not support authentication token [com.smcx.xxx.common.oauth2.OAuth2Token@204e1cd0].
     *      Please ensure that the appropriate Realm implementation is configured correctly or that the realm accepts AuthenticationTokens of this type.
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("super");
        return info;
    }
    
    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 之所以这个是token 是因为实现了一个shiro的filter的过滤器
        String accessToken = (String) token.getPrincipal();
        Object userId = redisTemplate.opsForValue().get(accessToken);
        redisTemplate.opsForValue().set(accessToken, userId, CommonConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        return new SimpleAuthenticationInfo(userId, accessToken, getName());
    }
}
