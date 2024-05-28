package com.example.aircraftwar.item;

public class FireItem extends BaseItem{
    public FireItem(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }

    @Override
    public boolean onEffect() {
        return false;
    }
}
