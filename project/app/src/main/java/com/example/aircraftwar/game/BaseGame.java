package com.example.aircraftwar.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.aircraft.HeroAircraft;
import com.example.aircraftwar.aircraft.enemy.BaseEnemyAircraft;
import com.example.aircraftwar.basic.AbstractFlyingObject;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.factory.enemyFactory.EliteEnemyFactory;
import com.example.aircraftwar.factory.enemyFactory.EnemyFactory;
import com.example.aircraftwar.item.BaseItem;
import com.example.aircraftwar.manager.ImageManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BaseGame extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    public static final String TAG = "BaseGame";
    protected boolean mIsDrawing;
    private Canvas mCanvas;
    private final Paint mPaint;
    private final SurfaceHolder mSurfaceHolder;
    protected Bitmap background;
    protected int backgroundTop = 0;
    private final int flushInterval = 16;
    private final HeroAircraft hero;
    protected final List<BaseEnemyAircraft> enemys;
    protected final List<BaseBullet> bulletsOfHero;
    protected final List<BaseBullet> bulletsOfEnemy;
    protected final List<BaseItem> items;
    private EnemyFactory enemyFactory;
    private int cycleTime = 0;
    private int score = 0;
    private boolean gameOver = false;

    public BaseGame(Context context) {
        super(context);
        // Initialize Paint Tools
        mIsDrawing = true;
        mPaint = new Paint();
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        this.setFocusable(true);
        ImageManager.initImage(context);
        // Initialize Game Objects
        this.hero = HeroAircraft.getInstance();
        this.hero.setHp(1000);
        enemys = new CopyOnWriteArrayList<>();
        bulletsOfHero = new CopyOnWriteArrayList<>();
        bulletsOfEnemy = new CopyOnWriteArrayList<>();
        items = new CopyOnWriteArrayList<>();

        enemyFactory = new EliteEnemyFactory();

        this.heroController();
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
            // TODO : Time Tick Tasks: Generate Enemy and Raise up Hard Level

            // Routines
            this.enemys.forEach(AbstractFlyingObject::move);
            this.bulletsOfHero.forEach(AbstractFlyingObject::move);
            this.bulletsOfEnemy.forEach(AbstractFlyingObject::move);
            this.items.forEach(AbstractFlyingObject::move);

            this.bulletsOfHero.forEach(bullet -> this.enemys.forEach(bullet::hitObject));
            this.bulletsOfEnemy.forEach(bullet -> bullet.hitObject(this.hero));

            this.items.forEach(BaseItem::onEffect);
            this.postProcess();

        };
        task.run();
    }
    private void postProcess() {
        enemys.removeIf(AbstractFlyingObject::notValid);
        bulletsOfHero.removeIf(AbstractFlyingObject::notValid);
        bulletsOfEnemy.removeIf(AbstractFlyingObject::notValid);
        items.removeIf(AbstractFlyingObject::notValid);
        if (hero.notValid()) {
            this.gameOver = true;
            this.mIsDrawing = false;
            Log.i(TAG,"HeroAircraft is not valid.");
        }
    }

    public int getScore() {
        return score;
    }

    public void draw() {
        mCanvas = mSurfaceHolder.lockCanvas();
        if (mCanvas == null) return;
        mCanvas.drawBitmap(background,0, backgroundTop-background.getHeight(),mPaint);
        mCanvas.drawBitmap(background,0,backgroundTop,mPaint);
        backgroundTop = backgroundTop == background.getHeight() ? 0 : backgroundTop + 5;
        paintWithPositionRevised(bulletsOfEnemy);
        paintWithPositionRevised(bulletsOfHero);
        paintWithPositionRevised(enemys);
        paintWithPositionRevised(items);
        paintWithPositionRevised(hero);

        mSurfaceHolder.unlockCanvasAndPost(mCanvas);

    }
    private void paintWithPositionRevised(List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) return;
        objects.forEach(this::paintWithPositionRevised);
    }
    private void paintWithPositionRevised(AbstractFlyingObject obj) {
        Bitmap image = obj.getImage();
        assert image != null : obj.getClass().getName() + " has no image!!!";
        mCanvas.drawBitmap(image,obj.getX() - image.getWidth() / 2,
                obj.getY() - image.getHeight() / 2,mPaint );
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        GameActivity.screenWidth = i1;
        GameActivity.screenHeight = i2;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            this.action();
            draw();
        }
    }
}