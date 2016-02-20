package com.dojo.parkinglot.domain;

import com.dojo.parkinglot.domain.car.VehicleInterface;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

@Component
public class FreeSpaceCounter {
    private final static Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public FreeSpaceCounter() {
    }

    private Map<Class, Integer> maxFreeSpace = new HashMap<>();

    private Map<Class, Integer> freeSpace = new HashMap<>();

    public void setFreeSpace(Class aClass, Integer size) {
        LOG.debug("setting freespace");
        maxFreeSpace.put(aClass, size);
        freeSpace.put(aClass, size);
    }

    private boolean hasSpace (VehicleInterface vehicle) {
        return (freeSpace.get(vehicle.getClass()) > 0);
    }

    public Integer getFreeSpace(VehicleInterface vehicle) {
        return freeSpace.get(vehicle.getClass());
    }

    public boolean useSpace(VehicleInterface vehicle) {
        LOG.debug(String.format("useSpace() called for %s", vehicle.getClass().toString()));
        if (hasSpace(vehicle)) {
            freeSpace.put(vehicle.getClass(), getFreeSpace(vehicle) - 1);
            return true;
        }
        return false;
    }

    public void release(VehicleInterface vehicle) {
        if (freeSpace.get(vehicle.getClass()) < maxFreeSpace.get(vehicle.getClass())) {
            freeSpace.put(vehicle.getClass(), getFreeSpace(vehicle) + 1);
        } else {
            throw new RuntimeException("Cannot release beyond upper limit!");
        }
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public Map<Class, Integer> getFreeSpace() {
        return freeSpace;
    }

    public Map<Class, Integer> getMaxFreeSpace() {
        return maxFreeSpace;
    }
}
