package com.example.coyanoh.quizgame;

/**
 * Created by coyanoh on 1/4/16.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by coyanoh on 12/28/15.
 */
public class Background {

    private Bitmap image;
    private int x,y,dx;

    public Background(Bitmap res){

        image = res;
        //dx = GamePanel.MOVE;
    }

    public void update(){
        /*x+=dx;
        if (x < -GamePanel.WIDTH){
            x = 0;
        }*/
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x,y, null);
        /*if ( x < 0 ){
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }*/
    }

    /*public void setVector(int dx){
        this.dx = dx;
    }*/
}
