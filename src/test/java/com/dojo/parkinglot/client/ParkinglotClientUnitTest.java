package com.dojo.parkinglot.client;

import com.dojo.parkinglot.resource.ParkingResource;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.invoke.MethodHandles;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

// http://stackoverflow.com/questions/16170572/unable-to-mock-service-class-in-spring-mvc-controller-tests

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ParkinglotClientUnitTest {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Before
    public void beforeEachTest() {
        RestAssuredMockMvc.standaloneSetup(new ParkingResource());
    }

    @Ignore
    @Test
    public void postEntranceOK() {
        when().get("/webapi/parkingResource/entrance").then().statusCode(is(200));
    }

}
