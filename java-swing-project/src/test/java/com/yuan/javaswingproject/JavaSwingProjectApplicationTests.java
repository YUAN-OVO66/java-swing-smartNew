package com.yuan.javaswingproject;

import com.yuan.javaswingproject.service.AiServiceImpl;
import com.yuan.javaswingproject.service.AuthServiceImpl;
import com.yuan.javaswingproject.service.HotServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest(classes = com.yuan.javaswingproject.ui.MainView.class)
@SpringBootTest(classes = JavaSwingProjectApplication.class)
class JavaSwingProjectApplicationTests {

    @Autowired
    AiServiceImpl aiService;
    @Autowired
    AuthServiceImpl authService;
    @Autowired
    HotServiceImpl hotService;

    @Test
    void contextLoads() {
        hotService.getHotSearch("weibo").forEach(System.out::println);
    }

    @Test
    void chat(){
        String result = aiService.chat("你好");
        System.out.println(result);
    }

    @Test
    void register(){
        Boolean result = authService.register("yuan", "yuan","123456", "123456");
        System.out.println(result);
    }

    @Test
    void login(){
        Boolean result = authService.login("yuan", "123456");
        System.out.println(result);
    }

}
