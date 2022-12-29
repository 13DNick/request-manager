package com.nkdev.erequests.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nkdev.erequests.entity.Request;
import com.nkdev.erequests.service.RequestService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class RequestController {
	
	@Autowired
	private RequestService service;
	
	@PostMapping("/request")
	public void placeRequest(@RequestBody Request request) {
		this.service.saveRequest(request);
	}
	
	@GetMapping("/request/{id}")
	public Request getRequest(@PathVariable int id) {
		Request request = this.service.getRequest(id);
		
		if(request == null) {
			throw new RuntimeException("Request id not found: " + id);
		}
		
		return request;
	}
	
	@GetMapping("/request")
	public List<Request> getRequests(){
		return this.service.getRequests();
	}
	
	@GetMapping("/request/search")
	public List<Request> getRequestsBySearch(@RequestParam String term, @RequestParam String field){
		
		List<Request> list;
		
		if(field.equals("id")) {
			list = this.service.getRequestsByIdSearch(term);
		} else if(field.equals("name")) {
			list = this.service.getRequestsByNameSearch(term);
		} else if(field.equals("email")) {
			list = this.service.getRequestsByEmailSearch(term);
		} else if(field.equals("department")) {
			list = this.service.getRequestsByDepartmentSearch(term);
		} else if(field.equals("employeeId")){
			list = this.service.getRequestsByEIdSearch(term);
		} else {
			list = this.service.getRequestsByStatusSearch(term);
		}	
		
		return list;
	}
}
