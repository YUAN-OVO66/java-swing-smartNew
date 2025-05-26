package com.yuan.javaswingproject.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.yuan.javaswingproject.entity.Article;
import com.yuan.javaswingproject.entity.json.HotSearchItem;
import com.yuan.javaswingproject.service.ArticleServiceImpl;
import com.yuan.javaswingproject.util.SpringContext;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.util.List;

@Component
public class UserNewView extends JFrame {

	private JPanel contentPane;
	JPanel panel = new JPanel();
	private Integer pageNum = 1;
	private Integer pageSize = 5;
	private Integer pageTotal;

	@Autowired
	private ArticleServiceImpl articleService;

	/**
	 * Create the frame.
	 */
	@PostConstruct
	public void init() throws UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(new FlatLightLaf());
		JMenuBar jMenuBar = new JMenuBar();
		setLocationRelativeTo(null);

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
				JOptionPane.showMessageDialog(null, "已是当前界面", "提示", JOptionPane.INFORMATION_MESSAGE);

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
		setResizable(false);

		setTitle("智慧新闻管理系统");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(746, 616, 134, 15);
		contentPane.add(lblNewLabel);

		panel.setBounds(10, 10, 1246, 577);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(5, 1, 0, 0));

		JButton btn1 = new JButton("上一页");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageNum == 1){
					JOptionPane.showMessageDialog(null, "已是第一页", "提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					pageNum--;
					List<Article> articleList = articleService.getArticleList(null, pageNum, pageSize);
					pageTotal = articleList.size() / pageSize + 1;
					lblNewLabel.setText("当前页第 " + pageNum + " 页，共 " + pageTotal + " 页");
					updatePanel(articleList);
				}
			}
		});
		btn1.setBounds(928, 597, 124, 53);
		contentPane.add(btn1);

		JButton btn2 = new JButton("下一页");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageNum == pageTotal){
					JOptionPane.showMessageDialog(null, "已是最后一页", "提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					pageNum++;
					List<Article> articleList = articleService.getArticleList(null, pageNum, pageSize);
					updatePanel(articleList);
					pageTotal = articleList.size() / pageSize + 1;
					lblNewLabel.setText("当前页第 " + pageNum + " 页，共 " + pageTotal + " 页");
				}

			}
		});
		btn2.setBounds(1122, 597, 134, 53);
		contentPane.add(btn2);
		List<Article> articleList = articleService.getArticleList(null, 1, pageSize);
		updatePanel(articleList);
		lblNewLabel.setText("当前页第 " + pageNum + " 页，共 " + (articleList.size() / pageSize + 1) + " 页");

		JButton btn3 = new JButton("刷新");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Article> articleList = articleService.getArticleList(null, 1, pageSize);
				updatePanel(articleList);
				lblNewLabel.setText("当前页第 " + pageNum + " 页，共 " + (articleList.size() / pageSize + 1) + " 页");
			}
		});
		btn3.setBounds(20, 597, 103, 53);
		contentPane.add(btn3);
	}

	public UserNewView() {

	}

	public void updatePanel(List<Article> articles) {
		panel.removeAll(); // 清除旧内容
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // 垂直布局

		for (Article article : articles) {
			// 单个文章卡片面板
			JPanel card = new JPanel();
			card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
			card.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
					BorderFactory.createEmptyBorder(10, 10, 10, 10)
			));
			card.setBackground(Color.WHITE);

			// 标题
			JLabel titleLabel = new JLabel("标题： " + article.getTitle());
			titleLabel.setFont(new Font("宋体", Font.BOLD, 16));
			titleLabel.setForeground(new Color(33, 37, 41)); // 深灰色

			// 概要
			JLabel abstractLabel = new JLabel("概要： " + article.getAbstractText());
			abstractLabel.setFont(new Font("宋体", Font.PLAIN, 14));
			abstractLabel.setForeground(new Color(73, 80, 87));

			// 作者
			JLabel authorLabel = new JLabel("作者： " + article.getAuthor());
			authorLabel.setFont(new Font("宋体", Font.PLAIN, 13));
			authorLabel.setForeground(new Color(108, 117, 125));

			// 时间
			JLabel createAtLabel = new JLabel("创建时间： " + article.getCreatedAt());
			createAtLabel.setFont(new Font("宋体", Font.ITALIC, 12));
			createAtLabel.setForeground(new Color(134, 142, 150));

			// “查看详情”按钮
			JButton detailButton = new JButton("查看详情");
			detailButton.setFont(new Font("宋体", Font.PLAIN, 13));
			detailButton.addActionListener(e -> {
				showArticleDetailDialog(article);
			});

			// 加入到卡片面板
			card.add(titleLabel);
			card.add(Box.createVerticalStrut(5));
			card.add(abstractLabel);
			card.add(Box.createVerticalStrut(5));
			card.add(authorLabel);
			card.add(Box.createVerticalStrut(5));
			card.add(createAtLabel);
			card.add(Box.createVerticalStrut(10));
			card.add(detailButton);

			// 外部主 panel 中添加卡片
			panel.add(Box.createVerticalStrut(10)); // 卡片间距
			panel.add(card);
		}

		panel.revalidate();
		panel.repaint();
	}

	private void showArticleDetailDialog(Article article) {
		JDialog detailDialog = new JDialog();
		detailDialog.setTitle("文章详情");
		detailDialog.setSize(500, 400);
		detailDialog.setLocationRelativeTo(null);
		detailDialog.setModal(true);

		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JLabel title = new JLabel("标题：" + article.getTitle());
		title.setFont(new Font("宋体", Font.BOLD, 18));

		JLabel author = new JLabel("作者：" + article.getAuthor());
		author.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel time = new JLabel("创建时间：" + article.getCreatedAt());
		time.setFont(new Font("宋体", Font.ITALIC, 12));

		JTextArea contentArea = new JTextArea(article.getContentText());
		contentArea.setFont(new Font("宋体", Font.PLAIN, 14));
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(contentArea);

		content.add(title);
		content.add(Box.createVerticalStrut(10));
		content.add(author);
		content.add(Box.createVerticalStrut(5));
		content.add(time);
		content.add(Box.createVerticalStrut(10));
		content.add(scrollPane);

		detailDialog.setContentPane(content);
		detailDialog.setVisible(true);
	}

}
