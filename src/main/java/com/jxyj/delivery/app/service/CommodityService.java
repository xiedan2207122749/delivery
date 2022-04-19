package com.jxyj.delivery.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxyj.delivery.app.entity.CommodityEntity;
import com.jxyj.delivery.app.form.SearchCommodityForm;
import com.jxyj.delivery.sys.form.SaveCommodityForm;
import com.jxyj.delivery.sys.vo.CommodityVO;

/**
 * 商品表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
public interface CommodityService extends IService<CommodityEntity> {
    /**
     * 获取商品分页数据用来添加商品
     * @param form
     * @return
     */
    IPage<CommodityEntity> pageCommodityOfOrder(SearchCommodityForm form);

    /**
     * 获取商品分页数据
     * @param form
     * @return
     */
    IPage<CommodityVO> pageCommodity(SearchCommodityForm form);

    /**
     * 保存或修改商品
     * @param form
     */
    void updateOrSave(SaveCommodityForm form);
}

