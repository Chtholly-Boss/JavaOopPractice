package com.example.aircraftwar.item;

import com.example.aircraftwar.aircraft.HeroAircraft;
import com.example.aircraftwar.basic.AbstractFlyingObject;

import java.util.ArrayList;
import java.util.List;

public class BombItem extends BaseItem{
    public static List<AbstractFlyingObject> observers = new ArrayList<>();
    public BombItem(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }

    @Override
    public boolean onEffect() {
        HeroAircraft hero = HeroAircraft.getInstance();
        if (this.crash(hero)) {
            BombItem.observers.forEach(AbstractFlyingObject::updateForBomb);
            return true;
        }
        return false;
    }
}
