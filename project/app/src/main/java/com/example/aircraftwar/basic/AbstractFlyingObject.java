package com.example.aircraftwar.basic;

import android.graphics.Bitmap;

import com.example.aircraftwar.aircraft.BaseEmoji;
import com.example.aircraftwar.manager.ImageManager;
import com.example.aircraftwar.strategy.move.MoveStrategy;

public abstract class AbstractFlyingObject {
    protected int x;
    protected int y;
    protected int vx;
    protected int vy;
    protected int directionX = 1;
    protected int directionY = -1;
    protected Bitmap image;
    protected int width = -1;
    protected int height = -1;
    protected MoveStrategy movePattern;
    protected boolean isValid = true;
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

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getWidth() {
        if (width == -1) {
            width = ImageManager.get(this).getWidth();
        }
        return width;
    }

    public int getHeight() {
        if (height == -1) {
            height = ImageManager.get(this).getHeight();
        }
        return height;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    // Move Pattern: Specified by Subclass
    public void move(){
        assert movePattern != null : "Move Pattern Unset!!!";
        this.movePattern.moveWithPattern(this);
    };

    public void setMovePattern(MoveStrategy movePattern) {
        this.movePattern = movePattern;
    }

    public boolean crash(AbstractFlyingObject that) {
        int factor = this instanceof BaseEmoji ? 2 : 1;
        int tFactor = that instanceof BaseEmoji ? 2 : 1;
        int thatX = that.getX();
        int thatY = that.getY();
        int thatWidth = that.getWidth();
        int thatHeight = that.getHeight();
        return
                thatX + (thatWidth + this.getWidth()) / 2 > this.x
                && thatX - (thatWidth + this.getWidth()) /2 < this.x
                && thatY + (thatHeight/tFactor + this.getHeight()/factor) / 2 > this.y
                && thatY - (thatHeight/tFactor + this.getHeight()/factor) /2 < this.y;
    }
    public boolean notValid() {
        return !this.isValid;
    }
    public void vanish() {
        this.isValid = false;
    }
    public Bitmap getImage() {
        if (image == null) image = ImageManager.get(this);
        return image;
    }
}
