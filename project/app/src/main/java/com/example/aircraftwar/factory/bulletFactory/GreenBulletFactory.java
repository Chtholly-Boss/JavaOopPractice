package com.example.aircraftwar.factory.bulletFactory;

import com.example.aircraftwar.aircraft.HeroEmoji;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.bullet.GreenBullet;

public class GreenBulletFactory implements BulletFactory{
    @Override
    public BaseBullet makeBullet() {
        HeroEmoji src = HeroEmoji.getInstance();
        return new GreenBullet(src.getX(),src.getY(),src.getVx(),src.getVy());
    }
}
