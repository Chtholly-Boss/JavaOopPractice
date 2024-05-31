package com.example.aircraftwar.item;

public class ShootBulletAdd extends BaseItem{
    private int bulletAdd = 1;
    public ShootBulletAdd(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }

    @Override
    public boolean onEffect() {
        if (this.crash(hero)) {
            hero.getShootStrategy().addBullet(this.bulletAdd);
            return true;
        }
        return false;
    }
}
