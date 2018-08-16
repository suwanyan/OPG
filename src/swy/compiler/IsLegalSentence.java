package swy.compiler;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class IsLegalSentence {
	String temp=new String();
	char[] input = new char[100]; // 文法输入符号串
	Method method=new Method();
	PublicVariable publicPath=new PublicVariable();
	String path_temp1=publicPath.getPath_temp();
	char[][] analysisTable = publicPath.getAnalysisTable(); // 算符优先关系
	char[][] string = publicPath.getString(); // 用于输入串的分析
	int r1=publicPath.getR1();
	Scanner in = new Scanner(System.in);
	public void input(String input_temp,JTextArea analysisProcArear){
		//System.out.print("请输入文法输入符号串以#结束:");
		//input_temp = in.next();
		input_temp=input_temp+'\0';
		input = input_temp.toCharArray();// 字符串转换成字符数组
		deal(input,analysisProcArear);
	}
	public int deal(char[] input,JTextArea analysisProcArear) {
		String messageT = new String("输入串符合文法的定义！\n");
		String messageF = new String("\n该语句不符合文法定义");
		
		char[] s = new char[100]; // 模拟符号栈s
		char a,q;
		int i, j;
		int x, y;
		int z; // 输入串的长度
		int k = 1;
		s[k] = '#'; // 栈置初值
		for (i = 0; input[i] != '\0'; i++)
			; // 计算输入串的长度
		z = i--;
		i = 0;
		while ((input[i]) != '\0') {
			a = input[i];
			if (method.zhongjie(s[k]))
				j = k;
			else
				j = k - 1;
			x = method.xiabiao(s[j]);
			y = method.xiabiao(a);
			if (analysisTable[x][y] == '>') {
				int m, n, N;
				out(1, k, s,analysisProcArear);
				System.out.print(a);
				temp=String.valueOf(a);
				analysisProcArear.append(temp);
				out(i + 1, z, input,analysisProcArear);
				System.out.print("规约\n");
				analysisProcArear.append("规约\n");
				do {
					q = s[j];
					if (method.zhongjie(s[j - 1]))
						j = j - 1;
					else
						j = j - 2;
					x = method.xiabiao(s[j]);
					y = method.xiabiao(q);
				} while (analysisTable[x][y] != '<');

				for (m = j + 1; m <= k; m++) {
					for (N = 0; N < r1; N++)
						for (n = 1; string[N][n] != '\0'; n++) {
							if (!method.zhongjie(s[m]) && !method.zhongjie(string[N][n])) {
								if (method.zhongjie(s[m + 1]) && method.zhongjie(string[N][n + 1]) && s[m + 1] == string[N][n + 1]) {
									s[j + 1] = string[N][0];
									break;
								}
							} else if (method.zhongjie(s[m]))
								if (s[m] == string[N][n]) {
									s[j + 1] = string[N][0];
									break;
								}
						}
				}
				k = j + 1;
				if (k == 2 && a == '#') {
					out(1, k, s,analysisProcArear);
					System.out.print(a);
					temp=String.valueOf(a);
					analysisProcArear.append(temp);
					out(i + 1, z, input,analysisProcArear);
					System.out.print("结束\n");
					System.out.print("输入串符合文法的定义！\n");
					analysisProcArear.append("结束\n");
					analysisProcArear.append("输入串符合文法的定义！\n");
					JOptionPane.showMessageDialog(null, messageT);
					return 1; // 输入串符合文法的定义
				}
			} else if (analysisTable[x][y] == '<' || analysisTable[x][y] == '=') { // 移进
				out(1, k, s,analysisProcArear);
				System.out.print(a);
				temp=String.valueOf(a);
				analysisProcArear.append(temp);
				out(i + 1, z, input,analysisProcArear);
				System.out.print("移进\n");
				analysisProcArear.append("移进\n");
				k++;
				s[k] = a;
				i++;
			} else {
				System.out.print("\n该语句不符合文法定义");
				analysisProcArear.append("\n该语句不符合文法定义");
				JOptionPane.showMessageDialog(null, messageF, "错误提示 ", JOptionPane.ERROR_MESSAGE);
				return 0;
			}
		}
		System.out.print("\n该语句不符合文法定义");
		analysisProcArear.append("\n该语句不符合文法定义");
		JOptionPane.showMessageDialog(null, messageF, "错误提示 ", JOptionPane.ERROR_MESSAGE);
		return 0;
	}

	public void out(int j, int k, char[] s,JTextArea analysisProcArear) {
		int n = 0;
		int i;
		for (i = j; i <= k; i++) {
			// char swy='m';
			temp=String.valueOf(s[i]);
			System.out.print(s[i]);
			analysisProcArear.append(temp);
			// printf("%c",swy);
			n++;
		}
		for (; n < 15; n++) {
			System.out.print(" ");
			analysisProcArear.append(" ");
		}
	}
}
