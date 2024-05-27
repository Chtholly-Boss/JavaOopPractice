package com.example.aircraftwar.factory.enemyFactory;

import com.example.aircraftwar.aircraft.enemy.BaseEnemyAircraft;

public interface EnemyFactory {
    BaseEnemyAircraft makeEnemy(int x,int y);
}
