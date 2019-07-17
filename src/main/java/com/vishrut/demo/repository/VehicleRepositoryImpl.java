package com.vishrut.demo.repository;

import com.vishrut.demo.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Vehicle> findAll() {
        TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v", Vehicle.class);
        return query.getResultList();
    }

    @Override
    public Vehicle findOne(String vehicleVin) {
        return em.find(Vehicle.class, vehicleVin);
    }

    @Override
    public Vehicle create(Vehicle newVehicle) {
        em.persist(newVehicle);
        return newVehicle;
    }

    @Override
    public Vehicle update(Vehicle existingVehicle) {
        em.merge(existingVehicle);
        return existingVehicle;
    }

    @Override
    public void delete(Vehicle vehicle) {
        em.remove(vehicle);
    }
}