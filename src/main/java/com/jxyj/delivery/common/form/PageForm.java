package com.jxyj.delivery.common.form;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author xieDan
 * @Classname PageUtil
 * @Description
 * @Date 2021/3/31 14:54
 * @Created by mr_xie
 */
@Data
@ApiModel("分页基类")
public class PageForm {

    /**
     * 分页尺寸
     */
    @Min(value = 5, message = "分页尺寸不能小于5")
    @ApiModelProperty("分页尺寸")
    private Integer pageSize = 10;

    /**
     * 当前页
     */
    @Min(value = 1, message = "当前页最少为1")
    @ApiModelProperty("当前页")
    private Integer currPage = 1;

    public <T>IPage<T> getPage() {
        return new Page<T>(currPage, pageSize);
    }
}
