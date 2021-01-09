package com.personal.api.complaintLog.ComplainLoggerAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.personal.api.complaintLog.ComplainLoggerAPI.bean.ResponseBean;
import com.personal.api.complaintLog.ComplainLoggerAPI.enums.Messages;
import com.personal.api.complaintLog.ComplainLoggerAPI.model.Complaints;
import com.personal.api.complaintLog.ComplainLoggerAPI.repository.ComplaintsRepository;
import com.personal.api.complaintLog.ComplainLoggerAPI.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService{

	@Autowired
	ComplaintsRepository complaintRepo;
	
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
		String id  = complaintRepo.save(comp).get_id();
		if(id != null && !id.equals("")){
			return new ResponseBean(id, Messages.complaint_added_successfully.getMessage());
		}
		
		return new ResponseBean(id, Messages.complaint_added_unsuccessfully.getMessage());
	}

	
}
