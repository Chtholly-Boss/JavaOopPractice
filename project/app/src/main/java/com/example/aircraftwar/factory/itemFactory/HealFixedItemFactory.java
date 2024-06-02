package com.example.aircraftwar.factory.itemFactory;

import com.example.aircraftwar.item.BaseItem;
import com.example.aircraftwar.item.HealFixedItem;
import com.example.aircraftwar.strategy.move.Forward;

public class HealFixedItemFactory implements ItemFactory{

    @Override
    public BaseItem makeItem(int x, int y) {
        BaseItem res = new HealFixedItem(x,y,0,2);
        res.setMovePattern(new Forward());
        res.setDirectionY(1);
        return res;
    }
}
