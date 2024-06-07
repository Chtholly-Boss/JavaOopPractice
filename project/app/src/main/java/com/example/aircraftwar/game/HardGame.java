package com.example.aircraftwar.game;

import android.content.Context;
import android.os.Handler;

import com.example.aircraftwar.aircraft.enemy.BaseEnemyEmoji;
import com.example.aircraftwar.factory.enemyFactory.EnemyEmojiBossFactory_1;
import com.example.aircraftwar.factory.enemyFactory.EnemyEmojiBossFactory_3;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.utils.WeightedRandomSelector;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class HardGame extends BaseGame{
    private int enemyProduceInterval = 200;
    private int maxEnemyNum = 10;
    private Map<Supplier<BaseEnemyEmoji>,Double> enemyProbabilities;
    private WeightedRandomSelector<BaseEnemyEmoji> selector;
    public HardGame(Context context, Handler handler, boolean isSound) {
        super(context, handler, isSound);
        this.background = ImageManager.STAGE_3_IMAGE;
        enemyProbabilities = Map.of(
                () -> enemyEmojiMobFactory_1.makeEnemy(),0.5,
                () -> enemyEmojiMobFactory_2.makeEnemy(), 0.8,
                () -> enemyEmojiMobFactory_3.makeEnemy(), 1.0
        );
        selector = new WeightedRandomSelector<>(enemyProbabilities);
        selector.setMaxProb(1.0);
    }

    @Override
    protected List<BaseEnemyEmoji> produceEnemy() {
        List<BaseEnemyEmoji> res = new LinkedList<>();
        if (this.cycleTime % enemyProduceInterval == 0) {
            if (enemys.size() < maxEnemyNum) {
                res.add(selector.selectRandomObject());
            }
        }
        if (!isBossExist && score > 100) {
            res.add(new EnemyEmojiBossFactory_3().makeEnemy());
            isBossExist = true;
        }
        return res;
    }
}
