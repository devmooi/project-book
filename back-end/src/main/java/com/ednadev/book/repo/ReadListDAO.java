package com.ednadev.book.repo;

import java.util.List;

import com.ednadev.book.domain.ReadList;

public interface ReadListDAO {
	void insertReadList(ReadList readList) throws Exception;
	List<ReadList> selectReadList(String email) throws Exception;
	ReadList selectBook(ReadList readList) throws Exception;
	void updateReadList(ReadList readList) throws Exception;
	void deleteReadList(int readCode) throws Exception;
	ReadList selectDetail(ReadList readList) throws Exception;
}
