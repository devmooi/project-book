package com.ednadev.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednadev.book.domain.BookUser;
import com.ednadev.book.repo.BookUserDAO;

@Service
public class BookUserServiceImpl implements BookUserService {
	
	@Autowired
	private BookUserDAO dao;

	@Override
	public void insertBookUser(BookUser bookUser) throws Exception {
		dao.insertBookUser(bookUser);
	}

}
