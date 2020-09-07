package com.ednadev.book.domain;

public class BookList {
	private int listCode;
	private String listDate, userEmail, bookIsbn;
	private Book book;
	
	public int getListCode() {
		return listCode;
	}
	public void setListCode(int listCode) {
		this.listCode = listCode;
	}
	public String getListDate() {
		return listDate;
	}
	public void setListDate(String listDate) {
		this.listDate = listDate;
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
		return "BookList [listCode=" + listCode + ", listDate=" + listDate + ", userEmail=" + userEmail + ", bookIsbn="
				+ bookIsbn + ", book=" + book + "]";
	}
	
}
