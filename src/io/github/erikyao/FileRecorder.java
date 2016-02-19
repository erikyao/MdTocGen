package io.github.erikyao;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;


public class FileRecorder {
	// private String outputPath;
	// private List<String> content;
	
	private String anchoredMdPath;
	private String tocPath;
	
	private Writer anchoredMdWriter;
	private Writer tocWriter;
	
	public FileRecorder(String mdPath) {
		super();
		// this.outputPath = outputPath;
		// this.content = new ArrayList<String>();
		genOutputFilePaths(mdPath);
		genOutputFileWriters();
	}

	private void genOutputFileWriters() {
		try {
			anchoredMdWriter = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream(anchoredMdPath), "utf-8"));
			tocWriter = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream(tocPath), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void genOutputFilePaths(String mdPath) {
		if (mdPath.endsWith(".md")) {
			String mdName = mdPath.substring(0, mdPath.length() - 3);
			anchoredMdPath = mdName + ".anc.md";
			tocPath = mdName + ".toc.md";
		} else {
			anchoredMdPath = mdPath + ".anc.md";
			tocPath = mdPath + ".toc.md";
		}
	}

	public void recordLine(String line) {
		try {
			anchoredMdWriter.write(line);
			anchoredMdWriter.write("\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void recordToc(String tocLine) {
		try {
			tocWriter.write(tocLine);
			tocWriter.write("\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		try {
			anchoredMdWriter.close();
			tocWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Writer getAnchoredMdWriter() {
		return anchoredMdWriter;
	}

	public Writer getTocWriter() {
		return tocWriter;
	}

	public String getAnchoredMdPath() {
		return anchoredMdPath;
	}

	public String getTocPath() {
		return tocPath;
	}
}
