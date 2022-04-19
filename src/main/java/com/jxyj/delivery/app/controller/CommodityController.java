package com.jxyj.delivery.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxyj.delivery.app.entity.CommodityEntity;
import com.jxyj.delivery.app.enums.CommodityStatusEnum;
import com.jxyj.delivery.app.service.CommodityService;
import com.jxyj.delivery.common.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 商品表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="商品表")
@RestController
@RequestMapping("applet/commodity")
@Validated
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/listCommodity")
    @ApiOperation("获取所有商品")
    public Result<List<CommodityEntity>> pageCommodity(@RequestParam(required = false) Integer typeId) {
        return Result.ok(commodityService.list(new QueryWrapper<CommodityEntity>().select("id, name, price, unit, introduce_image")
                .eq(typeId != null, "type_id", typeId)
                .eq("status", CommodityStatusEnum.UP.getValue())
                .orderByDesc("id")
        ));
    }

}
