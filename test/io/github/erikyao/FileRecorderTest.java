package io.github.erikyao;

import static org.junit.Assert.*;
import io.github.erikyao.FileRecorder;

import org.junit.Test;

public class FileRecorderTest {

	@Test
	public void test() {
		FileRecorder frec = new FileRecorder("./2015-07-08-digest-of-advanced-r.md");
		
		assertNotNull(frec.getAnchoredMdWriter());
		assertNotNull(frec.getTocWriter());
		
		System.out.println(frec.getAnchoredMdPath());
		System.out.println(frec.getTocPath());
	}

}
