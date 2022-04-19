package com.jxyj.delivery.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxyj.delivery.app.entity.CommodityEntity;
import com.jxyj.delivery.app.form.SearchCommodityForm;
import com.jxyj.delivery.sys.vo.CommodityVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Mapper
public interface CommodityDao extends BaseMapper<CommodityEntity> {
    /**
     * 获取商品分页数据
     * @param page
     * @param form
     * @return
     */
    IPage<CommodityVO> pageCommodity(IPage<CommodityVO> page, SearchCommodityForm form);
}
