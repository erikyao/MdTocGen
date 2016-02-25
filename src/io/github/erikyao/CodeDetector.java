package io.github.erikyao;


public class CodeDetector {
	
	private static final String CODE_START = "<pre class=\"prettyprint linenums\">";
	private static final String CODE_END = "</pre>";
	
	private static boolean isCode = false;

	public static boolean isCode() {
		return isCode;
	}
	
	public static boolean isCode(String line) {
		if (isCode == false) {
			if (line.startsWith(CODE_START)) {
				isCode = true;
			}
			return isCode;
		} else { // now isCode == true
			if (line.startsWith(CODE_END)) {
				isCode = false; // ��Ȼ�������� false�����ǵ�ǰ����Ȼ�� code
								// ��� false ���´ν���ʱ��������
			}
			return true;
		}
	}
}
