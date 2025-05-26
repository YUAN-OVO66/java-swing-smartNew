package com.yuan.javaswingproject;

import com.yuan.javaswingproject.ui.LoginView;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


import java.awt.*;

@SpringBootApplication
@MapperScan("com.yuan.javaswingproject.mapper")
public class JavaSwingProjectApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(JavaSwingProjectApplication.class)
                .headless(false) // 必须设为 false，允许使用图形界面
                .run(args);
        EventQueue.invokeLater(() -> {
            LoginView loginView = context.getBean(LoginView.class);
            loginView.setLocationRelativeTo(null);
            loginView.setVisible(true);
        });
    }
}
