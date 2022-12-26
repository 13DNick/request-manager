package com.nkdev.erequests.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nkdev.erequests.entity.Request;

import jakarta.persistence.EntityManager;

@Repository
public class RequestDAOImpl implements RequestDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Request> getRequests() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Request> query = session.createQuery("from Request", Request.class);
		List<Request> request = query.getResultList();
		
		return request;
	}

	@Override
	public void saveRequest(Request request) {
		Session session = entityManager.unwrap(Session.class);
		session.persist(request);
	}

	@Override
	public Request getRequest(int id) {
		Session session = entityManager.unwrap(Session.class);
		Request request = session.get(Request.class, id);
		return request;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteRequest(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<?> query = session.createQuery("delete from Request r where r.id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
