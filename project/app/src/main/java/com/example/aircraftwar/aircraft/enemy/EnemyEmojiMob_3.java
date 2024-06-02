package com.example.aircraftwar.aircraft.enemy;

import com.example.aircraftwar.factory.itemFactory.HealFixedItemFactory;
import com.example.aircraftwar.item.BaseItem;

import java.util.LinkedList;
import java.util.List;

public class EnemyEmojiMob_3 extends BaseEnemyEmoji {
    public EnemyEmojiMob_3(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
    }

    @Override
    public List<BaseItem> addItem() {
        List<BaseItem> res = new LinkedList<>();
        BaseItem item = selector.selectRandomObject();
        if (item != null) {
            res.add(item);
        }
        return res;
    }
}
