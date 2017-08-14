package com.vessel.api.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vessel.api.message.Response;
import com.vessel.api.model.Locale;
import com.vessel.api.model.Position;
import com.vessel.api.repository.LocaleRepository;

@RestController
public class LocaleControler {
	@Autowired
	LocaleRepository repository;
	Locale locale;
	
	
	@RequestMapping("/timeForLatLng")
	public Response timeForLatLng(@RequestParam("lng") String lng,@RequestParam("lat") String lat){
		
		Position pos1 = new Position();
		pos1.setTimeZoneName("TimeZone1");
		
		Position pos2 = new Position();
		pos2.setTimeZoneName("TimeZone2");
		
		ArrayList<Position> responseList = new ArrayList<Position>();
		responseList.add(pos1);
		//responseList.add(pos2);
		
		return new Response("Done", responseList);
	}

	
}
