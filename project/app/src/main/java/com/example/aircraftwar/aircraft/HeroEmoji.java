package com.example.aircraftwar.aircraft;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.factory.bulletFactory.BulletFactory;
import com.example.aircraftwar.factory.bulletFactory.GreenBulletFactory;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.Stay;
import com.example.aircraftwar.strategy.shoot.DisperseShoot;

public class HeroEmoji extends BaseEmoji {
    private volatile static HeroEmoji __hero__;
    private int maxHp;

    private HeroEmoji() {
        super(GameActivity.screenWidth/2, GameActivity.screenHeight - 2 * ImageManager.HERO_IMAGE.getHeight(), 0, 10, 1000);
        this.maxHp = 1000;
        this.setMovePattern(new Stay()); // Controlled by Player
        this.bulletFactory = new GreenBulletFactory();
        this.shootStrategy = new DisperseShoot(bulletFactory);
    }
    public static HeroEmoji getInstance() {
        if (__hero__ == null) {
            synchronized (HeroEmoji.class) {
                if (__hero__ == null) {
                    __hero__ = new HeroEmoji();
                }
            }
        }
        return __hero__;
    }

    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public void setHp(int hp) {
        super.setHp(hp);
        this.maxHp = hp;
    }

    @Override
    public void addHp(int delta) {
        super.addHp(delta);
        if (this.hp > this.maxHp) this.hp = maxHp;
    }

    @Override
    public void vanish(){
        super.vanish();
        __hero__ = null;
    }
}
