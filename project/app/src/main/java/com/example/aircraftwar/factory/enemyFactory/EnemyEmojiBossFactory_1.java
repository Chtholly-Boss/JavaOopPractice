package com.example.aircraftwar.factory.enemyFactory;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.aircraft.enemy.BaseEnemyEmoji;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiBoss_1;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiMob_1;
import com.example.aircraftwar.factory.bulletFactory.HeroBulletFactory;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.Forward;
import com.example.aircraftwar.strategy.move.Stay;
import com.example.aircraftwar.strategy.shoot.DontShoot;

public class EnemyEmojiBossFactory_1 extends EnemyFactory{
    @Override
    public BaseEnemyEmoji makeEnemy() {
        this.x = ImageManager.ENEMY_BOSS_1_IMAGE.getWidth() /2;
        this.y = ImageManager.ENEMY_BOSS_1_IMAGE.getHeight() / 2;
        this.hp = 200000;
        this.bulletFactory = new HeroBulletFactory();
        BaseEnemyEmoji res = new EnemyEmojiBoss_1(x,y,0,0);
        res.setHp(this.hp);
        res.setMovePattern(new Stay());
        res.setShootStrategy(new DontShoot(bulletFactory));
        return res;
    }
}