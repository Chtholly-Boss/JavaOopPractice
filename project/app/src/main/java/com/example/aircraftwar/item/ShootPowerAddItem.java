package com.example.aircraftwar.item;

public class ShootPowerAddItem extends BaseItem{
    private int powerDelta = 20;

    public ShootPowerAddItem(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }

    @Override
    public boolean onEffect() {
        if (this.crash(hero)) {
            hero.getShootStrategy().addPower(powerDelta);
            return true;
        }
        return false;
    }
}
