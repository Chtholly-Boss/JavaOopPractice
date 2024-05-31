package com.example.aircraftwar.strategy.shoot;

import com.example.aircraftwar.aircraft.BaseAircraft;
import com.example.aircraftwar.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public class DirectShoot extends ShootStrategy{

    @Override
    public List<BaseBullet> shootWithStrategy(BaseAircraft src) {
        assert this.factory != null : "Bullet Type Not Specified!!!";
        List<BaseBullet> res = new LinkedList<>();
        int xSrc = src.getX();
        int ySrc = src.getY();
        // TODO : Bullet Mode
        BaseBullet bullet;
        for (int i = 0; i < this.shootNum; i++) {
            bullet = this.factory.makeBullet();
            bullet.setX(xSrc + (i * 2 - this.shootNum + 1) * 25);
            bullet.setY(ySrc);
            bullet.setVx(0);
            bullet.setVy(src.getVy());
            bullet.setDirectionY(src.getDirectionY());
            res.add(bullet);
        }
        return res;
    }
}
