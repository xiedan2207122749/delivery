package com.jxyj.delivery.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxyj.delivery.app.entity.OrderEntity;
import com.jxyj.delivery.sys.form.SearchOrderForm;
import com.jxyj.delivery.sys.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

    /**
     * 后台获取大订单记录
     * @param page
     * @param form
     * @return
     */
    IPage<OrderVO> pageOrder(IPage<OrderVO> page, SearchOrderForm form);

    OrderVO getExportOrderData(Long id);
}
