package com.ednadev.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednadev.book.domain.IdeaNote;
import com.ednadev.book.repo.IdeaNoteDAO;

@Service
public class IdeaNoteServiceImpl implements IdeaNoteService {

	@Autowired
	private IdeaNoteDAO dao;

	@Override
	public void insertIdeaNote(IdeaNote ideaNote) throws Exception {
		dao.insertIdeaNote(ideaNote);
	}

	@Override
	public List<IdeaNote> selectIdeaNote(int readCode) throws Exception {
		return dao.selectIdeaNote(readCode);
	}

	@Override
	public void updateIdeaNote(IdeaNote ideaNote) throws Exception {
		dao.updateIdeaNote(ideaNote);
	}

	@Override
	public void deleteIdeaNote(int ideaCode) throws Exception {
		dao.deleteIdeaNote(ideaCode);
	}
	
	
}
