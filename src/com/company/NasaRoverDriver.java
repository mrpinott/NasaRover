package com.company;


import java.util.Scanner;

/**
 * NasaRoverDriver class is the driver program for the NasaRover. Uses the NasaRover class
 * for Mars exploration. Program assumes that the ability to direct a specific rover to move
 * is outside of scope or outside of the minimum viable product (MVP) route.
 *
 * Program takes a total of 3 different kinds of inputs, with item 4 as optional for program
 * exit/termination. The program runs indefinitely after initial grid size, item 1 below, is
 * entered. Subsequently, the program awaits input for items 2 & 3.
 *
 * 1. "x y" = Input 1: initializes grid size as 2d array
 * 2. x y z = Input 2: positionX, positionY, heading
 * 3. "L", "R", "M" = Input 3: move direction
 *
 * Optional
 * 4. "e", or "E" = Input 4: terminate program
 */
public class NasaRoverDriver {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String instructions;
        NasaRover rover = null;
        boolean run = true;

        // initial print
        System.out.println("Initializing NasaRover program...\n");

        while(run) {
            try {
                // read user input
                instructions = scan.nextLine().toUpperCase();
                if (isValidInput(instructions)) {
                    // assumption: rover’s current position and heading will always parse on space
                    String[] instruction = instructions.split("\\s"); // split user input on empty space
                    // initialize NasaRover
                    if (rover == null) {
                        rover = new NasaRover(Integer.valueOf(instruction[0]), Integer.valueOf(instruction[1]));
                    } else {
                        // new position will be X Y Z
                        if (instruction.length > 1)
                            // set current position and heading
                            rover.updatePosition(instruction);
                        else // move
                            rover.move(instruction[0]);
                    }
                }
                // added scope, for program exit
                else if (instructions.equals("E")) {
                    // exit program
                    run = false;
                    System.out.println("Terminating NasaRover program...\n");
                }
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    // simple data check
    private static boolean isValidInput(String s) {
        return s.length() > 1;
    }
}
