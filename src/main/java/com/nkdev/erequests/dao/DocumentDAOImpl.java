package com.nkdev.erequests.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nkdev.erequests.entity.Document;

import jakarta.persistence.EntityManager;

@Repository
public class DocumentDAOImpl implements DocumentDAO{
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Document> getDocuments() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Document> query = session.createQuery("from Document", Document.class);
		List<Document> request = query.getResultList();
		
		return request;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Document saveDocument(MultipartFile file) throws Exception {
		Session session = entityManager.unwrap(Session.class);
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(name.contains("..")) {
				throw new Exception("File name contains invalid characters: " + name);
			}
			
			Document document = new Document(name, file.getContentType(), file.getBytes());
			session.saveOrUpdate(document);
			return document;
		} catch(Exception e) {
			throw new Exception(e);
		}
		
	}


	@Override
	public Document getDocument(int id) {
		Session session = entityManager.unwrap(Session.class);
		Document document = session.get(Document.class, id);
		return document;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteDocument(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<?> query = session.createQuery("delete from Document d where d.id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
}
