package com.vishrut.demo.repository;


import com.vishrut.demo.entity.Alert;
import com.vishrut.demo.entity.Reading;
import com.vishrut.demo.entity.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {

    @Query("select al from Alert al, Reading rd where al.readId = rd.id and rd.vin =:paramVin")
    List<Alert> getAlerts(@Param("paramVin") String vehicleVin);

    @Query("select al from Alert al, Reading rd where al.readId = rd.id and rd.timestamp >=:paramTime and al.type=:paramType")
    List<Alert> getHighAlerts(@Param("paramTime")Date d,@Param("paramType") String type);

    @Query("select rd from Reading rd where rd.vin =:paramVin and rd.timestamp >=:paramTime")
    List<Reading> getGeoLocation(@Param("paramVin")String vehicleVin, @Param("paramTime") Date d);
}


