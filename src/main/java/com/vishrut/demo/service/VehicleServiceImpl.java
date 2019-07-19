package com.vishrut.demo.service;

import com.vishrut.demo.entity.Alert;
import com.vishrut.demo.entity.Reading;
import com.vishrut.demo.entity.Vehicle;
import com.vishrut.demo.exception.BadRequestException;
import com.vishrut.demo.exception.ResourceNotFoundException;
import com.vishrut.demo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle findOne(String vehicleVin) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleVin);

        if(!vehicle.isPresent()){
            throw  new ResourceNotFoundException("Vehicle with id= " + vehicleVin + "Not Found");
        }else{
            return vehicle.get();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAlerts(String vehicleVin) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleVin);
        if(!vehicle.isPresent()){
            throw  new ResourceNotFoundException("Vehicle with id= " + vehicleVin + "Not Found");
        }else{
            return vehicleRepository.getAlerts(vehicleVin);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alert> getHighAlerts() {
        Date d = new Date(System.currentTimeMillis() - 7200 *1000);
        return vehicleRepository.getHighAlerts(d, "HIGH");

    }

    @Override
    @Transactional(readOnly = true)
    public List<Reading> getGeoLocation(String vehicleVin) {
        Date d = new Date(System.currentTimeMillis() - 1800 * 1000);
        return vehicleRepository.getGeoLocation(vehicleVin, d);
    }

    @Override
    @Transactional
    public Vehicle create(Vehicle newVehicle) {
        Optional<Vehicle> existing = vehicleRepository.findById(newVehicle.getVin());

        if(!existing.isPresent()){
            throw new BadRequestException("Vehicle with vin: "+ newVehicle.getVin()  +" already exist in the database");
        }else{
            return vehicleRepository.save(newVehicle);
        }
    }

    @Override
    @Transactional
    public List<Vehicle> batchInsert(List<Vehicle> vehicleList) {
        Optional<Vehicle> temp;
        System.out.println("i am in the insert all method");
        for(int i =0; i < vehicleList.size(); i++){
            temp = vehicleRepository.findById(vehicleList.get(i).getVin());
            System.out.println(temp);
           vehicleRepository.save(vehicleList.get(i));
        }
        return vehicleList;
    }

    @Override
    @Transactional
    public Vehicle update(String vehicleVin,Vehicle existingVehicle) {
        Optional<Vehicle> existing = vehicleRepository.findById(vehicleVin);

        if(!existing.isPresent()){
            throw  new ResourceNotFoundException("Vehicle with vin= " + vehicleVin + "Not Found");
        }else{
            return vehicleRepository.save(existingVehicle);
        }
    }

    @Override
    @Transactional
    public void delete(String vehicleVin) {
        Optional<Vehicle> existing = vehicleRepository.findById(vehicleVin);

        if(existing.isPresent()){
            throw  new ResourceNotFoundException("Vehicle with id= " + vehicleVin + "Not Found");
        }
        vehicleRepository.delete(existing.get());
    }
}
