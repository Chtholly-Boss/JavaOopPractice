package com.example.aircraftwar.factory.enemyFactory;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.aircraft.enemy.BaseEnemyAircraft;
import com.example.aircraftwar.aircraft.enemy.EliteEnemyAircraft;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.Forward;
import com.example.aircraftwar.strategy.shoot.DirectShoot;

public class EliteEnemyFactory implements EnemyFactory{
    private int eliteHp = 60;
    private int vy = 5;
    @Override
    public BaseEnemyAircraft makeEnemy() {
        EliteEnemyAircraft elite = new EliteEnemyAircraft(
                (int) (Math.random() * (GameActivity.screenWidth - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * GameActivity.screenHeight * 0.05),
                (int) ((Math.random() - 0.5) * 20),
                (int) (this.eliteHp)
        );
        elite.setMovePattern(new Forward().setSpeed(this.vy));
        elite.setShootStrategy(new DirectShoot());
        return elite;
    }
}
