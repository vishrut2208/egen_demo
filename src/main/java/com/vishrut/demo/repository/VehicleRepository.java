package com.vishrut.demo.repository;

import com.vishrut.demo.entity.Alert;
import com.vishrut.demo.entity.Reading;
import com.vishrut.demo.entity.Vehicle;

import java.util.List;

public interface VehicleRepository {

        List<Vehicle> findAll();

        Vehicle findOne(String vehicleVin);

        List<Alert> getAlerts(String vehicleVin);

        List<Alert> getHighAlerts();

        List<Reading> getGeoLocation(String vehicleVin);

        Vehicle create(Vehicle newVehicle);

        Vehicle update(Vehicle existingVehicle);

        void delete(Vehicle vehicle);
}
