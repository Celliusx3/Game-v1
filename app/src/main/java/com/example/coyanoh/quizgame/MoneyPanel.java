package com.example.coyanoh.quizgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by coyanoh on 1/4/16.
 */
public class MoneyPanel extends GameObject {
   // private Bitmap spritesheet;

    public MoneyPanel(Bitmap res, int w, int h){
        x = 300;
        y = 20;

        //name = "King";
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

