package com.example.aircraftwar.aircraft;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.Stay;

public class HeroAircraft extends BaseAircraft{
    // TODO : Singleton Pattern---Hero should be initialize here
    private volatile static HeroAircraft __hero__;
    private int maxHp;
    private HeroAircraft() {
        super(GameActivity.screenWidth/2, GameActivity.screenHeight- ImageManager.HERO_IMAGE.getHeight(), 0, 0, 1000);
        this.maxHp = 1000;
        this.setMovePattern(new Stay()); // Controlled by Player
        // TODO : set ShootStrategy
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
