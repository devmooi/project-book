package com.ednadev.book.repo;

import java.util.List;

import com.ednadev.book.domain.ReadNote;

public interface ReadNoteDAO {
	void insertReadNote(ReadNote readNote) throws Exception;
	List<ReadNote> selectReadNote(int readCode) throws Exception;
	void updateReadNote(ReadNote readNote) throws Exception;
	void deleteReadNote(int noteCode) throws Exception;
}
