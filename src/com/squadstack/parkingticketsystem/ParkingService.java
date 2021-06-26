package com.squadstack.parkingticketsystem;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Service Class i.e. Main Business logic.
 * */
public class ParkingService {
    private Map<Integer, CarDetails> slotToCarMap;
    private Map<String, Integer> regNoToSlotMap;
    private Map<Integer, List<String>> ageToRegNoMap;

    private List<Integer> availableSlots;

    public void initiateParking(final Integer size) {
        ParkingLot.PARKING_SIZE = size;
        slotToCarMap = new HashMap<>();
        regNoToSlotMap = new HashMap<>();
        ageToRegNoMap = new HashMap<>();
        availableSlots = Stream.iterate(1, n -> n + 1)
                .limit(ParkingLot.PARKING_SIZE)
                .collect(Collectors.toList());
        System.out.printf("Created parking of %d slots %n", ParkingLot.PARKING_SIZE);
    }

    public void parkNewCar(final String carRegNo, final Integer driverAge) {
        if (ParkingLot.PARKING_SIZE.equals(0)) {
            System.out.println("Can't park car since parking lot is not created");
        } else if (slotToCarMap.size() == ParkingLot.PARKING_SIZE) {
            System.out.println("Can't park car since parking lot is full.");
        } else {
            Collections.sort(availableSlots);
            Integer allocateSlot = availableSlots.get(0);
            availableSlots.remove(0);

            CarDetails newCarDetails = new CarDetails(carRegNo, driverAge);

            slotToCarMap.put(allocateSlot, newCarDetails);
            regNoToSlotMap.put(carRegNo, allocateSlot);
            if (ageToRegNoMap.containsKey(driverAge)) {
                ageToRegNoMap.get(driverAge).add(newCarDetails.getRegNo());
            } else {
                List<String> newRegNoList = new ArrayList<>();
                newRegNoList.add(newCarDetails.getRegNo());
                ageToRegNoMap.put(driverAge, newRegNoList);
            }
            System.out.println("Car with vehicle registration number " + newCarDetails.getRegNo() + " has been parked at slot number " + allocateSlot);
        }
    }

    public void leaveParkingSlot(final Integer parkingSlot) {
        if (ParkingLot.PARKING_SIZE.equals(0)) {
            System.out.println("Parking lot is not created!");
        } else if (!slotToCarMap.containsKey(parkingSlot)) {
            System.out.println("No car parked at slot No. " + parkingSlot);
        } else {
            CarDetails leavingCarDetails = slotToCarMap.get(parkingSlot);
            availableSlots.add(parkingSlot);
            slotToCarMap.remove(parkingSlot);
            regNoToSlotMap.remove(leavingCarDetails.getRegNo());
            ageToRegNoMap.get(leavingCarDetails.getDriverAge()).remove(leavingCarDetails.getRegNo());
            if (ageToRegNoMap.get(leavingCarDetails.getDriverAge()).size() == 0) {
                ageToRegNoMap.remove(leavingCarDetails.getDriverAge());
            }
            System.out.println("Slot number " + parkingSlot + " vacated, the car with vehicle registration number "
                    + leavingCarDetails.getRegNo() + " left the space, the driver of the car was of age " + leavingCarDetails.getDriverAge());
        }
    }

    public void getAllRegNoForAge(final Integer age) {
        if (ParkingLot.PARKING_SIZE.equals(0)) {
            System.out.println("Parking lot is not created!");
        } else if (ageToRegNoMap.containsKey(age)) {
            List<String> regNoList = ageToRegNoMap.get(age);
            for (int i = 0; i < regNoList.size(); i++) {
                System.out.print(regNoList.get(i));
                if (i + 1 < regNoList.size()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println();
        }
    }

    public void getSlotForRegNo(final String regNo) {
        if (ParkingLot.PARKING_SIZE.equals(0)) {
            System.out.println("Parking lot not created!");
        } else if (regNoToSlotMap.containsKey(regNo)) {
            System.out.println(regNoToSlotMap.get(regNo));
        } else {
            System.out.println("No car parked with registration no. " + regNo);
        }
    }

    public void getAllSlotNoForAge(final Integer age) {
        if (ParkingLot.PARKING_SIZE.equals(0)) {
            System.out.println("Parking lot not created!");
        } else if (ageToRegNoMap.containsKey(age)) {
            List<String> regNoList = ageToRegNoMap.get(age);
            for (int i = 0; i < regNoList.size(); i++) {
                String regNo = regNoList.get(i);
                System.out.print(regNoToSlotMap.get(regNo));
                if (i + 1 < regNoList.size()) {
                    System.out.print(",");
                }
            }
        } else {
            System.out.println();
        }
    }
}
