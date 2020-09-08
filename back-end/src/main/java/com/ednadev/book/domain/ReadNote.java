package com.ednadev.book.domain;

public class ReadNote {
	private int noteCode, readCode;
	private String notePage, noteDesc;
	
	public int getNoteCode() {
		return noteCode;
	}
	public void setNoteCode(int noteCode) {
		this.noteCode = noteCode;
	}
	public int getReadCode() {
		return readCode;
	}
	public void setReadCode(int readCode) {
		this.readCode = readCode;
	}
	public String getNotePage() {
		return notePage;
	}
	public void setNotePage(String notePage) {
		this.notePage = notePage;
	}
	public String getNoteDesc() {
		return noteDesc;
	}
	public void setNoteDesc(String noteDesc) {
		this.noteDesc = noteDesc;
	}
	
	@Override
	public String toString() {
		return "ReadNote [noteCode=" + noteCode + ", readCode=" + readCode + ", notePage=" + notePage + ", noteDesc="
				+ noteDesc + "]";
	}
}
