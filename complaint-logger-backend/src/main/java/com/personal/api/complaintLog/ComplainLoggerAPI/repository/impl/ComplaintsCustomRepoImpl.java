package com.personal.api.complaintLog.ComplainLoggerAPI.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.personal.api.complaintLog.ComplainLoggerAPI.model.Complaints;
import com.personal.api.complaintLog.ComplainLoggerAPI.repository.ComplaintsCustomRepository;

@Repository
public class ComplaintsCustomRepoImpl implements ComplaintsCustomRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public boolean deleteComplaint(String complaintId) {
		Complaints comp = getByComplaintID(complaintId);

		if (comp != null) {
			mongoTemplate.remove(comp);
			return true;
		}

		return false;
	}

	@Override
	public Complaints getByComplaintID(String id) {
		return mongoTemplate.findOne(Query.query(Criteria.where("complaintID").is(id)), Complaints.class);
	}

	@Override
	public Complaints updateOneComplaint(Complaints complaintBean) {
		return mongoTemplate.save(complaintBean);
	}

	@Override
	public boolean updateMultipleComplaints(Complaints complaintBean) {
		return false;
	}

}
