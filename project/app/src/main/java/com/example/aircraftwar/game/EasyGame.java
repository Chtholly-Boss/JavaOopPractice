package com.example.aircraftwar.game;

import android.content.Context;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.example.aircraftwar.aircraft.enemy.BaseEnemyAircraft;
import com.example.aircraftwar.manager.ImageManager;

import java.util.LinkedList;
import java.util.List;

public class EasyGame extends BaseGame{
    private int enemyProduceInterval = 200;
    private int maxEnemyNum = 5;
    public EasyGame(Context context) {
        super(context);
        this.background = ImageManager.STAGE_2_IMAGE;
    }

    @Override
    protected List<BaseEnemyAircraft> produceEnemy() {
        List<BaseEnemyAircraft> res = new LinkedList<>();
        if (this.cycleTime % enemyProduceInterval == 0) {
            if (enemys.size() < maxEnemyNum) {
                res.add(enemyFactory.makeEnemy());
            }
        }
        return res;
    }
}
