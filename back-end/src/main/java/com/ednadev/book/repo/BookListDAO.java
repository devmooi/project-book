package com.ednadev.book.repo;

import java.util.List;

import com.ednadev.book.domain.BookList;

public interface BookListDAO {
	void insertBookList(BookList bookList) throws Exception;
	List<BookList> selectBookList(String email) throws Exception;
	BookList selectBook(BookList bookList) throws Exception;
	void updateBookList(BookList bookList) throws Exception;
}
