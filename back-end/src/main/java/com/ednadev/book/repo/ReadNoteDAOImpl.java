package com.ednadev.book.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ednadev.book.domain.ReadNote;

@Repository
public class ReadNoteDAOImpl implements ReadNoteDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertReadNote(ReadNote readNote) throws Exception {
		sqlSession.insert("NoteMapper.insertNote", readNote);
	}

	@Override
	public List<ReadNote> selectReadNote(int readCode) throws Exception {
		return sqlSession.selectList("NoteMapper.selectNote", readCode);
	}

	@Override
	public void updateReadNote(ReadNote readNote) throws Exception {
		sqlSession.update("NoteMapper.updateNote", readNote);
	}

	@Override
	public void deleteReadNote(int noteCode) throws Exception {
		sqlSession.delete("NoteMapper.deleteNote", noteCode);
	}

}
