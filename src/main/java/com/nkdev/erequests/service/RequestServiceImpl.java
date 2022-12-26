package com.nkdev.erequests.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nkdev.erequests.dao.RequestDAO;
import com.nkdev.erequests.entity.Request;

@Service
public class RequestServiceImpl implements RequestService{
	
	@Autowired
	private RequestDAO dao;
	
	@Override
	@Transactional
	public List<Request> getRequests() {
		return this.dao.getRequests();
	}

	@Override
	@Transactional
	public void saveRequest(Request request) {
		this.dao.saveRequest(request);
	}

	@Override
	@Transactional
	public Request getRequest(int id) {
		return this.dao.getRequest(id);
	}

	@Override
	@Transactional
	public void deleteRequest(int id) {
		this.dao.deleteRequest(id);
	}

}
