package com.example.coyanoh.quizgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by coyanoh on 1/2/16.
 */
public class Soldier extends GameObject {
    //private Bitmap spritesheet;
    private long startTime;
    public Soldier(Bitmap res, int w, int h){
        x = 600;
        y = 400;
        initialX = x;
        initialY = y;
        name = "Soldier";
        //spritesheet = BitmapFactory.decodeResource(getResources(), R.drawable.diamond);
                //y = GamePanel.HEIGHT;
                height = h;
        width = w;
        spritesheet =res;
        //startTime = System.nanoTime();
    }

    public void update(){

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(spritesheet,x,y,null);
    }
}
