package com.vishrut.demo.service;

import com.vishrut.demo.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAll();

    Vehicle findOne(String vehicleVin);

    Vehicle create(Vehicle newVehicle);

    List<Vehicle> batchInsert(List<Vehicle> vehicleList);

    Vehicle update(String vehicleVin, Vehicle existingVehicle);

    void delete(String vehicleVin);


}
