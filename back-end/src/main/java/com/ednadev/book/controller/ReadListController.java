package com.ednadev.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ednadev.book.domain.ReadList;
import com.ednadev.book.service.ReadListService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class ReadListController {
	
	@Autowired
	private ReadListService service;
	
	@PostMapping("readList")
	public ResponseEntity insertReadList(@RequestBody ReadList readList) throws Exception {
		service.insertReadList(readList);
		return new ResponseEntity(HttpStatus.OK);
	}
}
