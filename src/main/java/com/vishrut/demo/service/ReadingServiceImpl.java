package com.vishrut.demo.service;

import com.vishrut.demo.entity.Reading;
import com.vishrut.demo.entity.Vehicle;
import com.vishrut.demo.exception.ResourceNotFoundException;
import com.vishrut.demo.repository.ReadingRepository;
import com.vishrut.demo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {


    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Reading> findAll() {
        return readingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Reading findOne(String id) {
        return readingRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reading> findByVin(String vehicleVin) {
        Vehicle vehicle = vehicleRepository.findOne(vehicleVin);

        if(vehicle == null){
            throw  new ResourceNotFoundException("Employee with id= " + vehicleVin + "Not Found");
        }else{
            return readingRepository.findByVin(vehicleVin);
        }
    }

    @Override
    @Transactional
    public Reading create(Reading newReading) {
        Vehicle vehicle = vehicleRepository.findOne(newReading.getVin());

        if(vehicle == null){
            throw new ResourceNotFoundException("Vehicle with VIN " + newReading.getVin() + "is not found");
        }else {

            vehicle.addReading(newReading);
            vehicleRepository.update(vehicle);
            return newReading;
        }
    }

    @Override
    @Transactional
    public void delete(String id) {
        Reading existing = readingRepository.findOne(id);

        if(existing == null){
            throw  new ResourceNotFoundException("Reading with id= " + id + "Not Found");
        }
        readingRepository.delete(existing);

    }
}
