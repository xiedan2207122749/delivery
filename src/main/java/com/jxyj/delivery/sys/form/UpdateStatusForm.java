package com.jxyj.delivery.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author xieDan
 * @Classname CommodityUpdateStatusForm
 * @Description 修改状态form
 * @Date 2022/4/9 18:19
 * @Created by mr_xie
 */
@Data
@ApiModel("修改状态form")
public class UpdateStatusForm {

    @ApiModelProperty("id")
    @NotNull(message = "id不能为空")
    @Range(min = 1, message = "id不在取值范围内")
    private Long id;

    @ApiModelProperty("状态")
    @NotNull(message = "状态不能为空")
    @Range(min = 1, max = 2, message = "状态不在取值范围内")
    private Integer status;
}
