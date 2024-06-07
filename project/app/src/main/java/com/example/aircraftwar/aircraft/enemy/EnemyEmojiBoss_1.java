package com.example.aircraftwar.aircraft.enemy;

import com.example.aircraftwar.game.BaseGame;
import com.example.aircraftwar.item.BaseItem;
import com.example.aircraftwar.manager.MusicManager;

import java.util.LinkedList;
import java.util.List;

public class EnemyEmojiBoss_1 extends BaseEnemyEmoji{

    public EnemyEmojiBoss_1(int _x, int _y, int _vx, int _vy) {
        super(_x, _y, _vx, _vy);
        this.score = 10;
    }

    @Override
    public List<BaseItem> addItem() {
        List<BaseItem> res = new LinkedList<>();
        return res;
    }

    @Override
    public void vanish() {
        super.vanish();
        BaseGame.isBossExist = false;
        MusicManager.pauseSound("bgm_boss");
        MusicManager.loopSound("bgm");
    }
}
