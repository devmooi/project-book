package com.ednadev.book.service;

import java.util.List;

import com.ednadev.book.domain.ReadNote;

public interface ReadNoteService {
	void insertReadNote(ReadNote readNote) throws Exception;
	List<ReadNote> selectReadNote(int readCode) throws Exception;
	void updateReadNote(ReadNote readNote) throws Exception;
	void deleteReadNote(int noteCode) throws Exception;
}
