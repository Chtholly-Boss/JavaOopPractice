package com.example.aircraftwar.strategy.move;

import com.example.aircraftwar.basic.AbstractFlyingObject;

public class Horizontal implements MoveStrategy{
    private int speed = 1;
    private Horizontal(int _speed) {
        speed = _speed;
    }

    public Horizontal setSpeed(int speed) {
        return new Horizontal(speed);
    }

    @Override
    public void moveWithPattern(AbstractFlyingObject src) {
        int xNew = src.getX();
        int direction = src.getDirectionX();
        xNew += this.speed * direction;
        src.setX(xNew);
        // TODO : Go out of Bound--Vanished
    }
}
