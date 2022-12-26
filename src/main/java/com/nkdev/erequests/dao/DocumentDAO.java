package com.nkdev.erequests.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nkdev.erequests.entity.Document;

public interface DocumentDAO {
	
	public List<Document> getDocuments();
	
	public Document saveDocument(MultipartFile document) throws Exception;

	public Document getDocument(int id);

	public void deleteDocument(int id);
	
}
