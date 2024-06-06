package com.example.aircraftwar.manager;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MusicManager {
    private Map<String, MediaPlayer> mediaPlayers = new HashMap<>();
    private static MusicManager instance;

    private MusicManager() {
        // 私有构造函数，实现单例模式
    }

    public static synchronized MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    public void playSound(String soundKey) {
        MediaPlayer mediaPlayer = mediaPlayers.get(soundKey);
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void loopSound(String soundKey) {
        MediaPlayer mediaPlayer = mediaPlayers.get(soundKey);
        if (mediaPlayer != null&& !mediaPlayer.isPlaying()) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            Log.i("i","****loop****");
        }
    }

    public void pauseSound(String soundKey) {
        MediaPlayer mediaPlayer = mediaPlayers.get(soundKey);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stopSound(String soundKey) {
        MediaPlayer mediaPlayer = mediaPlayers.get(soundKey);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void releaseSound(String soundKey) {
        MediaPlayer mediaPlayer = mediaPlayers.get(soundKey);
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayers.remove(soundKey);
        }
    }

    public void initializeSound(String soundKey, int resourceId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), resourceId);
        mediaPlayers.put(soundKey, mediaPlayer);
    }

    // 需要提供一个方法来获取Context，以便MediaPlayer.create()可以使用
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
