package com.example.aircraftwar.factory.itemFactory;

import com.example.aircraftwar.item.BaseItem;

public interface ItemFactory {
    BaseItem makeItem(int x,int y);
}
