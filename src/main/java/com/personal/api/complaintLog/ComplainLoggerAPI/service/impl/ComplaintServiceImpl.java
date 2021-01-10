package com.personal.api.complaintLog.ComplainLoggerAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.personal.api.complaintLog.ComplainLoggerAPI.bean.ResponseBean;
import com.personal.api.complaintLog.ComplainLoggerAPI.enums.Messages;
import com.personal.api.complaintLog.ComplainLoggerAPI.model.Complaints;
import com.personal.api.complaintLog.ComplainLoggerAPI.repository.ComplaintsRepository;
import com.personal.api.complaintLog.ComplainLoggerAPI.service.ComplaintService;
import com.personal.api.complaintLog.ComplainLoggerAPI.util.Utility;

@Service
public class ComplaintServiceImpl implements ComplaintService{

	@Autowired
	ComplaintsRepository complaintRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<Complaints> getAllComplaints() {
		return complaintRepo.findAll();
	}

	@Override
	public Complaints getComplaintByData(Complaints comp) {
		Optional<Complaints> complaint = complaintRepo.findById(comp.get_id());
		return (complaint.isPresent())?complaint.get():null;
	}

	@Override
	public ResponseBean addComplaint(Complaints comp) {
		
		comp.setComplaintID(Utility.createComplaintID(comp.getUserName()));
		
		Complaints obj  = complaintRepo.save(comp);
		if(obj != null ){
			return new ResponseBean(obj.getComplaintID(), Messages.complaint_added_successfully.getMessage());
		}
		
		return new ResponseBean("XXX", Messages.complaint_added_unsuccessfully.getMessage());
	}

	@Override
	public ResponseBean deleteComplaint(String complaintId) {
		
		Complaints comp = mongoTemplate.findOne(Query.query(Criteria.where("complaintID").is(complaintId)), Complaints.class);
		
		if(comp != null){
			mongoTemplate.remove(comp);
			return new ResponseBean(complaintId, Messages.complaint_deleted_successfully.getMessage());
		}
		
		return new ResponseBean(complaintId, Messages.complaint_not_found.getMessage());
	}

	
}
