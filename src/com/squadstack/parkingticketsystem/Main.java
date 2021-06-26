package com.squadstack.parkingticketsystem;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        QueryParser queryParser = new QueryParser();
        switch (args.length) {
            case 0:
                System.out.println("Please enter 'exit' to quit");
                System.out.println("Waiting for input...");
                // Interactive command-line input/output
                // Run an infinite loop
                for (;;) {
                    try {

                        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                        String inputString = bufferRead.readLine();
                        if (inputString.equalsIgnoreCase("exit")) {
                            break;
                        } else if ((inputString == null) || (inputString.isEmpty())) {
                            // Do nothing
                        } else {
                            queryParser.parseQueryInput(inputString.trim());
                        }
                    } catch(IOException e) {
                        System.out.println("Oops! Error in reading the input from console.");
                        e.printStackTrace();
                    }
                }
                break;
            case 1:
                // File input/output
                queryParser.parseFileInput(args[0]);
                break;
            default:
                System.out.println("Invalid input. Usage: java -jar <jar_file_path> <input_file_path>");
        }
    }
}
