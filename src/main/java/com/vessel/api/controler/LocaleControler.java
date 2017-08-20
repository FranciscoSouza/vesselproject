package com.vessel.api.controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	/**
	 * EndPoint to get the Location TimeZone
	 * @param lng
	 * @param lat
	 * @return TimeZones Object
	 */
	@RequestMapping("/timeForLatLng")
	public Response timeForLatLng(@RequestParam("lng") String lng,@RequestParam("lat") String lat){
		
		TimeZones pos1 = new TimeZones();
		try {
			Double.parseDouble(lng);
			Double.parseDouble(lat);
			
			// jdbc query 
			pos1 =jdbc.queryForObject("SELECT t.gid,t.time_zone, t.zone, t.tz_name1st FROM timezones t WHERE ST_Intersects(ST_GeomFromText('POINT("+lng+"  "+lat+" )', 4326), geom)",locationMapper); 
			
			return new Response("Done", pos1);
			
		}catch (EmptyResultDataAccessException e) {
			return new Response("Not Found", pos1);
		}catch (Exception e) {
			return new Response("Fail", pos1);
		}
	}
	
	/**
	 * Wrapper from TimeZones Object that comes from Database
	 * @return TimeZones Object
	 */
	private final RowMapper<TimeZones> locationMapper = new RowMapper<TimeZones>() {
        public TimeZones mapRow(ResultSet rs, int rowNum) throws SQLException {
        	TimeZones tz = new TimeZones();
        	tz.setId(rs.getLong("gid"));
        	tz.setTimeZoneName(rs.getString("tz_name1st"));
        	tz.setOffSetText(rs.getString("time_zone"));
        	tz.setOffSet(rs.getDouble("zone"));
        	tz.setCurrentLocalTime(getCurrentDateTime());
        	tz.setCurrentUTCTime(getCurrentUTCTime(rs.getDouble("zone")));
            return tz;
        }
    };

    /**
     * Private function that gets the current date time
     * @return String with current date using the dd/MM/yyyy HH:mm:ss format
     */
    private String getCurrentDateTime(){
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    
    /**
     * Add or Remove hours from current date time
     * @param time offset
     * @return @return String with current date using the dd/MM/yyyy HH:mm:ss format
     */
    private String getCurrentUTCTime(Double d) {
    	Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, d.intValue()); // adds one hour
        cal.getTime();
        
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(cal.getTime());
	}
	
}
