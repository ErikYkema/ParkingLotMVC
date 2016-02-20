package com.dojo.parkinglot.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.invoke.MethodHandles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ParkingLotPropertiesTest {

    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    ParkingLotProperties properties;

    @Test
    public void testGenericSize() throws Exception {
        properties.setGenericSize(10);
        assertThat(properties.getGenericSize(), is(10));
        properties.setGenericSize(0);
        assertThat(properties.getGenericSize(), is(0));
    }

    @Test
    public void testElectricSize() throws Exception {
        properties.setElectricSize(10);
        assertThat(properties.getElectricSize(), is(10));
        properties.setElectricSize(0);
        assertThat(properties.getElectricSize(), is(0));
    }

    @Test
    public void testGetId() throws Exception {
        properties.setId(1);
        assertThat(properties.getId(), is(1));
        properties.setId(-1);
        assertThat(properties.getId(), is(-1));
    }

    @Test
    public void testGetName() throws Exception {
        properties.setName("foo");
        assertThat(properties.getName(), is("foo"));
        properties.setName("bar");
        assertThat(properties.getName(), is("bar"));
    }

}