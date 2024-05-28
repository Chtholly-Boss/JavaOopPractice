package com.example.aircraftwar.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.aircraft.HeroAircraft;
import com.example.aircraftwar.aircraft.enemy.BaseEnemyAircraft;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.item.BaseItem;
import com.example.aircraftwar.manager.ImageManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BaseGame extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    public static final String TAG = "BaseGame";
    boolean mbLoop;
    private Canvas mCanvas;
    private final Paint mPaint;
    private final SurfaceHolder mSurfaceHolder;
    protected Bitmap background;
    private final int flushInterval = 16;
    private final HeroAircraft hero;
    protected final List<BaseEnemyAircraft> enemys;
    protected final List<BaseBullet> bullets;
    protected final List<BaseItem> items;
    private int score = 0;

    public BaseGame(Context context) {
        super(context);
        // Initialize Paint Tools
        mPaint = new Paint();
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        this.setFocusable(true);
        ImageManager.initImage(context);
        // Initialize Game Objects
        this.hero = HeroAircraft.getInstance();
        enemys = new CopyOnWriteArrayList<>();
        bullets = new CopyOnWriteArrayList<>();
        items = new CopyOnWriteArrayList<>();

    }

    private void heroController() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int xClick = (int)motionEvent.getX();
                int yClick = (int)motionEvent.getY();
                hero.setX(xClick);
                hero.setY(yClick);
                return xClick >= 0 && xClick <= GameActivity.screenWidth && yClick >= 0 && yClick <= GameActivity.screenHeight;
            }
        });
    }
    public void action() {
        Runnable task = () -> {
            // TODO : Game Logic
        };
        task.run();
    }
}