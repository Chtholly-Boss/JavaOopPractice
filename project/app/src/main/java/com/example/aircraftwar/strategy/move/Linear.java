package com.example.aircraftwar.strategy.move;

import com.example.aircraftwar.basic.AbstractFlyingObject;

public class Linear implements MoveStrategy{
    private int vx = 1;
    private int vy = 1;
    public Linear(){

    }
    private Linear(int _vx, int _vy) {
        this.vx = _vx;
        this.vy = _vy;
    }
    public Linear setSpeed(int vx,int vy) {
        return new Linear(vx,vy);
    }
    @Override
    public void moveWithPattern(AbstractFlyingObject src) {
        int x = src.getX();
        int y = src.getY();
        int directionX = src.getDirectionX();
        int directionY = src.getDirectionY();
        x += this.vx * directionX;
        y += this.vy * directionY;
        src.setX(x);
        src.setY(y);
        // TODO : Bound Check: X Direction Reverse, Y--Vanish
    }
}
