package com.ednadev.book.service;

import java.util.List;

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

	@Override
	public List<ReadList> selectReadList(String email) throws Exception {
		return dao.selectReadList(email);
	}

	@Override
	public ReadList selectCheck(ReadList readList) throws Exception {
		return dao.selectBook(readList);
	}

	@Override
	public void updateReadList(ReadList readList) throws Exception {
		dao.updateReadList(readList);
	}

	@Override
	public void deleteReadList(int readCode) throws Exception {
		dao.deleteReadList(readCode);
	}

	@Override
	public ReadList selectDetail(int readCode) throws Exception {
		return dao.selectDetail(readCode);
	}

}
