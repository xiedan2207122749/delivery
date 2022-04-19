package com.jxyj.delivery.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxyj.delivery.app.entity.TypeEntity;
import com.jxyj.delivery.app.enums.WhetherEnum;
import com.jxyj.delivery.app.service.TypeService;
import com.jxyj.delivery.common.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 类型表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="类型")
@RestController
@RequestMapping("/applet/type")
@Validated
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("listAllType")
    @ApiOperation("获取所有类型")
    public Result<List<TypeEntity>> listAllType() {
        return Result.ok(typeService.list(new QueryWrapper<TypeEntity>().select("id, value").eq("deleted", WhetherEnum.NO.getValue()).orderByDesc("id")));
    }
}
