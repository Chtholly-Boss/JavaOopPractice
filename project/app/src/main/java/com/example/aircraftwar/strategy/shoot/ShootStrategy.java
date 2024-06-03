package com.example.aircraftwar.strategy.shoot;

import com.example.aircraftwar.aircraft.BaseEmoji;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.factory.bulletFactory.BulletFactory;

import java.util.List;

public abstract class ShootStrategy {
    protected int shootNum = 2;
    protected int power = 100;
    protected int shootInterval = 100; // ms
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

    public int getShootInterval() {
        return shootInterval;
    }

    public void setShootInterval(int shootInterval) {
        this.shootInterval = shootInterval;
    }

    public abstract List<BaseBullet> shootWithStrategy(BaseEmoji src);
}
