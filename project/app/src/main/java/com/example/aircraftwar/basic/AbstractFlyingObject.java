package com.example.aircraftwar.basic;

import android.graphics.Bitmap;

public abstract class AbstractFlyingObject {
    private int x;
    private int y;
    private int vx;
    private int vy;
    protected Bitmap image;
    private int width = -1;
    private int height = -1;
    private boolean isValid = false;
    // Constructor: Physics Properties
    public AbstractFlyingObject(int _x,int _y,int _vx,int _vy) {
        x = _x;
        y = _y;
        vx = _vx;
        vy = _vy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Move Pattern: Specified by Subclass
    public void move(){};
    // Crash Check Logic
    // TODO : CheckPoint vary from obj to obj;
    // TODO : Scale to be done
    public boolean crash(AbstractFlyingObject that) {
        int xT = that.getX();
        int yT = that.getY();
        int widthT = that.getWidth();
        int heightT = that.getHeight();

        return false;
    }
    public boolean notValid() {
        return !this.isValid;
    }
    public void vanish() {
        this.isValid = false;
    }
}
