package com.example.aircraftwar.aircraft.enemy;

import com.example.aircraftwar.factory.itemFactory.HealFixedItemFactory;
import com.example.aircraftwar.item.BaseItem;
import com.example.aircraftwar.utils.WeightedRandomSelector;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EnemyEmojiMob_1 extends BaseEnemyEmoji {
    public EnemyEmojiMob_1(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
        this.score = 10;
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
