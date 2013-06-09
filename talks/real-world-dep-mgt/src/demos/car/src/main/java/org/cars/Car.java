package org.cars;

import com.google.common.collect.MapMaker;

import java.util.Map;

public class Car {

    Map locations = new MapMaker().makeMap();

    public Car() {
        locations.put("grocery", new Location());
    }

    public void driveTo(String location) {
        if (!locations.containsKey(location)) {
            throw new IllegalArgumentException("sorry, I don't know how to drive to " + location);
        }
    }

    private class Location {
    }
}