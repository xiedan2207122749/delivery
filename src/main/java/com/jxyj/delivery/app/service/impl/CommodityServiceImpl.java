package com.jxyj.delivery.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxyj.delivery.app.dao.CommodityDao;
import com.jxyj.delivery.app.entity.CommodityEntity;
import com.jxyj.delivery.app.enums.CommodityStatusEnum;
import com.jxyj.delivery.app.form.SearchCommodityForm;
import com.jxyj.delivery.app.service.CommodityService;
import com.jxyj.delivery.common.util.DeleteFileUtil;
import com.jxyj.delivery.common.util.FileUploadUtil;
import com.jxyj.delivery.common.util.ShiroUtils;
import com.jxyj.delivery.sys.form.SaveCommodityForm;
import com.jxyj.delivery.sys.vo.CommodityVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


/**
 * @author Mr_xie
 */
@Service("commodityService")
public class CommodityServiceImpl extends ServiceImpl<CommodityDao, CommodityEntity> implements CommodityService {

    @Override
    public IPage<CommodityEntity> pageCommodityOfOrder(SearchCommodityForm form) {
        IPage<CommodityEntity> page = form.getPage();
        this.page(page, new QueryWrapper<CommodityEntity>().select("id, name, price, unit, introduce_image")
                .eq(form.getTypeId() != null, "type", form.getTypeId())
                .eq("status", CommodityStatusEnum.UP.getValue())
        );
        return page;
    }


    @Override
    public IPage<CommodityVO> pageCommodity(SearchCommodityForm form) {
        IPage<CommodityVO> page = form.getPage();
        this.baseMapper.pageCommodity(page, form);
        return page;
    }

    @Override
    public void updateOrSave(SaveCommodityForm form) {
        CommodityEntity commodityEntity = wrapperCommodityEntity(form);
        if (commodityEntity.getId() == null) {
            commodityEntity.setCreatorUserId(ShiroUtils.getUserId());
            this.save(commodityEntity);
        } else {
            // 如果介绍图片不为空 说明这个人换了介绍图片 需要把之前的图片删掉
            if (StringUtils.isNotBlank(commodityEntity.getIntroduceImage())) {
                // 删除之前的图片
                String introduceImage = this.getObj(new QueryWrapper<CommodityEntity>().select("introduce_image").eq("id", form.getId()).last("limit 1"), Object::toString);
                if (StringUtils.isNotBlank(introduceImage)) {
                    DeleteFileUtil.deleteFile(introduceImage);
                }
            }
            this.update()
                    .set("name", commodityEntity.getName())
                    .set("price", commodityEntity.getPrice())
                    .set(StringUtils.isNotBlank(commodityEntity.getIntroduceImage()), "introduce_image", commodityEntity.getIntroduceImage())
                    .set("type_id", commodityEntity.getTypeId())
                    .set("remark", commodityEntity.getRemark())
                    .set("unit", commodityEntity.getUnit())
                    .eq("id", commodityEntity.getId())
                    .update();
        }
    }


    /**
     * 包装wrapperCommodityEntity
     *
     * @param form
     * @return
     */
    private CommodityEntity wrapperCommodityEntity(SaveCommodityForm form) {
        CommodityEntity commodityEntity = BeanUtil.copyProperties(form, CommodityEntity.class);
        if (form.getIntroduceImageFile() != null && StringUtils.isNotBlank(form.getIntroduceImageFile().getName())) {
            String introduceImagePosition = FileUploadUtil.uploadFileAndCreateThumbnail(form.getIntroduceImageFile());
            commodityEntity.setIntroduceImage(introduceImagePosition);
        }
        return commodityEntity;
    }
}
