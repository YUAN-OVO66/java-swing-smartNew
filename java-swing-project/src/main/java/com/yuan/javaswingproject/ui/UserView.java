package com.yuan.javaswingproject.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.yuan.javaswingproject.entity.Article;
import com.yuan.javaswingproject.entity.Session;
import com.yuan.javaswingproject.service.AiServiceImpl;
import com.yuan.javaswingproject.service.ArticleServiceImpl;
import com.yuan.javaswingproject.util.SpringContext;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

import static com.yuan.javaswingproject.util.ClockUtils.createClockLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class UserView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	@Autowired
	private ArticleServiceImpl articleService;

	@Autowired
	private AiServiceImpl  aiService;

	/**
	 * Create the frame.
	 */
	@PostConstruct
	public void init() throws UnsupportedLookAndFeelException {
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
				JOptionPane.showMessageDialog(null, "已是当前界面", "提示", JOptionPane.INFORMATION_MESSAGE);

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
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 添加时间显示
		JLabel clockLabel = createClockLabel();
		clockLabel.setBounds(1050, 20, 200, 30); // 放在右上角
		contentPane.add(clockLabel);

		JLabel lbl1 = new JLabel("你好");
		lbl1.setFont(new Font("宋体", Font.PLAIN, 20));
		lbl1.setBounds(44, 10, 170, 42);
		contentPane.add(lbl1);

		textField = new JTextField();
		textField.setBounds(44, 207, 1167, 59);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(44, 98, 552, 59);
		contentPane.add(textField_1);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(true); // 可输入
		textPane.setContentType("text/plain"); // 纯文本

		// 将 JTextPane 放入 JScrollPane 中
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(44, 314, 1167, 336);

		// 关键设置：禁用水平滚动条，启用自动换行
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		// 自动换行的关键：用 JTextPane + JScrollPane + 设置 Viewport 宽度跟随
		textPane.setEditorKit(new javax.swing.text.StyledEditorKit() {
			@Override
			public ViewFactory getViewFactory() {
				return new StyledViewFactory();
			}

			class StyledViewFactory implements ViewFactory {
				public View create(Element elem) {
					String kind = elem.getName();
					if (kind != null) {
						if (kind.equals(AbstractDocument.ContentElementName)) {
							return new WrapLabelView(elem);
						} else if (kind.equals(AbstractDocument.ParagraphElementName)) {
							return new ParagraphView(elem);
						} else if (kind.equals(AbstractDocument.SectionElementName)) {
							return new BoxView(elem, View.Y_AXIS);
						} else if (kind.equals(StyleConstants.ComponentElementName)) {
							return new ComponentView(elem);
						} else if (kind.equals(StyleConstants.IconElementName)) {
							return new IconView(elem);
						}
					}
					// default fallback
					return new LabelView(elem);
				}
			}
			// 实现自动换行的 LabelView 子类
			class WrapLabelView extends LabelView {
				public WrapLabelView(Element elem) {
					super(elem);
				}

				@Override
				public float getMinimumSpan(int axis) {
					switch (axis) {
						case View.X_AXIS:
							return 0;
						case View.Y_AXIS:
							return super.getMinimumSpan(axis);
						default:
							throw new IllegalArgumentException("Invalid axis: " + axis);
					}
				}
			}
		});
		// 添加到容器中
		contentPane.add(scrollPane);

		JLabel lbl2 = new JLabel("标题栏：");
		lbl2.setFont(new Font("宋体", Font.PLAIN, 16));
		lbl2.setBounds(44, 62, 103, 25);
		contentPane.add(lbl2);

		JLabel lbl3 = new JLabel("副标题栏（建议30字左右）：");
		lbl3.setFont(new Font("宋体", Font.PLAIN, 16));
		lbl3.setBounds(44, 167, 288, 30);
		contentPane.add(lbl3);

		JLabel lbl4 = new JLabel("正文内容：");
		lbl4.setFont(new Font("宋体", Font.PLAIN, 16));
		lbl4.setBounds(44, 276, 189, 28);
		contentPane.add(lbl4);

		JButton btn1 = new JButton("AI润色");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textPane.getText();
				String runText = aiService.chat(text);
				//添加等待框
				JOptionPane.showMessageDialog(null, "正在润色中，请稍等...", "提示", JOptionPane.INFORMATION_MESSAGE);
				textPane.setText(runText);
			}
		});
		btn1.setBounds(617, 98, 130, 59);
		contentPane.add(btn1);

		JButton btn2 = new JButton("重置");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//弹出提示框是否重置
				int result = JOptionPane.showConfirmDialog(null, "是否重置？", "提示", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "已重置", "提示", JOptionPane.INFORMATION_MESSAGE);
					textField.setText("");
					textField_1.setText("");
					textPane.setText("");
				}
			}
		});
		btn2.setBounds(760, 98, 121, 59);
		contentPane.add(btn2);

		JButton btn3 = new JButton("发布");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//弹出提示框是否发布
				int result = JOptionPane.showConfirmDialog(null, "是否发布？", "提示", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					String title = textField.getText();
					String abstractText = textField_1.getText();
					String contentText = textPane.getText();
					Article article = new Article();
					article.setTitle(title);
					article.setAbstractText(abstractText);
					article.setContentText(contentText);
					article.setAuthor(Session.getCurrentUsername());
					article.setCreatedAt(LocalDateTime.now());
					article.setUpdatedAt(LocalDateTime.now());
					if (articleService.addArticle(article)){
						JOptionPane.showMessageDialog(null, "发布成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						textField.setText("");
						textField_1.setText("");
						textPane.setText("");
					}
				}
			}
		});

		btn3.setBounds(903, 98, 121, 59);
		contentPane.add(btn3);
	}

	public UserView() {

	}

}
