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

	@Override
	public BookUser login(BookUser bookUser) throws Exception {
		return dao.login(bookUser);
	}

	@Override
	public BookUser selectBookUser(String email) throws Exception {
		return dao.selectBookUser(email);
	}

}
