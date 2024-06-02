package com.example.aircraftwar.aircraft.enemy;

import com.example.aircraftwar.item.BaseItem;

import java.util.LinkedList;
import java.util.List;

public class EnemyEmojiBoss_2 extends BaseEnemyEmoji{

    public EnemyEmojiBoss_2(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
        this.score = 10000;
    }

    @Override
    public List<BaseItem> addItem() {
        List<BaseItem> res = new LinkedList<>();
        return res;
    }
}
