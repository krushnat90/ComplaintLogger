package com.personal.api.complaintLog.ComplainLoggerAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.api.complaintLog.ComplainLoggerAPI.bean.ResponseBean;
import com.personal.api.complaintLog.ComplainLoggerAPI.model.Complaints;
import com.personal.api.complaintLog.ComplainLoggerAPI.service.ComplaintService;
import com.personal.api.complaintLog.ComplainLoggerAPI.util.Constants;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

	@Autowired
	ComplaintService service;
	
	@GetMapping("/")
	public String sayHello(){
		return Constants.hello_message;
	}
	
	@GetMapping(value={"/showAll","/getAll","displayAll"})
	public List<Complaints> getComplaint(){
		return service.getAllComplaints();
	}
	
	@GetMapping(value={"/show/{compID}"})
	public Complaints getComplaint(@PathVariable(name="compID") String id){
		return service.getByComplaintID(id);
	}
	
	@PostMapping("/addComplaint")
	public ResponseBean addComplaint(@RequestBody Complaints comp){
		return service.addComplaint(comp);
	}
	
	@DeleteMapping("/deleteComplaint")
	public ResponseBean deleteComplaint(@RequestBody String complaintID){
		
		return service.deleteComplaint(complaintID);
	}
	
	@RequestMapping(value="/updateComplaint",method=RequestMethod.PUT)
	public ResponseBean updateComplaint(@RequestBody Complaints comp){
		
		return service.updateComplaint(comp);
	}
	
}
