package com.personal.api.complaintLog.ComplainLoggerAPI.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

	public static String createComplaintID(String userID) {
		StringBuilder sb = new StringBuilder();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		sb.append(sdf.format(new Date()));

		return sb.toString();
	}

	public static String getTodaysDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		return sdf.format(new Date());

	}

}
