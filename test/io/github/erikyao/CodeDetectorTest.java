package io.github.erikyao;

import static org.junit.Assert.*;

import io.github.erikyao.CodeDetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CodeDetectorTest {
	
	private static String text = 
			"### 1.2 Atomic Vectors\r\n" + // line 0
			"\r\n" + 
			"`c()` means \"combine\".\r\n" + 
			"\r\n" + 
			"<pre class=\"prettyprint linenums\">\r\n" + 
			"# 你没看错，你没写小数点也默认是 double\r\n" + 
			"dbl_var &lt;- c(1, 2, 4)\r\n" + 
			"\r\n" + 
			"# With the L suffix, you get an integer rather than a double\r\n" + 
			"int_var &lt;- c(1L, 6L, 10L)\r\n" + 
			"\r\n" + 
			"# Use TRUE and FALSE (or T and F) to create logical vectors\r\n" + 
			"log_var &lt;- c(TRUE, FALSE, T, F)\r\n" + 
			"\r\n" + 
			"# string vector\r\n" + 
			"chr_var &lt;- c(\"these are\", \"some strings\")\r\n" + 
			"</pre>\r\n" + 
			"\r\n"; // line 17

	@Test
	public void test() throws IOException {
		StringReader sr = new StringReader(text);
		BufferedReader br = new BufferedReader(sr);
		
		List<Boolean> result = new ArrayList<Boolean>();
		
		String line = null;
		while((line = br.readLine()) != null) {
			result.add(CodeDetector.isCode(line));
		}
		
		// System.out.println(result);
		
		for (int i = 0; i < 4; ++i) {
			assertFalse(result.get(i));
		}
		
		for (int i = 4; i < 17; ++i) {
			assertTrue(result.get(i));
		}
		
		assertFalse(result.get(17));
	}

}
