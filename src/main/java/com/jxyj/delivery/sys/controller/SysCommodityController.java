package com.jxyj.delivery.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxyj.delivery.app.form.SearchCommodityForm;
import com.jxyj.delivery.app.service.CommodityService;
import com.jxyj.delivery.common.util.Result;
import com.jxyj.delivery.sys.form.SaveCommodityForm;
import com.jxyj.delivery.sys.form.UpdateStatusForm;
import com.jxyj.delivery.sys.vo.CommodityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 商品表
 *
 * @author 
 * @email 
 * @date 2022-04-01 20:35:52
 */
@Api(tags="商品表(后台)")
@RestController
@RequestMapping("/sys/commodity")
@Validated
public class SysCommodityController {

    @Autowired
    private CommodityService commodityService;

    @PostMapping("/updateOrSave")
    @ApiOperation("添加商品")
    public Result updateOrSave(@Valid SaveCommodityForm form) {
        // 如果是添加  必须要有图片 所以判断一下
        if (form.getId() == null) {
            if (form.getIntroduceImageFile() == null || form.getIntroduceImageFile().isEmpty() || StringUtils.isBlank(form.getIntroduceImageFile().getOriginalFilename())) {
                return Result.error("文件不能为空");
            }
        }
        commodityService.updateOrSave(form);
        return Result.ok();
    }

    @PostMapping("/pageCommodity")
    @ApiOperation("获取商品分页数据")
    public Result<IPage<CommodityVO>> pageCommodity(@RequestBody @Valid SearchCommodityForm form) {
        return Result.ok(commodityService.pageCommodity(form));
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除商品")
    public Result delete(@PathVariable Long id) {
        commodityService.removeById(id);
        return Result.ok();
    }

    @PostMapping("/updateStatus")
    @ApiOperation("修改状态")
    public Result updateStatus(@RequestBody @Valid UpdateStatusForm form) {
        commodityService.update().set("status", form.getStatus()).eq("id", form.getId()).last("limit 1").update();
        return Result.ok();
    }


}
