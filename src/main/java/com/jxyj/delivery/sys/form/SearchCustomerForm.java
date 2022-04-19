package com.jxyj.delivery.sys.form;

import com.jxyj.delivery.common.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xieDan
 * @Classname SearchCustomerForm
 * @Description 查询客户form
 * @Date 2022/4/6 22:44
 * @Created by mr_xie
 */
@Data
@ApiModel("查询客户form")
public class SearchCustomerForm extends PageForm {
    /**
     * 客户名称
     */
    @ApiModelProperty("客户名称")
    private String name;
}
