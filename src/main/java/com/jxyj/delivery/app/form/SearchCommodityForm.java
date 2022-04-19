package com.jxyj.delivery.app.form;

import com.jxyj.delivery.common.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xieDan
 * @Classname SearchCommodityForm
 * @Description 查询商品form
 * @Date 2022/4/5 13:51
 * @Created by mr_xie
 */
@Data
@ApiModel("查询商品form")
public class SearchCommodityForm extends PageForm {

    @ApiModelProperty("商品类型id")
    private Integer typeId;

    @ApiModelProperty("商品名称")
    private String name;

}
