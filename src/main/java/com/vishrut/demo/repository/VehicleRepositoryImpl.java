package com.vishrut.demo.repository;

import com.vishrut.demo.entity.Alert;
import com.vishrut.demo.entity.Reading;
import com.vishrut.demo.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;


@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Vehicle> findAll() {
        TypedQuery<Vehicle> query = em.createQuery("SELECT veh FROM Vehicle veh", Vehicle.class);
        return query.getResultList();
    }

    @Override
    public Vehicle findOne(String vehicleVin) {
        return em.find(Vehicle.class, vehicleVin);
    }

    @Override
    public List<Alert> getAlerts(String vehicleVin) {
        TypedQuery<Alert> query = em.createQuery("select al from Alert al, Reading rd where al.readId = rd.id and rd.vin =:paramVin", Alert.class);
        query.setParameter("paramVin", vehicleVin);
        return query.getResultList();
    }

    @Override
    public List<Alert> getHighAlerts() {
        Date d = new Date(System.currentTimeMillis() - 7200 *1000);
        //TypedQuery<Alert> query = em.createQuery("select al from Alert al where al.type =:paramType and al.timestamp >=:paramTime", Alert.class);
        TypedQuery<Alert> query = em.createQuery("select al from Alert al, Reading rd where al.readId = rd.id and rd.timestamp >=:paramTime and al.type=:paramType", Alert.class);
        query.setParameter("paramTime", d, TemporalType.TIMESTAMP);
        query.setParameter("paramType", "HIGH");
        return query.getResultList();
    }

    public List<Reading> getGeoLocation(String vehicleVin){
        Date d = new Date(System.currentTimeMillis() - 1800 * 1000);
        TypedQuery<Reading> query = em.createQuery("select rd from Reading rd where rd.vin =:paramVin and rd.timestamp >=:paramTime", Reading.class);
        query.setParameter("paramTime", d, TemporalType.TIMESTAMP);
        query.setParameter("paramVin", vehicleVin);
        return query.getResultList();
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
