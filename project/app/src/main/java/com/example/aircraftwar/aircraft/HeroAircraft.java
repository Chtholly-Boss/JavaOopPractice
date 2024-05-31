package com.example.aircraftwar.aircraft;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.factory.bulletFactory.BulletFactory;
import com.example.aircraftwar.factory.bulletFactory.HeroBulletFactory;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.Stay;
import com.example.aircraftwar.strategy.shoot.DirectShoot;

public class HeroAircraft extends BaseAircraft{
    private volatile static HeroAircraft __hero__;
    private BulletFactory bulletFactory;
    private int maxHp;
    private HeroAircraft() {
        super(GameActivity.screenWidth/2, GameActivity.screenHeight- ImageManager.HERO_IMAGE.getHeight(), 0, 0, 1000);
        this.maxHp = 1000;
        this.setMovePattern(new Stay()); // Controlled by Player
        this.bulletFactory = new HeroBulletFactory();
        this.shootStrategy = new DirectShoot(bulletFactory);
    }
    public static HeroAircraft getInstance() {
        if (__hero__ == null) {
            synchronized (HeroAircraft.class) {
                if (__hero__ == null) {
                    __hero__ = new HeroAircraft();
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
}
