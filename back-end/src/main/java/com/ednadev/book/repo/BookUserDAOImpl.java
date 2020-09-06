package com.ednadev.book.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednadev.book.domain.BookUser;

@Repository
public class BookUserDAOImpl implements BookUserDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertBookUser(BookUser bookUser) throws Exception {
		sqlSession.insert("UserMapper.insertUser", bookUser);
	}

	@Override
	public BookUser login(BookUser bookUser) throws Exception {
		return sqlSession.selectOne("UserMapper.login", bookUser);
	}

	@Override
	public BookUser selectBookUser(String email) throws Exception {
		return sqlSession.selectOne("UserMapper.selectUser", email);
	}

}
