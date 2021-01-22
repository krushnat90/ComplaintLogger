package com.personal.api.complaintLog.ComplainLoggerAPI.bean;

import com.personal.api.complaintLog.ComplainLoggerAPI.enums.Messages;

public class ResponseBean {

	private String id;
	private String message;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ResponseBean(String id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	
	
	
	
}
