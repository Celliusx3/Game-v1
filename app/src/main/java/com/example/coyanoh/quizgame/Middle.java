package com.example.coyanoh.quizgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by coyanoh on 1/5/16.
 */
public class Middle extends GameObject{
        //private Bitmap spritesheet;
        private long startTime;
        public Middle(Bitmap res, int w, int h, int x, int y){
            this.x = x;
            this.y = y;
            name = "Middle";
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

