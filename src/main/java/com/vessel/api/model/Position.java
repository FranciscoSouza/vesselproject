package com.vessel.api.model;

public class Position {
	
	private String timeZoneName;
	private String offSet;
	private String currentLocalTime;
	private String currentUTCTime;
	
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Position(String timeZoneName, String offSet, String currentLocalTime, String currentUTCTime) {
		super();
		this.timeZoneName = timeZoneName;
		this.offSet = offSet;
		this.currentLocalTime = currentLocalTime;
		this.currentUTCTime = currentUTCTime;
	}

	public String getTimeZoneName() {
		return timeZoneName;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	public String getOffSet() {
		return offSet;
	}

	public void setOffSet(String offSet) {
		this.offSet = offSet;
	}

	public String getCurrentLocalTime() {
		return currentLocalTime;
	}

	public void setCurrentLocalTime(String currentLocalTime) {
		this.currentLocalTime = currentLocalTime;
	}

	public String getCurrentUTCTime() {
		return currentUTCTime;
	}

	public void setCurrentUTCTime(String currentUTCTime) {
		this.currentUTCTime = currentUTCTime;
	}

}
