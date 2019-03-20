package com.www.skeleton.service.common.exception;

import com.www.skeleton.util.exception.BaseException;
import com.www.skeleton.util.i18n.LocaleMessageService;
import com.www.skeleton.util.spring.SpringContextHolder;

import java.text.MessageFormat;

/*
 * 业务处理通用异常类
 * code=10000表示业务请求正确，code>10000表示业务处理错误
 * 都会带有error code和error msg，返回给客户端
 */
public class ServiceException extends BaseException {


	private LocaleMessageService localeMessageService = SpringContextHolder.getBean(LocaleMessageService.class);

	private final static  Integer SYSTEM_UNKONW_ERROR_CODE = 10001;
	private final static  String SYSTEM_UNKONW_ERROR_MSG_CODE = "system.unkonw.error_10001";

	/*提供给外部code*/
	private Integer code = SYSTEM_UNKONW_ERROR_CODE;

	/*消息代码*/
	private String msgCode;

	/*
	 * 占位符参数，用于message构造
	 */
	private String[] args = null;

	public ServiceException(String msgCode){
		this(msgCode,null,null);
//		this.msgCode = msgCode;
	}

	public ServiceException(String msgCode, String...args){
//		this.args = args;
//		this.msgCode = msgCode;
		this(msgCode,null,args);
	}

	public ServiceException(String msgCode, Throwable t){
		this(msgCode,t,null);
//		super(t);
//		this.msgCode = msgCode;
	}

	public ServiceException(String msgCode, Throwable t, String...args){
		super(t);
		this.msgCode = msgCode;
		this.args = args;
		this.code = convetMsgCode2Code(msgCode);
	}


	/**
	 * 根据msgCode获取到code，此方法不能抛出异常
	 * @param msgCode
	 */
	private Integer convetMsgCode2Code(String msgCode){
		Integer numberCode;

		if(msgCode == null || "".equals(msgCode)){
			msgCode = SYSTEM_UNKONW_ERROR_MSG_CODE;
		}

		Integer lastIndex = msgCode.lastIndexOf("_");
		String numberCodeText = null;

		//'_'不能是最后一位
		if(lastIndex != -1 && msgCode.length()>lastIndex - 1){
			numberCodeText = msgCode.substring(lastIndex+1,msgCode.length());
		}

		//numberCodeText为5位数字
		if(numberCodeText == null || numberCodeText.length() != 5){
			numberCodeText = msgCode;
		}

		try {
			numberCode = Integer.parseInt(numberCodeText);
		}catch (NumberFormatException ex){
			numberCode = SYSTEM_UNKONW_ERROR_CODE;
		}
		return numberCode;
	}

	public Integer getCode() {
		return code;
	}

	@Override
	public String getMessage(){
//		return getFormatMessage(CcbcMessage.getMessage(code), args);
		return getLocaleMessage(msgCode, args);
	}

	private String getFormatMessage(String msg,String...args){

		for (int i = 0; args != null && i < args.length; i++) {
			if(args[i] == null){
				args[i] = "";
			}
		}
		return MessageFormat.format(msg, args);
	}

	private String getLocaleMessage(String msg,String...args){
		return localeMessageService.getMessage(msg,args);
	}
}
