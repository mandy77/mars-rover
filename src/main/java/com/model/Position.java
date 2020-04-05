package com.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Position  {
    private Point x;

    private Point y;
}
