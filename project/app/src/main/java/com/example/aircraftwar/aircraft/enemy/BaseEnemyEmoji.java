package com.example.aircraftwar.aircraft.enemy;

import com.example.aircraftwar.aircraft.BaseEmoji;
import com.example.aircraftwar.factory.itemFactory.BulletAddItemFactory;
import com.example.aircraftwar.factory.itemFactory.HealFixedItemFactory;
import com.example.aircraftwar.factory.itemFactory.ItemFactory;
import com.example.aircraftwar.factory.itemFactory.PowerAddItemFactory;
import com.example.aircraftwar.item.BaseItem;
import com.example.aircraftwar.utils.WeightedRandomSelector;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public abstract class BaseEnemyEmoji extends BaseEmoji {
    protected Map<Supplier<BaseItem>,Double> itemProb;
    protected WeightedRandomSelector<BaseItem> selector;
    protected int score = 10;

    protected ItemFactory bulletAddFactory;
    protected ItemFactory powerAddFactory;
    protected ItemFactory healFixedFactory;

    public void setScore(int score) {
        this.score = score;
    }

    public BaseEnemyEmoji(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
        this.directionY = 1;
        bulletAddFactory = new BulletAddItemFactory();
        powerAddFactory = new PowerAddItemFactory();
        healFixedFactory = new HealFixedItemFactory();
        itemProb = Map.of(
                () -> healFixedFactory.makeItem(this.getX(),this.getY()),0.2,
                () -> bulletAddFactory.makeItem(this.getX(),this.getY()),0.5,
                () -> bulletAddFactory.makeItem(this.getX(),this.getY()),0.8
        );
        selector = new WeightedRandomSelector<>(itemProb);
        selector.setMaxProb(1.5);
    }
    public int addScore() {
        return this.score;
    }
    public abstract List<BaseItem> addItem();
}
