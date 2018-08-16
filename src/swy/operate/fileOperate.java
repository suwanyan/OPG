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
	public boolean createDir(String destDirName, JLabel createSpaceError) { // 新建文法空间，即创建一个目录
		File dir = new File(destDirName);
		if (dir.exists()) {
			createSpaceError.setText("目标目录已经存在");
			System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			System.out.println("创建目录" + destDirName + "成功！");
			return true;
		} else {
			createSpaceError.setText("创建目录失败");
			System.out.println("创建目录" + destDirName + "失败！");
			return false;
		}
	}

	public boolean createFile(String filePath, String fileName) {
		// 在指定目录下创建指定文件名的文件,JLabel createSpaceError
		String filePathName = new String(filePath + fileName);
		File file = new File(filePathName);
		file_temp = file;
		if (file.exists()) { // 同名文件已经
			System.out.println("创建单个文件" + filePathName + "失败，目标文件已存在！");
			return false;
		}
		if (filePathName.endsWith(File.separator)) {
			System.out.println("创建单个文件" + filePathName + "失败，目标文件不能为目录！");
			// createSpaceError.setText("目标目录已经存在");
			return false;
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {
				System.out.println("创建目标文件所在目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				// fileName_temp=file;
				System.out.println("创建单个文件" + filePathName + "成功！");
				return true;
			} else {
				System.out.println("创建单个文件" + filePathName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("创建单个文件" + filePathName + "失败！" + e.getMessage());
			return false;
		}
	}

	public void inputToFile(String path_temp, String fileName_temp, String gt) throws IOException {// 路径名，文件名，要写入文件的文档
		// 向指定文件中写入，fileName_temp为指定文件名
		File f1;
		try {
			createFile(path_temp, fileName_temp);// 在该空间下创建一个指定文件名的文件
			f1 = file_temp;// 获取新建的文件
			Writer fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f1), "UTF-8"));
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(gt, 0, gt.length());
			bw.flush();
			fw.close();
			JOptionPane.showMessageDialog(null, "保存成功");
		} catch (Exception e1) {System.out.println(" 向指定文件中写入错误");
		}
	}

	public void arrtoFile(char[][] gatmmer,char arr[][],int r,String path_temp, String fileName_temp) throws IOException {// 将字符数组写入文件
		try {
			createFile(path_temp, fileName_temp);// 在该空间下创建一个指定文件名的文件
			File file=file_temp ;
			System.out.println(" 将字符数组写入文件");
			FileWriter out = new FileWriter(file); // 文件写入流
			// 将数组中的数据写入到文件中。每行各数据之间TAB间隔
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
		} catch (Exception e3) {System.out.println(" 将字符数组写入文件错误");
		}
	}
	public void arrtoFile(char[][] analysisTable,char[] lable, int k,String path_temp, String fileName_temp) throws IOException {// 将字符数组写入文件
		try {
			int i, j;
			createFile(path_temp, fileName_temp);// 在该空间下创建一个指定文件名的文件
			File file=file_temp ;
			FileWriter out = new FileWriter(file); // 文件写入流
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
		} catch (Exception e3) {System.out.println(" 将字符数组写入文件错误");
		}
	}
	public void fileToTextArear(String f1,JTextArea TextArea) throws IOException {
	FileInputStream fis=new FileInputStream(f1);
		InputStreamReader fr =new InputStreamReader(fis);
		 BufferedReader br = new BufferedReader(fr);
		 String str;
		 TextArea.setText("");// 先清空文本框，再写入
			while ((str = br.readLine()) != null)
				TextArea.append(str + '\n');
			fr.close();
	}
	public void fileToTextArear(File f1,JTextArea TextArea) throws IOException {//向文本框中写入文件内容
		try {
			String str;
			InputStreamReader fr = new InputStreamReader(new FileInputStream(f1), "UTF-8");
			BufferedReader br = new BufferedReader(fr);
			TextArea.setText("");// 先清空文本框，再写入
			while ((str = br.readLine()) != null)
				TextArea.append(str + '\n');
			fr.close();
		} catch (Exception e3) {System.out.println(" 向文本框中写入文件内容错误");
		}
	}
}
