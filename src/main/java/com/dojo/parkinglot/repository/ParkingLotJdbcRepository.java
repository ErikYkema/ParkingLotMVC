package com.dojo.parkinglot.repository;

import com.dojo.parkinglot.dao.PropertiesDao;
import com.dojo.parkinglot.dao.VehicleDao;
import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.domain.car.ElectricCar;
import com.dojo.parkinglot.domain.car.GenericCar;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Primary
@Component
public class ParkingLotJdbcRepository implements ParkingLotRepositoryInterface {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public ParkingLotJdbcRepository() {
        LOG.debug("constructor...");
    }

    private VehicleDao vehicleDao;
    private PropertiesDao propertiesDao;
    private ParkingLotProperties properties;
    public static final String PARKING_LOT_NAME = "DanielDogShed";

    @Autowired
    public void setPropertiesDao(PropertiesDao propertiesDao) {
        this.propertiesDao = propertiesDao;
    }

    @Autowired
    public void setVehicleDao(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public void setup() {
        LOG.debug("setup JDBC environment...");
        propertiesDao.setup();
        propertiesDao.deleteProperties();
        properties = new ParkingLotProperties();
        properties.setName(PARKING_LOT_NAME);
        properties.setGenericSize(11);
        properties.setElectricSize(3);
        properties.setId(propertiesDao.saveProperties(properties));

        vehicleDao.setup();
        vehicleDao.deleteVehicles();
        vehicleDao.saveVehicle(new ElectricCar("ELEC1"));
        vehicleDao.saveVehicle(new ElectricCar("ELEC2"));
        vehicleDao.saveVehicle(new ElectricCar("ELEC3"));
        vehicleDao.saveVehicle(new ElectricCar("ELEC4"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC1"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC2"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC3"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC4"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC5"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC6"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC7"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC8"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC9"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC10"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC11"));
        vehicleDao.saveVehicle(new GenericCar("GENERIC12"));

    }

    public ParkingLotProperties getPropertiesByName(String name) {
        return propertiesDao.getPropertiesByName(name);
    }

    public ParkingLotProperties getPropertiesById(int id) {
        return propertiesDao.getPropertiesById(id);
    }

    public Integer saveProperties(ParkingLotProperties properties) {
        return propertiesDao.saveProperties(properties);
    }

    public VehicleInterface findByLicensePlate(String licensePlate) {
        return vehicleDao.findByLicensePlate(licensePlate);
    }

    public List<VehicleInterface> getVehicles() {
        return vehicleDao.getVehicles();
    }
}




