package com.ednadev.book.service;

import com.ednadev.book.domain.BookUser;

public interface BookUserService {
	void insertBookUser(BookUser bookUser) throws Exception;
	BookUser login(BookUser bookUser) throws Exception;
	BookUser selectBookUser(String email) throws Exception;
}
