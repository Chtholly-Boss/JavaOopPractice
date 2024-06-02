package com.example.aircraftwar.strategy.shoot;

import com.example.aircraftwar.aircraft.BaseEmoji;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.factory.bulletFactory.BulletFactory;
import com.example.aircraftwar.strategy.move.Linear;

import java.util.LinkedList;
import java.util.List;

public class DisperseShoot extends ShootStrategy{

    public DisperseShoot(BulletFactory factory) {
        super(factory);
    }

    @Override
    public List<BaseBullet> shootWithStrategy(BaseEmoji src) {
        assert this.factory != null : "Bullet Type Not Specified!!!";
        List<BaseBullet> res = new LinkedList<>();
        int directionY = src.getDirectionY();
        int x = src.getX();
        int y = src.getY() + src.getHeight() * directionY / 2;
        BaseBullet bullet;
        for (int i = 0;i < this.shootNum; i++) {
            bullet = this.factory.makeBullet();
            bullet.setX(x + (i * 2 - this.shootNum + 1) * 25);
            bullet.setY(y);
            bullet.setPower(this.power);
            bullet.setDirectionY(src.getDirectionY());
            bullet.setDirectionX(src.getDirectionX());
            bullet.setMovePattern(new Linear().setSpeed(src.getVx() + (i * 2 - this.shootNum + 1)
                    ,src.getVy()));
            res.add(bullet);
        }
        return res;
    }
}
