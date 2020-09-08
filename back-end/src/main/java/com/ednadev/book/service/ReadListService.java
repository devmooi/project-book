package com.ednadev.book.service;

import java.util.List;

import com.ednadev.book.domain.ReadList;

public interface ReadListService {
	void insertReadList(ReadList readList) throws Exception;
	List<ReadList> selectReadList(String email) throws Exception;
	ReadList selectCheck(ReadList readList) throws Exception;
	void updateReadList(ReadList readList) throws Exception;
	void deleteReadList(int readCode) throws Exception;
	ReadList selectDetail(int readCode) throws Exception;
}
