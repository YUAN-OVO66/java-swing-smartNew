package com.yuan.javaswingproject.ui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.yuan.javaswingproject.entity.json.HotSearchItem;
import com.yuan.javaswingproject.service.HotServiceImpl;
import com.yuan.javaswingproject.util.SpringContext;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@Component
public class MainView extends JFrame {

	private JPanel contentPane;
	private Integer row = 5;
	private Integer col = 2;
	private String type = "weibo";
	JPanel panel = new JPanel();

	@Autowired
	private HotServiceImpl hotService;

	/**
	 * Create the frame.
	 */
	@PostConstruct
	public void init() throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new FlatIntelliJLaf());
		JMenuBar jMenuBar = new JMenuBar();

		JMenu m1 = new JMenu("今日热搜");
		m1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "已是当前界面", "提示", JOptionPane.INFORMATION_MESSAGE);
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
				AboutView aboutView = SpringContext.getBean(AboutView.class);
				aboutView.setVisible(true);
				aboutView.setLocationRelativeTo(null);
				dispose();
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
		setTitle("智慧新闻管理系统");
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);



		JButton btn1 = new JButton("微博热搜top10");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "weibo";
				List<HotSearchItem> hotSearchItems = hotService.getHotSearch(type);
				updatePanel(hotSearchItems);
			}
		});
		btn1.setFont(new Font("宋体", Font.PLAIN, 16));
		btn1.setBounds(10, 10, 171, 69);
		contentPane.add(btn1);

		JButton btn2 = new JButton("百度热搜top10");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "baidu";
				List<HotSearchItem> hotSearchItems = hotService.getHotSearch(type);
				updatePanel(hotSearchItems);
			}
		});
		btn2.setFont(new Font("宋体", Font.PLAIN, 16));
		btn2.setBounds(191, 10, 171, 69);
		contentPane.add(btn2);

		JButton btn3 = new JButton("知乎热搜top10");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "zhihu";
				List<HotSearchItem> hotSearchItems = hotService.getHotSearch(type);
				updatePanel(hotSearchItems);
			}
		});
		btn3.setFont(new Font("宋体", Font.PLAIN, 16));
		btn3.setBounds(372, 10, 171, 69);
		contentPane.add(btn3);

		JButton btn4 = new JButton("CSDN热搜top10");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "csdn";
				List<HotSearchItem> hotSearchItems = hotService.getHotSearch(type);
				updatePanel(hotSearchItems);
			}
		});
		btn4.setFont(new Font("宋体", Font.PLAIN, 16));
		btn4.setBounds(553, 10, 171, 69);
		contentPane.add(btn4);

		JButton btn5 = new JButton("头条热搜top10");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "toutiao";
				List<HotSearchItem> hotSearchItems = hotService.getHotSearch(type);
				updatePanel(hotSearchItems);
			}
		});
		btn5.setFont(new Font("宋体", Font.PLAIN, 16));
		btn5.setBounds(734, 10, 171, 69);
		contentPane.add(btn5);

		panel.setBounds(10, 89, 1246, 561);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(row, col, 0, 0));
		updatePanel(hotService.getHotSearch(type));
		// 更新面板内容的方法

	}
	public void updatePanel(List<HotSearchItem> hotSearchItems) {
		panel.removeAll(); // 清除旧内容

		for (HotSearchItem hotSearchItem : hotSearchItems) {
			// 标题
			JLabel titleLabel = new JLabel("标题：" + hotSearchItem.getKeyword());
			titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
			titleLabel.setForeground(new Color(33, 37, 41));

			// 排行标签
			JLabel rankLabel = new JLabel("排行：" + hotSearchItem.getRank() + "      网页链接：");
			rankLabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			rankLabel.setForeground(new Color(100, 100, 100));

			// 热值标签
			Long hotValue = hotSearchItem.getHotValue();
			if (hotValue == null) hotValue = 99999999L;
			JLabel hotLabel = new JLabel("热值：" + hotValue);
			hotLabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			hotLabel.setForeground(new Color(120, 120, 120));

			// 链接
			JLabel urlLabel = new JLabel("<html><u>点击跳转</u></html>");
			urlLabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			urlLabel.setForeground(new Color(0, 102, 204));
			urlLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			urlLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					try {
						Desktop.getDesktop().browse(new URI(hotSearchItem.getUrl()));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				public void mouseEntered(MouseEvent e) {
					urlLabel.setForeground(new Color(0, 51, 153));
				}

				public void mouseExited(MouseEvent e) {
					urlLabel.setForeground(new Color(0, 102, 204));
				}
			});

			// 容器面板：imgPane（保留 BorderLayout）
			JPanel imgPane = new JPanel(new BorderLayout(8, 8));
			imgPane.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
					BorderFactory.createEmptyBorder(10, 10, 10, 10)
			));
			imgPane.setBackground(new Color(250, 250, 250)); // 卡片背景色

			// 分别放入四个方向区域
			imgPane.add(titleLabel, BorderLayout.NORTH);   // 上
			imgPane.add(rankLabel, BorderLayout.WEST);     // 左
			imgPane.add(urlLabel, BorderLayout.CENTER);    // 中
			imgPane.add(hotLabel, BorderLayout.SOUTH);     // 下

			// 添加到主panel中
			panel.add(imgPane);
		}

		panel.revalidate(); // 刷新面板布局
		panel.repaint();    // 重绘面板
	}

	public MainView() {

	}

}
