package com.ednadev.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednadev.book.domain.BookList;
import com.ednadev.book.repo.BookListDAO;

@Service
public class BookListServiceImpl implements BookListService {
	
	@Autowired
	private BookListDAO dao;

	@Override
	public void insertBookList(BookList bookList) throws Exception {
		dao.insertBookList(bookList);
	}

	@Override
	public List<BookList> selectBookList(String email) throws Exception {
		return dao.selectBookList(email);
	}
}
