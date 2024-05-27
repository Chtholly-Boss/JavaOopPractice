package com.example.aircraftwar.strategy.move;

import com.example.aircraftwar.basic.AbstractFlyingObject;

public class Forward implements MoveStrategy{
    private int speed = 1;
    private Forward(int _speed){
        this.speed = _speed;
    }
    // DIY the speed
    public Forward setSpeed(int _speed) {
        return new Forward(_speed);
    }
    @Override
    // Move forward
    public void moveWithPattern(AbstractFlyingObject src) {
        int yNew = src.getY();
        int direction = src.getDirectionY();
        yNew += this.speed * direction;
        src.setY(yNew);
        // TODO: Go out of the Bound--Vanished
    }
}
