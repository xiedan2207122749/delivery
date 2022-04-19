package com.jxyj.delivery.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxyj.delivery.app.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 客户表
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Mapper
public interface CustomerDao extends BaseMapper<CustomerEntity> {

    /**
     * 统计账号出现的次数
     * @param account
     * @param id
     * @return
     */
    int countByAccount(@Param("account") String account, @Param("id") Long id);
}
