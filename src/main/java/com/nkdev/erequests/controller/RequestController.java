package com.nkdev.erequests.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	private Request getRequest(@PathVariable int id) {
		Request request = this.service.getRequest(id);
		
		if(request == null) {
			throw new RuntimeException("Request id not found: " + id);
		}
		
		return request;
	}
	
	@GetMapping("/request")
	private List<Request> getRequests(){
		return this.service.getRequests();
	}
}
