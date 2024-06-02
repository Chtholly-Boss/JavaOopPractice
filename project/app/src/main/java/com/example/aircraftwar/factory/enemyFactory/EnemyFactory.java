package com.example.aircraftwar.factory.enemyFactory;

import com.example.aircraftwar.aircraft.enemy.BaseEnemyEmoji;
import com.example.aircraftwar.factory.bulletFactory.BulletFactory;

public abstract class EnemyFactory {
    // Optional Properties here
    protected int x;
    protected int y;
    protected int vx;
    protected int vy = 5;
    protected int hp = 60;
    protected BulletFactory bulletFactory;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }
    public abstract BaseEnemyEmoji makeEnemy();
}
