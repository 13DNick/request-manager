package com.nkdev.erequests.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nkdev.erequests.entity.Document;

public interface DocumentService {
	
	public List<Document> getDocuments();
	
	public Document saveDocument(MultipartFile document) throws Exception;

	public Document getDocument(int id);

	public void deleteDocument(int id);
	
}
