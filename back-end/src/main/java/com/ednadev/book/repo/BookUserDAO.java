package com.ednadev.book.repo;

import com.ednadev.book.domain.BookUser;

public interface BookUserDAO {
	void insertBookUser(BookUser bookUser) throws Exception;
	BookUser login(BookUser bookUser) throws Exception;
	BookUser selectBookUser(String email) throws Exception;
	void updateBookUser(BookUser bookUser) throws Exception;
}
