package com.dojo.parkinglot.service;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.repository.ParkingLotLeanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.invoke.MethodHandles;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ParkingServiceTest {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    //todo implement tests
    @Test
    public void test() {
        assertTrue(true);
    }

}