package com.example.aircraftwar.factory.bulletFactory;

import com.example.aircraftwar.bullet.BaseBullet;

public interface BulletFactory {
    // Only cares about creating a bullet,delay setting properties in Strategy
    BaseBullet makeBullet();
}
