package com.www.skeleton.util.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author lijianyu
 * @date 2019/3/20 13:43
 */
@Component
public class LocaleMessageService {

    @Resource
    private MessageSource messageSource;

    /**
     * @param msgTemplate ：对应messages配置的key.
     * @return
     */
    public String getMessage(String msgTemplate) {
        return getMessage(msgTemplate, null);
    }

    /**
     * @param msgTempalte ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public String getMessage(String msgTempalte, Object[] args) {
        return getMessage(msgTempalte, args, null);
    }

    /**
     * @param msgTempalte           ：对应messages配置的key.
     * @param args           : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
    public String getMessage(String msgTempalte, Object[] args, String defaultMessage) {
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgTempalte, args, defaultMessage, locale);

    }
}
