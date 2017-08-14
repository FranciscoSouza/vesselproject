package com.vessel.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.vessel.api.model.TimeZones;

public interface LocaleRepository extends CrudRepository<TimeZones, Long> {

	//@Query("SELECT timeZoneName,offSet FROM TimeZones WHERE ST_Intersects(ST_GeomFromText('POINT(:lat :lng)', 4326), geom)")
    //TimeZones findTimeZoneBylngAndlat(@Param("lng") String lng , @Param("lat") String lat);

}
