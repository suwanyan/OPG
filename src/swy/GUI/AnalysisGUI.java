package swy.GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import swy.compiler.IsLegalSentence;
import swy.compiler.PublicVariable;
import swy.operate.fileOperate;

public class AnalysisGUI {
	// public static void main(String[] args){
	public void a() throws IOException {
		GUI sfl = new GUI();
	}
}

class GUI extends JFrame implements ActionListener {
	IsLegalSentence isLegalSentence;
	JPanel[] panel;// ����
	JPanel[] panelIn;// ����
	JFrame analysisFrame;// ���
	String[] labelStr;// ��ǩ����
	JButton[] btn;// ��ť
	MenuBar menubar; // �˵���
	Menu grammerDtailMenu, helpMenu;// �˵�
	JTextField inputTextField;
	JLabel[] label;
	JTable table;

	JTextArea grammerArear;
	JTextArea FisrstArear, lastArear;// �ı���
	JTextArea tableArear, analysisProcArear;
	JScrollPane fscroll, lscroll, scroll, anaScroll,tscroll;// ������
	PublicVariable publicPath = new PublicVariable();
	fileOperate folderFile = new fileOperate();// �ļ�����
	String path_temp1 = publicPath.getPath_temp();

	GUI() throws IOException {
		// ------------------------��ʼ������---------------------------
		int k = 0;// ��������±�
		inputTextField = new JTextField();
		analysisProcArear=new JTextArea();
		// ---------------------------- 6������
		panel = new JPanel[] { new JPanel(), new JPanel() };
		panelIn = new JPanel[] { new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
				new JPanel() };
		//for (JPanel seven : panel) {// ����������С
			// seven.setSize(200,200);
		//seven.setBackground(Color.red);
			// seven.setVisible(true);
		//}
		
		for (JPanel seven : panelIn) {// ����������С
		//	seven.setBackground(Color.orange);
			seven.setVisible(true);
		}
		// ---------------------------- ���
		analysisFrame = new JFrame("����---"+path_temp1+"\\");// ���"
		analysisFrame.setSize(1160,700);
		analysisFrame.setBackground(Color.CYAN);
		// ---------------------------- �˵���
		menubar = new MenuBar();
		grammerDtailMenu = new Menu("�ķ�����");
		helpMenu = new Menu("����");
		// ---------------------------- ��ǩ
		labelStr = new String[] { "��ǰ�ķ�", "FIRSTVT��", "LASTVT��", "���ȹ�ϵ����", "�������ķ�����(#�Ž���)", "��ʷ��¼", "���Ŵ���������" };
		label = new JLabel[] { new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),
				new JLabel() };
		for (JLabel seven : label) {// ��ǩ��ʼ��
			seven.setText(labelStr[k]);
			++k;
		}
		// ----------------------------�ı���
		FisrstArear = new JTextArea("Fist");
		//FisrstArear.setLineWrap(true); // �ı����Զ�����
		fscroll = new JScrollPane(FisrstArear); // ������
		fscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		fscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lastArear = new JTextArea(" last");
		lastArear.setLineWrap(true); // �ı����Զ�����
		lscroll = new JScrollPane(lastArear); // ������
		lscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		lscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		String path_first = path_temp1 + "/first.txt";
		folderFile.fileToTextArear(path_first, FisrstArear);// ���ı�����д���ļ�����
		String path_last = path_temp1 + "/last.txt";
		folderFile.fileToTextArear(path_last, lastArear);// ���ı�����д���ļ�����
		grammerArear = new JTextArea("");
		//grammerArear.setLineWrap(true); // �ı����Զ�����
		scroll = new JScrollPane(grammerArear); // ������
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		String path_grammer = path_temp1 + "/Grammer.txt";
		folderFile.fileToTextArear(path_grammer, grammerArear);// ���ı�����д���ļ�����

		tableArear = new JTextArea(" ");
		//tableArear.setLineWrap(true); // �ı����Զ�����
		tscroll = new JScrollPane(tableArear); // ������
		tscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		tscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		String path_table = path_temp1 + "/analysisTable.txt";
		folderFile.fileToTextArear(path_table, tableArear);// ���ı�����д���ļ�����

		analysisProcArear = new JTextArea("");
		//analysisProcArear.setLineWrap(true); // �ı����Զ�����
		anaScroll = new JScrollPane(analysisProcArear); // ������
		anaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		anaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// ---------------------------- ��ť
		btn = new JButton[] { new JButton("����"), new JButton("����"), new JButton("����") };
		// Font.PLAIN
		//
		
		panelIn[0].setPreferredSize(new Dimension(210, 170));// �ؼ�����,����JPanel�Ĵ�С
		panelIn[1].setPreferredSize(new Dimension(170, 170));// �ؼ�����,����JPanel�Ĵ�С
		panelIn[2].setPreferredSize(new Dimension(170, 170));// �ؼ�����,����JPanel�Ĵ�С
		panelIn[3].setPreferredSize(new Dimension(570, 450));// �ؼ�����,����JPanel�Ĵ�С
		panelIn[4].setPreferredSize(new Dimension(570, 170));// �ؼ�����,����JPanel�Ĵ�С
		//panelIn[5].setPreferredSize(new Dimension(200, 100));// �ؼ�����,����JPanel�Ĵ�С
		panelIn[6].setPreferredSize(new Dimension(570, 450));// �ؼ�����,����JPanel�Ĵ�С
		
		inputTextField.setBounds(10, 30, 550, 30);// ���У�10��10����λ�ã���50��50���Ǵ�С.
		label[0].setBounds(10, 5, 100, 25);
		label[1].setBounds(10, 5, 100, 25);
		label[2].setBounds(10, 5, 100, 25);
		label[3].setBounds(10, 5, 100, 25);
		label[4].setBounds(10, 5, 200, 25);
		label[5].setBounds(10, 5, 100, 25);
		label[6].setBounds(10, 5, 100, 25);
		scroll.setBounds(10, 30, 190, 130);
		fscroll.setBounds(10, 30, 150, 130);
		lscroll.setBounds(10, 30, 150, 130);// �ı���
		tscroll.setBounds(10, 30, 550, 400);// �ı���
		anaScroll.setBounds(10, 30, 400, 400);// �ı���;
		btn[0].setBounds(50, 90, 60, 35);
		//btn[1].setBounds(120, 90, 60, 35);
		btn[2].setBounds(190, 90, 60, 35);
		// --------------------------------------GridLayout����-----------------------------------
		panelIn[0].setLayout(null);
		panelIn[0].add(label[0]);
		panelIn[0].add(scroll);
		panelIn[1].setLayout(null);
		panelIn[1].add(label[1]);
		panelIn[1].add(fscroll);
		panelIn[2].setLayout(null);
		panelIn[2].add(label[2]);
		panelIn[2].add(lscroll);
		panelIn[3].setLayout(null);
		panelIn[3].add(label[3]);
		panelIn[3].add(tscroll);
		panelIn[4].setLayout(null);
		panelIn[4].add(label[4]);
		panelIn[4].add(inputTextField);
		panelIn[4].add(btn[0]);
		//panelIn[4].add(btn[1]);
		panelIn[4].add(btn[2]);
		//panelIn[5].add(label[5]);
		panelIn[6].setLayout(null);
		panelIn[6].add(label[6]);
		panelIn[6].add(anaScroll);
		panel[0].add(panelIn[0], BorderLayout.NORTH);
		panel[0].add(panelIn[1], BorderLayout.EAST);
		panel[0].add(panelIn[2], BorderLayout.CENTER);
		panel[0].add(panelIn[3], BorderLayout.SOUTH);
		panel[1].add(panelIn[4], BorderLayout.NORTH);
		//panel[1].add(panelIn[5], BorderLayout.CENTER);
		panel[1].add(panelIn[6], BorderLayout.SOUTH);
		menubar.add(grammerDtailMenu);// �˵���
		menubar.add(helpMenu);
		analysisFrame.setMenuBar(menubar);
		analysisFrame.setLayout(new GridLayout(1, 2, 10, 10));// ���񲼾�
		for (JPanel seven : panel) {// ��������ӵ������
			analysisFrame.add(seven);
		}
		btn[0].addActionListener(this);
		btn[1].addActionListener(this);
		btn[2].addActionListener(this);
		analysisFrame.setVisible(true);// ���ڹر��¼�
		analysisFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		analysisFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn[0]) {
			inputTextField.setText("");
			analysisProcArear.setText("");
		}
		if (e.getSource() == btn[1]) {
		}
		if (e.getSource() == btn[2]) {
			String input=new String("");
			input=inputTextField.getText();
			System.out.println(input+"1");
			isLegalSentence=new IsLegalSentence();//���봮����
			isLegalSentence.input(input,analysisProcArear);
			System.out.println(input+"2");
		}
	}
}