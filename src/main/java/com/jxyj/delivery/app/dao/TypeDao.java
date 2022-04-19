package com.jxyj.delivery.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxyj.delivery.app.entity.TypeEntity;
import com.jxyj.delivery.sys.vo.TypeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 单位表
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Mapper
public interface TypeDao extends BaseMapper<TypeEntity> {
    /**
     * 后台获取商品大类
     * @return
     */
    List<TypeVO> listAllType();
}
