package com.www.skeleton.util.i18n;

import com.www.skeleton.BaseConfigTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author lijianyu
 * @date 2019/3/20 16:32
 */
public class LocaleMessageServiceTest extends BaseConfigTest{

    @Autowired
    private LocaleMessageService localeMessageService;

    @Test
    public void getMessage() {
        String msg1 = localeMessageService.getMessage("hello.msg.echo_50000");
        Assert.assertNotNull(msg1);
        String msg2 = localeMessageService.getMessage("hello.msg.echo_50000",new String[]{"aaa"});
        Assert.assertNotNull(msg2);
        String msg3 = localeMessageService.getMessage("xxxx");
        Assert.assertNotNull(msg3);
    }
}
