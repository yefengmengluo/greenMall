package com.wk.p3.greenmall.modules.user.web;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.utils.DictUtils;
import com.wk.p3.greenmall.modules.user.LoginType;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.ext.jackson.JacksonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * 前台用户登陆注册等单元测试
 * 参考：http://jinnianshilongnian.iteye.com/blog/2004660
 * Created by cc on 16-1-4.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@WebAppConfiguration
//@EnableWebMvc
@ContextConfiguration({
        "/spring-context.xml",
        "/spring-context-activiti.xml",
        "/spring-context-shiro.xml",
        "/spring-context-mp.xml",
        "/spring-context-cache.xml",
        "/spring-context-jedis.xml",
        "/spring-mvc.xml"}) //指定Spring的配置文件 /为classpath下
@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class PcUserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    /**
     * 测试用户注册
     * @throws Exception
     */
    @Test
    //@Rollback(false)//不回滚
    @Rollback(true)//回滚
    public void user() throws Exception {

        MvcResult result = mockMvc.perform(
                //post("/f/userApi/user").accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user))
                post("/f/userApi/user")
                        .param("loginName", "loginName")
                        .param("password", "123456")
                        .param("name", "name")
                        .param("userType", "pcWeb")
        ).andExpect(status().isOk()
        ).andReturn();


    }



}
