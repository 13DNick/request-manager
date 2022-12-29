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

	@Override
	public List<Request> getRequestsByStatusSearch(String term) {
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r where r.employmentStatus like :term", Request.class);
		q.setParameter("term", "%" + term + "%");
		List<Request> requests = q.getResultList();
		return requests;
	}

	@Override
	public List<Request> getRequestsByDepartmentSearch(String term) {
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r where r.department like :term", Request.class);
		q.setParameter("term", "%" + term + "%");
		List<Request> requests = q.getResultList();
		return requests;
	}

	@Override
	public List<Request> getRequestsByEmailSearch(String term) {
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r where r.email like :term", Request.class);
		q.setParameter("term", "%" + term + "%");
		List<Request> requests = q.getResultList();
		return requests;
	}

	@Override
	public List<Request> getRequestsByNameSearch(String term) {
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r where r.name like :term", Request.class);
		q.setParameter("term", "%" + term + "%");
		List<Request> requests = q.getResultList();
		return requests;
	}

	@Override
	public List<Request> getRequestsByIdSearch(String term) {
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r where r.id like :term", Request.class);
		q.setParameter("term", term);
		List<Request> requests = q.getResultList();
		return requests;
	}
	
	@Override
	public List<Request> getRequestsByEIdSearch(String term) {
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r where r.employeeId like :term", Request.class);
		q.setParameter("term", term);
		List<Request> requests = q.getResultList();
		return requests;
	}
}
