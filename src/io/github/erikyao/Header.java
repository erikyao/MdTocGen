package io.github.erikyao;

import org.apache.commons.lang3.StringUtils;

public class Header {
	public static final String SIGN = "#";
	
	// 原始数据
	// private String rawLine; // 完整的 1 行，比如 "### 1.2 Atomic Vectors"
	
	// 通过 rawLine 解析出的数据
	private int level; // # 的个数，比如 3
	private String text; // 除去 # 的部分，不包括空格，包括小节数，比如 "1.2 Atomic Vectors"
	private String anchor; // 比如 "1-2-Atomic-Vectors"
	
	// 生成 toc 时需要用到的数据
	private String tocLine; // 比如 "	- [1.2 Atomic Vectors](#1-2-Atomic-Vectors)"
							// text + anchor 构成文本；level 负责缩进
	private String anchoredLine; // 比如 "### 1.2 Atomic Vectors <a name="1-2-Atomic-Vectors"></a>"
							// rawLine + anchor 构成文本
	
	public Header(String rawLine) {
		super();
	
		parse(rawLine.trim());
	}

	private void parse(String rawLine) {
		this.level = StringUtils.countMatches(rawLine, Header.SIGN);
		this.text = rawLine.substring(level + 1); // ### plus 1 space
		
		this.anchor = this.text.replaceAll("[\\[\\]\\(\\)\\{\\},:$#?`…]", "").replaceAll("[ ._/]", "-");
		
		if (level > 2) {
			this.tocLine = StringUtils.repeat("\t", level - 2);
		} else {
			this.tocLine = "";
		}
		this.tocLine = tocLine + "- [" + text + "](#" + anchor + ")";
		
		this.anchoredLine = rawLine + " <a name=\"" + anchor + "\"></a>";
	}
		 
	public int getLevel() {
		return level;
	}
	
	public String getText() {
		return text;
	}
	
	public String getAnchor() {
		return anchor;
	}
	
	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}
	
	public String getTocLine() {
		return tocLine;
	}

	public void setTocLine(String tocLine) {
		this.tocLine = tocLine;
	}
	
	public String getAnchoredLine() {
		return anchoredLine;
	}

	public void setAnchoredLine(String anchoredLine) {
		this.anchoredLine = anchoredLine;
	}

}
