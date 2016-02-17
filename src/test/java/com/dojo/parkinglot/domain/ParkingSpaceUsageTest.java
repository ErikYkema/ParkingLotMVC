package com.dojo.parkinglot.domain;

import com.dojo.parkinglot.domain.car.GenericCar;
import com.dojo.parkinglot.domain.parkingspace.RegularParkingSpace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.invoke.MethodHandles;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ParkingSpaceUsageTest {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private GenericCar car;

    @Test
    public void assertThatNewParkingSpaceCountsDuration() {
        double duration = 100.0;
        ParkingSpaceUsage parkingSpaceUsage = new ParkingSpaceUsage(car);
        try {
            Thread.sleep((long) duration);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        assertThat(parkingSpaceUsage.getParkingSpace(), is(instanceOf(RegularParkingSpace.class)));
        assertThat(parkingSpaceUsage.getParkingDuration()*1000, greaterThanOrEqualTo(duration));
    }
}
