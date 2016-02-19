package io.github.erikyao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class HeaderDetector {
	
	private FileRecorder frec;

	public HeaderDetector(FileRecorder frec) {
		super();
		this.frec = frec;
	}

	public List<Header> scan(String text) throws IOException {
		CodeDetector cd = new CodeDetector();
		List<Header> result = new LinkedList<Header>();
		
		StringReader sr = new StringReader(text);
		BufferedReader br = new BufferedReader(sr);
		
		String line = null;
		Header h = null;
		while((line = br.readLine()) != null) {
			if (cd.isCode(line) || !line.startsWith(Header.SIGN)) {
				frec.recordLine(line);
				continue;
			} else { // now line is a header!
				h = new Header(line);
				frec.recordLine(h.getAnchoredLine());
				frec.recordToc(h.getTocLine());
				result.add(h);
			}
		}
		
		frec.shutdown();
		
		return result;
	}
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: [cmd] [fileName]");
			return;
		}
		
		String mdPath = args[0];
		
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(mdPath));
			String text = new String(encoded, "utf-8");
			
			FileRecorder frec = new FileRecorder(mdPath);
			HeaderDetector hd = new HeaderDetector(frec);
			
			hd.scan(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
