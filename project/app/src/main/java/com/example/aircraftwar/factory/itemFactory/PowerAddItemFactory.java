package com.example.aircraftwar.factory.itemFactory;

import com.example.aircraftwar.item.BaseItem;
import com.example.aircraftwar.item.HealFixedItem;
import com.example.aircraftwar.item.ShootPowerAddItem;
import com.example.aircraftwar.strategy.move.Forward;

public class PowerAddItemFactory implements ItemFactory{

    @Override
    public BaseItem makeItem(int x, int y) {
        BaseItem res = new ShootPowerAddItem(x,y,0,2);
        res.setMovePattern(new Forward());
        res.setDirectionY(1);
        return res;
    }
}
