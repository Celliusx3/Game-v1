package com.example.coyanoh.quizgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by coyanoh on 1/7/16.
 */
public class Score extends GameObject {
    private Bitmap number1, number2;
    private int numbers = 10;
    private Bitmap[] imageMoney = new Bitmap[numbers];
    //public ArrayList<Integer> money = new ArrayList<>();
    public Score (Bitmap res,int w, int h){

        x = 200;
        y = 200;
        dx = 30;
        spritesheet = res;
        height = h;
        width = w;
        for (int i = 0; i < numbers; i++){
            imageMoney[i] = Bitmap.createBitmap(res, i*32 ,0,32,64);

        }
    }

    public Bitmap getImage(int i){
        return imageMoney[i];
    }

    /*public int getValue(int i){
        return vals[i];
    }*/

    public ArrayList<Integer> drawUpdate(Integer i) {
        ArrayList<Integer> moneyUpdate = new ArrayList<Integer>();
        int twoIndi = i % 10;
        int oneIndi = i / 10;
        moneyUpdate.add(oneIndi);
        moneyUpdate.add(twoIndi);
        //System.out.println("Two: " + twoIndi + "One: "+ oneIndi);
        return moneyUpdate;
    }

    public void update(){

    }

    public void setBitmap(Bitmap res1, Bitmap res2){
        number1 = res1;
        number2 = res2;
    }



    public void draw(Canvas canvas){
        canvas.drawBitmap(number1,x,y,null);
        canvas.drawBitmap(number2,x+dx,y,null);
    }
}
