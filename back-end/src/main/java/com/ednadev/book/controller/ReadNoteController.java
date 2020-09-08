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

import com.ednadev.book.domain.ReadNote;
import com.ednadev.book.service.ReadNoteService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class ReadNoteController {
	
	@Autowired
	private ReadNoteService service;
	
	@PostMapping("readNote")
	public ResponseEntity insertReadNote(@RequestBody ReadNote readNote) throws Exception {
		service.insertReadNote(readNote);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("readNote/{readCode}")
	public ResponseEntity selectReadNote(@PathVariable int readCode) throws Exception {
		List<ReadNote> list = service.selectReadNote(readCode);
		if(list==null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PutMapping("readNote")
	public ResponseEntity updateReadNote(@RequestBody ReadNote readNote) throws Exception {
		service.updateReadNote(readNote);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("readNote/{noteCode}")
	public ResponseEntity deleteReadNote(@PathVariable int noteCode) throws Exception {
		service.deleteReadNote(noteCode);
		return new ResponseEntity(HttpStatus.OK);
	}
}
