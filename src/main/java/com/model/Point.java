package com.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Point {

    private int location;

    private int maxLocation;

    public int getForwardLocation() {
        return (location + 1) % (maxLocation + 1);
    }

    public int getBackwardLocation() {
        if (location > 0) return location - 1;
        else return getMaxLocation();
    }

}