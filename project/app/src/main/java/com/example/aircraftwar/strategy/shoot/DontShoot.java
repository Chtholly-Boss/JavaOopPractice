package com.example.aircraftwar.strategy.shoot;

import com.example.aircraftwar.aircraft.BaseAircraft;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.factory.bulletFactory.BulletFactory;

import java.util.LinkedList;
import java.util.List;

public class DontShoot extends ShootStrategy{

    public DontShoot(BulletFactory factory) {
        super(factory);
    }

    @Override
    public List<BaseBullet> shootWithStrategy(BaseAircraft src) {
        return new LinkedList<>();
    }
}