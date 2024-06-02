package com.example.aircraftwar.item;

public class ShootBulletAddItem extends BaseItem{
    private int bulletAdd = 1;
    public ShootBulletAddItem(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }

    @Override
    public boolean onEffect() {
        if (this.crash(hero)) {
            hero.getShootStrategy().addBullet(this.bulletAdd);
            this.vanish();
            return true;
        }
        return false;
    }
}
