package com.jxyj.delivery.sys.form;

import com.jxyj.delivery.common.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xieDan
 * @Classname SearchOrderForm
 * @Description 查询订单form
 * @Date 2022/4/9 11:16
 * @Created by mr_xie
 */
@ApiModel("查询订单form")
@Data
public class SearchOrderForm extends PageForm {

    @ApiModelProperty("客户名称")
    private String customerName;

    private String startCreateTime;

    private String endCreateTime;
}
