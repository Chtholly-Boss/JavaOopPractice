package com.example.aircraftwar.game;

import android.content.Context;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.example.aircraftwar.manager.ImageManager;

public class EasyGame extends BaseGame{

    public EasyGame(Context context) {
        super(context);
        this.background = ImageManager.STAGE_1_IMAGE;
    }
}
