package com.nkdev.erequests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkdev.erequests.entity.Document;
import com.nkdev.erequests.entity.Request;
import com.nkdev.erequests.model.ResponseData;
import com.nkdev.erequests.service.DocumentService;
import com.nkdev.erequests.service.RequestService;

@RestController
public class DocumentController {
	
	@Autowired
	private DocumentService service;
	@Autowired 
	private RequestService rService;
	
	@PostMapping(value="/upload", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseData uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String r) throws Exception {
		Document document = null;
		Request request = null;
		String downloadURL = "";
		
		document = this.service.saveDocument(file);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			request = mapper.readValue(r, Request.class);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		request.setDocument(document);
		this.rService.saveRequest(request);
		
		downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/").path(Integer.toString(document.getId())).toUriString();
		
		return new ResponseData(document.getName(), downloadURL, file.getContentType(), file.getSize());
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int id){
		Document document = service.getDocument(id);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(document.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "document; filename=\"" + document.getName() + "\"")
				.body(new ByteArrayResource(document.getData()));
	}
}
