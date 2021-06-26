package com.squadstack.parkingticketsystem;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**Parser Class to read to Parse the Query
 * */
public class QueryParser {
    private final Query queries;
    private static ParkingService parkingService;

    public QueryParser() {
        queries = new Query();
        parkingService = new ParkingService();
    }

    public void parseQueryInput(String inputString) {
        // Split the input string to get command and input value
        String[] inputs = inputString.split(" ");
        switch (inputs[0]) {
            case "Park":
                try {
                    Method method = queries.queryMap.get(inputs[0]);
                    if (method != null) {
                        try {
                            String regNo = inputs[1];
                            Integer age = Integer.parseInt(inputs[3]);
                            method.invoke(parkingService, regNo, age);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Lot size");
                            e.printStackTrace();
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case "Create_parking_lot":
            case "Leave":
            case "Vehicle_registration_number_for_driver_of_age":
            case "Slot_numbers_for_driver_of_age" :
                try {
                    Method method = queries.queryMap.get(inputs[1]);
                    if (method != null) {
                        try {
                            Integer inputArg = Integer.parseInt(inputs[1]);
                            method.invoke(parkingService, inputArg);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Lot size");
                            e.printStackTrace();
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case "Slot_number_for_car_with_number" :
                try {
                    Method method = queries.queryMap.get(inputs[1]);
                    if (method != null) {
                        try {
                            method.invoke(parkingService, inputs[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Lot size");
                            e.printStackTrace();
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + inputs[0]);
        }
    }

    public void parseFileInput(String filePath) {
        // Assuming input to be a valid file path.
        File inputFile = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    parseQueryInput(line.trim());
                }
            } catch (IOException ex) {
                System.out.println("Error in reading the input file.");
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the path specified.");
            e.printStackTrace();
        }
    }
}
