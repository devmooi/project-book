package com.ednadev.book.domain;

public class IdeaNote {
	private int ideaCode, readCode;
	private String ideaCheck, ideaDesc;
	
	public int getIdeaCode() {
		return ideaCode;
	}
	public void setIdeaCode(int ideaCode) {
		this.ideaCode = ideaCode;
	}
	public int getReadCode() {
		return readCode;
	}
	public void setReadCode(int readCode) {
		this.readCode = readCode;
	}
	public String getIdeaCheck() {
		return ideaCheck;
	}
	public void setIdeaCheck(String ideaCheck) {
		this.ideaCheck = ideaCheck;
	}
	public String getIdeaDesc() {
		return ideaDesc;
	}
	public void setIdeaDesc(String ideaDesc) {
		this.ideaDesc = ideaDesc;
	}
	
	@Override
	public String toString() {
		return "IdeaNote [ideaCode=" + ideaCode + ", readCode=" + readCode + ", ideaCheck=" + ideaCheck + ", ideaDesc="
				+ ideaDesc + "]";
	}
}
