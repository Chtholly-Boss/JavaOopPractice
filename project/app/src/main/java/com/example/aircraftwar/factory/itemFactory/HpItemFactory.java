package com.example.aircraftwar.factory.itemFactory;

import com.example.aircraftwar.item.BaseItem;
import com.example.aircraftwar.item.HealFixedItem;

public class HpItemFactory implements ItemFactory{

    @Override
    public BaseItem makeItem(int x, int y) {
        return new HealFixedItem(x,y,0,2);
    }
}
