package com;

import com.controller.RoverController;
import com.model.Obstacle;
import com.model.Point;
import com.model.Position;
import com.model.Rover;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final String ROVER_POS_MESSAGE = "Enter Rover starting position: ";
    private static final String INVALID_ROVER_POS_MESSAGE = "Invalid position, enter a valid position for example 1 2 N";
    private static final String COMMANDS = "Enter movement commands: ";
    private static final String EXIT_MESSAGE = "exit";
    private static final int AXIS_MAXIM = 99;

    private static Rover rover;
    private static RoverController roverController;
    private static Direction direction;

    private static boolean invalidRoverPos = true;
    private static boolean exit = false;

    private static int xRoverStart, yRoverStart;

    public static void main(String[] args) {

        setRoverStartingPosition();
        while(!exit) {
            moveRover();
        }
    }

    public static void setRoverStartingPosition() {
        while (invalidRoverPos) {
            System.out.print(ROVER_POS_MESSAGE);
            Scanner scanner1 = new Scanner(System.in);
            if (scanner1.hasNextInt()) {
                xRoverStart = scanner1.nextInt();
                if (scanner1.hasNextInt()) {
                    yRoverStart = scanner1.nextInt();
                    if (scanner1.hasNext()) {
                        invalidRoverPos = false;
                        resolveDirection(scanner1.next().charAt(0));
                    }
                } else {
                    System.out.println(INVALID_ROVER_POS_MESSAGE);
                }
            } else {
                System.out.println(INVALID_ROVER_POS_MESSAGE);
            }
        }

        Point x = Point.builder()
                .location(xRoverStart)
                .maxLocation(AXIS_MAXIM)
                .build();

        Point y = Point.builder()
                .location(yRoverStart)
                .maxLocation(AXIS_MAXIM)
                .build();

        Position roverPosition = Position.builder()
                .x(x)
                .y(y)
                .build();

        rover = new Rover(roverPosition, direction);

        List<Obstacle> obstacles = new ArrayList<Obstacle>();
        Obstacle obs = Obstacle.builder()
                .x(AXIS_MAXIM / 2)
                .y(AXIS_MAXIM / 2)
                .build();
        obstacles.add(obs);

        roverController = new RoverController(rover, obstacles);
    }


    public static void resolveDirection(char command) {
        switch (Character.toUpperCase(command)) {
            case 'N':
                direction = Direction.NORTH;
                break;
            case 'E':
                direction = Direction.EAST;
                break;
            case 'S':
                direction = Direction.SOUTH;
                break;
            case 'W':
                direction = Direction.WEST;
                break;
            default:
                invalidRoverPos = true;
                System.out.println(INVALID_ROVER_POS_MESSAGE);
        }
    }

    public static void moveRover() {
        System.out.print(COMMANDS);
        Scanner scanner = new Scanner(System.in);
        String commands = scanner.next();
        if (!EXIT_MESSAGE.equalsIgnoreCase(commands)) {
            try {
                roverController.commands(commands);
                System.out.println(roverController.currentPosition());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            exit = true;
        }
    }
}
