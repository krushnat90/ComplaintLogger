package com.personal.api.complaintLog.ComplainLoggerAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.personal.api.complaintLog.ComplainLoggerAPI.bean.ResponseBean;
import com.personal.api.complaintLog.ComplainLoggerAPI.builder.ComplaintsBuilder;
import com.personal.api.complaintLog.ComplainLoggerAPI.enums.Messages;
import com.personal.api.complaintLog.ComplainLoggerAPI.enums.Status;
import com.personal.api.complaintLog.ComplainLoggerAPI.model.Complaints;
import com.personal.api.complaintLog.ComplainLoggerAPI.repository.ComplaintsCustomRepository;
import com.personal.api.complaintLog.ComplainLoggerAPI.repository.ComplaintsRepository;
import com.personal.api.complaintLog.ComplainLoggerAPI.service.ComplaintService;
import com.personal.api.complaintLog.ComplainLoggerAPI.util.Utility;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	ComplaintsRepository complaintRepo;

	@Autowired
	ComplaintsCustomRepository customRepo;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Complaints> getAllComplaints() {
		return complaintRepo.findAll();
	}

	@Override
	public Complaints getComplaintByData(Complaints comp) {
		Optional<Complaints> complaint = complaintRepo.findById(comp.get_id());
		return (complaint.isPresent()) ? complaint.get() : null;
	}

	@Override
	public ResponseBean addComplaint(Complaints comp) {

		comp.setComplaintID(Utility.createComplaintID(comp.getUserName()));
		comp.setDate(Utility.getTodaysDate());
		comp.setStatus(Status.ACTIVE.toString());

		Complaints obj = complaintRepo.save(comp);
		if (obj != null) {
			return new ResponseBean(obj.getComplaintID(), Messages.complaint_added_successfully.getMessage());
		}

		return new ResponseBean("XXX", Messages.complaint_added_unsuccessfully.getMessage());
	}

	@Override
	public ResponseBean deleteComplaint(String complaintId) {

		boolean result = customRepo.deleteComplaint(complaintId);

		if (result) {
			return new ResponseBean(complaintId, Messages.complaint_deleted_successfully.getMessage());
		}

		return new ResponseBean(complaintId, Messages.complaint_not_found.getMessage());
	}

	@Override
	public Complaints getByComplaintID(String id) {

		return customRepo.getByComplaintID(id);
	}

	@Override
	public ResponseBean updateComplaint(Complaints complaintBean) {

		if(StringUtils.isBlank(complaintBean.getComplaintID())){
			return new ResponseBean("XXX", Messages.complaint_id_needed.getMessage());
		}
		
		Complaints complaintToUpdate = getByComplaintID(complaintBean.getComplaintID());

		if (complaintToUpdate != null) {
			customRepo.updateOneComplaint(setNewComplaintBean(complaintToUpdate, complaintBean));
			return new ResponseBean(complaintToUpdate.getComplaintID(),
					Messages.complaint_updated_successfully.getMessage());
		}

		return new ResponseBean(complaintBean.getComplaintID(), Messages.complaint_not_found.getMessage());
	}

	public Complaints setNewComplaintBean(Complaints DbBean, Complaints incoming) {

		ComplaintsBuilder cb = new ComplaintsBuilder(DbBean);

		
		if (StringUtils.isNotBlank(incoming.getStatus())) {
			cb.addStatus(incoming.getStatus());
		}
		if (StringUtils.isNotBlank(incoming.getResolution())) {
			cb.addResolution(incoming.getResolution());
		}

		return cb.build();
	}

}
