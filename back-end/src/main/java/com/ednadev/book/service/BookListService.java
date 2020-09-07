package com.ednadev.book.service;

import java.util.List;

import com.ednadev.book.domain.BookList;

public interface BookListService {
	void insertBookList(BookList bookList) throws Exception;
	List<BookList> selectBookList(String email) throws Exception;
	BookList selectBook(BookList bookList) throws Exception;
}
