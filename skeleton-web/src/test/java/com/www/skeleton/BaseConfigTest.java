package com.www.skeleton;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author lijianyu
 * @date 2019/1/29 14:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkeletonWebApplication.class)
@WebAppConfiguration
public class BaseConfigTest {

    @Autowired
    public WebApplicationContext wac;

    public MockMvc mockMvc = null;

    /*请求头*/
    public String TOKEN_NAME = "X-token";
    public String TOKEN_VALUE = "2e97543b-6718-40ff-9200-dc153724a3e6";

    @Before
    public void setupMockMvc(){
        DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(wac);
        defaultMockMvcBuilder.defaultRequest(MockMvcRequestBuilders.head(TOKEN_NAME,TOKEN_VALUE));

        /* 添加 filter测试*/
//        mockMvc = defaultMockMvcBuilder.addFilter((Filter)wac.getBean("shiroFilter"), "/*").build();
        mockMvc = defaultMockMvcBuilder.build();
    }

}