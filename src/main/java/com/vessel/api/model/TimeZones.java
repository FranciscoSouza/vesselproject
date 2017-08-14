package com.vessel.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timezones")
public class TimeZones {

	@Id  
    @GeneratedValue  
    @Column(name="gid")  
    private Long id;  

	
	@Column(name="places")  
	private String timeZoneName;
	
    @Column(name="time_zone")  
	private String offSetText;
    
    private Double offSet;
    
	private String currentLocalTime;
	private String currentUTCTime;
	
	public TimeZones() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public TimeZones(Long id, String timeZoneName, String offSetText, Double offSet, String currentLocalTime,
			String currentUTCTime) {
		super();
		this.id = id;
		this.timeZoneName = timeZoneName;
		this.offSetText = offSetText;
		this.offSet = offSet;
		this.currentLocalTime = currentLocalTime;
		this.currentUTCTime = currentUTCTime;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTimeZoneName() {
		return timeZoneName;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	public String getOffSetText() {
		return offSetText;
	}

	public void setOffSetText(String offSetText) {
		this.offSetText = offSetText;
	}

	public Double getOffSet() {
		return offSet;
	}

	public void setOffSet(Double offSet) {
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



	@Override
	public String toString() {
		return "TimeZones [timeZoneName=" + timeZoneName + ", offSetText=" + offSetText + ", currentLocalTime="
				+ currentLocalTime + ", currentUTCTime=" + currentUTCTime + "]";
	}

	
	
	

}
