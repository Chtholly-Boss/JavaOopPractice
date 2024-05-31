package com.example.aircraftwar.factory.enemyFactory;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.aircraft.enemy.BaseEnemyAircraft;
import com.example.aircraftwar.aircraft.enemy.EliteEnemyAircraft;
import com.example.aircraftwar.factory.bulletFactory.BulletFactory;
import com.example.aircraftwar.factory.bulletFactory.HeroBulletFactory;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.Forward;
import com.example.aircraftwar.strategy.shoot.DirectShoot;

public class EliteEnemyFactory extends EnemyFactory{
    private BulletFactory bulletFactory;
    public EliteEnemyFactory() {
        this.x = (int) (Math.random() * (GameActivity.screenWidth - ImageManager.ELITE_ENEMY_IMAGE.getWidth()));
        this.y = (int) (Math.random() * GameActivity.screenHeight * 0.05);
        this.vx = (int) ((Math.random() - 0.5) * 20);
        this.vy = 5;
        this.hp = 60;
        this.bulletFactory = new HeroBulletFactory();
    }
    @Override
    public BaseEnemyAircraft makeEnemy() {
        EliteEnemyAircraft elite = new EliteEnemyAircraft(x,y,vx,vy);
        elite.setMovePattern(new Forward().setSpeed(this.vy));
        elite.setShootStrategy(new DirectShoot(bulletFactory));
        return elite;
    }
}
