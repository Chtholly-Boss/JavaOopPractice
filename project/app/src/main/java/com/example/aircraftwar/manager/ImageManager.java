package com.example.aircraftwar.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.aircraftwar.R;
import com.example.aircraftwar.aircraft.HeroAircraft;
import com.example.aircraftwar.aircraft.enemy.EliteEnemyAircraft;
import com.example.aircraftwar.bullet.HeroBullet;
import com.example.aircraftwar.item.HealFixedItem;
import com.example.aircraftwar.item.ShootBulletAdd;
import com.example.aircraftwar.item.ShootPowerAddItem;
import com.example.aircraftwar.item.ShootSpeedAddItem;

import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    private static final Map<String, Bitmap> CLASSNAME_IMAGE_MAP = new HashMap<>();
    // Stage Background Image:
    public static Bitmap STAGE_1_IMAGE;
    public static Bitmap STAGE_2_IMAGE;
    public static Bitmap STAGE_3_IMAGE;
    public static Bitmap STAGE_4_IMAGE;
    // Character Image:
    public static Bitmap HERO_IMAGE;
    public static Bitmap ELITE_ENEMY_IMAGE;
    // Bullet Image:
    public static Bitmap HERO_BULLET_IMAGE;
    // Item Images:
    public static Bitmap ITEM_HEAL_FIXED;
    public static Bitmap ITEM_SHOOT_POWER_ADD;
    public static Bitmap ITEM_SHOOT_SPEED_ADD;
    public static Bitmap ITEM_SHOOT_BULLET_ADD;
    public static void initImage(Context context) {
        initStageImage(context);
        initAircraftImage(context);
        initBulletImage(context);
        initItemImage(context);
    }
    private static void initStageImage(Context context) {
        ImageManager.STAGE_1_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.stage_1);
        ImageManager.STAGE_2_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.stage_2);
        ImageManager.STAGE_3_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.stage_3);
        ImageManager.STAGE_4_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.stage_4);
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
        ImageManager.ITEM_HEAL_FIXED = BitmapFactory.decodeResource(context.getResources(),R.drawable.heal_fixed);
        CLASSNAME_IMAGE_MAP.put(HealFixedItem.class.getName(),ITEM_HEAL_FIXED);
        ImageManager.ITEM_SHOOT_POWER_ADD = BitmapFactory.decodeResource(context.getResources(),R.drawable.shoot_power_add);
        CLASSNAME_IMAGE_MAP.put(ShootPowerAddItem.class.getName(),ITEM_SHOOT_POWER_ADD);
        ImageManager.ITEM_SHOOT_SPEED_ADD = BitmapFactory.decodeResource(context.getResources(),R.drawable.shoot_speed_add);
        CLASSNAME_IMAGE_MAP.put(ShootSpeedAddItem.class.getName(),ITEM_SHOOT_SPEED_ADD);
        ImageManager.ITEM_SHOOT_BULLET_ADD = BitmapFactory.decodeResource(context.getResources(),R.drawable.shoot_bullet_add);
        CLASSNAME_IMAGE_MAP.put(ShootBulletAdd.class.getName(),ITEM_SHOOT_BULLET_ADD);
    }

    public static Bitmap get(String className) {
        return CLASSNAME_IMAGE_MAP.get(className);
    }
    public static Bitmap get(Object obj) {
        if (obj == null) return null;
        return get(obj.getClass().getName());
    }
}
