package com.vishrut.demo.controller;


import com.vishrut.demo.entity.Alert;
import com.vishrut.demo.entity.Vehicle;
import com.vishrut.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> findAll(){
        return vehicleService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Vehicle findOne(@PathVariable("id") String vehicleVin){
        return vehicleService.findOne(vehicleVin);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}/alerts")
    public List<Alert> getAlerts(@PathVariable("id") String vehicleVin){
        return vehicleService.getAlerts(vehicleVin);
    }


    @RequestMapping(method = RequestMethod.POST)
    public Vehicle create(@RequestBody Vehicle newVehicle){
        return vehicleService.create(newVehicle);
    }

    @CrossOrigin(origins = "http://mocker.ennate.academy")
    @RequestMapping(method = RequestMethod.PUT)
    public List<Vehicle> createAll(@RequestBody List<Vehicle> vehicleList){
        return vehicleService.batchInsert(vehicleList);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "{id}")
    public Vehicle update(@PathVariable("id") String vehicleVin,@RequestBody Vehicle existingVehicle){
        return vehicleService.update(vehicleVin,existingVehicle);
    }

    @RequestMapping(method = RequestMethod.DELETE, value= "{id}")
    public void delete(@PathVariable("id") String vehicleVin){
        vehicleService.delete(vehicleVin);
    }




}
