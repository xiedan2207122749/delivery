package com.jxyj.delivery.common.exception;

import com.jxyj.delivery.common.util.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 异常处理器
 *
 * @author dell
 */
@RestControllerAdvice
public class BaseExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 具体异常处理类
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BaseException.class)
	public Result handleException(BaseException e) {
		logger.error(e.getMessage());
		return Result.error(e.getMessage());
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public Result handlerNoFoundException(Exception e) {
		logger.error(e.getMessage());
		return handleException(new BaseException(GlobalErrorEnum.NO_HANDLER_FOUND));
	}
	
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public Result handlerIndexOutOfBoundsException(IndexOutOfBoundsException e) {
		logger.error("数组下标越界{}", e.getMessage(), e);
		return handleException(new BaseException(GlobalErrorEnum.UNKNOWN_ERROR));
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException e) {
		logger.error(e.getMessage());
		return handleException(new BaseException(GlobalErrorEnum.DUPLICATE_KEY));
	}

	@ExceptionHandler(AuthorizationException.class)
	public Result handleAuthorizationException(AuthorizationException e) {
		logger.error(e.getMessage());
		return handleException(new BaseException(GlobalErrorEnum.NO_HAVE_PERMISSION));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		logger.error(e.getMessage());
		return handleException(new BaseException(GlobalErrorEnum.HTTP_MESSAGE_NOT_READABLE));
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		logger.error(e.getMessage());
		return handleException(new BaseException(GlobalErrorEnum.WAY_ERROR_OR_NO_LOGIN));
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Result handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		logger.error(e.getMessage());
		return handleException(new BaseException(GlobalErrorEnum.HTTP_MEDIA_TYPE_NOT_SUPPORTED));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		logger.error(e.getMessage());
		return handleException(new BaseException(GlobalErrorEnum.REQUIRED_FIELD_EMPTY));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		logger.error(e.getMessage());
		return handleException(new BaseException(GlobalErrorEnum.METHOD_ARGS_TYPE_ERROR));
	}

	@ExceptionHandler(NumberFormatException.class)
	public Result handleNumberFormatException(NumberFormatException e) {
		logger.error(e.getMessage());
		return handleException(new BaseException(GlobalErrorEnum.STRING_CONVERT_NUMBER_ERROR));
	}

	/**
	 * get请求验证form如果不通过会抛这个异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = BindException.class)
	public Result bindExceptionException(BindException e) {
		return Result.error(e.getAllErrors().get(0).getDefaultMessage());
	}

	/**
	 * post请求验证form如果不通过会抛这个异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Result methodArgumentNotValidExceptionException(MethodArgumentNotValidException e) {
		return Result.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
	}

	/**
	 * 控制层单个参数验证不通过会进这个异常  主要是针对get请求
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public Result constraintViolationException(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		if(!violations.isEmpty()) {
			// 设置验证结果状态码
			for (ConstraintViolation<?> item : violations) {
				return Result.error(item.getMessage());
			}
		}
		return Result.error();
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e) {
		logger.error("{}", e.getMessage(), e);
		return Result.error();
	}
}
