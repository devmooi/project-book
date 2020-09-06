package com.ednadev.book.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednadev.book.domain.Book;

@Repository
public class BookDAOImpl implements BookDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertBook(Book book) throws Exception {
		sqlSession.insert("BookMapper.insertBook", book);
	}

}
