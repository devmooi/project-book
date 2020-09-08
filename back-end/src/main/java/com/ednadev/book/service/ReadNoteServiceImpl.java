package com.ednadev.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednadev.book.domain.ReadNote;
import com.ednadev.book.repo.ReadNoteDAO;

@Service
public class ReadNoteServiceImpl implements ReadNoteService {
	
	@Autowired
	private ReadNoteDAO dao;

	@Override
	public void insertReadNote(ReadNote readNote) throws Exception {
		dao.insertReadNote(readNote);
	}

	@Override
	public List<ReadNote> selectReadNote(int readCode) throws Exception {
		return dao.selectReadNote(readCode);
	}

	@Override
	public void updateReadNote(ReadNote readNote) throws Exception {
		dao.updateReadNote(readNote);
	}

	@Override
	public void deleteReadNote(int noteCode) throws Exception {
		dao.deleteReadNote(noteCode);
	}

}
