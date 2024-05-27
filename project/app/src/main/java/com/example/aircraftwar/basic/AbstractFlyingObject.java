package com.example.aircraftwar.basic;

import android.graphics.Bitmap;

import com.example.aircraftwar.strategy.move.MoveStrategy;

public abstract class AbstractFlyingObject {
    protected int x;
    protected int y;
    protected int vx;
    protected int vy;
    protected int directionX = 0;
    protected int directionY = 0;
    protected Bitmap image;
    protected int width = -1;
    protected int height = -1;
    protected MoveStrategy movePattern;
    protected boolean isValid = false;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    // Move Pattern: Specified by Subclass
    public void move(){
        assert movePattern != null : "Move Pattern Unset!";
        this.movePattern.moveWithPattern(this);
    };

    public void setMovePattern(MoveStrategy movePattern) {
        this.movePattern = movePattern;
    }

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
    public void updateForBomb(){}
}
