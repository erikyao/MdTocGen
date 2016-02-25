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
				isCode = false; // 虽然这里置了 false，但是当前行仍然是 code
								// 这个 false 在下次进入时才起作用
			}
			return true;
		}
	}
}
