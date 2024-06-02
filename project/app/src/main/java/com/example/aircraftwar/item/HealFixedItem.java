package com.example.aircraftwar.item;

public class HealFixedItem extends BaseItem{
    private int recoverHp = 500;

    public HealFixedItem(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }

    @Override
    public boolean onEffect() {
        if (this.crash(hero)) {
            hero.addHp(recoverHp);
            this.vanish();
            return true;
        }
        return false;
    }
}
