package com.vessel.api.controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vessel.api.message.Response;
import com.vessel.api.model.TimeZones;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.vessel.api.repository.LocaleRepository;

@RestController
public class LocaleControler {
	@Autowired
	LocaleRepository repository;
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	
	@RequestMapping("/timeForLatLng")
	public Response timeForLatLng(@RequestParam("lng") String lng,@RequestParam("lat") String lat){
		
		TimeZones pos1 =jdbc.queryForObject("SELECT t.gid,t.places,t.time_zone, t.zone FROM timezones t WHERE ST_Intersects(ST_GeomFromText('POINT("+lat+"  "+lng+" )', 4326), geom)",userMapper); 
		
		return new Response("Done", pos1);
	}
	
	private final RowMapper<TimeZones> userMapper = new RowMapper<TimeZones>() {
        public TimeZones mapRow(ResultSet rs, int rowNum) throws SQLException {
        	TimeZones tz = new TimeZones();
        	tz.setId(rs.getLong("gid"));
        	tz.setTimeZoneName(rs.getString("places"));
        	tz.setOffSetText(rs.getString("time_zone"));
        	tz.setOffSet(rs.getDouble("zone"));
        	tz.setCurrentLocalTime(getCurrentDateTime());
        	tz.setCurrentUTCTime(getCurrentUTCTime(rs.getDouble("zone")));
            return tz;
        }
    };

    private String getCurrentDateTime(){
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    
    private String getCurrentUTCTime(Double d) {
    	Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, d.intValue()); // adds one hour
        cal.getTime();
        
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(cal.getTime());
	}
	
}
