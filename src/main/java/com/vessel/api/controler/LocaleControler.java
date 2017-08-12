package com.vessel.api.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vessel.api.model.Locale;
import com.vessel.api.repository.LocaleRepository;

@RestController
public class LocaleControler {
	@Autowired
	LocaleRepository repository;
	Locale locale;
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id){
		String result = "";
		locale = new Locale();
		locale = (Locale)repository.findOne(id);
		return locale.getDataInfo();
	}

}
