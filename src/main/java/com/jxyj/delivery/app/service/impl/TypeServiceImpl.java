package com.jxyj.delivery.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxyj.delivery.app.dao.TypeDao;
import com.jxyj.delivery.app.entity.TypeEntity;
import com.jxyj.delivery.app.enums.WhetherEnum;
import com.jxyj.delivery.app.service.CommodityService;
import com.jxyj.delivery.app.service.TypeService;
import com.jxyj.delivery.common.exception.BaseException;
import com.jxyj.delivery.common.exception.GlobalErrorEnum;
import com.jxyj.delivery.common.util.ShiroUtils;
import com.jxyj.delivery.sys.form.SaveTypeForm;
import com.jxyj.delivery.sys.vo.TypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("typeService")
public class TypeServiceImpl extends ServiceImpl<TypeDao, TypeEntity> implements TypeService {
    @Autowired
    private CommodityService commodityService;

    @Override
    public List<TypeVO> listAllType() {
        return this.baseMapper.listAllType();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveType(SaveTypeForm form) {
        TypeEntity entity = this.getOne(new QueryWrapper<TypeEntity>().select("id, deleted").eq("value", form.getValue()).ne(form.getId() != null, "id", form.getId()).last("limit 1"));
        // 如果之前已经存在重复的值了
        if (entity != null) {
            if (entity.getDeleted() == WhetherEnum.IS.getValue()) {
                // 如果是增加这个值 那我只用把之前被删除的激活一下就行了
                this.update().set("deleted", WhetherEnum.NO.getValue()).eq("id", entity.getId()).update();
                if (form.getId() != null) {
                    // 修改的话 需要先把被删除的value激活  然后把当前这个id关联的商品全部转移到之前那个id上面去 然后把当前这个id删除掉
                    commodityService.update().set("type_id", entity.getId()).eq("type_id", form.getId()).update();
                    this.update().set("deleted", WhetherEnum.IS.getValue()).eq("id", form.getId()).update();
                }
                return;
            } else {
                throw new BaseException(GlobalErrorEnum.TYPE_VALUE_REPEAT);
            }
        }
        if (form.getId() == null) {
            TypeEntity typeEntity = new TypeEntity();
            Long beforeMaxId = this.getObj(new QueryWrapper<TypeEntity>().orderByDesc("id").last("limit 1"), item -> Long.parseLong(item.toString()));
            typeEntity.setId(beforeMaxId + 1);
            typeEntity.setValue(form.getValue());
            typeEntity.setCreatorUserId(ShiroUtils.getUserId());
            this.save(typeEntity);
        } else {
            this.update().set("value", form.getValue()).eq("id", form.getId()).last("limit 1").update();
        }
    }
}
