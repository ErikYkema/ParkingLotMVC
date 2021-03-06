package com.dojo.parkinglot.domain;

import com.dojo.parkinglot.dao.PropertiesDao;
import com.dojo.parkinglot.dao.PropertiesDaoImpl;
import com.dojo.parkinglot.dao.VehicleDao;
import com.dojo.parkinglot.dao.VehicleDaoImpl;
import com.dojo.parkinglot.domain.car.GenericCar;
import com.dojo.parkinglot.domain.car.VehicleInterface;
import com.dojo.parkinglot.repository.ParkingLotJdbcRepository;
import com.dojo.parkinglot.repository.ParkingLotLeanRepository;
import com.dojo.parkinglot.repository.ParkingLotRepositoryInterface;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.lang.invoke.MethodHandles;
import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ParkingLotTest {

    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    ParkingLotInterface parkingLot;

    private ParkingLotLeanRepository parkingLotLeanRepository;
    private ParkingLotJdbcRepository parkingLotJdbcRepository;
    private DataSource datasource;

    @Autowired
    GenericCar car;
    private VehicleDao vehicleDao;
    private PropertiesDao propertiesDao;


    @Before
    public void setUp() {

        // Lean
        parkingLotLeanRepository = new ParkingLotLeanRepository();
        parkingLotLeanRepository.setup();

        //Derby
        parkingLotJdbcRepository = new ParkingLotJdbcRepository();;
        vehicleDao = new VehicleDaoImpl();
        propertiesDao = new PropertiesDaoImpl();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        datasource = (DataSource)applicationContext.getBean("dataSource");
        vehicleDao.setDataSource(datasource);
        propertiesDao.setDataSource(datasource);
        parkingLotJdbcRepository.setPropertiesDao(propertiesDao);
        parkingLotJdbcRepository.setVehicleDao(vehicleDao);

    }

    // integration test
    @Test
    public void testUsage() {
        LOG.info ("start");
        for (int i=0; i<3; i++) {
            LOG.info(String.format("i: %s", i));
            GenericCar car = new GenericCar(new Date().toString());
            assertTrue("cannot get parking space", parkingLot.requestParkingSpace(car));
            LOG.info(ToStringBuilder.reflectionToString(parkingLot.getFreeSpaceCounter()));
            LOG.info(String.format("nr of usages: %s", parkingLot.getParkingSpaceUsages().size()));
            LOG.info(ToStringBuilder.reflectionToString(parkingLot.getParkingSpaceUsages().get(car)));
            try {
                // sleep to get different usage (time) values
                Thread.sleep(1000L); // millis
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOG.info(String.format("parked duration: %s", parkingLot.getParkingSpaceUsages().get(car).getParkingDuration()));
        }
    }

    // integration test
    @Test
    public void testTicketGenericCar() throws Exception {
        String licensePlate = "GENERIC";
        VehicleInterface car = new GenericCar(licensePlate);
        assertTrue("cannot get parking space", parkingLot.requestParkingSpace(car));
        Thread.sleep(1000L); // millis
        ParkingTicket ticket = parkingLot.releaseParkingSpace(licensePlate);
        assertThat(ticket.getDuration(), greaterThanOrEqualTo(0.5));
        assertThat(ticket.getChargingCost(), is(0.0));
        assertThat(ticket.getParkingCost(), greaterThanOrEqualTo(parkingLot.getProperties().getParkingRate() * 1 / 60));
    }

    // integration test
    @Test
    public void testTicketElectricCarLean() throws Exception {
        String licensePlate = "ELEC1";
        VehicleInterface car = parkingLotLeanRepository.findByLicensePlate(licensePlate);
        assertNotNull(car);
        assertTrue("cannot get parking space", parkingLot.requestParkingSpace(car));
        Thread.sleep(1000L); // millis
        ParkingTicket ticket = parkingLot.releaseParkingSpace(licensePlate);
        assertThat(ticket.getDuration(), greaterThanOrEqualTo(0.5));
        assertThat(ticket.getChargingCost(), greaterThanOrEqualTo(parkingLot.getProperties().getChargingRate() * 1 / 60));
        assertThat(ticket.getParkingCost(), greaterThanOrEqualTo(parkingLot.getProperties().getParkingRate() * 1/60));
    }

    @Test
    public void testTicketElectricCarDerby() throws Exception {
        String licensePlate = "ELEC1";
        VehicleInterface car = parkingLotJdbcRepository.findByLicensePlate(licensePlate);
        assertNotNull(car);
        assertTrue("cannot get parking space", parkingLot.requestParkingSpace(car));
        Thread.sleep(1000L); // millis
        ParkingTicket ticket = parkingLot.releaseParkingSpace(licensePlate);
        assertThat(ticket.getDuration(), greaterThanOrEqualTo(0.5));
        assertThat(ticket.getChargingCost(), greaterThanOrEqualTo(parkingLot.getProperties().getChargingRate() * 1/60));
        assertThat(ticket.getParkingCost(), greaterThanOrEqualTo(parkingLot.getProperties().getParkingRate() * 1/60));
    }
}
