package com.example.aircraftwar.game;

import android.content.Context;
import android.os.Handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class OnlineGame extends EasyGame{
    private Socket socket;
    private int opScore = 0;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OnlineGame(Context context, Handler handler, boolean isSound) {
        super(context, handler, isSound);
        System.out.println("Go into the Game!");
    }

    @Override
    public void action() {
        super.action();
        if (cycleTime % 2000 == 0) {
            sendScore2Server();
        }
    }
    private void sendScore2Server() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
