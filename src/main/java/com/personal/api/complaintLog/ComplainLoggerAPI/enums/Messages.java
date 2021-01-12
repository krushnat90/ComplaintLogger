package com.personal.api.complaintLog.ComplainLoggerAPI.enums;

public enum Messages {
	
	complaint_added_successfully("Your complaint is added successfully"),
	complaint_added_unsuccessfully("Your complaint could not be added"),
	
	complaint_updated_successfully("Your complaint is updated successfully"),
	complaint_not_found("Your complaint id is not in the database. Kindly verify."),
	complaint_id_needed("complaint id is mandatory for this request"),
	complaint_deleted_successfully("Your complaint is deleted successfully");
	
	private String message;
	
	private Messages(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
