package com.personal.api.complaintLog.ComplainLoggerAPI.repository;

import com.personal.api.complaintLog.ComplainLoggerAPI.model.Complaints;

public interface ComplaintsCustomRepository {

	boolean deleteComplaint(String complaintId);
	
	Complaints getByComplaintID(String id) ;
	
	Complaints updateOneComplaint(Complaints complaintId);
	
	boolean updateMultipleComplaints(Complaints complaintId);
}
