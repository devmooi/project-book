package com.ednadev.book.domain;

public class Book {
	private String bookIsbn, bookImg, bookTitle, bookAuthor, bookPublisher, bookPubdate, bookDesc;

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookPubdate() {
		return bookPubdate;
	}

	public void setBookPubdate(String bookPubdate) {
		this.bookPubdate = bookPubdate;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	@Override
	public String toString() {
		return "Book [bookIsbn=" + bookIsbn + ", bookImg=" + bookImg + ", bookTitle=" + bookTitle + ", bookAuthor="
				+ bookAuthor + ", bookPublisher=" + bookPublisher + ", bookPubdate=" + bookPubdate + ", bookDesc="
				+ bookDesc + "]";
	}
	
}
