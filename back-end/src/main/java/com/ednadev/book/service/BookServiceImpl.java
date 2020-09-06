package com.ednadev.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednadev.book.domain.Book;
import com.ednadev.book.repo.BookDAO;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO dao;
	
	@Override
	public void insertBook(Book book) throws Exception {
		dao.insertBook(book);
	}

}
