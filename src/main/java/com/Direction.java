package com;

public enum Direction {

    NORTH(0, 'N'),
    EAST(1, 'E'),
    SOUTH(2, 'S'),
    WEST(3, 'W');

    public static int NUM_OF_DIRECTIONS = 4;
    private int value;
    private char shortName;

    Direction(int newValue, char shortNameValue) {
        value = newValue;
        shortName = shortNameValue;
    }

    public Direction reverseDirection() {
        return values()[(value + 2) % NUM_OF_DIRECTIONS];
    }

    public int getValue() {
        return value;
    }

    public char getShortName() { return shortName; }

}