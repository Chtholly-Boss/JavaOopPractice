package com.example.aircraftwar.factory.enemyFactory;

import com.example.aircraftwar.aircraft.enemy.BaseEnemyEmoji;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiBoss_1;
import com.example.aircraftwar.factory.bulletFactory.GreenBulletFactory;
import com.example.aircraftwar.factory.bulletFactory.PureBulletFactory;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.Horizontal;
import com.example.aircraftwar.strategy.move.Stay;
import com.example.aircraftwar.strategy.shoot.DirectShoot;
import com.example.aircraftwar.strategy.shoot.DontShoot;

public class EnemyEmojiBossFactory_1 extends EnemyFactory{
    @Override
    public BaseEnemyEmoji makeEnemy() {
        this.x = ImageManager.ENEMY_BOSS_1_IMAGE.getWidth() /2;
        this.y = ImageManager.ENEMY_BOSS_1_IMAGE.getHeight() / 2;
        this.hp = 2000;
        this.bulletFactory = new PureBulletFactory();
        BaseEnemyEmoji res = new EnemyEmojiBoss_1(x,y,0,0);
        res.setHp(this.hp);
        //res.setMovePattern(new Stay());
        res.setMovePattern(new Horizontal().setSpeed(10));
        res.setBulletFactory(new PureBulletFactory());
        res.setShootStrategy(new DirectShoot(bulletFactory));
        res.getShootStrategy().setShootInterval(500);
        return res;
    }
}
