package com.yuan.javaswingproject.ui;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.formdev.flatlaf.FlatLightLaf;
import com.yuan.javaswingproject.entity.Session;
import com.yuan.javaswingproject.entity.User;
import com.yuan.javaswingproject.service.AuthServiceImpl;
import com.yuan.javaswingproject.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	@Autowired
	private AuthServiceImpl authService;

	public LoginView() throws UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(new FlatLightLaf());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("登录 - 智慧新闻系统");
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 500, 350);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245)); // 背景颜色
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("欢迎登录智慧新闻系统");
		lblWelcome.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblWelcome.setBounds(140, 15, 300, 30);
		contentPane.add(lblWelcome);

		JLabel lblUsername = new JLabel("账号：");
		lblUsername.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblUsername.setBounds(60, 70, 60, 30);
		contentPane.add(lblUsername);

		username = new JTextField();
		username.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		username.setBounds(130, 70, 280, 35);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblPassword = new JLabel("密码：");
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblPassword.setBounds(60, 125, 60, 30);
		contentPane.add(lblPassword);

		password = new JPasswordField();
		password.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		password.setBounds(130, 125, 280, 35);
		contentPane.add(password);

		JButton loginBtn = new JButton("登录");
		loginBtn.setFont(new Font("微软雅黑", Font.BOLD, 16));
		loginBtn.setBackground(new Color(70, 130, 180));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setFocusPainted(false);
		loginBtn.setBounds(130, 200, 120, 45);
		contentPane.add(loginBtn);

		JButton registerBtn = new JButton("注册");
		registerBtn.setFont(new Font("微软雅黑", Font.BOLD, 16));
		registerBtn.setBackground(new Color(60, 179, 113));
		registerBtn.setForeground(Color.WHITE);
		registerBtn.setFocusPainted(false);
		registerBtn.setBounds(290, 200, 120, 45);
		contentPane.add(registerBtn);

		// 登录按钮点击事件
		loginBtn.addActionListener(e -> {
			String userText = username.getText();
			String passText = password.getText();
			if (authService.login(userText, passText)) {
				User user = authService.findByUsername(userText);
				Session.setCurrentUsername(user.getNickname());
				MainView mainView = SpringContext.getBean(MainView.class);
				mainView.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "账号或密码错误！", "提示", JOptionPane.WARNING_MESSAGE);
			}
		});

		// 注册按钮点击事件
		registerBtn.addActionListener(e -> {
			RegisterView registerView = SpringContext.getBean(RegisterView.class);
			registerView.setLocationRelativeTo(null);
			registerView.setVisible(true);
			dispose();
		});
	}
}
