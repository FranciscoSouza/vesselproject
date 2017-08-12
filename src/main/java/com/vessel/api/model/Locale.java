package com.vessel.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity  
public class Locale {
  
	@Id  
    @GeneratedValue  
    @Column(name="id")  
    private Long id;  
	
    @Column(nullable=false)  
    private String dataInfo;

    
    
	public Locale() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Locale(Long id, String dataInfo) {
		super();
		this.id = id;
		this.dataInfo = dataInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataInfo() {
		return dataInfo;
	}

	public void setDataInfo(String dataInfo) {
		this.dataInfo = dataInfo;
	}  
    
    
}
