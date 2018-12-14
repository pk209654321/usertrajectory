package cn.sscf.usertrajectory.controller;


import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sscf.education.common.entity.Result;
import com.sscf.education.common.enumm.ResultEnum;
import com.sscf.education.common.exception.BusinessException;
import com.sscf.education.common.util.ResultUtil;

import redis.clients.jedis.exceptions.JedisDataException;

public class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 运行时异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Result runtimeExceptionHandler(RuntimeException ex) {
		logger.error("运行时异常:", ex);
		return ResultUtil.error(ResultEnum.RUNTIME_EXCEPTION);
	}

	/**
	 * 业务异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public Result businessExceptionHandler(BusinessException ex) {
		logger.error("业务异常:", ex);
		return ResultUtil.error(ex);
	}

	/**
	 * 方法参数校验异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		logger.error("方法参数校验异常:", ex);
		BindingResult bindingResult = ex.getBindingResult();
		String msg = getValidMsg(bindingResult);
		return ResultUtil.error(ResultEnum.METHOD_ARG_NOT_VALID, msg);
	}

	/**
	 * http消息不可读异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public Result httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
		logger.error("http消息不可读异常:", ex);
		return ResultUtil.error(ResultEnum.HTTP_MESSAGE_NOT_READABLE_EXCEPTION);
	}

	/**
	 * 数据库操作失败.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	public Result dataAccessExceptionHandler(DataAccessException ex) {
		logger.error("数据库操作失败:", ex);
		return ResultUtil.error(ResultEnum.DATA_ACCESS_EXCEPTION);
	}

	/**
	 * redis操作异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(JedisDataException.class)
	@ResponseBody
	public Result jedisDataExceptionHandler(JedisDataException ex) {
		logger.error("redis操作失败:", ex);
		return ResultUtil.error(ResultEnum.REDIS_ACCESS_EXCEPTION);
	}

	/**
	 * 主键冲突异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	@ResponseBody
	public Result dataAccessExceptionHandler(DuplicateKeyException ex) {
		logger.error("主键冲突:", ex);
		return ResultUtil.error(ResultEnum.DUPLICATE_KEY_EXCEPTION);
	}

	/**
	 * 数据库操作失败.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public Result nullPointerExceptionHandler(NullPointerException ex) {
		logger.error("空指针异常:", ex);
		return ResultUtil.error(ResultEnum.NULL_POINTER_EXCEPTION);
	}

	/**
	 * io异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(IOException.class)
	@ResponseBody
	public Result iOExceptionHandler(IOException ex) {
		logger.error("IO异常:", ex);
		return ResultUtil.error(ResultEnum.IO_EXCEPTION);
	}

	/**
	 * 指定的类不存在.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ClassNotFoundException.class)
	@ResponseBody
	public Result classNotFoundExceptionHandler(ClassNotFoundException ex) {
		logger.error("指定的类不存在:", ex);
		return ResultUtil.error(ResultEnum.CLASS_NOT_FOUND_EXCEPTION);
	}

	/**
	 * 数学运算异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public Result arithmeticExceptionHandler(ArithmeticException ex) {
		logger.error("数学运算异常:", ex);
		return ResultUtil.error(ResultEnum.ARITHMETIC_EXCEPTION);
	}

	/**
	 * 数组下标越界.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ResponseBody
	public Result arrayIndexOutOfBoundsExceptionHandler(ArrayIndexOutOfBoundsException ex) {
		logger.error("数组下标越界:", ex);
		return ResultUtil.error(ResultEnum.ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION);
	}

	/**
	 * 数组下标越界.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public Result illegalArgumentExceptionHandler(IllegalArgumentException ex) {
		logger.error("不正确的参数:", ex);
		return ResultUtil.error(ResultEnum.ILLEGAL_ARGUMENT_EXCEPTION);
	}

	/**
	 * 类型强制转换错误.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public Result classCastExceptionHandler(ClassCastException ex) {
		logger.error("类型强制转换错误", ex);
		return ResultUtil.error(ResultEnum.CLASS_CAST_EXCEPTION);
	}

	/**
	 * 违背安全原则异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(SecurityException.class)
	@ResponseBody
	public Result securityExceptionHandler(SecurityException ex) {
		logger.error("违背安全原则异常", ex);
		return ResultUtil.error(ResultEnum.SECURITY_EXCEPTION);
	}

	/**
	 * 操作数据库异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public Result sqlExceptionHandler(SQLException ex) {
		logger.error("操作数据库异常", ex);
		return ResultUtil.error(ResultEnum.SQL_EXCEPTION);
	}

	/**
	 * 方法未找到异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NoSuchMethodError.class)
	@ResponseBody
	public Result noSuchMethodErrorHandler(NoSuchMethodError ex) {
		logger.error("方法未找到异常", ex);
		return ResultUtil.error(ResultEnum.NO_SUCH_METHOD_ERROR);
	}

	/**
	 * 方法未找到异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(InternalError.class)
	@ResponseBody
	public Result internalErrorHandler(InternalError ex) {
		logger.error("JVM内部错误", ex);
		return ResultUtil.error(ResultEnum.INTERNAL_ERROR);
	}

	/**
	 * 其他异常.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result exceptionHandler(Exception ex) {
		logger.error("未知异常", ex);
		return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
	}

	/**
	 * 获取校验异常信息.
	 * 
	 * @param bindingResult
	 * @return
	 */
	public String getValidMsg(BindingResult bindingResult) {
		if (bindingResult.getAllErrors().isEmpty()) {
			return StringUtils.EMPTY;
		}

		StringBuilder sb = new StringBuilder();
		for (FieldError error : bindingResult.getFieldErrors()) {
			sb.append(error.getField()).append(" ").append(error.getDefaultMessage()).append(";");
		}
		return sb.toString();
	}

}
