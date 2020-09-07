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

import com.ednadev.book.domain.BookList;
import com.ednadev.book.service.BookListService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class BookListController {
	
	@Autowired
	private BookListService service;
	
	@PostMapping("bookList")
	public ResponseEntity insertBookList(@RequestBody BookList bookList) throws Exception {
		BookList book = service.selectBook(bookList);
		if(book!=null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		service.insertBookList(bookList);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("bookList/{email}")
	public ResponseEntity selectBookList(@PathVariable String email) throws Exception {
		List<BookList> list = service.selectBookList(email);
		if(list==null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PutMapping("bookList")
	public ResponseEntity updateBookList(@RequestBody BookList bookList) throws Exception {
		service.updateBookList(bookList);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("bookList/{listCode}")
	public ResponseEntity deleteBookList(@PathVariable int listCode) throws Exception {
		service.deleteBookList(listCode);
		return new ResponseEntity(HttpStatus.OK);
	}
}
