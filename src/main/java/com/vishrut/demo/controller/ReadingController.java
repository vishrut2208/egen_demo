package com.vishrut.demo.controller;


import com.vishrut.demo.entity.Reading;
import com.vishrut.demo.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("readings")
public class ReadingController {

    @Autowired
    private ReadingService readingService;


    @RequestMapping(method = RequestMethod.GET)
    public List<Reading> findAll(){
       return readingService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Reading findOne(@PathVariable("id") String id){
        return readingService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET,value = "all/{id}")
    public List<Reading> findByVin(@PathVariable("id") String id){
        return readingService.findByVin(id);
    }

    @CrossOrigin(origins = "http://mocker.ennate.academy")
    @RequestMapping(method = RequestMethod.POST)
    public Reading create(@RequestBody Reading reading){
        return readingService.create(reading);
    }

    @RequestMapping(method = RequestMethod.DELETE, value= "{id}")
    public void delete(@PathVariable("id") String id){
        readingService.delete(id);
    }


}
