package com.jxyj.delivery.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxyj.delivery.app.entity.OrderEntity;
import com.jxyj.delivery.app.form.SaveOrderForm;
import com.jxyj.delivery.sys.form.SearchOrderForm;
import com.jxyj.delivery.sys.vo.OrderVO;

import javax.servlet.http.HttpServletResponse;

/**
 * 订单表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
public interface OrderService extends IService<OrderEntity> {
    /**
     * 删除大订单
     * @param id
     */
    void delete(Long id);

    /**
     * 添加大订单
     * @param form
     */
    void addOrder(SaveOrderForm form);

    /**
     * 获取分页订单
     * @param form
     * @return
     */
    IPage<OrderVO> pageOrder(SearchOrderForm form);

    /**
     * 导出订单
     * @param id
     * @param response
     */
    void exportOrder(Long id, HttpServletResponse response);

    /**
     * 客户删除订单
     * @param id
     */
    void deleteOfCustomer(Long id);
}

