package com.jxyj.delivery.sys.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxyj.delivery.common.exception.BaseException;
import com.jxyj.delivery.common.exception.GlobalErrorEnum;
import com.jxyj.delivery.sys.dao.SysUserDao;
import com.jxyj.delivery.sys.entity.SysUserEntity;
import com.jxyj.delivery.sys.form.LoginForm;
import com.jxyj.delivery.sys.service.SysUserService;
import com.jxyj.delivery.sys.util.SysConstant;
import com.jxyj.delivery.sys.vo.LoginInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 系统用户
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public LoginInfoVO login(LoginForm form) {
        SysUserEntity user = this.getOne(new QueryWrapper<SysUserEntity>().select("id, password, username, deleted").eq("account", form.getAccount()).last("limit 1"));
        if (user == null) {
            throw new BaseException(GlobalErrorEnum.ACCOUNT_OR_PASSWORD_ERROR);
        } else if (user.getDeleted() == 1) {
            // 被冻结
            throw new BaseException(GlobalErrorEnum.ACCOUNT_HAS_BEEN_BANNED);
        } else if (!user.getPassword().equals(DigestUtil.md5Hex(form.getPassword()))) {
            // 密码错误
            throw new BaseException(GlobalErrorEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }
        String nowToken = UUID.randomUUID().toString();
        securityCheck(user.getId(), nowToken);
        redisTemplate.opsForValue().set(nowToken, user.getId());

        LoginInfoVO loginInfoVO = new LoginInfoVO();
        loginInfoVO.setToken(nowToken);
        loginInfoVO.setUsername(user.getUsername());
        return loginInfoVO;


    }


    /**
     * 关于一些ip的安全检测
     *
     * @param userId
     * @param nowToken
     */
    private void securityCheck(Long userId, String nowToken) {
        // 先去把之前用的token获取到
        Object obj = redisTemplate.opsForHash().get(SysConstant.USER_TOKEN, userId.toString());
        if (obj != null) {
            // 如果删除之前的token成功了的话 说明上一个用的token还没过期
            redisTemplate.delete(obj.toString());
        }
        // 把目前用户用的token放到 最后一次使用的token里面去
        redisTemplate.opsForHash().put(SysConstant.USER_TOKEN, userId.toString(), nowToken);
    }
}
