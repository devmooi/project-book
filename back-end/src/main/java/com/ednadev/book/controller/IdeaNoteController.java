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

import com.ednadev.book.domain.IdeaNote;
import com.ednadev.book.service.IdeaNoteService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class IdeaNoteController {
	
	@Autowired
	private IdeaNoteService service;
	
	@PostMapping("ideaNote")
	public ResponseEntity insertIdeaNote(@RequestBody IdeaNote ideaNote) throws Exception {
		service.insertIdeaNote(ideaNote);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("ideaNote/{readCode}")
	public ResponseEntity selectIdeaNote(@PathVariable int readCode) throws Exception {
		List<IdeaNote> list = service.selectIdeaNote(readCode);
		if(list==null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PutMapping("ideaNote")
	public ResponseEntity updateIdeaNote(@RequestBody IdeaNote ideaNote) throws Exception {
		service.updateIdeaNote(ideaNote);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("ideaNote/{ideaCode}")
	public ResponseEntity deleteIdeaNote(@PathVariable int ideaCode) throws Exception {
		service.deleteIdeaNote(ideaCode);
		return new ResponseEntity(HttpStatus.OK);
	}
}
