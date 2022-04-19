package com.jxyj.delivery.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxyj.delivery.app.entity.TypeEntity;
import com.jxyj.delivery.sys.form.SaveTypeForm;
import com.jxyj.delivery.sys.vo.TypeVO;

import java.util.List;

/**
 * 单位表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
public interface TypeService extends IService<TypeEntity> {

    /**
     * 后台展示所有类型
     * @return
     */
    List<TypeVO> listAllType();

    /**
     * 保存大类类型
     * @param form
     */
    void saveType(SaveTypeForm form);
}

