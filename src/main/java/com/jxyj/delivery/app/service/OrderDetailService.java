package com.jxyj.delivery.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxyj.delivery.app.entity.OrderDetailEntity;
import com.jxyj.delivery.sys.vo.OrderDetailVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单详情
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
public interface OrderDetailService extends IService<OrderDetailEntity> {

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    List<OrderDetailVO> getOrderDetail(Long orderId);

    /**
     * 到处订单详情
     * @param ids
     * @param response
     */
    void exportOrder(Long[] ids, HttpServletResponse response);
}

