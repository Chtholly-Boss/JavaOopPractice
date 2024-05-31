package com.example.aircraftwar.factory.bulletFactory;

import com.example.aircraftwar.aircraft.HeroAircraft;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.bullet.HeroBullet;

public class HeroBulletFactory implements BulletFactory{
    @Override
    public BaseBullet makeBullet() {
        HeroAircraft src = HeroAircraft.getInstance();
        return new HeroBullet(src.getX(),src.getY(),src.getVx(),src.getVy());
    }
}
