package swy.operate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class fileOperate {
	public File file_temp;
	public boolean createDir(String destDirName, JLabel createSpaceError) { // �½��ķ��ռ䣬������һ��Ŀ¼
		File dir = new File(destDirName);
		if (dir.exists()) {
			createSpaceError.setText("Ŀ��Ŀ¼�Ѿ�����");
			System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�Ŀ��Ŀ¼�Ѿ�����");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// ����Ŀ¼
		if (dir.mkdirs()) {
			System.out.println("����Ŀ¼" + destDirName + "�ɹ���");
			return true;
		} else {
			createSpaceError.setText("����Ŀ¼ʧ��");
			System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�");
			return false;
		}
	}

	public boolean createFile(String filePath, String fileName) {
		// ��ָ��Ŀ¼�´���ָ���ļ������ļ�,JLabel createSpaceError
		String filePathName = new String(filePath + fileName);
		File file = new File(filePathName);
		file_temp = file;
		if (file.exists()) { // ͬ���ļ��Ѿ�
			System.out.println("���������ļ�" + filePathName + "ʧ�ܣ�Ŀ���ļ��Ѵ��ڣ�");
			return false;
		}
		if (filePathName.endsWith(File.separator)) {
			System.out.println("���������ļ�" + filePathName + "ʧ�ܣ�Ŀ���ļ�����ΪĿ¼��");
			// createSpaceError.setText("Ŀ��Ŀ¼�Ѿ�����");
			return false;
		}
		// �ж�Ŀ���ļ����ڵ�Ŀ¼�Ƿ����
		if (!file.getParentFile().exists()) {
			// ���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽���Ŀ¼
			System.out.println("Ŀ���ļ�����Ŀ¼�����ڣ�׼����������");
			if (!file.getParentFile().mkdirs()) {
				System.out.println("����Ŀ���ļ�����Ŀ¼ʧ�ܣ�");
				return false;
			}
		}
		// ����Ŀ���ļ�
		try {
			if (file.createNewFile()) {
				// fileName_temp=file;
				System.out.println("���������ļ�" + filePathName + "�ɹ���");
				return true;
			} else {
				System.out.println("���������ļ�" + filePathName + "ʧ�ܣ�");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("���������ļ�" + filePathName + "ʧ�ܣ�" + e.getMessage());
			return false;
		}
	}

	public void inputToFile(String path_temp, String fileName_temp, String gt) throws IOException {// ·�������ļ�����Ҫд���ļ����ĵ�
		// ��ָ���ļ���д�룬fileName_tempΪָ���ļ���
		File f1;
		try {
			createFile(path_temp, fileName_temp);// �ڸÿռ��´���һ��ָ���ļ������ļ�
			f1 = file_temp;// ��ȡ�½����ļ�
			Writer fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f1), "UTF-8"));
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(gt, 0, gt.length());
			bw.flush();
			fw.close();
			JOptionPane.showMessageDialog(null, "����ɹ�");
		} catch (Exception e1) {System.out.println(" ��ָ���ļ���д�����");
		}
	}

	public void arrtoFile(char[][] gatmmer,char arr[][],int r,String path_temp, String fileName_temp) throws IOException {// ���ַ�����д���ļ�
		try {
			createFile(path_temp, fileName_temp);// �ڸÿռ��´���һ��ָ���ļ������ļ�
			File file=file_temp ;
			System.out.println(" ���ַ�����д���ļ�");
			FileWriter out = new FileWriter(file); // �ļ�д����
			// �������е�����д�뵽�ļ��С�ÿ�и�����֮��TAB���
			for (int i = 0; i < r; i++) {
				out.write(gatmmer[i][0]);
				out.write(":{");
				for (int j = 0; j < arr[i][0]; j++) {
					out.write("  ");
					out.write(arr[i][j+1]);
					out.write("  ");
				}
				out.write("}");
				out.write("\n");
			}
			out.close();
		} catch (Exception e3) {System.out.println(" ���ַ�����д���ļ�����");
		}
	}
	public void arrtoFile(char[][] analysisTable,char[] lable, int k,String path_temp, String fileName_temp) throws IOException {// ���ַ�����д���ļ�
		try {
			int i, j;
			createFile(path_temp, fileName_temp);// �ڸÿռ��´���һ��ָ���ļ������ļ�
			File file=file_temp ;
			FileWriter out = new FileWriter(file); // �ļ�д����
			out.write("\t");
			for (i = 0; lable[i] != '\0'; i++)
				out.write(lable[i] + "\t");
			out.write("\n");
			for (i = 0; i < k + 1; i++) {
				out.write(lable[i] + "\t");
				for (j = 0; j < k + 1; j++) {
					out.write(analysisTable[i][j] + "\t");
				}
				out.write("\n");
			}
			out.close();
		} catch (Exception e3) {System.out.println(" ���ַ�����д���ļ�����");
		}
	}
	public void fileToTextArear(String f1,JTextArea TextArea) throws IOException {
	FileInputStream fis=new FileInputStream(f1);
		InputStreamReader fr =new InputStreamReader(fis);
		 BufferedReader br = new BufferedReader(fr);
		 String str;
		 TextArea.setText("");// ������ı�����д��
			while ((str = br.readLine()) != null)
				TextArea.append(str + '\n');
			fr.close();
	}
	public void fileToTextArear(File f1,JTextArea TextArea) throws IOException {//���ı�����д���ļ�����
		try {
			String str;
			InputStreamReader fr = new InputStreamReader(new FileInputStream(f1), "UTF-8");
			BufferedReader br = new BufferedReader(fr);
			TextArea.setText("");// ������ı�����д��
			while ((str = br.readLine()) != null)
				TextArea.append(str + '\n');
			fr.close();
		} catch (Exception e3) {System.out.println(" ���ı�����д���ļ����ݴ���");
		}
	}
}
