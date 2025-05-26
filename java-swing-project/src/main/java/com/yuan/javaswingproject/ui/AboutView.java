package com.yuan.javaswingproject.ui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.yuan.javaswingproject.util.SpringContext;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

@Component
public class AboutView extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */

	@PostConstruct
	public void init () throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new FlatLightLaf());
		JMenuBar jMenuBar = new JMenuBar();

		JMenu m1 = new JMenu("今日热搜");
		m1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView mainView = SpringContext.getBean(MainView.class);
				mainView.setVisible(true);
				mainView.setLocationRelativeTo(null);
				dispose();
			}
		});

		JMenu m2 = new JMenu("用户新闻");
		m2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserNewView userNewView = SpringContext.getBean(UserNewView.class);
				userNewView.setVisible(true);
				userNewView.setLocationRelativeTo(null);
				dispose();
			}
		});

		JMenu m3 = new JMenu("智能助手");
		m3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AiHelperView aiHelperView = SpringContext.getBean(AiHelperView.class);
				aiHelperView.setVisible(true);
				aiHelperView.setLocationRelativeTo(null);
				dispose();
			}
		});

		JMenu m4 = new JMenu("个人中心");
		m4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserView userView = SpringContext.getBean(UserView.class);
				userView.setVisible(true);
				userView.setLocationRelativeTo(null);
				dispose();
			}
		});

		JMenu m5 = new JMenu("关于");
		m5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "已是当前界面", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});


		jMenuBar.add(m1);
		jMenuBar.add(m2);
		jMenuBar.add(m3);
		jMenuBar.add(m4);
		jMenuBar.add(m5);

		setJMenuBar(jMenuBar);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);
		setTitle("智慧新闻管理系统");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("关于");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(557, 10, 61, 38);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("技术选型");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(398, 49, 149, 33);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("JDK17");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(557, 49, 108, 33);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("SpringBoot 3.x");
		lblNewLabel_2_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(557, 92, 197, 33);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("MyBatis - Plus");
		lblNewLabel_2_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2_1_1.setBounds(557, 135, 197, 33);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Spring - Ai");
		lblNewLabel_2_1_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2_1_1_1.setBounds(557, 178, 197, 33);
		contentPane.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("RapidOcr - Onnx");
		lblNewLabel_2_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2_1_1_1_1.setBounds(557, 221, 197, 33);
		contentPane.add(lblNewLabel_2_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Hutool - Tool");
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2_1_1_1_1_1.setBounds(557, 263, 197, 33);
		contentPane.add(lblNewLabel_2_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("Fastjson2");
		lblNewLabel_2_1_1_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2_1_1_1_1_1_1.setBounds(557, 306, 197, 33);
		contentPane.add(lblNewLabel_2_1_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1_1_1 = new JLabel("MySQL 8.x");
		lblNewLabel_2_1_1_1_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2_1_1_1_1_1_1_1.setBounds(557, 349, 197, 33);
		contentPane.add(lblNewLabel_2_1_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1 = new JLabel("制作小组");
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(398, 394, 149, 33);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1_1_1_1 = new JLabel("码农烧烤");
		lblNewLabel_2_1_1_1_1_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2_1_1_1_1_1_1_1_1.setBounds(557, 392, 197, 33);
		contentPane.add(lblNewLabel_2_1_1_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("开源协议");
		lblNewLabel_1_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(398, 437, 149, 33);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1_1_1_1_1 = new JLabel("Apache License");
		lblNewLabel_2_1_1_1_1_1_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2_1_1_1_1_1_1_1_1_1.setBounds(557, 437, 197, 33);
		contentPane.add(lblNewLabel_2_1_1_1_1_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1_1_1_1_1_1 = new JLabel("Copyright © 2024-2025");
		lblNewLabel_2_1_1_1_1_1_1_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_1_1_1_1_1.setBounds(489, 617, 279, 33);
		contentPane.add(lblNewLabel_2_1_1_1_1_1_1_1_1_1_1);
	}

	public AboutView() {
	}



}
