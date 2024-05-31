package com.example.aircraftwar.factory.itemFactory;

import com.example.aircraftwar.item.BaseItem;
import com.example.aircraftwar.item.HpItem;

public class HpItemFactory implements ItemFactory{

    @Override
    public BaseItem makeItem(int x, int y) {
        return new HpItem(x,y,0,2);
    }
}
