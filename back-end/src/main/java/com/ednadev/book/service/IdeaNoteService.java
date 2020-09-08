package com.ednadev.book.service;

import java.util.List;

import com.ednadev.book.domain.IdeaNote;

public interface IdeaNoteService {
	void insertIdeaNote(IdeaNote ideaNote) throws Exception;
	List<IdeaNote> selectIdeaNote(int readCode) throws Exception;
	void updateIdeaNote(IdeaNote ideaNote) throws Exception;
	void deleteIdeaNote(int ideaCode) throws Exception;
}
