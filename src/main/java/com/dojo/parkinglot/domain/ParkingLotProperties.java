package com.dojo.parkinglot.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="properties")
@Component("properties")
public class ParkingLotProperties {
    private int genericSize;
    private int electricSize;
    private int id;
    private String name;
    private double parkingRate;
    private double chargingRate;


    public ParkingLotProperties() {
    }

    public int getGenericSize() {
        return genericSize;
    }

    public void setGenericSize(int genericSize) {
        this.genericSize = genericSize;
    }

    public int getElectricSize() {
        return electricSize;
    }

    public void setElectricSize(int electricSize) {
        this.electricSize = electricSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(double parkingRate) {
        this.parkingRate = parkingRate;
    }

    public double getChargingRate() {
        return chargingRate;
    }

    public void setChargingRate(double chargingRate) {
        this.chargingRate = chargingRate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
