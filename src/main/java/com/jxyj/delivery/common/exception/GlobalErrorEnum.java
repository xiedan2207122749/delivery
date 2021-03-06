package com.jxyj.delivery.common.exception;


import lombok.Getter;

/**
 * 定义全局的错误code码与相关提示信息
 * @author dell
 */
@Getter
public enum GlobalErrorEnum {
    /**
     * 小程序错误码
     */


    /**
     * 公共错误
     */
    TOKEN_EMPTY(1, "token不能为空"),
    TOKEN_NO_EXISTS(2, "token不存在"),
    PLEASE_TRY_AGAIN_LATER(3, "访问太频繁, 请稍等几秒再访问"),
    /**
     * 后台系统错误码
     */
    TYPE_VALUE_REPEAT(2, "类型的值已经存在"),
    NO_HAVE_PERMISSION(2, "NO_HAVE_PERMISSION"),
    CONTAIN_ILLEGAL_CHARACTER(3, "CONTAIN_ILLEGAL_CHARACTER"),
    WAY_ERROR_OR_NO_LOGIN(4, "WAY_ERROR_OR_NO_LOGIN"),
    FILE_FORMAT_ERROR(5, "FILE_FORMAT_ERROR"),
    FILE_EMPTY(6, "FILE_EMPTY"),
    FILE_NO_HAVE_DATA(7, "FILE_NO_HAVE_DATA"),
    YOU_HAVE_BEEN_TAKEN_OFF_LINE(8, "YOU_HAVE_BEEN_TAKEN_OFF_LINE"),
    ACCOUNT_OR_PASSWORD_ERROR(9, "ACCOUNT_OR_PASSWORD_ERROR"),
    ACCOUNT_EXIST_ERROR(10, "账号已经存在了"),
    ACCOUNT_HAS_BEEN_BANNED(11, "ACCOUNT_HAS_BEEN_BANNED"),
    NO_HANDLER_FOUND(12, "NO_HANDLER_FOUND"),
    DUPLICATE_KEY(13, "DUPLICATE_KEY"),
    HTTP_MESSAGE_NOT_READABLE(14, "HTTP_MESSAGE_NOT_READABLE"),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED(15, "HTTP_MEDIA_TYPE_NOT_SUPPORTED"),
    REQUIRED_FIELD_EMPTY(16, "REQUIRED_FIELD_EMPTY"),
    METHOD_ARGS_TYPE_ERROR(17, "METHOD_ARGS_TYPE_ERROR"),
    STRING_CONVERT_NUMBER_ERROR(18, "STRING_CONVERT_NUMBER_ERROR"),
    ILLEGAL_DATA(19, "非法参数, 请刷新后重试"),
    REPETITION_SUBMIT_ERROR(20, "REPETITION_SUBMIT_ERROR"),
    INSERT_ERROR(21, "INSERT_ERROR"),
    UPDATE_ERROR(22, "UPDATE_ERROR"),
    DELETE_ERROR(23, "DELETE_ERROR"),
    EMAIL_SEND_ERROR(24, "EMAIL_SEND_ERROR"),
    THIS_ORDER_YOU_NO_CAN_UPDATE(25, "THIS_ORDER_YOU_NO_CAN_UPDATE"),
    THIS_ORDER_CLEARED_NO_CAN_UPDATE_FREIGHT(26, "THIS_ORDER_CLEARED_NO_CAN_UPDATE_FREIGHT"),
    HOUSE_BL_EXIST(27, "HOUSE_BL_EXIST"),
    SHIPPING_MARK_EXIST(28, "SHIPPING_MARK_EXIST"),
    THIS_ORDER_NO_DEL(29, "THIS_ORDER_NO_DEL"),
    HOUSE_BL_ERROR(30, "HOUSE_BL_ERROR"),
    SHIPPING_MARK_ERROR(31, "SHIPPING_MARK_ERROR"),
    SENDER_COMPANY_NAME_ERROR(32, "SENDER_COMPANY_NAME_ERROR"),
    SENDER_ADDRESS_ERROR(33, "SENDER_ADDRESS_ERROR"),
    RECIPIENT_COMPANY_NAME_ERROR(34, "RECIPIENT_COMPANY_NAME_ERROR"),
    RECIPIENT_PHONE_ONE_ERROR(35, "RECIPIENT_PHONE_ONE_ERROR"),
    RECIPIENT_TYPE_ERROR(36, "RECIPIENT_TYPE_ERROR"),
    RECIPIENT_ADDRESS_ERROR(37, "RECIPIENT_ADDRESS_ERROR"),
    NUM_UNITS_ERROR(38, "NUM_UNITS_ERROR"),
    WEIGHT_ERROR(39, "WEIGHT_ERROR"),
    VOLUME_ERROR(40, "VOLUME_ERROR"),
    HOME_TRACKING_NUM_ERROR(41, "HOME_TRACKING_NUM_ERROR"),
    START_TIME_FORMAT_ERROR(42, "START_TIME_FORMAT_ERROR"),
    NO_CAN_CANCEL_SEND(43, "NO_CAN_CANCEL_SEND"),
    DATE_FORMAT_ERROR(44, "DATE_FORMAT_ERROR"),
    SATURDAY_NO_LOADING(45, "SATURDAY_NO_LOADING"),
    UPDATE_ACCOUNT_NO_EXIST(46, "UPDATE_ACCOUNT_NO_EXIST"),
    UPDATE_ACCOUNT_NO_YOU_CREATE(47, "UPDATE_ACCOUNT_NO_YOU_CREATE"),
    ORIGINAL_PASSWORD_ERROR(48, "ORIGINAL_PASSWORD_ERROR"),
    EXPORT_EXCEL_IO_EXCEPTION(49, "EXPORT_EXCEL_IO_EXCEPTION"),
    THIS_ORDER_CANNOT_BE_PROBLEM_PIECE(50, "THIS_ORDER_CANNOT_BE_PROBLEM_PIECE"),
    PLEASE_SELECT_SAME_COMPANY_ORDER(51, "PLEASE_SELECT_SAME_COMPANY_ORDER"),
    PLEASE_SELECT_SAME_SETTLEMENT_STATUS_ORDER(52, "PLEASE_SELECT_SAME_SETTLEMENT_STATUS_ORDER"),
    SOME_ORDER_NONE_START_TIME(53, "SOME_ORDER_NONE_START_TIME"),
    THIS_ORDER_FREIGHT_YOU_CANNOT_UPDATE(54, "THIS_ORDER_FREIGHT_YOU_CANNOT_UPDATE"),
    THIS_ORDER_FREIGHT_YOU_CANNOT_DELETE(55, "THIS_ORDER_FREIGHT_YOU_CANNOT_DELETE"),
    COMPANY_CODE_EXIST(56, "COMPANY_CODE_EXIST"),
    HOUSE_BL_FORMAT_ERROR(57, "HOUSE_BL_FORMAT_ERROR"),
    SHIPPING_MARK_FORMAT_ERROR(58, "SHIPPING_MARK_FORMAT_ERROR"),
    THIS_DATE_HAVE_SAME_COMPANY_NAME(59, "THIS_DATE_HAVE_SAME_COMPANY_NAME"),
    THIS_MASTER_BL_NUM_REPEAT(60, "THIS_MASTER_BL_NUM_REPEAT"),
    THIS_CONTAINER_NUM_REPEAT(61, "THIS_CONTAINER_NUM_REPEAT"),
    TASK_CREATION_FAILED(62, "TASK_CREATION_FAILED"),
    ARRIVE_CANNOT_BEFORE_START(63, "ARRIVE_CANNOT_BEFORE_START"),
    SHIP_CANNOT_UPDATE(64, "SHIP_CANNOT_UPDATE"),
    SELECT_ORDER_STATUS_NO_SAME(65, "SELECT_ORDER_STATUS_NO_SAME"),
    HOME_TRACKING_NUM_NO_SAME(66, "HOME_TRACKING_NUM_NO_SAME"),
    SHIP_ID_HAVE_NO_SAME(67, "SHIP_ID_HAVE_NO_SAME"),
    CONTAINER_ID_HAVE_NO_SAME(68, "CONTAINER_ID_HAVE_NO_SAME"),
    THIS_DATE_HAVE_SAME_SHIP(69, "THIS_DATE_HAVE_SAME_SHIP"),
    START_DATE_NEED_IS_FUTURE_OR_TODAY(70, "START_DATE_NEED_IS_FUTURE_OR_TODAY"),
    THERE_CAN_BE_ONLY_ONE_DELIVERY_MODE(71, "THERE_CAN_BE_ONLY_ONE_DELIVERY_MODE"),
    UPDATE_SETTLEMENT_STATUS_SPAN_TOO_LAGER(72, "UPDATE_SETTLEMENT_STATUS_SPAN_TOO_LAGER"),
    THE_SHIP_INFORMATION_FOR_THE_ORDER_IS_EMPTY(73, "THE_SHIP_INFORMATION_FOR_THE_ORDER_IS_EMPTY"),
    THE_SHIP_INFORMATION_FOR_WITH_THS_ORDER_IS_EMPTY(74, "THE_SHIP_INFORMATION_FOR_WITH_THS_ORDER_IS_EMPTY"),
    INCONSISTENT_SHIP_INFORMATION_FOR_THE_ORDER(75, "INCONSISTENT_SHIP_INFORMATION_FOR_THE_ORDER"),
    ORDER_START_TIME_BEFORE(76, "ORDER_START_TIME_BEFORE"),
    ORDER_NO_CONTAINER_ID(77, "ORDER_NO_CONTAINER_ID"),
    ORDERS_NO_CONTAINER_ID(78, "ORDERS_NO_CONTAINER_ID"),
    ORDERS_CONTAINER_ID_NO_SAME(79, "ORDERS_CONTAINER_ID_NO_SAME"),
    BEFORE_SETTLEMENT_STATUS_IS_CLEARING(80, "BEFORE_SETTLEMENT_STATUS_IS_CLEARING"),
    BEFORE_SETTLEMENT_STATUS_IS_CLEARED(81, "BEFORE_SETTLEMENT_STATUS_IS_CLEARED"),
    SETTLEMENT_STATUS_ROLLBACK(82, "SETTLEMENT_STATUS_ROLLBACK"),
    BEFORE_START_TIME(83, "BEFORE_START_TIME"),
    BEFORE_ARRIVE_TIME(84, "BEFORE_ARRIVE_TIME"),
    PLEASE_SELECT_TODAY_OR_A_FUTURE_DATE(85, "PLEASE_SELECT_TODAY_OR_A_FUTURE_DATE"),
    THIS_AREA_DONT_EXIST(86, "THIS_AREA_DONT_EXIST"),
    BILLING_TIME_CANNOT_EMPTY(87, "BILLING_TIME_CANNOT_EMPTY"),
    RECEIPT_TIME_CANNOT_EMPTY(88, "RECEIPT_TIME_CANNOT_EMPTY"),
    RECEIPT_WAY_CANNOT_EMPTY(89, "RECEIPT_WAY_CANNOT_EMPTY"),
    PLEASE_SELECT_AGENCY_CAN_UPDATE(90, "PLEASE_SELECT_AGENCY_CAN_UPDATE"),
    PLEASE_SELECT_HEAD_OFFICE_UPDATE(91, "PLEASE_SELECT_HEAD_OFFICE_UPDATE"),
    THIS_ORDER_WAS_NOT_DELETED_BY_YOU(92, "THIS_ORDER_WAS_NOT_DELETED_BY_YOU"),
    NAME_EXITS(93, "NAME_EXITS"),
    CHARGE_MODE_REPEAT(94, "CHARGE_MODE_REPEAT"),
    DELIVERY_WAY_FREIGHT_NO_MATCH(95, "DELIVERY_WAY_FREIGHT_NO_MATCH"),
    THIS_CARRIER_COMPANY_CANNOT_DEL(96, "THIS_CARRIER_COMPANY_CANNOT_DEL"),
    ARRIVE_DATE_NEED_IS_FUTURE_OR_TODAY(97, "ARRIVE_DATE_NEED_IS_FUTURE_OR_TODAY"),
    THIS_SHIP_COMPANY_CANNOT_DEL(98, "THIS_SHIP_COMPANY_CANNOT_DEL"),
    THIS_MASTER_BL_COMPANY_CANNOT_DEL(98, "THIS_MASTER_BL_COMPANY_CANNOT_DEL"),
    THIS_CONTAINER_COMPANY_CANNOT_DEL(98, "THIS_CONTAINER_COMPANY_CANNOT_DEL"),
    MUST_IS_PICTURE(99, "MUST_IS_PICTURE"),
    THIS_ORDER_YET_DECLARE_CANNOT_UPDATE_HOUSE_BL(100, "THIS_ORDER_YET_DECLARE_CANNOT_UPDATE_HOUSE_BL"),
    THE_FILE_IS_TOO_BIG(101, "THE_FILE_IS_TOO_BIG"),
    PLEASE_ADD_TOMORROW_START_SHIP(102, "PLEASE_ADD_TOMORROW_START_SHIP"),
    HAVE_A_SAME_HOUSE_BL_WITH_FILE(103, "HAVE_A_SAME_HOUSE_BL_WITH_FILE"),
    HAVE_A_SAME_SHIPPING_MARK_WITH_FILE(104, "HAVE_A_SAME_SHIPPING_MARK_WITH_FILE"),
    HAVE_A_SAME_HOUSE_BL_WITH_DATABASE(105, "HAVE_A_SAME_HOUSE_BL_WITH_DATABASE"),
    HAVE_A_SAME_SHIPPING_MARK_WITH_DATABASE(106, "HAVE_A_SAME_SHIPPING_MARK_WITH_DATABASE"),
    ORDERS_NO_SELECT_SHIP(107, "ORDERS_NO_SELECT_SHIP"),
    ORDERS_NO_SELECT_CONTAINER(108, "ORDERS_NO_SELECT_CONTAINER"),
    
    UNKNOWN_ERROR(1000, "未知错误");

    private int code;
    private String message;


    GlobalErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}