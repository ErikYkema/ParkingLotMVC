package com.dojo.parkinglot.repository;

import com.dojo.parkinglot.domain.ParkingLotProperties;
import com.dojo.parkinglot.repository.ParkingLotJdbcRepository;
import com.dojo.parkinglot.repository.ParkingLotLeanRepository;
import org.junit.Before;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ParkingLotLeanRepositoryTest {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
//todo JDBC test
    @Autowired
    ParkingLotLeanRepository repository;
    @Autowired
    ParkingLotProperties properties;

    @Test
    public void testSavePropertiesGetById() throws Exception {
        properties.setName("Foo");
        properties.setGenericSize(10);
        properties.setElectricSize(5);
        Integer id = repository.saveProperties(properties);
        properties = repository.getPropertiesById(id);
        LOG.debug("fetched: "+ properties.toString());
        assertThat(properties.getGenericSize(), is(10));
        assertThat(properties.getElectricSize(), is(5));
        assertThat(properties.getName(), is("Foo"));
    }

    @Test
    public void testSavePropertiesGetByName() throws Exception {
        String name = UUID.randomUUID().toString();
        LOG.debug(String.format("Name: %s", name));
        properties.setName(name);
        properties.setGenericSize(1);
        properties.setElectricSize(2);
        properties.setId(repository.saveProperties(properties));
        ParkingLotProperties propertiesByName = repository.getPropertiesByName(name);
        LOG.debug("fetched: "+ propertiesByName.toString());
        // TODO implement equals/hashcode
        assertThat(propertiesByName.getId(), is(properties.getId()));
        assertThat(propertiesByName.getName(), is(properties.getName()));
    }

    @Test
    public void testGetAllProperties() throws Exception {
    }

}