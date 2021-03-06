package com.vishrut.demo.repository;


import com.vishrut.demo.entity.Reading;

import java.util.List;

public interface ReadingRepository {

    List<Reading> findAll();

    Reading findOne(String id);

    List<Reading> findByVin(String vehicleVin);

    Reading create(Reading newReading);

    void delete(Reading reading);
}
