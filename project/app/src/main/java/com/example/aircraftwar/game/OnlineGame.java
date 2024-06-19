package com.example.aircraftwar.game;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.widget.ThemedSpinnerAdapter;

import com.example.aircraftwar.activity.GameActivity;
import com.example.aircraftwar.manager.MusicManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.stream.Stream;

public class OnlineGame extends EasyGame{
    private int opScore = 0;
    private boolean opGameOver = false;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OnlineGame(Context context, Handler handler, boolean isSound) {
        super(context, handler, isSound);
        System.out.println("Go into the Game!");
    }

    @Override
    public void run() {
        msgExchange();
        gameRunning();
        while (true) {
            if (gameOver && opGameOver) {
                competitionDone();
                break;
            }
        }
    }
    private void competitionDone() {
        // TODO : Specify what you want to do when both players finished
        System.out.println("Game Over");
    }
    private void gameRunning() {
        while (mIsDrawing) {
            action();
            draw();
        }
    }
    private void msgExchange() {
        // new Thread to prevent from Blocking
        new Thread(() -> {
            while(!gameOver) {
                sendMsg2Server("score," + score);
            }
        }).start();
        new Thread(() -> {
            while(!opGameOver) {
                if (socket.isClosed()) {
                    System.out.println("Socket Closed!!!");
                    break;
                }
                receiveMsgFromServer();
            }
        }).start();
    }
    @Override
    protected void draw() {
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
        mCanvas.drawText("Opponent score: " + opScore,0,150,mPaint);
        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
    }

    @Override
    protected void GameOverAction() {
        if (gameOver) {
            this.mIsDrawing = false;
            MusicManager.stopAllMediaPlayers();
            MusicManager.playSound("game_over");
            sendMsg2Server("final," + score);
        }
    }
    public void setOpScore(int opScore) {
        this.opScore = opScore;
    }

    private void sendMsg2Server(String msg) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)), true);
            out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void receiveMsgFromServer() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            String msg;
            if ((msg = in.readLine()) != null) {
                System.out.println(msg);
                String[] msgInfo = msg.split(",");
                if ("score".equals(msgInfo[0])) {
                    setOpScore(Integer.parseInt(msgInfo[1]));
                }
                if ("final".equals(msgInfo[0])) {
                    opGameOver = true;
                    setOpScore(Integer.parseInt(msgInfo[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
