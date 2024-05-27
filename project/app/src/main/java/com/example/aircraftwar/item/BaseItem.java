package com.example.aircraftwar.item;

import com.example.aircraftwar.aircraft.HeroAircraft;
import com.example.aircraftwar.basic.AbstractFlyingObject;

public abstract class BaseItem extends AbstractFlyingObject {

    public BaseItem(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }
    // Do sth when being obtained
    public abstract boolean onEffect(HeroAircraft hero);
}
