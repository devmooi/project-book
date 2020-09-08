package com.ednadev.book.repo;

import java.util.List;

import com.ednadev.book.domain.IdeaNote;

public interface IdeaNoteDAO {
	void insertIdeaNote(IdeaNote ideaNote) throws Exception;
	List<IdeaNote> selectIdeaNote(int readCode) throws Exception;
	void updateIdeaNote(IdeaNote ideaNote) throws Exception;
	void deleteIdeaNote(int ideaCode) throws Exception;
}
