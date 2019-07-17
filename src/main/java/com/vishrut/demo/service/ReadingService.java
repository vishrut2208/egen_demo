package com.vishrut.demo.service;

import com.vishrut.demo.entity.Reading;

import java.util.List;

public interface ReadingService {

    List<Reading> findAll();

    Reading findOne(String id);

    List<Reading> findByVin(String vehicleVin);

    Reading create(Reading newReading);

    void delete(String id);

}
