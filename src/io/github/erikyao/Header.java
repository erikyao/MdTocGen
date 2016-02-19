package io.github.erikyao;

import org.apache.commons.lang3.StringUtils;

public class Header {
	public static final String SIGN = "#";
	
	// ԭʼ����
	// private String rawLine; // ������ 1 �У����� "### 1.2 Atomic Vectors"
	
	// ͨ�� rawLine ������������
	private int level; // # �ĸ��������� 3
	private String text; // ��ȥ # �Ĳ��֣��������ո񣬰���С���������� "1.2 Atomic Vectors"
	private String anchor; // ���� "1-2-Atomic-Vectors"
	
	// ���� toc ʱ��Ҫ�õ�������
	private String tocLine; // ���� "	- [1.2 Atomic Vectors](#1-2-Atomic-Vectors)"
							// text + anchor �����ı���level ��������
	private String anchoredLine; // ���� "### 1.2 Atomic Vectors <a name="1-2-Atomic-Vectors"></a>"
							// rawLine + anchor �����ı�
	
	public Header(String rawLine) {
		super();
	
		parse(rawLine.trim());
	}

	private void parse(String rawLine) {
		this.level = StringUtils.countMatches(rawLine, Header.SIGN);
		this.text = rawLine.substring(level + 1); // ### plus 1 space
		
		this.anchor = this.text.replaceAll("[\\[\\]\\(\\)\\{\\},:$#?`��]", "").replaceAll("[ ._/]", "-");
		
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
