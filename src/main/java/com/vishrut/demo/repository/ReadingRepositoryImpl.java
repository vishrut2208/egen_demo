package com.vishrut.demo.repository;


import com.vishrut.demo.entity.Reading;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ReadingRepositoryImpl implements ReadingRepository{

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Reading> findAll() {
        TypedQuery<Reading> query = em.createQuery("SELECT read FROM Reading read", Reading.class);
        return query.getResultList();
    }

    @Override
    public List<Reading> findByVin(String vehicleVin) {
        TypedQuery<Reading> query = em.createQuery("SELECT read FROM Reading read WHERE read.vin=:paramVin", Reading.class);
        query.setParameter("paramVin", vehicleVin);
        return  query.getResultList();

    }

    @Override
    public Reading create(Reading newReading) {
        em.persist(newReading);
        return newReading;
    }

    @Override
    public void delete(Reading reading) {
        em.remove(reading);
    }
}
