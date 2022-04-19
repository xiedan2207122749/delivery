package com.jxyj.delivery.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxyj.delivery.app.dao.CustomerDao;
import com.jxyj.delivery.app.entity.CustomerEntity;
import com.jxyj.delivery.app.service.CustomerService;
import com.jxyj.delivery.app.util.AppletConstant;
import com.jxyj.delivery.common.exception.BaseException;
import com.jxyj.delivery.common.exception.GlobalErrorEnum;
import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.common.util.ShiroUtils;
import com.jxyj.delivery.sys.form.LoginForm;
import com.jxyj.delivery.sys.form.SaveCustomerForm;
import com.jxyj.delivery.sys.form.SearchCustomerForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result<String> login(LoginForm form) {
        CustomerEntity user = this.getOne(new QueryWrapper<CustomerEntity>().select("id, password, deleted").eq("account", form.getAccount()).last("limit 1"));
        if (user == null) {
            throw new BaseException(GlobalErrorEnum.ACCOUNT_OR_PASSWORD_ERROR);
        } else if (user.getDeleted() == 1) {
            // 被冻结
            throw new BaseException(GlobalErrorEnum.ACCOUNT_HAS_BEEN_BANNED);
        } else if (!user.getPassword().equals(form.getPassword())) {
            // 密码错误
            throw new BaseException(GlobalErrorEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }
        String nowToken = UUID.randomUUID().toString();
        securityCheck(user.getId(), nowToken);
        redisTemplate.opsForValue().set(nowToken, user.getId());
        return Result.ok(nowToken);
    }

    @Override
    public void updateOrSave(SaveCustomerForm form) {
        int count = this.baseMapper.countByAccount(form.getAccount(), form.getId());
        if (count > 0) {
            throw new BaseException(GlobalErrorEnum.ACCOUNT_EXIST_ERROR);
        }
        CustomerEntity customerEntity = BeanUtil.copyProperties(form, CustomerEntity.class);
        if (customerEntity.getId() == null) {
            customerEntity.setCreatorUserId(ShiroUtils.getUserId());
            this.save(customerEntity);
        } else {
            this.updateById(customerEntity);
        }
    }

    @Override
    public IPage<CustomerEntity> pageCustomer(SearchCustomerForm form) {
        IPage<CustomerEntity> page = form.getPage();
        this.page(page, new QueryWrapper<CustomerEntity>()
            .select("id,account,name,phone,address,company_name,remark,create_time,deleted, password")
            .like(StringUtils.isNotBlank(form.getName()), "name", form.getName())
            .orderByDesc("id")
        );
        return page;
    }

    /**
     * 关于一些ip的安全检测
     *
     * @param userId
     * @param nowToken
     */
    private void securityCheck(Long userId, String nowToken) {
        // 先去把之前用的token获取到
        Object obj = redisTemplate.opsForHash().get(AppletConstant.USER_TOKEN, userId.toString());
        if (obj != null) {
            // 如果删除之前的token成功了的话 说明上一个用的token还没过期
            redisTemplate.delete(obj.toString());
        }
        // 把目前用户用的token放到 最后一次使用的token里面去
        redisTemplate.opsForHash().put(AppletConstant.USER_TOKEN, userId.toString(), nowToken);
    }
}
