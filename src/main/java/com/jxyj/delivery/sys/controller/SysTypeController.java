package com.jxyj.delivery.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxyj.delivery.app.entity.TypeEntity;
import com.jxyj.delivery.app.enums.WhetherEnum;
import com.jxyj.delivery.app.service.TypeService;
import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.sys.form.SaveTypeForm;
import com.jxyj.delivery.sys.vo.TypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 类型表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="类型(后台)")
@RestController
@RequestMapping("/sys/type")
@Validated
public class SysTypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("listAllType")
    @ApiOperation("展示所有类型")
    public Result<List<TypeVO>> listAllType() {
        return Result.ok(typeService.listAllType());
    }

    @PostMapping("updateOrSave")
    @ApiOperation("保存类型")
    public Result updateOrSave(@RequestBody SaveTypeForm form) {
        typeService.saveType(form);
        return Result.ok();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除类型")
    public Result delete(@PathVariable Long id) {
        typeService.update().set("deleted", WhetherEnum.IS.getValue()).eq("id", id).last("limit 1").update();
        return Result.ok();
    }

    @GetMapping("listAllTypeOfAddCommodity")
    @ApiOperation("获取类型用来添加商品")
    public Result<List<TypeEntity>> listAllTypeOfAddCommodity() {
        return Result.ok(typeService.list(new QueryWrapper<TypeEntity>().select("id, value").eq("deleted", WhetherEnum.NO.getValue()).orderByDesc("id")));
    }

}
