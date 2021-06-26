package com.squadstack.parkingticketsystem;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Query Class to run queries working as DTO all business logic are called from here
 * */
public class Query {
    public Map<String, Method> queryMap;

    public Query() {
        queryMap = new HashMap<String, Method>();
        try {
            populateQueryHashMap();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void populateQueryHashMap() throws NoSuchMethodException {
        queryMap.put("Create_parking_lot", ParkingService.class.getMethod("initiateParking", Integer.class));
        queryMap.put("Park", ParkingService.class.getMethod("parkNewCar", String.class, Integer.class));
        queryMap.put("Leave", ParkingService.class.getMethod("leaveParkingSlot", Integer.class));
        queryMap.put("Vehicle_registration_number_for_driver_of_age", ParkingService.class.getMethod("getAllRegNoForAge", Integer.class));
        queryMap.put("Slot_number_for_car_with_number", ParkingService.class.getMethod("getSlotForRegNo", String.class));
        queryMap.put("Slot_numbers_for_driver_of_age", ParkingService.class.getMethod("getAllSlotNoForAge", Integer.class));
    }
}
