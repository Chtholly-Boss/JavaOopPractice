package com.example.aircraftwar.strategy.move;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.basic.AbstractFlyingObject;

public class Horizontal implements MoveStrategy{
    private int speed = 1;
    public Horizontal(){}
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
        if (xNew - src.getWidth()/2  < 0 || xNew + src.getWidth()/2 > GameActivity.screenWidth) {
            src.setDirectionX(-direction);
        }
    }
}
