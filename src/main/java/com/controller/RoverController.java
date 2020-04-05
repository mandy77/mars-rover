package com.controller;


import com.Direction;
import com.model.Rover;
import com.model.Obstacle;

import java.util.List;


public class RoverController {

    private List<Obstacle> obstacles;
    private boolean foundObstacle = false;

    private Rover rover;

    public RoverController(Rover rover, List<Obstacle> obstacles) {
        this.rover = rover;
        this.obstacles = obstacles;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    protected boolean move(Direction directionValue) {
        int xLocation = rover.getPosition().getX().getLocation();
        int yLocation = rover.getPosition().getY().getLocation();
        switch (directionValue) {
            case NORTH:
                yLocation = rover.getPosition().getY().getForwardLocation();
                break;
            case EAST:
                xLocation = rover.getPosition().getX().getForwardLocation();
                break;
            case SOUTH:
                yLocation = rover.getPosition().getY().getBackwardLocation();
                break;
            case WEST:
                xLocation = rover.getPosition().getX().getBackwardLocation();
                break;
        }
        if (!hasObstacle(xLocation, yLocation)) {
            rover.getPosition().getX().setLocation(xLocation);
            rover.getPosition().getY().setLocation(yLocation);
            return true;
        } else {
            return false;
        }
    }

    public boolean moveForward() {
        return move(rover.getDirection());
    }

    public boolean moveBackward() {
        return move(rover.getDirection().reverseDirection());
    }

    private void changeDirection(Direction directionValue, int directionStep) {
        int directions = Direction.NUM_OF_DIRECTIONS;
        int index = (directions + directionValue.getValue() + directionStep) % directions;
        rover.setDirection(Direction.values()[index]);
    }

    public void turnLeft() {
        changeDirection(rover.getDirection(), -1);
    }

    public void turnRight() {
        changeDirection(rover.getDirection(), 1);
    }

    public void commands(String commands) throws Exception {
        for (char command : commands.toCharArray()) {
            if (!command(command)) {
                break;
            }
        }
    }

    public boolean command(char command) throws Exception {
        switch (Character.toUpperCase(command)) {
            case 'F':
                return moveForward();
            case 'B':
                return moveBackward();
            case 'L':
                turnLeft();
                return true;
            case 'R':
                turnRight();
                return true;
            default:
                throw new Exception("Invalid movement command, should be F, B, R or L");
        }
    }

    private boolean hasObstacle(int xLocation, int yLocation) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() == xLocation && obstacle.getY() == yLocation) {
                foundObstacle = true;
                return true;
            }
        }
        return false;
    }

    public String currentPosition() {
        String status = "";
        if (foundObstacle) {
            status = " NOK";
        }
        return String.format("%s %s %s%s", rover.getPosition().getX().getLocation(),
                rover.getPosition().getY().getLocation(), rover.getDirection().getShortName(), status);
    }
}
