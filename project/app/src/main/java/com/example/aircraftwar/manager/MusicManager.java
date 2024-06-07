package com.example.aircraftwar.manager;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import com.example.aircraftwar.R;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MusicManager {
    public static Context context;
    private static boolean soundOn;
    private static SoundPool soundPool;
    private static final Map<String, MediaPlayer> mediaPlayers = new HashMap<>();
    private static final Map<String,Integer> soundPoolMap = new HashMap<>();

    public static void init(Context context,boolean soundOn){
        MusicManager.soundOn = soundOn;
        MusicManager.context = context;
        initMediaPlayer();
        initSoundPool();
    }
    private static void initMediaPlayer() {
        MusicManager.initializeSound("bgm", R.raw.tabula);
        MusicManager.initializeSound("bgm_boss", R.raw.sakuya);
    }
    private static void initSoundPool() {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(audioAttributes)
                .build();
        MusicManager.soundPoolMap.put("bullet_hit",soundPool.load(context,R.raw.bullet_hit,1));
        MusicManager.soundPoolMap.put("get_supply",soundPool.load(context,R.raw.get_supply,1));
        MusicManager.soundPoolMap.put("game_over",soundPool.load(context,R.raw.game_over,1));
    }
    public static void playSound(String soundKey) {
        if (MusicManager.soundOn) {
            MediaPlayer mediaPlayer = mediaPlayers.get(soundKey);
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
            if (soundPoolMap.get(soundKey) != null) {
                soundPool.play(soundPoolMap.get(soundKey),10,10,0,0,1.2f);
            }
        }
    }

    public static void loopSound(String soundKey) {
        if (MusicManager.soundOn) {
            MediaPlayer mediaPlayer = mediaPlayers.get(soundKey);
            if (mediaPlayer != null&& !mediaPlayer.isPlaying()) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        }
    }

    public static void pauseSound(String soundKey) {
        if (soundOn) {
            MediaPlayer mediaPlayer = mediaPlayers.get(soundKey);
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }
    }
    private static void initializeSound(String soundKey, int resourceId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(MusicManager.context, resourceId);
        mediaPlayer.setVolume(10.0f,10.0f);
        MusicManager.mediaPlayers.put(soundKey, mediaPlayer);
    }
    public static void stopAllMediaPlayers() {
        mediaPlayers.forEach((s, mediaPlayer) -> {
            mediaPlayer.stop();
            mediaPlayer.release();
        });
    }
}
