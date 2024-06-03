package com.example.aircraftwar.bullet;

import com.example.aircraftwar.aircraft.BaseEmoji;
import com.example.aircraftwar.strategy.move.Forward;

public class GoldenBullet extends BaseBullet{
    public GoldenBullet(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
        this.movePattern = new Forward().setSpeed(_vy);
        this.directionY = -1;
        this.power = 100;
    }

    @Override
    public boolean hitObject(BaseEmoji that) {
        if (this.crash(that)) {
            that.addHp(-this.power);
            this.vanish();
            return true;
        }
        return false;
    }
}
