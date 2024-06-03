package com.example.aircraftwar.factory.enemyFactory;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.aircraft.enemy.BaseEnemyEmoji;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiMob_1;
import com.example.aircraftwar.factory.bulletFactory.GreenBulletFactory;
import com.example.aircraftwar.factory.bulletFactory.PureBulletFactory;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.Forward;
import com.example.aircraftwar.strategy.shoot.DirectShoot;
import com.example.aircraftwar.strategy.shoot.DontShoot;

public class EnemyEmojiMobFactory_1 extends EnemyFactory{

    public EnemyEmojiMobFactory_1() {}
    @Override
    public BaseEnemyEmoji makeEnemy() {
        this.x = (int) (Math.random() * (GameActivity.screenWidth - ImageManager.ENEMY_MOB_1_IMAGE.getWidth()));
        this.y = (int) (Math.random() * GameActivity.screenHeight * 0.05);
        this.vx = (int) ((Math.random() - 0.5) * 20);
        this.vy = 5;
        this.hp = 60;
        this.bulletFactory = new PureBulletFactory();
        BaseEnemyEmoji res = new EnemyEmojiMob_1(x,y,vx,vy);
        res.setMovePattern(new Forward().setSpeed(this.vy));
        res.setBulletFactory(new PureBulletFactory());
        res.setShootStrategy(new DirectShoot(bulletFactory));
        res.getShootStrategy().setShootInterval(1000);
        return res;
    }
}
