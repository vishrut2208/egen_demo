package com.vishrut.demo.service;

import com.vishrut.demo.entity.Vehicle;
import com.vishrut.demo.exception.BadRequestException;
import com.vishrut.demo.exception.ResourceNotFoundException;
import com.vishrut.demo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle findOne(String vehicleVin) {
        Vehicle vehicle = vehicleRepository.findOne(vehicleVin);

        if(vehicle == null){
            throw  new ResourceNotFoundException("Employee with id= " + vehicleVin + "Not Found");
        }else{
            return vehicle;
        }
    }

    @Override
    @Transactional
    public Vehicle create(Vehicle newVehicle) {
        Vehicle existing = vehicleRepository.findOne(newVehicle.getVin());

        if(existing != null){
            throw new BadRequestException("Vehicle with vin: "+ newVehicle.getVin()  +" already exist in the database");
        }else{
            return vehicleRepository.create(newVehicle);
        }
    }

    @Override
    @Transactional
    public Vehicle update(String vehicleVin,Vehicle existingVehicle) {
        Vehicle existing = vehicleRepository.findOne(vehicleVin);

        if(existing == null){
            throw  new ResourceNotFoundException("Vehicle with vin= " + vehicleVin + "Not Found");
        }else{
            return vehicleRepository.update(existingVehicle);
        }
    }

    @Override
    @Transactional
    public void delete(String vehicleVin) {
        Vehicle existing = vehicleRepository.findOne(vehicleVin);

        if(existing == null){
            throw  new ResourceNotFoundException("Vehicle with id= " + vehicleVin + "Not Found");
        }
        vehicleRepository.delete(existing);
    }
}
