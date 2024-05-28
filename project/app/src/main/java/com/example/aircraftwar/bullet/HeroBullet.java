package com.example.aircraftwar.bullet;

public class HeroBullet extends BaseBullet{
    public HeroBullet(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }

    @Override
    public boolean hitObject() {
        return false;
    }
}
