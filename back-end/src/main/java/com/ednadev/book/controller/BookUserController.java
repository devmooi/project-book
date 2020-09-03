package com.ednadev.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ednadev.book.domain.BookUser;
import com.ednadev.book.service.BookUserService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class BookUserController {
	
	@Autowired
	private BookUserService service;
	
	@PostMapping("bookUser")
	public ResponseEntity insertBookUser(@RequestBody BookUser bookUser) throws Exception {
		service.insertBookUser(bookUser);
		return new ResponseEntity(HttpStatus.OK);
	}
		
}
