package com.nkdev.erequests.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nkdev.erequests.dao.DocumentDAO;
import com.nkdev.erequests.entity.Document;

@Service
public class DocumentServiceImpl implements DocumentService{

	@Autowired
	private DocumentDAO dao;

	@Override
	@Transactional
	public List<Document> getDocuments() {
		return this.dao.getDocuments();
	}

	@Override
	@Transactional
	public Document saveDocument(MultipartFile document) throws Exception {
		return this.dao.saveDocument(document);
	}

	@Override
	@Transactional
	public Document getDocument(int id) {
		return this.dao.getDocument(id);
	}

	@Override
	@Transactional
	public void deleteDocument(int id) {
		this.dao.deleteDocument(id);
	}
	
		
}
