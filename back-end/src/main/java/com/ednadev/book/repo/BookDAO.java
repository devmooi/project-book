package com.ednadev.book.repo;

import com.ednadev.book.domain.Book;

public interface BookDAO {
	void insertBook(Book book) throws Exception;
}
