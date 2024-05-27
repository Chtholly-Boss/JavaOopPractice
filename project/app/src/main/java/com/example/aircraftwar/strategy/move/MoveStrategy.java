package com.example.aircraftwar.strategy.move;

import com.example.aircraftwar.basic.AbstractFlyingObject;

public interface MoveStrategy {
    void moveWithPattern(AbstractFlyingObject src);
}
