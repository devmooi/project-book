package com.ednadev.book.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednadev.book.domain.ReadList;

@Repository
public class ReadListDAOImpl implements ReadListDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertReadList(ReadList readList) throws Exception {
		sqlSession.insert("ReadMapper.insertRead", readList);
	}

}
