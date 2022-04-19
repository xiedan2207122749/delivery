package com.jxyj.delivery.sys.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author xieDan
 * @Classname SaveCommodityForm
 * @Description 保存商品form
 * @Date 2022/4/7 22:20
 * @Created by mr_xie
 */
@Data
@ApiModel("保存商品form")
public class SaveCommodityForm {

    /**
     *
     */
    @ApiModelProperty("")
    private Long id;
    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    @NotBlank(message = "商品名称不能为空")
    private String name;
    /**
     * 价格
     */
    @ApiModelProperty("价格")
    @NotNull(message = "价格不能为空")
    private BigDecimal price;
    /**
     * 介绍图片文件
     */
    @ApiModelProperty("介绍图片文件")
    @JsonIgnore
    private MultipartFile introduceImageFile;
    /**
     * 类型id
     */
    @ApiModelProperty("类型id")
    @NotNull(message = "类型id不能为空")
    private Integer typeId;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
    /**
     * 单位
     */
    @ApiModelProperty("单位")
    @NotBlank(message = "单位不能为空")
    private String unit;
}
