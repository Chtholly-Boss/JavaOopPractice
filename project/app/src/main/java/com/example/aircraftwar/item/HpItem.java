package com.example.aircraftwar.item;

import com.example.aircraftwar.aircraft.HeroAircraft;

public class HpItem extends BaseItem{
    private int recoverHp = 1;

    public HpItem(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }

    @Override
    public boolean onEffect() {
        HeroAircraft hero = HeroAircraft.getInstance();
        this.recoverHp = (int)(hero.getMaxHp() * 0.1);
        if (this.crash(hero)) {
            hero.updateHp(recoverHp);
            return true;
        }
        return false;
    }
}
