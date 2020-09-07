package com.ednadev.book.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ednadev.book.domain.BookUser;
import com.ednadev.book.domain.UploadData;
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
	
	@GetMapping("bookUser/{email}")
	public ResponseEntity selectBookUser(@PathVariable String email) throws Exception {
		BookUser user = service.selectBookUser(email);
		if(user==null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@PutMapping("bookUser")
	public ResponseEntity updateBookUser(@RequestBody BookUser bookUser) throws Exception {
		service.updateBookUser(bookUser);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PatchMapping("bookUser")
	public ResponseEntity updateBookUser(@ModelAttribute UploadData upload) throws Exception {
		MultipartFile mFile = upload.getUploadFile();		
		String path = "C:\\project-book\\front-end\\src\\assets\\upload\\";
		
		File copyFile = new File(path + mFile.getOriginalFilename());
		mFile.transferTo(copyFile);
		
		BookUser bookUser = new BookUser();
		bookUser.setUserEmail(upload.getUserEmail());
		bookUser.setUserImg(mFile.getOriginalFilename());
		service.updateBookUser(bookUser);
		
		return new ResponseEntity(HttpStatus.OK);
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
