package com.example.aircraftwar.factory.enemyFactory;

import com.example.aircraftwar.aircraft.enemy.BaseEnemyEmoji;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiBoss_2;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiBoss_3;
import com.example.aircraftwar.factory.bulletFactory.PureBulletFactory;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.Horizontal;
import com.example.aircraftwar.strategy.shoot.DirectShoot;
import com.example.aircraftwar.strategy.shoot.DisperseShoot;
import com.example.aircraftwar.strategy.shoot.RingShoot;

public class EnemyEmojiBossFactory_3 extends EnemyFactory{
    @Override
    public BaseEnemyEmoji makeEnemy() {
        this.x = ImageManager.ENEMY_BOSS_1_IMAGE.getWidth() /2;
        this.y = ImageManager.ENEMY_BOSS_1_IMAGE.getHeight() / 2;
        this.hp = 8000;
        this.bulletFactory = new PureBulletFactory();
        BaseEnemyEmoji res = new EnemyEmojiBoss_3(x,y,0,0);
        res.setDirectionY(1);
        res.setVy(10);
        res.setHp(this.hp);
        res.setMovePattern(new Horizontal().setSpeed(10));
        res.setBulletFactory(new PureBulletFactory());
        res.setShootStrategy(new DisperseShoot(this.bulletFactory));
        res.getShootStrategy().setShootInterval(500);
        res.getShootStrategy().setShootNum(10);
        return res;
    }
}
