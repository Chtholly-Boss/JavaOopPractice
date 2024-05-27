package com.example.aircraftwar.aircraft;

import com.example.aircraftwar.strategy.move.Stay;

public class HeroAircraft extends BaseAircraft{
    // TODO : Singleton Pattern---Hero should be initialize here
    private static HeroAircraft __hero__;
    private int maxHp;
    private HeroAircraft(int _x, int _y, int _vx, int _vy, int _hp) {
        super(_x, _y, _vx, _vy, _hp);
        this.maxHp = _hp;
        this.setMovePattern(new Stay()); // Controlled by Player
    }
    public static HeroAircraft getInstance() {
        return __hero__;
    }

    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public void updateHp(int delta) {
        super.updateHp(delta);
        if (this.hp > this.maxHp) this.hp = maxHp;
    }
}
