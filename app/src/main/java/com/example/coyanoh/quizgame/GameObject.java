package com.example.coyanoh.quizgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by coyanoh on 12/31/15.
 */
public abstract class GameObject {
    protected int x,y,dy,dx, initialX, initialY;
    protected int width,height;
    protected Bitmap spritesheet;

    protected String name = new String("GameObject");

    public Bitmap getBit(){
        return spritesheet;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getInitialX(){ return initialX;}

    public int getInitialY(){ return initialY;}

    public String getString(){return name;}

    public int getHeight(){return height;}
    public int getWidth(){return width;}
    public Rect getRectangle(){return new Rect(x,y,x+width,y+height);}
    public void resetBitmap(){
        spritesheet = null;
    }
    public void draw(Canvas canvas){

    }

    public void update(){

    }
}
