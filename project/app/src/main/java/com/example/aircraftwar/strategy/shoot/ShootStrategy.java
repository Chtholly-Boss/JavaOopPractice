package com.example.aircraftwar.strategy.shoot;

import com.example.aircraftwar.aircraft.BaseAircraft;
import com.example.aircraftwar.bullet.BaseBullet;

import java.util.List;

public interface ShootStrategy {
    List<BaseBullet> shootWithStrategy(BaseAircraft src);
}
