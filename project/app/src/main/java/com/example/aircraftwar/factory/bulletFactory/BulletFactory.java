package com.example.aircraftwar.factory.bulletFactory;

import com.example.aircraftwar.aircraft.BaseAircraft;
import com.example.aircraftwar.bullet.BaseBullet;

public interface BulletFactory {
    BaseBullet makeBullet(BaseAircraft src);
}
