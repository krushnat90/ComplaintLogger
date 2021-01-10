package com.personal.api.complaintLog.ComplainLoggerAPI.service;

import java.util.List;

import com.personal.api.complaintLog.ComplainLoggerAPI.bean.ResponseBean;
import com.personal.api.complaintLog.ComplainLoggerAPI.model.Complaints;

public interface ComplaintService {
	
	List<Complaints> getAllComplaints();
	
	Complaints getComplaintByData(Complaints comp);
	
	ResponseBean addComplaint(Complaints comp);

	ResponseBean deleteComplaint(String complaintId);
}
