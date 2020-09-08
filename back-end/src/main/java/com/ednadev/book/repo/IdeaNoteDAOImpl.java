package com.ednadev.book.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednadev.book.domain.IdeaNote;

@Repository
public class IdeaNoteDAOImpl implements IdeaNoteDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertIdeaNote(IdeaNote ideaNote) throws Exception {
		sqlSession.insert("IdeaMapper.insertIdea", ideaNote);
	}

	@Override
	public List<IdeaNote> selectIdeaNote(int readCode) throws Exception {
		return sqlSession.selectList("IdeaMapper.selectIdea", readCode);
	}

	@Override
	public void updateIdeaNote(IdeaNote ideaNote) throws Exception {
		sqlSession.update("IdeaMapper.updateIdea", ideaNote);
	}

	@Override
	public void deleteIdeaNote(int ideaCode) throws Exception {
		sqlSession.delete("IdeaMapper.deleteIdea", ideaCode);
	}
}
