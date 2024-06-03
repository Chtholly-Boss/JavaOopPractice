package com.example.aircraftwar.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar.game.BaseGame;
import com.example.aircraftwar.game.EasyGame;
import com.example.aircraftwar.game.HardGame;
import com.example.aircraftwar.game.MidGame;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";
    public static int screenWidth;
    public static int screenHeight;
    private int gameType;
    private Map<Integer, Supplier<BaseGame>> hardLevelMap = Map.of(
            1,() -> new EasyGame(this),
            2,() -> new MidGame(this),
            3,() -> new HardGame(this)
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenParams();
        if (getIntent() != null) {
            gameType = getIntent().getIntExtra("gameType", 1);
            System.out.println("gameType: " + gameType);
        }
        BaseGame baseGameView = Objects.requireNonNull(hardLevelMap.get(gameType)).get();
        setContentView(baseGameView);
    }
    private void getScreenParams(){
        WindowMetrics paramGetter = getWindowManager().getCurrentWindowMetrics();
        screenWidth = paramGetter.getBounds().width();
        screenHeight = paramGetter.getBounds().height();
        Log.i(TAG,"screenWidth : " + screenWidth + " screenHeight : " + screenHeight);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
