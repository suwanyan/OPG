package swy.compiler;
public class Method {
	PublicVariable publicPath=new PublicVariable();
	char[] terminal =publicPath.getTerminal();
	public boolean zhongjie(char c) // �ж��ַ�c�Ƿ����ռ���
	{
		
		int i;
		for (i = 0; terminal[i] != '\0'; i++) {
			if (c == terminal[i])
				return true;
		}
		return false;
	}
	public int xiabiao(char c) // ���ַ�c��������ȹ�ϵ���е��±�
	{
		int i;
		for (i = 0; terminal[i] != '\0'; i++) {
			if (c == terminal[i])
				return i;
		}
		return -1;
	}
}
