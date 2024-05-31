package com.example.aircraftwar.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.aircraftwar.R;
import com.example.aircraftwar.aircraft.HeroAircraft;
import com.example.aircraftwar.aircraft.enemy.EliteEnemyAircraft;
import com.example.aircraftwar.bullet.HeroBullet;
import com.example.aircraftwar.item.FireItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ImageManager {
    private static final Map<String, Bitmap> CLASSNAME_IMAGE_MAP = new HashMap<>();
    public static Bitmap STAGE_1_IMAGE;
    public static Bitmap HERO_IMAGE;
    public static Bitmap ELITE_ENEMY_IMAGE;
    public static Bitmap HERO_BULLET_IMAGE;
    public static Bitmap ITEM_FIRE;
    public static void initImage(Context context) {
        initStageImage(context);
        initAircraftImage(context);
        initBulletImage(context);

    }
    private static void initStageImage(Context context) {
        ImageManager.STAGE_1_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.stage1);
    }
    private static void initAircraftImage(Context context) {
        ImageManager.HERO_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.hero);
        CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);

        ImageManager.ELITE_ENEMY_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.elite);
        CLASSNAME_IMAGE_MAP.put(EliteEnemyAircraft.class.getName(),ELITE_ENEMY_IMAGE);
    }
    private static void initBulletImage(Context context) {
        ImageManager.HERO_BULLET_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.hero_bullet);
        CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(),HERO_BULLET_IMAGE);
    }
    private static void initItemImage(Context context) {
        ImageManager.ITEM_FIRE = BitmapFactory.decodeResource(context.getResources(),R.drawable.fire_item);
        CLASSNAME_IMAGE_MAP.put(FireItem.class.getName(),ITEM_FIRE);
    }

    public static Bitmap get(String className) {
        return CLASSNAME_IMAGE_MAP.get(className);
    }
    public static Bitmap get(Object obj) {
        if (obj == null) return null;
        return get(obj.getClass().getName());
    }
}
