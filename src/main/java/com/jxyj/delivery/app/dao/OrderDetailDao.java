package com.jxyj.delivery.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxyj.delivery.app.entity.OrderDetailEntity;
import com.jxyj.delivery.sys.bo.ExportOrderDetailBO;
import com.jxyj.delivery.sys.vo.OrderDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单详情
 * 
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Mapper
public interface OrderDetailDao extends BaseMapper<OrderDetailEntity> {

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    List<OrderDetailVO> getOrderDetail(Long orderId);

    /**
     * 导出订单详情
     * @param ids
     * @return
     */
    List<ExportOrderDetailBO> listExportOrderDetailData(Long[] ids);
}
