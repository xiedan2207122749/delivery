package com.jxyj.delivery.app.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xieDan
 * @Classname SaveOrderForm
 * @Description 保存订单form
 * @Date 2022/4/5 17:00
 * @Created by mr_xie
 */
@Data
@ApiModel("保存订单form")
public class SaveOrderForm {

    @ApiModelProperty("配送时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "配送时间不能为空")
    @Future(message = "必须是将来的时间")
    private Date deliveryTime;

    @ApiModelProperty("备注")
    @Length(max = 333, message = "备注不能超过300个汉字")
    private String remark;

    @Size(min = 1, message = "下单商品最少不能少于一条")
    private List<OrderDetailForm> orderDetailFormList;


    @Data
    public static class OrderDetailForm {

        @ApiModelProperty("商品id")
        @NotNull(message = "商品id不能为空")
        @Range(min = 1, message = "商品id的值不在取值范围内")
        private Long id;

        @ApiModelProperty("数量")
        @NotNull(message = "数量不能为空")
        @DecimalMax(value = "99999999.99", message = "数量超过最大值99999999.99")
        @Positive(message = "必须是正数")
        private BigDecimal quantity;
    }
}
