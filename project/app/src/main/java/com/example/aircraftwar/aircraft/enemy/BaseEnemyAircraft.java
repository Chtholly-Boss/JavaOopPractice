package com.example.aircraftwar.aircraft.enemy;

import com.example.aircraftwar.aircraft.BaseAircraft;
import com.example.aircraftwar.item.BaseItem;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseEnemyAircraft extends BaseAircraft {
    protected int score = 10;

    public void setScore(int score) {
        this.score = score;
    }

    public BaseEnemyAircraft(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }
    public int addScore() {
        return this.score;
    }
    public abstract List<BaseItem> addItem();
}
