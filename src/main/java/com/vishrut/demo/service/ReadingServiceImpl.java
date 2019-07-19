package com.vishrut.demo.service;

import com.vishrut.demo.entity.Alert;
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

            if(newReading.getEngineRpm() > vehicle.getRedlineRpm()){
                System.out.println("+++++++++++++++++====EngineRPm > redLineRpm=====////////////// i am in the Alert1");
                Alert newAlert = new Alert();
                newAlert.setVin(newReading.getVin());
                newAlert.setType("HIGH");
                newAlert.setDescription("EngineRPm > redLineRpm");
                newReading.addAlert(newAlert);
            }

            if(newReading.getFuelVolume() < (vehicle.getMaxFuelVolume()*0.1)){
                System.out.println("+++++++++++++++++=========////////////// i am in the Alert2");
                Alert newalert = new Alert();
                newalert.setVin(newReading.getVin());
                newalert.setType("MEDIUM");
                newalert.setDescription("fuelVolume < 10% of maxFuelVolume");
                newReading.addAlert(newalert);
            }

            if(newReading.isEngineCoolantLow() || newReading.isCheckEngineLightOn()){
                System.out.println("+++++++++++++++++=========////////////// i am in the Alert3");
                Alert newalert = new Alert();
                newalert.setVin(newReading.getVin());
                newalert.setType("LOW");
                newalert.setDescription("engineCoolantLow = true || checkEngineLightOn = true");
                newReading.addAlert(newalert);
            }

            if((newReading.getTires().getFrontLeft() < 32 || newReading.getTires().getFrontLeft() > 36) &&
                    (newReading.getTires().getFrontRight() < 32 || newReading.getTires().getFrontRight() > 36) &&
                    (newReading.getTires().getRearLeft() < 32 || newReading.getTires().getRearLeft() > 36) &&
                    (newReading.getTires().getRearRight() < 32 || newReading.getTires().getRearRight() > 36)
            ){
                System.out.println("+++++++++++++++++=========////////////// i am in the Alert4");
                Alert newalert = new Alert();
                newalert.setVin(newReading.getVin());
                newalert.setType("LOW");
                newalert.setDescription("tire pressure of any tire < 32psi || > 36psi");
                newReading.addAlert(newalert);

            }
            if(newReading.getVin() != null){
                vehicle.addReading(newReading);
            }
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
