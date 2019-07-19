package com.vishrut.demo.controller;


import com.vishrut.demo.entity.Alert;
import com.vishrut.demo.entity.Reading;
import com.vishrut.demo.entity.Vehicle;
import com.vishrut.demo.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicles")
@Api(description = "Vehicle related endpoints")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find all the vehicles",
                  notes = "Return a list of all the vehicles available in the database")
    public List<Vehicle> findAll(){
        return vehicleService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    @ApiOperation(value = "Find a Vehicle",
                  notes = "Return a specific vehicle based on the Vin, available in the database")
    public Vehicle findOne(@PathVariable("id") String vehicleVin){
        return vehicleService.findOne(vehicleVin);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}/alerts")
    @ApiOperation(value = "Find all the alerts of a vehicle",
            notes = "Return a list of all the alerts of a vehicle available in the database")
    public List<Alert> getAlerts(@PathVariable("id") String vehicleVin){
        return vehicleService.getAlerts(vehicleVin);
    }

    @RequestMapping(method = RequestMethod.GET, value = "highalerts")
    @ApiOperation(value = "Find all the highalerts",
            notes = "Return a list of all the highalerts within 2 hours, available in the database")
    public List<Alert> getHighAlerts(){
        return vehicleService.getHighAlerts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}/geolocation")
    @ApiOperation(value = "Find all the geolocations of a vehicle",
            notes = "Return a list of all the geolocations of a vehcile within 1/2 hr,  available in the database")
    public List<Reading> getGeoLocation(@PathVariable("id") String vehicleVin){
        return vehicleService.getGeoLocation(vehicleVin);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Vehicle create(@RequestBody Vehicle newVehicle){
        return vehicleService.create(newVehicle);
    }

    @CrossOrigin(origins = "http://mocker.ennate.academy")
    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "insert batch of the vehicles",
            notes = "Insert a list of all the vehicles getting from the mocker")
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
