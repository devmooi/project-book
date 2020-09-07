package com.ednadev.book.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednadev.book.domain.BookList;

@Repository
public class BookListDAOImpl implements BookListDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertBookList(BookList bookList) throws Exception {
		sqlSession.insert("ListMapper.insertList", bookList);
	}
	
	@Override
	public List<BookList> selectBookList(String email) throws Exception {
		return sqlSession.selectList("ListMapper.selectList", email);
	}
	
	@Override
	public BookList selectBook(BookList bookList) throws Exception {
		return sqlSession.selectOne("ListMapper.selectCheck", bookList);
	}

	@Override
	public void updateBookList(BookList bookList) throws Exception {
		sqlSession.update("ListMapper.updateList", bookList);
	}

	@Override
	public void deleteBookList(int listCode) throws Exception {
		sqlSession.delete("ListMapper.deleteList", listCode);
	}

}
