package io.github.erikyao;

import static org.junit.Assert.assertEquals;
import io.github.erikyao.Header;

import org.junit.Test;

public class HeaderTest {

	@Test
	public void test() {
		String rawLine = "### 1.2 Atomic Vectors";
		
		Header h = new Header(rawLine);
		
		assertEquals(h.getLevel(), 3);
		
		assertEquals(h.getText(), "1.2 Atomic Vectors");
		
		assertEquals(h.getAnchor(), "1-2-Atomic-Vectors");
		
		assertEquals(h.getTocLine(), "	- [1.2 Atomic Vectors](#1-2-Atomic-Vectors)");
		
		assertEquals(h.getAnchoredLine(), "### 1.2 Atomic Vectors <a name=\"1-2-Atomic-Vectors\"></a>");
	}
}
