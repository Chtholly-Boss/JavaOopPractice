package com.example.aircraftwar.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aircraftwar.R;
import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.aircraft.HeroEmoji;
import com.example.aircraftwar.aircraft.enemy.BaseEnemyEmoji;
import com.example.aircraftwar.basic.AbstractFlyingObject;
import com.example.aircraftwar.bullet.BaseBullet;
import com.example.aircraftwar.factory.enemyFactory.EnemyEmojiMobFactory_1;
import com.example.aircraftwar.factory.enemyFactory.EnemyEmojiMobFactory_2;
import com.example.aircraftwar.factory.enemyFactory.EnemyEmojiMobFactory_3;
import com.example.aircraftwar.factory.enemyFactory.EnemyFactory;
import com.example.aircraftwar.item.BaseItem;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.manager.MusicManager;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public abstract class BaseGame extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    public static final String TAG = "BaseGame";
    public final int GAME_OVER = 1;
    protected boolean mIsDrawing;
    protected Canvas mCanvas;
    protected final Paint mPaint;
    protected final SurfaceHolder mSurfaceHolder;
    protected Bitmap background;
    protected int backgroundTop = 0;
    protected final HeroEmoji hero;
    protected final List<BaseEnemyEmoji> enemys;
    protected final List<BaseBullet> bulletsOfHero;
    protected final List<BaseBullet> bulletsOfEnemy;
    protected final List<BaseItem> items;
    protected EnemyFactory enemyEmojiMobFactory_1;
    protected EnemyFactory enemyEmojiMobFactory_2;
    protected EnemyFactory enemyEmojiMobFactory_3;
    protected int cycleTime = 0;
    protected int cycleBreakPoint = 10000;
    protected int score = 0;
    public static boolean isBossExist = false;
    public static int bossThreshold = 100;
    protected boolean gameOver = false;

    protected Handler gameHandler;
    public BaseGame(Context context, Handler gameActivityHandler, boolean isSound) {
        super(context);
        MusicManager.init(context,isSound);
        MusicManager.loopSound("bgm");
        this.gameHandler = gameActivityHandler;
        mIsDrawing = true;
        mPaint = new Paint();
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        this.setFocusable(true);
        ImageManager.initImage(context);
        // Initialize Game Objects
        this.hero = HeroEmoji.getInstance();
        this.hero.setHp(1000000);
        enemys = new CopyOnWriteArrayList<>();
        bulletsOfHero = new CopyOnWriteArrayList<>();
        bulletsOfEnemy = new CopyOnWriteArrayList<>();
        items = new CopyOnWriteArrayList<>();

        enemyEmojiMobFactory_1 = new EnemyEmojiMobFactory_1();
        enemyEmojiMobFactory_2 = new EnemyEmojiMobFactory_2();
        enemyEmojiMobFactory_3 = new EnemyEmojiMobFactory_3();
        this.heroController();
    }
    private boolean timeCountAndNewCycleJudge() {
        int timeInterval = 10;
        this.cycleTime += timeInterval;
        if (cycleTime >= cycleBreakPoint && cycleTime - timeInterval < cycleTime) {
            cycleTime %= cycleBreakPoint;
            return true;
        }
        return false;
    }
    @SuppressLint("ClickableViewAccessibility")
    private void heroController() {
        setOnTouchListener((view, motionEvent) -> {
            int xClick = (int)motionEvent.getX();
            int yClick = (int)motionEvent.getY();
            hero.setX(xClick);
            hero.setY(yClick);
            return xClick >= 0 && xClick <= GameActivity.screenWidth && yClick >= 0 && yClick <= GameActivity.screenHeight;
        });
    }
    public void action() {
        Runnable task = () -> {
            if(isBossExist){
                MusicManager.pauseSound("bgm");
                MusicManager.loopSound("bgm_boss");
            }
            else{

            }
            timeCountAndNewCycleJudge();
            enemys.addAll(produceEnemy());
            shootAction();
            moveAction();
            crachCheckAction();
            postProcess();
            GameOverAction();
        };
        task.run();
    }
    protected abstract List<BaseEnemyEmoji> produceEnemy();
    private void moveAction() {
        Stream.of(this.enemys, this.bulletsOfHero, this.bulletsOfEnemy, this.items)
                .flatMap(Collection::stream)
                .forEach(AbstractFlyingObject::move);
    }
    private void shootAction() {
        if (cycleTime % hero.getShootStrategy().getShootInterval() == 0) {
            bulletsOfHero.addAll(hero.shoot());
        }
        enemys.forEach(enemy -> {
            if (cycleTime % enemy.getShootStrategy().getShootInterval() == 0) {
                bulletsOfEnemy.addAll(enemy.shoot());
            }
        });
    }
    private void crachCheckAction() {
        this.bulletsOfHero.forEach(bullet -> this.enemys.forEach(enemy -> {
            if (bullet.hitObject(enemy)) {
                MusicManager.playSound("bullet_hit");
                if (enemy.notValid()) {
                    this.score += enemy.addScore();
                    items.addAll(enemy.addItem());
                }
            }
        }));
        this.bulletsOfEnemy.forEach(bullet -> bullet.hitObject(this.hero));
        this.enemys.forEach(enemy -> {
            if (enemy.crash(hero)) hero.vanish();
        });
        //this.items.forEach(BaseItem::onEffect);
        this.items.forEach(item -> {
            if (item.onEffect()) {
                MusicManager.playSound("get_supply");
            }
        });
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
    private void GameOverAction() {
        if (gameOver) {
            MusicManager.stopAllMediaPlayers();
            MusicManager.playSound("game_over");
            // 创建一个Message对象，附加score数据，并发送
            Message message = gameHandler.obtainMessage(GAME_OVER, score);
            gameHandler.sendMessage(message);
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
        backgroundTop = backgroundTop == GameActivity.screenHeight ? 0 : backgroundTop + 1;
        Stream.concat(Stream.of(this.hero), Stream.of(this.bulletsOfEnemy, this.bulletsOfHero, this.enemys, this.items).flatMap(Collection::stream))
                .forEach(this::paintWithPositionRevised);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(50);
        mCanvas.drawText("Score: " + this.score,0,50,mPaint);
        mCanvas.drawText("HP: " + hero.getHp(),0,100,mPaint);
        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
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