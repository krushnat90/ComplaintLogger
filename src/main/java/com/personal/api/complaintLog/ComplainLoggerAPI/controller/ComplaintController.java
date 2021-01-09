package com.personal.api.complaintLog.ComplainLoggerAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.api.complaintLog.ComplainLoggerAPI.bean.ResponseBean;
import com.personal.api.complaintLog.ComplainLoggerAPI.model.Complaints;
import com.personal.api.complaintLog.ComplainLoggerAPI.service.ComplaintService;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

	@Autowired
	ComplaintService service;
	
	@GetMapping("/")
	public String sayHello(){
		return "Hello";
	}
	
	@GetMapping("/showAll")
	public List<Complaints> getComplaint(){
		return service.getAllComplaints();
	}
	
	@PostMapping("/addComplaint")
	public ResponseBean addComplaint(@RequestBody Complaints comp){
		return service.addComplaint(comp);
	}
	
}
