package com.nkdev.erequests.service;

import java.util.List;

import com.nkdev.erequests.entity.Request;

public interface RequestService {
	
	public List<Request> getRequests();
	
	public void saveRequest(Request request);

	public Request getRequest(int id);

	public void deleteRequest(int id);

	public List<Request> getRequestsByStatusSearch(String term);

	public List<Request> getRequestsByDepartmentSearch(String term);

	public List<Request> getRequestsByEmailSearch(String term);

	public List<Request> getRequestsByNameSearch(String term);

	public List<Request> getRequestsByIdSearch(String term);

	List<Request> getRequestsByEIdSearch(String term);
}
