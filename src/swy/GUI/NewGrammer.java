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
	private SimpleDialog dialog;// ��������
	FileDialog open;// �Ի���
	JButton openBtn, saveBtn, clearBtn, analysisBtn, helpBtn, newBtn;
	JTextArea grammerArear, errorArear;
	JLabel newGrammerLabel, errorLabel;
	Box newGrammerBox, btnBox, errorBox;
	JScrollPane scroll;// ������
	String path_temp = new String("no");// ��ȡ��ǰ�洢·��
	fileOperate folderFile = new fileOperate();// �ļ�����

	InputGrammer() {
		JFrame input = new JFrame("������ȷ�����-�����ķ�");
		input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��������Ķ���
		// ����
		newGrammerBox = Box.createVerticalBox();
		btnBox = Box.createVerticalBox();
		errorBox = Box.createVerticalBox();
		// ��ť
		openBtn = new JButton("����");
		saveBtn = new JButton("����");
		clearBtn = new JButton("���");
		analysisBtn = new JButton("����");
		helpBtn = new JButton("����");
		newBtn = new JButton("�½�");
		// �ı���
		grammerArear = new JTextArea("");
		grammerArear.setLineWrap(true); // �ı����Զ�����
		scroll = new JScrollPane(grammerArear); // ������
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		errorArear = new JTextArea(" ");
		errorArear.setForeground(Color.red);
		// ��ǩ
		newGrammerLabel = new JLabel("�����ķ�");
		errorLabel = new JLabel("����");
		// ������
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
		input.setSize(400, 300);// ���ڴ�С
		input.setVisible(true);
		input.validate();// ȷ�������Ч
		open = new FileDialog(this, "��", FileDialog.LOAD);
		// save = new FileDialog(this, "����", FileDialog.SAVE);
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
		if (!path_temp.equals("no")) {// ·��û�д��������½����������ڸ�Ŀ¼��
			newGrammerLabel.setText("��ǰ�ķ��ռ䡪��" + path_temp);
		}
	}

	public void newGrammerSpace() {// �½��ķ��ռ�
		if (dialog == null) {
			dialog = new SimpleDialog(this, "���빤������");
		}
		dialog.setModal(true); // ��ģ̬��ʾС����
		dialog.setVisible(true);
		setLabel(newGrammerLabel);
		System.out.println("�¿�");
	}

	public void newFileToSpace() {// �½��ռ䣬�ڿռ����½�Grammer.txt�ļ��������ı����ķ������ļ���
		File f1;
		try {
			String fileName_temp = new String("/Grammer.txt");
			String gt = grammerArear.getText();// ��ȡ�ı�������
			if (path_temp.equals("no")) {// ·��û�д��������½����������ڸ�Ŀ¼��
				newGrammerSpace();// �½�һ���ķ��ռ�
			}
			if (!path_temp.equals("no")) {
				folderFile.inputToFile(path_temp, fileName_temp, gt);// ��ָ���ķ��ռ��´���һ��Grammer.txt�ļ������ڱ����ķ�
			}
		} catch (Exception e3) {
			System.out.println(" �½��ռ䣬�ڿռ����½�Grammer.txt�ļ��������ı����ķ������ļ���");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openBtn) {// �����ķ�����
			open.setVisible(true);
			try {
				File f1 = new File(open.getDirectory(), open.getFile());
				path_temp = f1.getParent();// ��ȡ���ļ����ļ���
				folderFile.fileToTextArear(f1, grammerArear);// ���ı�����д���ļ�����
				setLabel(newGrammerLabel);
			} catch (Exception e1) {
				System.out.println(" �����ķ�����");
			}
		}
		if (e.getSource() == newBtn) {// �½�,���½��ķ�ʱ�ȴ���һ���ļ���
			try {
				/* ���������������ƣ���ȡ���� */
				// �����ťʱ��ʾ�Ի���
				newGrammerSpace();
				grammerArear.setText("");// ����ı���
			} catch (Exception e2) {
				System.out.println(" ���");
			}
		}
		if (e.getSource() == saveBtn) {// ����
			newFileToSpace();// ���ķ��ռ����½�Grammer.txt�����ұ����ı������ݵ����ļ�
		}
		if (e.getSource() == clearBtn) {// ���
			try {
				grammerArear.setText("");

			} catch (Exception e4) {
				System.out.println(" ����");
			}
		}
		if (e.getSource() == analysisBtn) {// ����
			try {
				newFileToSpace();// ���ķ��ռ����½�Grammer.txt�����ұ����ı������ݵ����ļ�
				if (!path_temp.equals("no")) {
					String gt = grammerArear.getText();// ��ȡ�ı�������
					publicPath.setPath_temp(path_temp);
					Analysis analysis = new Analysis();// ���÷�������
					analysis.initGrammer(gt, errorArear);
				}
				// AnalysisGUI analysisGUI=new AnalysisGUI();

			} catch (Exception e5) {
				System.out.println(" ����");
			}
		}
		if (e.getSource() == helpBtn) {// ����

			try {
				String message = new String("��ʾ��\n1�������ʽ���Ҳ���ʽ��ļ�ͷ��д�ɡ�->���������޷�׼ȷ����\n" + "2��ÿ������ʽ�Ի���Ϊ�ֽ�");
				JOptionPane.showMessageDialog(null, message);
				System.out.println("����" + path_temp);
			} catch (Exception e6) {
				System.out.println(" ����");
			}
		}
	}

}

// �½��ķ��ռ䣬��������
class SimpleDialog extends JDialog implements ActionListener {
	fileOperate folder = new fileOperate();// �ļ�����
	String name = new String();
	// �ı������������ַ���
	JTextField field;
	// �Ի���ĸ����塣
	InputGrammer parent;
	// ��ȷ������ť
	JButton setButton, cancelButton;
	JLabel createSpaceError;
	String path;// ȫ�ֵĴ洢·��

	/**
	 * ���캯��������Ϊ������ͶԻ���ı���
	 */
	SimpleDialog(JFrame prentFrame, String title) {
		// ���ø���Ĺ��캯����
		// ������������false��ʾ�������������塣Ϊtrue��ʾ���ܹ�������������
		super(prentFrame, title, false);
		parent = (InputGrammer) prentFrame;

		// ���Label�������ı���
		JPanel p1 = new JPanel();
		JLabel label = new JLabel("������ռ�����:");

		p1.add(label);
		field = new JTextField(30);
		field.addActionListener(this);
		p1.add(field);

		getContentPane().add("Center", p1);
		// ���ȷ����ȡ����ť
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		cancelButton = new JButton("ȡ ��");
		cancelButton.addActionListener(this);
		setButton = new JButton("ȷ ��");
		setButton.addActionListener(this);
		createSpaceError = new JLabel("");// �����¿ռ伴һ���ļ���ʧ��ʱ
		createSpaceError.setForeground(Color.RED);
		p2.add(setButton);
		p2.add(cancelButton);
		p2.add(createSpaceError);
		getContentPane().add("South", p2);

		// �����Ի��򲼾ִ�С
		pack();
	}

	/**
	 * �¼�����
	 */
	public void actionPerformed(ActionEvent event) {
		boolean ifFolder;

		Object source = event.getSource();
		if ((source == setButton)) {// ���ȷ����ť�����£�
			path = "F:/eclipse/OPG/grammer/";// ����ָ���ļ�·��
			name = field.getText().toString();
			String newPath = path + name;// ָ����·��
			if (folder.createDir(newPath, createSpaceError)) {
				setVisible(false);
				parent.path_temp = newPath;
				// path1.setPath_temp(newPath);
			} // �����ļ��У������ķ��ռ�

		}
		// ���ضԻ���
		else if (source == cancelButton) {
			setVisible(false);
		}
	}
}
