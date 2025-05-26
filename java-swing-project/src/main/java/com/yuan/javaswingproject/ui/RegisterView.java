package com.yuan.javaswingproject.ui;

import com.formdev.flatlaf.FlatLightLaf;
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
public class RegisterView extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField nickname;
	private JTextField password;
	private JTextField rePassword;

	@Autowired
	private AuthServiceImpl authService;

	public RegisterView() throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new FlatLightLaf());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("注册 - 智慧新闻系统");
		setResizable(false);
		setLocationRelativeTo(null);

		setBounds(100, 100, 720, 480);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245)); // 背景色
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("用户注册");
		lblTitle.setFont(new Font("微软雅黑", Font.BOLD, 24));
		lblTitle.setBounds(280, 20, 200, 40);
		contentPane.add(lblTitle);

		JLabel lblUsername = new JLabel("账号：");
		lblUsername.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblUsername.setBounds(110, 80, 100, 35);
		contentPane.add(lblUsername);

		username = new JTextField();
		username.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		username.setBounds(215, 80, 350, 35);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblNickname = new JLabel("用户名：");
		lblNickname.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNickname.setBounds(110, 140, 100, 35);
		contentPane.add(lblNickname);

		nickname = new JTextField();
		nickname.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		nickname.setBounds(215, 140, 350, 35);
		contentPane.add(nickname);
		nickname.setColumns(10);

		JLabel lblPassword = new JLabel("密码：");
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblPassword.setBounds(110, 200, 100, 35);
		contentPane.add(lblPassword);

		password = new JPasswordField();
		password.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		password.setBounds(215, 200, 350, 35);
		contentPane.add(password);

		JLabel lblRePassword = new JLabel("确认密码：");
		lblRePassword.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblRePassword.setBounds(90, 260, 120, 35);
		contentPane.add(lblRePassword);

		rePassword = new JPasswordField();
		rePassword.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		rePassword.setBounds(215, 260, 350, 35);
		contentPane.add(rePassword);

		JButton backBtn = new JButton("返回登录");
		backBtn.setFont(new Font("微软雅黑", Font.BOLD, 16));
		backBtn.setBackground(new Color(192, 192, 192));
		backBtn.setForeground(Color.BLACK);
		backBtn.setFocusPainted(false);
		backBtn.setBounds(215, 340, 140, 45);
		contentPane.add(backBtn);

		JButton registerBtn = new JButton("注 册");
		registerBtn.setFont(new Font("微软雅黑", Font.BOLD, 16));
		registerBtn.setBackground(new Color(70, 130, 180));
		registerBtn.setForeground(Color.WHITE);
		registerBtn.setFocusPainted(false);
		registerBtn.setBounds(425, 340, 140, 45);
		contentPane.add(registerBtn);

		// 返回登录按钮事件
		backBtn.addActionListener(e -> {
            LoginView loginView = null;
            try {
                loginView = new LoginView();
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
            loginView.setVisible(true);
			dispose();
		});

		// 注册按钮事件
		registerBtn.addActionListener(e -> {
			String user = username.getText();
			String name = nickname.getText();
			String pwd = password.getText();
			String repwd = rePassword.getText();

			if (authService.register(user, name, pwd, repwd)) {
				LoginView loginView = SpringContext.getBean(LoginView.class);
				loginView.setVisible(true);
				loginView.setLocationRelativeTo(null);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "注册失败，请检查输入信息", "错误", JOptionPane.WARNING_MESSAGE);
			}
		});
	}
}
