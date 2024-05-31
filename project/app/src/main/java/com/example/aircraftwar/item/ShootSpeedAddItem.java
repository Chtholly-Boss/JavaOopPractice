package com.example.aircraftwar.item;

public class ShootSpeedAddItem extends BaseItem{
    public ShootSpeedAddItem(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }

    @Override
    public boolean onEffect() {
        // TODO : Change Shoot Cycle
        return false;
    }
}
