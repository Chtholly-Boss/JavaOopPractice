package com.example.aircraftwar.basic;

import android.graphics.Bitmap;

import com.example.aircraftwar.aircraft.BaseAircraft;
import com.example.aircraftwar.manager.ImageManager;
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

    // Crash Check Logic
    // TODO : CheckPoint vary from obj to obj;
    // TODO : Scale to be done
    public boolean crash(AbstractFlyingObject that) {
        int factor = this instanceof BaseAircraft ? 2 : 1;
        int tFactor = that instanceof BaseAircraft ? 2 : 1;
        int thatX = that.getX();
        int thatY = that.getY();
        int thatWidth = that.getWidth();
        int thatHeight = that.getHeight();
        return
                this.x + (thatWidth + this.width) / 2 > this.x
                && this.x - (thatWidth + this.width) /2 < this.x
                && this.y + (thatHeight + this.height) / 2 > this.y
                && this.y - (thatHeight + this.height) /2 < this.y;
    }
    public boolean notValid() {
        return !this.isValid;
    }
    public void vanish() {
        this.isValid = false;
    }
    public void updateForBomb(){}

    public Bitmap getImage() {
        if (image == null) image = ImageManager.get(this);
        return image;
    }
}
