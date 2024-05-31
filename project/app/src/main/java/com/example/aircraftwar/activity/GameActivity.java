package com.example.aircraftwar.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar.game.BaseGame;
import com.example.aircraftwar.game.EasyGame;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";
    public static int screenWidth;
    public static int screenHeight;
    private int gameType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenParams();
        if (getIntent() != null) {
            gameType = getIntent().getIntExtra("gameType", 1);
        }
        // TODO : Choose Game corresponding to Game Type
        BaseGame baseGameView = new EasyGame(this);
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
