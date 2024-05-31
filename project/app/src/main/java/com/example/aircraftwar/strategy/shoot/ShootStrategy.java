package com.example.aircraftwar.strategy.shoot;

import com.example.aircraftwar.aircraft.BaseAircraft;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.factory.bulletFactory.BulletFactory;

import java.util.List;

public abstract class ShootStrategy {
    protected int shootNum = 0;
    protected int power = 0;
    protected BulletFactory factory;

    public ShootStrategy(BulletFactory factory) {
        this.factory = factory;
    }
    public void addPower(int delta) {
        this.power += delta;
    }
    public void addBullet(int delta){
        this.shootNum += delta;
    }

    public void setFactory(BulletFactory factory) {
        this.factory = factory;
    }

    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }

    public abstract List<BaseBullet> shootWithStrategy(BaseAircraft src);
}
