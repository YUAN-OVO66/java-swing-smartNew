package com.yuan.javaswingproject.util;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockUtils {

    public static JLabel createClockLabel() {
        JLabel clockLabel = new JLabel();
        clockLabel.setFont(new Font("Arial", Font.BOLD, 24));
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        // 创建 Swing Timer 每秒更新一次
        Timer timer = new Timer(1000, e -> {
            String currentTime = dateFormat.format(new Date());
            clockLabel.setText(currentTime);
        });

        timer.start(); // 启动定时器
        clockLabel.setText(dateFormat.format(new Date())); // 初始化时设置时间

        return clockLabel;
    }

}
