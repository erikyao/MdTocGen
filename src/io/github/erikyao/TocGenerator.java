package io.github.erikyao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TocGenerator {
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: [cmd] [fileName]");
			return;
		}
		
		String mdPath = args[0];
		
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(mdPath));
			String text = new String(encoded, "utf-8");
			
			FileRecorder fileRecorder = new FileRecorder(mdPath);
			HeaderDetector headerDetector = new HeaderDetector(fileRecorder);
			
			headerDetector.scan(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
