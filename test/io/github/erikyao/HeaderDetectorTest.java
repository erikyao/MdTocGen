package io.github.erikyao;

import static org.junit.Assert.assertEquals;

import io.github.erikyao.FileRecorder;
import io.github.erikyao.Header;
import io.github.erikyao.HeaderDetector;

import java.io.IOException;
import java.util.List;

import org.junit.Test;


public class HeaderDetectorTest {
	
	private static String text = "### 1.2 Atomic Vectors\r\n" + 
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
			"\r\n";

	@Test
	public void test() throws IOException {
		FileRecorder frec = new FileRecorder("tmp.txt");
		
		HeaderDetector hd = new HeaderDetector(frec);
		List<Header> result = hd.scan(text);
		
		assertEquals(result.size(), 1);
		
		Header h = result.get(0);
		
		assertEquals(h.getText(), "1.2 Atomic Vectors");
		
		assertEquals(h.getAnchor(), "1-2-Atomic-Vectors");
		
		assertEquals(h.getTocLine(), "	- [1.2 Atomic Vectors](#1-2-Atomic-Vectors)");
		
		assertEquals(h.getAnchoredLine(), "### 1.2 Atomic Vectors <a name=\"1-2-Atomic-Vectors\"></a>");
	}

}
