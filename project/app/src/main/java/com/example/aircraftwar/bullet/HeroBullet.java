package com.example.aircraftwar.bullet;

import com.example.aircraftwar.aircraft.BaseAircraft;
import com.example.aircraftwar.strategy.move.Forward;

public class HeroBullet extends BaseBullet{
    public HeroBullet(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
        this.movePattern = new Forward().setSpeed(_vy);
        this.directionY = -1;
    }

    @Override
    public boolean hitObject(BaseAircraft that) {
        return false;
    }
}
