package com.yuan.javaswingproject.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.yuan.javaswingproject.service.AiServiceImpl;
import com.yuan.javaswingproject.service.OCRServiceImpl;
import com.yuan.javaswingproject.util.SpringContext;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class AiHelperView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel imageLabel;

	@Autowired
	private AiServiceImpl aiService;

	@Autowired
	private OCRServiceImpl  ocrService;

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
				JOptionPane.showMessageDialog(null, "已是当前界面", "提示", JOptionPane.INFORMATION_MESSAGE);

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
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn1 = new JButton("上传图片");
		btn1.setBounds(37, 31, 129, 43);
		contentPane.add(btn1);

		btn1.addActionListener(e -> uploadAndRenderImage());

		textField = new JTextField();
		textField.setBounds(194, 31, 740, 43);
		contentPane.add(textField);
		textField.setColumns(10);

		imageLabel = new JLabel("");
		imageLabel.setBounds(37, 113, 574, 514);
		imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(imageLabel);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false); // 可输入
		textPane.setContentType("text/plain"); // 纯文本

		// 将 JTextPane 放入 JScrollPane 中
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(647, 113, 547, 514);
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

		JButton btn2 = new JButton("开始分析");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String imagePath = textField.getText();
				if (imagePath.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请先上传图片", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				String ocr = ocrService.ocr(imagePath);
				if (ocr == null){
					JOptionPane.showMessageDialog(null, "图片识别失败", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				String analysis = aiService.analysis(ocr);
				//添加等待框
				JOptionPane.showMessageDialog(null, "正在分析中，请稍等...", "提示", JOptionPane.INFORMATION_MESSAGE);
				textPane.setText(analysis);
			}
		});
		btn2.setBounds(955, 31, 140, 43);
		contentPane.add(btn2);

		//添加清空按钮
		JButton btn3 = new JButton("清空");
		btn3.setBounds(1115, 31, 89, 43);
		contentPane.add(btn3);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textPane.setText("");
				imageLabel.setIcon(null);
			}
		});
	}

	private void uploadAndRenderImage() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("选择图片");
		fileChooser.setFileFilter(new FileNameExtensionFilter("图片文件", "jpg", "png", "jpeg", "gif", "bmp"));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String imagePath = file.getAbsolutePath(); // ✅ 获取图片路径
			textField.setText(imagePath); // 显示路径
			// 渲染图片（缩放适配）
			ImageIcon icon = new ImageIcon(imagePath);
			Image image = icon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
			imageLabel.setIcon(new ImageIcon(image));
		}

	}
	public AiHelperView() {

	}

}
