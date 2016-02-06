package com.example.coyanoh.quizgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by coyanoh on 1/5/16.
 */
public class Timer extends GameObject {
    private Bitmap number1, number2;
    private long startTime;
    private int width,height,row;
    private int moneyNew = 50;
    private int time;
    private boolean gg = false;
    private int numbers = 10;

    private Random rand = new Random();
    //public int vals[] = new int[5];
    private Bitmap[] imageMoney = new Bitmap[numbers];
    //public ArrayList<Integer> money = new ArrayList<>();
    public Timer (Bitmap res,int w, int h){

        x = 100;
        y = 200;
        dx = 30;
        time = 10;
        spritesheet = res;
        height = h;
        width = w;
        row = 0;
        for (int i = 0; i < numbers; i++){
            imageMoney[i] = Bitmap.createBitmap(res, i*32 ,0,32,64);

        }
        startTime = System.nanoTime();
    }

    public Bitmap getImage(int i){
        return imageMoney[i];
    }

    public ArrayList<Integer> drawUpdate() {
        ArrayList<Integer> moneyUpdate = new ArrayList<Integer>();
        int twoIndi = time % 10;
        int oneIndi = time / 10;
        moneyUpdate.add(oneIndi);
        moneyUpdate.add(twoIndi);
        //System.out.println("Two: " + twoIndi + "One: "+ oneIndi);
        return moneyUpdate;
    }

    public boolean getGG(){
        return gg;
    }

    public void resetTimer(){
        gg = false;
        time = 10;
    }

    public void update(){
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if (elapsed > 1000 && time >0){
            time = time - 1;
            startTime = System.nanoTime();
            //System.out.println("Time: "+ time);
        }
        if (time == 0){
            gg = true;
        }
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
