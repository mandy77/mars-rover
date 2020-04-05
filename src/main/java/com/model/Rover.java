package com.model;

import com.Direction;

public class Rover {

    private Position position;

    public Direction getDirection() {
        return direction;
    }

    private Direction direction;

    public void setPosition(Position value) {
        position = value;
    }

    public Position getPosition() {
        return position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Rover(Position positionValue, Direction direction) {
        setPosition(positionValue);
        setDirection(direction);
    }
}