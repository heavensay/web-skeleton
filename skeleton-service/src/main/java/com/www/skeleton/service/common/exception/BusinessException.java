package com.www.skeleton.service.common.exception;

import com.www.skeleton.util.exception.BaseException;

import java.text.MessageFormat;

/*
 * 业务处理通用异常类
 * 都会带有error code和error msg，返回给客户端
 */
public class BusinessException extends BaseException {


	private final static  Integer DEFAULT_CODE= 0;
	private Integer code= DEFAULT_CODE;

	/*
	 * 占位符参数，用于message构造
	 */
	private String[] args = null;

	/**
	 * @param code
	 */

	public BusinessException(Integer code){
		this.code = code;
	}

	public BusinessException(Integer code, String...args){
		this.code = code;
		this.args = args;
	}

	public BusinessException(Integer code, Throwable t){
		super(t);
		this.code = code;
	}

	public BusinessException(Integer code, Throwable t, String...args){
		super(t);
		this.code = code;
		this.args = args;
	}

	public Integer getCode() {
		return code;
	}

	/**
	 * 可以考虑国际化,根据code来获取message
	 */
	@Override
	public String getMessage(){
//		return getFormatMessage(CcbcMessage.getMessage(code), args);
		return getFormatMessage("", args);
	}

	private String getFormatMessage(String msg,String...args){

		for (int i = 0; args != null && i < args.length; i++) {
			if(args[i] == null){
				args[i] = "";
			}
		}
		return MessageFormat.format(msg, args);
//		return String.format(msg, args);
	}
}
