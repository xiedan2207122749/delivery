package com.jxyj.delivery.sys.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author xieDan
 * @Classname SaveTypeForm
 * @Description 保存类型form
 * @Date 2022/4/7 23:28
 * @Created by mr_xie
 */
@Data
@ApiModel("保存类型form")
public class SaveTypeForm {
    /**
     *
     */
    private Long id;
    /**
     * 值
     */
    @NotBlank(message = "值不能为空")
    @Length(min = 1, max = 20, message = "值的类型")
    private String value;
}
