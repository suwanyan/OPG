package swy.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.print.SimpleDoc;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import swy.compiler.*;
import swy.operate.*;

public class NewGrammer {
	public static void main(String args[]) {
		InputGrammer ig = new InputGrammer();
	}
}

class InputGrammer extends JFrame implements ActionListener {
	PublicVariable publicPath = new PublicVariable();
	private SimpleDialog dialog;// 弹出窗口
	FileDialog open;// 对话框
	JButton openBtn, saveBtn, clearBtn, analysisBtn, helpBtn, newBtn;
	JTextArea grammerArear, errorArear;
	JLabel newGrammerLabel, errorLabel;
	Box newGrammerBox, btnBox, errorBox;
	JScrollPane scroll;// 进度条
	String path_temp = new String("no");// 获取当前存储路径
	fileOperate folderFile = new fileOperate();// 文件处理

	InputGrammer() {
		JFrame input = new JFrame("算符优先分析器-输入文法");
		input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 布局组件的定义
		// 盒子
		newGrammerBox = Box.createVerticalBox();
		btnBox = Box.createVerticalBox();
		errorBox = Box.createVerticalBox();
		// 按钮
		openBtn = new JButton("导入");
		saveBtn = new JButton("保存");
		clearBtn = new JButton("清空");
		analysisBtn = new JButton("分析");
		helpBtn = new JButton("帮助");
		newBtn = new JButton("新建");
		// 文本域
		grammerArear = new JTextArea("");
		grammerArear.setLineWrap(true); // 文本框自动换行
		scroll = new JScrollPane(grammerArear); // 滚动条
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		errorArear = new JTextArea(" ");
		errorArear.setForeground(Color.red);
		// 标签
		newGrammerLabel = new JLabel("输入文法");
		errorLabel = new JLabel("错误");
		// 添加组件
		newGrammerBox.add(scroll);
		btnBox.add(openBtn);
		btnBox.add(newBtn);
		btnBox.add(saveBtn);
		btnBox.add(clearBtn);
		btnBox.add(analysisBtn);
		btnBox.add(helpBtn);
		errorBox.add(errorLabel);
		errorBox.add(errorArear);
		input.add(newGrammerLabel, BorderLayout.NORTH);
		input.add(newGrammerBox, BorderLayout.CENTER);
		input.add(btnBox, BorderLayout.EAST);
		input.add(errorBox, BorderLayout.SOUTH);
		input.setSize(400, 300);// 窗口大小
		input.setVisible(true);
		input.validate();// 确保组件有效
		open = new FileDialog(this, "打开", FileDialog.LOAD);
		// save = new FileDialog(this, "保存", FileDialog.SAVE);
		openBtn.addActionListener(this);
		newBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		analysisBtn.addActionListener(this);
		helpBtn.addActionListener(this);
		input.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		input.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
	}

	public void setLabel(JLabel newGrammerLabel) {
		if (!path_temp.equals("no")) {// 路径没有创建，则新建，并保存在该目录下
			newGrammerLabel.setText("当前文法空间——" + path_temp);
		}
	}

	public void newGrammerSpace() {// 新建文法空间
		if (dialog == null) {
			dialog = new SimpleDialog(this, "输入工程名称");
		}
		dialog.setModal(true); // 用模态显示小窗体
		dialog.setVisible(true);
		setLabel(newGrammerLabel);
		System.out.println("新框");
	}

	public void newFileToSpace() {// 新建空间，在空间下新建Grammer.txt文件，保存文本框文法到该文件里
		File f1;
		try {
			String fileName_temp = new String("/Grammer.txt");
			String gt = grammerArear.getText();// 获取文本框内容
			if (path_temp.equals("no")) {// 路径没有创建，则新建，并保存在该目录下
				newGrammerSpace();// 新建一个文法空间
			}
			if (!path_temp.equals("no")) {
				folderFile.inputToFile(path_temp, fileName_temp, gt);// 在指定文法空间下创建一个Grammer.txt文件，用于保存文法
			}
		} catch (Exception e3) {
			System.out.println(" 新建空间，在空间下新建Grammer.txt文件，保存文本框文法到该文件里");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openBtn) {// 导入文法工程
			open.setVisible(true);
			try {
				File f1 = new File(open.getDirectory(), open.getFile());
				path_temp = f1.getParent();// 获取打开文件的文件夹
				folderFile.fileToTextArear(f1, grammerArear);// 向文本框中写入文件内容
				setLabel(newGrammerLabel);
			} catch (Exception e1) {
				System.out.println(" 导入文法工程");
			}
		}
		if (e.getSource() == newBtn) {// 新建,当新建文法时先创建一个文件夹
			try {
				/* 弹出窗口输入名称，获取名称 */
				// 点击按钮时显示对话框
				newGrammerSpace();
				grammerArear.setText("");// 清空文本框
			} catch (Exception e2) {
				System.out.println(" 清空");
			}
		}
		if (e.getSource() == saveBtn) {// 保存
			newFileToSpace();// 向文法空间中新建Grammer.txt，并且保存文本框内容到该文件
		}
		if (e.getSource() == clearBtn) {// 清空
			try {
				grammerArear.setText("");

			} catch (Exception e4) {
				System.out.println(" 保存");
			}
		}
		if (e.getSource() == analysisBtn) {// 分析
			try {
				newFileToSpace();// 向文法空间中新建Grammer.txt，并且保存文本框内容到该文件
				if (!path_temp.equals("no")) {
					String gt = grammerArear.getText();// 获取文本框内容
					publicPath.setPath_temp(path_temp);
					Analysis analysis = new Analysis();// 调用分析程序
					analysis.initGrammer(gt, errorArear);
				}
				// AnalysisGUI analysisGUI=new AnalysisGUI();

			} catch (Exception e5) {
				System.out.println(" 分析");
			}
		}
		if (e.getSource() == helpBtn) {// 帮助

			try {
				String message = new String("提示：\n1、左产生式与右产生式间的箭头请写成“->”，否则无法准确分析\n" + "2、每条产生式以换行为分解");
				JOptionPane.showMessageDialog(null, message);
				System.out.println("帮助" + path_temp);
			} catch (Exception e6) {
				System.out.println(" 帮助");
			}
		}
	}

}

// 新建文法空间，弹出窗口
class SimpleDialog extends JDialog implements ActionListener {
	fileOperate folder = new fileOperate();// 文件处理
	String name = new String();
	// 文本框，用于输入字符串
	JTextField field;
	// 对话框的父窗体。
	InputGrammer parent;
	// “确定”按钮
	JButton setButton, cancelButton;
	JLabel createSpaceError;
	String path;// 全局的存储路径

	/**
	 * 构造函数，参数为父窗体和对话框的标题
	 */
	SimpleDialog(JFrame prentFrame, String title) {
		// 调用父类的构造函数，
		// 第三个参数用false表示允许激活其他窗体。为true表示不能够激活其他窗体
		super(prentFrame, title, false);
		parent = (InputGrammer) prentFrame;

		// 添加Label和输入文本框
		JPanel p1 = new JPanel();
		JLabel label = new JLabel("请输入空间名称:");

		p1.add(label);
		field = new JTextField(30);
		field.addActionListener(this);
		p1.add(field);

		getContentPane().add("Center", p1);
		// 添加确定和取消按钮
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		cancelButton = new JButton("取 消");
		cancelButton.addActionListener(this);
		setButton = new JButton("确 定");
		setButton.addActionListener(this);
		createSpaceError = new JLabel("");// 创建新空间即一个文件夹失败时
		createSpaceError.setForeground(Color.RED);
		p2.add(setButton);
		p2.add(cancelButton);
		p2.add(createSpaceError);
		getContentPane().add("South", p2);

		// 调整对话框布局大小
		pack();
	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent event) {
		boolean ifFolder;

		Object source = event.getSource();
		if ((source == setButton)) {// 如果确定按钮被按下，
			path = "F:/eclipse/OPG/grammer/";// 定义指定文件路径
			name = field.getText().toString();
			String newPath = path + name;// 指定新路径
			if (folder.createDir(newPath, createSpaceError)) {
				setVisible(false);
				parent.path_temp = newPath;
				// path1.setPath_temp(newPath);
			} // 创建文件夹，即新文法空间

		}
		// 隐藏对话框
		else if (source == cancelButton) {
			setVisible(false);
		}
	}
}
