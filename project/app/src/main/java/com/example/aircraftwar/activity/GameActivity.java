package com.example.aircraftwar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.WindowMetrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar.game.BaseGame;
import com.example.aircraftwar.game.EasyGame;
import com.example.aircraftwar.game.HardGame;
import com.example.aircraftwar.game.MidGame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";
    public static final int GAME_OVER = 1;
    public static int screenWidth;
    public static int screenHeight;
    private int gameType;
    private Handler gameActivityHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == GAME_OVER) {
                // 从消息中获取score，并执行相应的操作，例如显示分数
                int score = (int) msg.obj;
                Intent intent = new Intent(GameActivity.this, RecordActivity.class);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");// HH:mm:ss
                Date date = new Date(System.currentTimeMillis());
                String time = simpleDateFormat.format(date);

                intent.putExtra("score", score);
                intent.putExtra("time", time);
                intent.putExtra("gameType", gameType);
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        }

    });
    private Map<Integer, Supplier<BaseGame>> hardLevelMap = Map.of(
            1,() -> new EasyGame(this, gameActivityHandler),
            2,() -> new MidGame(this, gameActivityHandler),
        3,() -> new HardGame(this, gameActivityHandler)
    );




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenParams();
        if (getIntent() != null) {
            gameType = getIntent().getIntExtra("gameType", 1);
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
