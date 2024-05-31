package com.example.aircraftwar.aircraft.enemy;

import com.example.aircraftwar.aircraft.BaseAircraft;

public abstract class BaseEnemyAircraft extends BaseAircraft {
    // TODO : Add Score when being hit down and Generate Items
    public BaseEnemyAircraft(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }
}
