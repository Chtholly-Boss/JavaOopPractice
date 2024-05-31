package com.example.aircraftwar.bullet;

import com.example.aircraftwar.aircraft.BaseAircraft;
import com.example.aircraftwar.basic.AbstractFlyingObject;

public abstract class BaseBullet extends AbstractFlyingObject {
    private int power;

    public BaseBullet(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }
    public BaseBullet(int _x, int _y, int _vx, int _vy,int _power) {
        super(_x, _y, _vx, _vy);
        this.power = _power;
    }
    // Hit a Flying Object that has hp
    // Return a boolean to determine whether hero get points
    public abstract boolean hitObject(BaseAircraft that);

    public void setPower(int power) {
        this.power = power;
    }
}
