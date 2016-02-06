package com.example.coyanoh.quizgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by coyanoh on 12/31/15.
 */
public class Diamond extends GameObject {
    //private Bitmap spritesheet;
    private long startTime;
    public Diamond(Bitmap res, int w, int h){
        x = 480;
        y =400;
        initialX = x;
        initialY = y;
        //y = GamePanel.HEIGHT;
        name = "Diamond";
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
