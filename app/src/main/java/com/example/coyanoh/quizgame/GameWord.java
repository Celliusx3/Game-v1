package com.example.coyanoh.quizgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by coyanoh on 1/2/16.
 */
public class GameWord extends GameObject {
    //private Bitmap spritesheet;
    private long startTime;
    public GameWord(Bitmap res, int w, int h, int x, int y,String s){
        this.x = x;
        this.y = y;
        name = s;

        //y = GamePanel.HEIGHT;
        height = h;
        width = w;
        spritesheet =res;
        //startTime = System.nanoTime();
    }

    public void update(){

    }

    public void setBitmap(Bitmap res){
        spritesheet = res;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(spritesheet,x,y,null);
    }
}

