package com.example.aircraftwar.factory.enemyFactory;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.aircraft.enemy.BaseEnemyEmoji;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiMob_2;
import com.example.aircraftwar.factory.bulletFactory.BulletFactory;
import com.example.aircraftwar.factory.bulletFactory.HeroBulletFactory;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.Forward;
import com.example.aircraftwar.strategy.shoot.DontShoot;

public class EnemyEmojiMobFactory_2 extends EnemyFactory{
    private BulletFactory bulletFactory;
    public EnemyEmojiMobFactory_2() {}
    @Override
    public BaseEnemyEmoji makeEnemy() {
        this.x = (int) (Math.random() * (GameActivity.screenWidth - ImageManager.ENEMY_MOB_1_IMAGE.getWidth()));
        this.y = (int) (Math.random() * GameActivity.screenHeight * 0.05);
        this.vx = (int) ((Math.random() - 0.5) * 20);
        this.vy = 5;
        this.hp = 60;
        this.bulletFactory = new HeroBulletFactory();
        BaseEnemyEmoji res = new EnemyEmojiMob_2(x,y,vx,vy);
        res.setMovePattern(new Forward().setSpeed(this.vy));
        //elite.setShootStrategy(new DirectShoot(bulletFactory));
        res.setShootStrategy(new DontShoot(bulletFactory));
        return res;
    }
}
