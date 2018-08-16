package swy.compiler;



import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextArea;

import swy.GUI.AnalysisGUI;
import swy.operate.fileOperate;

public class Analysis {
	Method method;
	IsLegalSentence isLegalSentence;
	AnalysisGUI analysisGUI=new AnalysisGUI();
	PublicVariable publicPath=new PublicVariable();
	String path_temp1=publicPath.getPath_temp();
	fileOperate folderFile = new fileOperate();// 文件处理
	char[][] analysisTable = new char[20][20]; // 算符优先关系
	char[] terminal = new char[20]; // 文法终极符集
	char[][] string = new char[20][10]; // 用于输入串的分析
	int grammerNum; // 文法规则个数
	int r1;
	String[] grammer_temp ; // 用来存储文法规则
	char[][] gatmmer; // 用来存储文法规则
	char[][] first = new char[10][10]; // 文法非终结符FIRSTVT集
	char[][] last = new char[10][10]; // 文法非终结符LASTVT集
	int[] fflag = new int[10]; // 标志第i个非终结符的FIRSTVT集是否已求出
	int[] lflag = new int[10]; // 标志第i个非终结符的LASTVT集是否已求出
	Scanner in = new Scanner(System.in);
	
	public void initGrammer(String gt,JTextArea errorArear) throws IOException{//从文本框获取文法进行处理
		grammer_temp = gt.split("\n");// 按照换行符号存储每条文法
		grammerNum= grammer_temp.length;// 获取文法规则数
		publicPath.setGrammerNum(grammerNum);
		gatmmer = new char[grammerNum][30]; // 用来存储文法规则
		for (int w = 0; w < grammerNum; w++) {
			char[] grammer_temp2=new char[grammer_temp[w].length()];
			grammer_temp2 = grammer_temp[w].toCharArray();// 字符串转换成字符数组
			for(int h=0;h<grammer_temp2.length;h++){
				gatmmer[w][h]=grammer_temp2[h];
			}
			gatmmer[w][grammer_temp2.length+1]='\0';
			publicPath.setGatmmer(gatmmer);
		}
		if(isOPG(gatmmer,errorArear)){//算符合法，往下 
			main();
			publicPath.setAnalysisTable(analysisTable);
		}
		
	}
	public void main() throws IOException {
		
		
		int i, j, k = 0;
		
		for (i = 0; i < grammerNum; i++) {
			for(j=0;gatmmer[i][j]!='\0';j++) {
				if ((gatmmer[i][j] < 'A' || gatmmer[i][j] > 'Z') && gatmmer[i][j] != '-' && gatmmer[i][j] != '>' && gatmmer[i][j] != '|')
					terminal[k++] = gatmmer[i][j];// 终结符
			}
		}
		terminal[k] = '#';
		terminal[k + 1] = '\0';
		publicPath.setTerminal(terminal);
		method=new Method();
		firstLast();//分析并且输出first last集
		table();//分析表
		String fileName_table=new String("/analysisTable.txt");//分析表写入文件
		folderFile.arrtoFile(analysisTable,terminal, k, path_temp1, fileName_table);
		
		
		
		analysisGUI.a();
		
	}
	public void firstLast(){
		for (int i = 0; i < 10; i++) {
			fflag[i] = 0;
		}
		for (int i = 0; i < 10; i++) {
			lflag[i] = 0;
		}
		for (int i = 0; i < grammerNum; i++) {
			first[i][0] = 0; 
			/*first[i][0]和last[i][0]分别表示st[i][0]非终极
			* 符的FIRSTVT集和LASTVT集中元素的个数*/
			last[i][0] = 0;
		}
		for (int i = 0; i < grammerNum; i++) {
			firstvt(gatmmer[i][0]);
			lastvt(gatmmer[i][0]);
		}
		publicPath.setFirst(first);
		publicPath.setLast(last);
		try {//first写入文件
			String fileName_first=new String("/first.txt");
			folderFile.arrtoFile(gatmmer,first, grammerNum, path_temp1, fileName_first);
			String fileName_last=new String("/last.txt");
			folderFile.arrtoFile(gatmmer,first, grammerNum, path_temp1, fileName_last);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("first 文件");
		}
		
		
	}
	public boolean isOPG(char[][] st,JTextArea errorArear) {//判断文法是否合法
		int i, j;
		boolean ifOPG=true;
		String message;
		int ik;
		for(i=0;i<grammerNum;i++)                           
		{
			if(st[i][0]<'A'||st[i][0]>'Z')
			{
				ik=i+1;
				message=new String("第"+ik+"条产生式左侧不是非终极符，不是算符文法!\n");
				System.out.print(message);
				errorArear.append(message);
				ifOPG=false;
			}
			for(j=0;st[i][j]!='\0';j++)
			{
				
				if(st[i][j]>='A'&&st[i][j]<='Z')
				{
				    if(st[i][j+1]>='A'&&st[i][j+1]<='Z')
					{
				    	ik=i+1;
				    	message=new String("第"+ik+"条产生式存在两个非终结符相邻，不是算符文法!\n");
				    	errorArear.append(message);
				    	System.out.print(message);
				    	ifOPG=false;
					}
				}
			}
		}
		return ifOPG;
	}


	public void table() {//计算分析表
		char[][] text = new char[20][10];
		int i, j, k, t, l, x = 0, y = 0;
		int m, n;
		x = 0;
		
		for(i=0;i<grammerNum;i++)
		{
			text[x][y]=gatmmer[i][0];
			y++;
			for(j=1;gatmmer[i][j]!='\0';j++)
			{
				if(gatmmer[i][j]=='|')
				{       
					text[x][y]='\0';
					x++;
					y=0;
					text[x][y]=gatmmer[i][0];
					y++;
					text[x][y++]='-';
					text[x][y++]='>';
				}
				else
				{
					text[x][y]=gatmmer[i][j];
					y++;
				}
			}
			text[x][y]='\0';
			x++;
			y=0;
		}
		r1=x;
		publicPath.setR1(r1);
		System.out.print("转化后的文法为:\n");
		for (i = 0; i < x; i++) // 输出转化后的文法规则串
		{
			for(int w=0;text[i][w]!='\0';w++){
			System.out.print(text[i][w]);
			}
			System.out.print("\n");
		}
		for (i = 0; i < x; i++) /*
								 * 求每个终结符的推导结果(去掉"->" 后的转化文法，用于最后的规约)
								 */
		{
			string[i][0] = text[i][0];
			for (j = 3, l = 1; text[i][j] != '\0'; j++, l++)
				string[i][l] = text[i][j];
			string[i][l] = '\0';
		}
		publicPath.setString(string);
		for (i = 0; i < x; i++) {
			for (j = 1; text[i][j + 1] != '\0'; j++) {
				if (method.zhongjie(text[i][j]) && method.zhongjie(text[i][j + 1])) {
					m = method.xiabiao(text[i][j]);
					n = method.xiabiao(text[i][j + 1]);
					analysisTable[m][n] = '=';
				}
				if (text[i][j + 2] != '\0' && method.zhongjie(text[i][j]) && method.zhongjie(text[i][j + 2])
						&& !method.zhongjie(text[i][j + 1])) {
					m =method.xiabiao(text[i][j]);
					n = method.xiabiao(text[i][j + 2]);
					analysisTable[m][n] = '=';
				}
				if (method.zhongjie(text[i][j]) && !method.zhongjie(text[i][j + 1])) {
					for (k = 0; k < grammerNum; k++) {
						if (gatmmer[k][0] == text[i][j + 1])
							break;
					}
					m = method.xiabiao(text[i][j]);
					for (t = 0; t < first[k][0]; t++) {
						n = method.xiabiao(first[k][t + 1]);
						analysisTable[m][n] = '<';
					}
				}
				if (!method.zhongjie(text[i][j]) && method.zhongjie(text[i][j + 1])) {
					for (k = 0; k < grammerNum; k++) {
						if (gatmmer[k][0] == text[i][j])
							break;
					}
					n = method.xiabiao(text[i][j + 1]);
					for (t = 0; t < last[k][0]; t++) {
						m = method.xiabiao(last[k][t + 1]);
						analysisTable[m][n] = '>';
					}
				}
			}
		}
		m = method.xiabiao('#');
		for (t = 0; t < first[0][0]; t++) {
			n = method.xiabiao(first[0][t + 1]);
			analysisTable[m][n] = '<';
		}
		n = method.xiabiao('#');
		for (t = 0; t < last[0][0]; t++) {
			m = method.xiabiao(last[0][t + 1]);
			analysisTable[m][n] = '>';
		}
		analysisTable[n][n] = '=';
	}

	public void firstvt(char c) // 求FIRSTVT集
	{
		int i, j, k, m, n;
		for (i = 0; i < grammerNum; i++) {//找到要求的非终结符
			if (gatmmer[i][0] == c)
				break;
		}
		if (fflag[i] == 0) { //标志第i个非终结符的FIRSTVT集是否已求出
			n = first[i][0] + 1;//first[i][0]表示st[i][0]非终极符的FIRSTVT集中元素的个数
			m = 0;
			do
			{
				if(m==2||gatmmer[i][m]=='|')
				{
					if(method.zhongjie(gatmmer[i][m+1]))
					{
						first[i][n]=gatmmer[i][m+1];
						n++;
					}
					else
					{
						if(method.zhongjie(gatmmer[i][m+2]))
						{
							first[i][n]=gatmmer[i][m+2];
							n++;
						}
						if(gatmmer[i][m+1]!=c)
						{
							firstvt(gatmmer[i][m+1]);
							for(j=0;j<grammerNum;j++)
							{
								if(gatmmer[j][0]==gatmmer[i][m+1])
									break;
							}
							for(k=0;k<first[j][0];k++)
							{
								int t;
								for(t=0;t<n;t++)
								{
									if(first[i][t]==first[j][k+1])
										break;
								}
								if(t==n)
								{
									first[i][n]=first[j][k+1];
									n++;
								}
							}
						}
					}
				}
				m++;
			}while(gatmmer[i][m]!='\0');
			first[i][n] = '\0';
			first[i][0] = (char) --n;
			fflag[i] = 1;
		}
	}

	public void lastvt(char c) // 求LASTVT集
	{
		int i,j,k,m,n;
		for(i=0;i<grammerNum;i++)
		{
			if(gatmmer[i][0]==c)
				break;
		}
		if(lflag[i]==0)
		{
			n=last[i][0]+1;
			m=0;
			do
			{
				if(gatmmer[i][m+1]=='\0'||gatmmer[i][m+1]=='|')
				{
					if(method.zhongjie(gatmmer[i][m]))
					{
						last[i][n]=gatmmer[i][m];
						n++;
					}
					else
					{
						if(m>0&&method.zhongjie(gatmmer[i][m-1]))
						{
							last[i][n]=gatmmer[i][m-1];
							n++;
						}
						if(gatmmer[i][m]!=c)
						{
							lastvt(gatmmer[i][m]);
							for(j=0;j<grammerNum;j++)
							{
								if(gatmmer[j][0]==gatmmer[i][m])
									break;
							}
							for(k=0;k<last[j][0];k++)
							{
								int t;
								for(t=0;t<n;t++)
								{
									if(last[i][t]==last[j][k+1])
										break;
								}
								if(t==n)
								{
									last[i][n]=last[j][k+1];
									n++;
								}
							}
						}
					}
				}
				m++;
			}while(gatmmer[i][m]!='\0');
			last[i][n]='\0';
			last[i][0]=(char) --n;
			lflag[i]=1;
		}
	}

	

	

}
