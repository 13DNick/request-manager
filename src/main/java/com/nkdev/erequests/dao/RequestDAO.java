package com.nkdev.erequests.dao;

import java.util.List;

import com.nkdev.erequests.entity.Request;

public interface RequestDAO {
	
	public List<Request> getRequests();
	
	public void saveRequest(Request request);

	public Request getRequest(int id);

	public void deleteRequest(int id);
	
	public List<Request> getRequestsByName();
	
	public List<Request> getRequestsByEmployeeId();
	
	public List<Request> getRequestsByEmail();
	
	public List<Request> getRequestsByDepartment();
	
	public List<Request> getRequestsByStatus();
}

