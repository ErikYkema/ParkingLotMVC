package com.dojo.parkinglot.domain.parkingspace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.invoke.MethodHandles;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ParkingSpaceFactoryTest {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void newRegularParkingSpaceShouldBeCreated() {
        assertThat(ParkingSpaceFactory.createParkingSpace(ParkingSpaceTypeEnum.GENERIC),
                is(instanceOf(RegularParkingSpace.class)));
    }

    @Test
    public void newElectricParkingSpaceShouldBeCreated() {
        assertThat(ParkingSpaceFactory.createParkingSpace(ParkingSpaceTypeEnum.ELECTRIC),
                is(instanceOf(ElectricParkingSpace.class)));
    }
}
