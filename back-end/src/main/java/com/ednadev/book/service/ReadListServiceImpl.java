package com.ednadev.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednadev.book.domain.ReadList;
import com.ednadev.book.repo.ReadListDAO;

@Service
public class ReadListServiceImpl implements ReadListService {

	@Autowired
	private ReadListDAO dao;
	
	@Override
	public void insertReadList(ReadList readList) throws Exception {
		dao.insertReadList(readList);
	}

}
