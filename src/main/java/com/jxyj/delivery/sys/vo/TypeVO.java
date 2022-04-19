package com.jxyj.delivery.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author xieDan
 * @Classname TypeVO
 * @Description 类型vo
 * @Date 2022/4/7 23:36
 * @Created by mr_xie
 */
@Data
@ApiModel("类型vo")
public class TypeVO {

    /**
     *
     */
    @ApiModelProperty("")
    private Long id;
    /**
     * 值
     */
    @ApiModelProperty("值")
    private String value;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String username;
    /**
     *
     */
    @ApiModelProperty("")
    private Date createTime;
}
