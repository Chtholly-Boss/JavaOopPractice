package com.example.aircraftwar.strategy.shoot;

import com.example.aircraftwar.aircraft.BaseEmoji;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.factory.bulletFactory.BulletFactory;
import com.example.aircraftwar.strategy.move.Linear;

import java.util.LinkedList;
import java.util.List;

public class RingShoot extends ShootStrategy{

    public RingShoot(BulletFactory factory) {
        super(factory);
        this.shootNum = 20;
    }

    @Override
    public List<BaseBullet> shootWithStrategy(BaseEmoji src) {
        assert this.factory != null : "Bullet Type Not Specified!!!";
        List<BaseBullet> res = new LinkedList<>();
        int x = src.getX();
        int y = src.getY();
        int radius = 5;
        double unitTheta = 2.0 * Math.PI / shootNum;
        BaseBullet bullet;
        for (int i = 0; i < this.shootNum;i++) {
            System.out.println("ShootCounter: " + i);
            bullet = this.factory.makeBullet();
            bullet.setX(x + radius * Math.cos(i * unitTheta));
            bullet.setY(y + radius * Math.sin(i * unitTheta));
            bullet.setPower(this.power);
            bullet.setVx(8);
            bullet.setMovePattern(new Linear().setSpeed(8,8));

            if (i <= shootNum / 2) bullet.setDirectionY(1);
            else bullet.setDirectionY(-1);

            if (i <= shootNum / 4 || i >= shootNum * 3/4)
                bullet.setDirectionX(1);
            else bullet.setDirectionX(-1);

            res.add(bullet);
        }
        return res;
    }
}
