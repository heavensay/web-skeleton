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
	private final static  String SYSTEM_UNKONW_ERROR_MSG_TEMPLATE_CODE = "system.unkonw.error_10001";

	/*提供给外部code*/
	private Integer code = SYSTEM_UNKONW_ERROR_CODE;

	/*消息代码*/
	private String msgTemplateCode;

	/*
	 * 占位符参数，用于message构造
	 */
	private String[] args = null;

	public ServiceException(String msgTemplateCode){
		this(msgTemplateCode,null,null);
	}

	public ServiceException(String msgTemplateCode, String...args){
		this(msgTemplateCode,null,args);
	}

	public ServiceException(String msgTemplateCode, Throwable t){
		this(msgTemplateCode,t,null);
	}

	public ServiceException(String msgTemplateCode, Throwable t, String...args){
		super(t);
		this.msgTemplateCode = msgTemplateCode;
		this.args = args;
		this.code = convetMsgCode2Code(msgTemplateCode);
	}


	/**
	 * 根据msgCode获取到code，此方法不能抛出异常
	 * @param msgTemplateCode
	 */
	private Integer convetMsgCode2Code(String msgTemplateCode){
		Integer numberCode;

		if(msgTemplateCode == null || "".equals(msgTemplateCode)){
			msgTemplateCode = SYSTEM_UNKONW_ERROR_MSG_TEMPLATE_CODE;
		}

		Integer lastIndex = msgTemplateCode.lastIndexOf("_");
		String numberCodeText = null;

		//'_'不能是最后一位
		if(lastIndex != -1 && msgTemplateCode.length()>lastIndex - 1){
			numberCodeText = msgTemplateCode.substring(lastIndex+1,msgTemplateCode.length());
		}

		//numberCodeText为5位数字
		if(numberCodeText == null || numberCodeText.length() != 5){
			numberCodeText = msgTemplateCode;
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
		return getLocaleMessage(msgTemplateCode, args);
	}

	private String getLocaleMessage(String msgTemplateCode,String...args){
		return localeMessageService.getMessage(msgTemplateCode,args);
	}
}
