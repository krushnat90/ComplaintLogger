package com.personal.api.complaintLog.ComplainLoggerAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.personal.api.complaintLog.ComplainLoggerAPI.model.Complaints;

@Repository
public interface ComplaintsRepository extends MongoRepository<Complaints, String> {
	
}
