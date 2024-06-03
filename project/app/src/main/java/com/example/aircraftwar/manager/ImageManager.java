package com.example.aircraftwar.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.aircraftwar.R;
import com.example.aircraftwar.aircraft.HeroEmoji;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiBoss_1;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiMob_1;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiMob_2;
import com.example.aircraftwar.aircraft.enemy.EnemyEmojiMob_3;
import com.example.aircraftwar.bullet.GreenBullet;
import com.example.aircraftwar.bullet.PureBullet;
import com.example.aircraftwar.item.HealFixedItem;
import com.example.aircraftwar.item.ShootBulletAddItem;
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
    // Character Image:
    public static Bitmap HERO_IMAGE;
    public static Bitmap ENEMY_MOB_1_IMAGE;
    public static Bitmap ENEMY_MOB_2_IMAGE;
    public static Bitmap ENEMY_MOB_3_IMAGE;
    public static Bitmap ENEMY_BOSS_1_IMAGE;
    // Bullet Image:
    public static Bitmap GREEN_BULLET_IMAGE;
    public static Bitmap PURE_BULLET_IMAGE;
    public static Bitmap GOLDEN_BULLET_IMAGE;
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
        ImageManager.STAGE_1_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg);
        ImageManager.STAGE_2_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.bg2);
        ImageManager.STAGE_3_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.bg3);
    }


    private static void initAircraftImage(Context context) {
        ImageManager.HERO_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.emoji_1);
        CLASSNAME_IMAGE_MAP.put(HeroEmoji.class.getName(), HERO_IMAGE);

        ImageManager.ENEMY_MOB_1_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.emoji_9);
        CLASSNAME_IMAGE_MAP.put(EnemyEmojiMob_1.class.getName(), ENEMY_MOB_1_IMAGE);
        ImageManager.ENEMY_MOB_2_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.emoji_15);
        CLASSNAME_IMAGE_MAP.put(EnemyEmojiMob_2.class.getName(),ENEMY_MOB_2_IMAGE);
        ImageManager.ENEMY_MOB_3_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.emoji_22);
        CLASSNAME_IMAGE_MAP.put(EnemyEmojiMob_3.class.getName(),ENEMY_MOB_3_IMAGE);

        ImageManager.ENEMY_BOSS_1_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.boss_beer);
        CLASSNAME_IMAGE_MAP.put(EnemyEmojiBoss_1.class.getName(),ENEMY_BOSS_1_IMAGE);
    }


    private static void initBulletImage(Context context) {
        ImageManager.GREEN_BULLET_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.mana_prism);
        CLASSNAME_IMAGE_MAP.put(GreenBullet.class.getName(), GREEN_BULLET_IMAGE);
        ImageManager.PURE_BULLET_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.pure_prism);
        CLASSNAME_IMAGE_MAP.put(PureBullet.class.getName(),PURE_BULLET_IMAGE);
        ImageManager.GOLDEN_BULLET_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.rare_prism);
    }


    private static void initItemImage(Context context) {
        ImageManager.ITEM_HEAL_FIXED = BitmapFactory.decodeResource(context.getResources(),R.drawable.heal_fixed);
        CLASSNAME_IMAGE_MAP.put(HealFixedItem.class.getName(),ITEM_HEAL_FIXED);
        ImageManager.ITEM_SHOOT_POWER_ADD = BitmapFactory.decodeResource(context.getResources(),R.drawable.shoot_power_add);
        CLASSNAME_IMAGE_MAP.put(ShootPowerAddItem.class.getName(),ITEM_SHOOT_POWER_ADD);
        ImageManager.ITEM_SHOOT_SPEED_ADD = BitmapFactory.decodeResource(context.getResources(),R.drawable.shoot_speed_add);
        CLASSNAME_IMAGE_MAP.put(ShootSpeedAddItem.class.getName(),ITEM_SHOOT_SPEED_ADD);
        ImageManager.ITEM_SHOOT_BULLET_ADD = BitmapFactory.decodeResource(context.getResources(),R.drawable.shoot_bullet_add);
        CLASSNAME_IMAGE_MAP.put(ShootBulletAddItem.class.getName(),ITEM_SHOOT_BULLET_ADD);
    }

    public static Bitmap get(String className) {
        return CLASSNAME_IMAGE_MAP.get(className);
    }
    public static Bitmap get(Object obj) {
        if (obj == null) return null;
        return get(obj.getClass().getName());
    }
}
