package com.jxyj.delivery.common.util;

import lombok.Data;

/**
 * 返回数据
 *
 * @author dell
 */
@Data
public class Result<T> {
	private String msg;

	private int code;

	private T data;

	private Result() {
		msg = "success";
		code = 200;
	}

	private Result(String msg, int code) {
		this.msg = msg;
		this.code = code;
	}

	private Result(String msg, int code, T data) {
		this(msg, code);
		this.data = data;
	}

	public static <T> Result<T> ok() {
		return new Result<>();
	}

	public static <T> Result<T> ok(T data) {
		return new Result<>("success", 200, data);
	}

	public static <T> Result<T> error() {
		return new Result<>("未知异常，请联系管理员", 500);
	}

	public static <T> Result<T> error(String msg) {
		return new Result<>(msg, 500);
	}

	public static <T> Result<T> error(T data, String msg) {
		return new Result<>(msg, 500, data);
	}

	public static <T> Result<T> error(T data, int code, String msg) {
		return new Result<>(msg, code, data);
	}

	public static <T> Result<T> error(int code, String msg) {
		return new Result<>(msg, code);
	}

}
