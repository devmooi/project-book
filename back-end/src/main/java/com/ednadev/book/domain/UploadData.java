package com.ednadev.book.domain;

import org.springframework.web.multipart.MultipartFile;

public class UploadData {
	private String userEmail;
	private MultipartFile uploadFile;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
