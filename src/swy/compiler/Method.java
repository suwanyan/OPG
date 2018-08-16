package swy.compiler;
public class Method {
	PublicVariable publicPath=new PublicVariable();
	char[] terminal =publicPath.getTerminal();
	public boolean zhongjie(char c) // 判断字符c是否是终极符
	{
		
		int i;
		for (i = 0; terminal[i] != '\0'; i++) {
			if (c == terminal[i])
				return true;
		}
		return false;
	}
	public int xiabiao(char c) // 求字符c在算符优先关系表中的下标
	{
		int i;
		for (i = 0; terminal[i] != '\0'; i++) {
			if (c == terminal[i])
				return i;
		}
		return -1;
	}
}
