package com.ednadev.book.domain;

public class ReadList {
	private int readCode;
	private String readDate, readCheck, userEmail, bookIsbn;
	private Book book;
	
	public int getReadCode() {
		return readCode;
	}
	public void setReadCode(int readCode) {
		this.readCode = readCode;
	}
	public String getReadDate() {
		return readDate;
	}
	public void setReadDate(String readDate) {
		this.readDate = readDate;
	}
	public String getReadCheck() {
		return readCheck;
	}
	public void setReadCheck(String readCheck) {
		this.readCheck = readCheck;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	@Override
	public String toString() {
		return "ReadList [readCode=" + readCode + ", readDate=" + readDate + ", readCheck=" + readCheck + ", userEmail="
				+ userEmail + ", bookIsbn=" + bookIsbn + ", book=" + book + "]";
	}
}
