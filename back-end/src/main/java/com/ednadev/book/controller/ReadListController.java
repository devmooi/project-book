package com.ednadev.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		ReadList book = service.selectCheck(readList);
		if(book!=null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		service.insertReadList(readList);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("readList/{email}")
	public ResponseEntity selectReadList(@PathVariable String email) throws Exception {
		List<ReadList> list = service.selectReadList(email);
		if(list==null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PutMapping("readList")
	public ResponseEntity updateReadList(@RequestBody ReadList readList) throws Exception {
		service.updateReadList(readList);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("readList/{readCode}")
	public ResponseEntity deleteReadList(@PathVariable int readCode) throws Exception {
		service.deleteReadList(readCode);
		return new ResponseEntity(HttpStatus.OK);
	}
}
