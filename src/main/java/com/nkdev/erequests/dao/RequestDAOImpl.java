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
	public List<Request> getRequestsByName() {
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r order by r.name", Request.class);
		List<Request> requests = q.getResultList();
		return requests;
	}
	
	@Override
	public List<Request> getRequestsByEmployeeId(){
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r order by r.employeeId", Request.class);
		List<Request> requests = q.getResultList();
		return requests;
	}
	
	@Override
	public List<Request> getRequestsByEmail(){
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r order by r.email", Request.class);
		List<Request> requests = q.getResultList();
		return requests;
	}

	@Override
	public List<Request> getRequestsByDepartment() {
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r order by r.department", Request.class);
		List<Request> requests = q.getResultList();
		return requests;
	}
	
	@Override
	public List<Request> getRequestsByStatus() {
		Session session = entityManager.unwrap(Session.class);
		Query<Request> q = session.createQuery("from Request r order by r.employmentStatus", Request.class);
		List<Request> requests = q.getResultList();
		return requests;
	}
}
