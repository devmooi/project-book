package com.ednadev.book.repo;

import java.util.List;

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

	@Override
	public List<ReadList> selectReadList(String email) throws Exception {
		return sqlSession.selectList("ReadMapper.selectRead", email);
	}

	@Override
	public ReadList selectBook(ReadList readList) throws Exception {
		return sqlSession.selectOne("ReadMapper.selectCheck", readList);
	}

	@Override
	public void updateReadList(ReadList readList) throws Exception {
		sqlSession.update("ReadMapper.updateRead", readList);
	}

	@Override
	public void deleteReadList(int readCode) throws Exception {
		sqlSession.delete("ReadMapper.deleteRead", readCode);
	}

	@Override
	public ReadList selectDetail(int readCode) throws Exception {
		return sqlSession.selectOne("ReadMapper.selectDetail", readCode);
	}

}
