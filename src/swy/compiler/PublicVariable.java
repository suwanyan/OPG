package swy.compiler;

public class PublicVariable {
	static String path_temp;// ��ȡ��ǰ�洢·��
	static String input;//�����ַ�������
	static char[][] analysisTable = new char[20][20]; // ������ȹ�ϵ
	static char[] terminal = new char[20]; // �ķ��ռ�����
	static char[][] string = new char[20][10]; // �������봮�ķ���
	static int grammerNum; // �ķ��������
	static int r1;
	static char[][] gatmmer; // �����洢�ķ�����
	public static char[][] getAnalysisTable() {
		return analysisTable;
	}

	public static void setAnalysisTable(char[][] analysisTable) {
		PublicVariable.analysisTable = analysisTable;
	}

	public static char[] getTerminal() {
		return terminal;
	}

	public static void setTerminal(char[] terminal) {
		PublicVariable.terminal = terminal;
	}

	public static char[][] getString() {
		return string;
	}

	public static void setString(char[][] string) {
		PublicVariable.string = string;
	}

	public static int getGrammerNum() {
		return grammerNum;
	}

	public static void setGrammerNum(int grammerNum) {
		PublicVariable.grammerNum = grammerNum;
	}

	public static int getR1() {
		return r1;
	}

	public static void setR1(int r1) {
		PublicVariable.r1 = r1;
	}

	public static char[][] getGatmmer() {
		return gatmmer;
	}

	public static void setGatmmer(char[][] gatmmer) {
		PublicVariable.gatmmer = gatmmer;
	}

	public static char[][] getFirst() {
		return first;
	}

	public static void setFirst(char[][] first) {
		PublicVariable.first = first;
	}

	public static char[][] getLast() {
		return last;
	}

	public static void setLast(char[][] last) {
		PublicVariable.last = last;
	}

	public static int[] getFflag() {
		return fflag;
	}

	public static void setFflag(int[] fflag) {
		PublicVariable.fflag = fflag;
	}

	public static int[] getLflag() {
		return lflag;
	}

	public static void setLflag(int[] lflag) {
		PublicVariable.lflag = lflag;
	}

	static char[][] first = new char[10][10]; // �ķ����ս��FIRSTVT��
	static char[][] last = new char[10][10]; // �ķ����ս��LASTVT��
	static int[] fflag = new int[10]; // ��־��i�����ս����FIRSTVT���Ƿ������
	static int[] lflag = new int[10]; // ��־��i�����ս����LASTVT���Ƿ������
	public static String getInput() {
		return input;
	}

	public static void setInput(String input) {
		PublicVariable.input = input;
	}

	public  String getPath_temp() {
		return path_temp;
	}

	public final void setPath_temp(String path_temp) {
		this.path_temp = path_temp;
	}
}
