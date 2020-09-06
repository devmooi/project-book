package com.ednadev.book.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ednadev.book.domain.BookUser;
import com.ednadev.book.service.BookUserService;
import com.ednadev.book.util.AES256Util;

@RestController
@RequestMapping("api")
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class BookUserController {
	
	@Autowired
	private BookUserService service;
	
	@PostMapping("bookUser")
	public ResponseEntity insertBookUser(@RequestBody BookUser bookUser) throws Exception {
		
		//패스워드 암호화
		if(bookUser.getUserPass()!=null) {
			AES256Util aes256 = new AES256Util("aes256-password-key");
			bookUser.setUserPass(aes256.encrypt(bookUser.getUserPass()));
		}
		
		service.insertBookUser(bookUser);
		return new ResponseEntity(bookUser.getUserEmail(), HttpStatus.OK);
	}
	
	@PostMapping("login")
	public ResponseEntity login(@RequestBody BookUser bookUser) throws Exception {
		//패스워드 암호화
		if(bookUser.getUserPass()!=null) {
			AES256Util aes256 = new AES256Util("aes256-password-key");
			bookUser.setUserPass(aes256.encrypt(bookUser.getUserPass()));
		}
		
		BookUser user = service.login(bookUser);
		if(user==null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity(bookUser.getUserEmail(), HttpStatus.OK);
	}
		
}
