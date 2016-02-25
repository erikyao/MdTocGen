package io.github.erikyao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

public class HeaderDetector {
	
	private FileRecorder fileRecoder;
	
	public HeaderDetector(FileRecorder fileRecoder) {
		super();
		this.fileRecoder = fileRecoder;
	}

	public List<Header> scan(String text) throws IOException {
		List<Header> result = new LinkedList<Header>();
		
		StringReader sr = new StringReader(text);
		BufferedReader br = new BufferedReader(sr);
		
		String line = null;
		Header h = null;
		while((line = br.readLine()) != null) {
			if (CodeDetector.isCode(line) || !line.startsWith(Header.SIGN)) {
				fileRecoder.recordLine(line);
				continue;
			} else { // now line is a header!
				h = new Header(line);
				fileRecoder.recordLine(h.getAnchoredLine());
				fileRecoder.recordToc(h.getTocLine());
				result.add(h);
			}
		}
		
		fileRecoder.shutdown();
		
		return result;
	}
}
