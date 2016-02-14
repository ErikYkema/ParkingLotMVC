package com.dojo.parkinglot.domain;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingLotPropertiesTest {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    ParkingLotProperties properties;

    @Before
    public void setUp() {
        properties = new ParkingLotProperties();
    }

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