package com.personal.api.complaintLog.ComplainLoggerAPI.builder;

import com.personal.api.complaintLog.ComplainLoggerAPI.model.Complaints;

public class ComplaintsBuilder {

	private Complaints complaint;

	public ComplaintsBuilder(Complaints complaint) {
		super();
		this.complaint = complaint;
	}

	public ComplaintsBuilder() {
		super();
		this.complaint = new Complaints();
	}

	public Complaints build() {

		return this.complaint;
	}

	public Complaints addDate(String date) {
		this.complaint.setDate(date);
		return this.complaint;
	}
	
	public Complaints addDeviceType(String device){
		this.complaint.setDeviceType(device);
		return this.complaint;
	} 
	
	public Complaints addLocation(String location){
		this.complaint.setLocation(location);
		return this.complaint;
	}
	
	public Complaints addComplaint(String complaint){
		this.complaint.setComplaint(complaint);
		return this.complaint;
	}
	
	public Complaints addStatus(String status){
		this.complaint.setStatus(status);
		return this.complaint;
	}
	
	public Complaints addLog(String log){
		this.complaint.setSystemLog(log);
		return this.complaint;
	}
	
	public Complaints addResolution(String res){
		this.complaint.setResolution(res);
		return this.complaint;
	}

}
