package com.example.aircraftwar.aircraft;

import com.example.aircraftwar.basic.AbstractFlyingObject;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.strategy.shoot.ShootStrategy;

import java.util.List;

public abstract class BaseEmoji extends AbstractFlyingObject {
    protected int hp;
    protected ShootStrategy shootStrategy;
    public BaseEmoji(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }
    public BaseEmoji(int _x, int _y, int _vx, int _vy, int _hp) {
        super(_x, _y, _vx, _vy);
        this.hp = _hp;
    }
    public void addHp(int delta) {
        this.hp += delta;
        if (this.hp <= 0) this.vanish();
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setShootStrategy(ShootStrategy shootStrategy) {
        this.shootStrategy = shootStrategy;
    }

    public ShootStrategy getShootStrategy() {
        return shootStrategy;
    }
    public List<BaseBullet> shoot() {
        assert shootStrategy != null : "Shoot Strategy unset!!!";
        return shootStrategy.shootWithStrategy(this);
    }
}
