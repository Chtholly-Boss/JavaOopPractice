package com.example.aircraftwar.aircraft;

import com.example.aircraftwar.basic.AbstractFlyingObject;

public abstract class BaseAircraft extends AbstractFlyingObject {
    protected int hp;
    public BaseAircraft(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }
    public BaseAircraft(int _x, int _y, int _vx, int _vy,int _hp) {
        super(_x, _y, _vx, _vy);
        this.hp = _hp;
    }
    public void updateHp(int delta) {
        this.hp += delta;
        if (this.hp <= 0) this.vanish();
    }

    public int getHp() {
        return hp;
    }
}
