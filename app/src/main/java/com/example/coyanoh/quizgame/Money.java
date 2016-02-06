package com.example.coyanoh.quizgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by coyanoh on 1/4/16.
 */
public class Money extends GameObject{
    private Bitmap number1, number2;
    private long startTime;
    private int width,height,row;
    private int moneyNew = 50;
    private int eachTurn;
    private int numbers = 10;
    private Random rand = new Random();
    public int vals[] = new int[5];
    private Bitmap[] imageMoney = new Bitmap[numbers];
    //public ArrayList<Integer> money = new ArrayList<>();
    public Money (Bitmap res,int w, int h){

        x = 100;
        y = 100;
        dx = 30;
        spritesheet = res;
        height = h;
        width = w;
        row = 0;
        for (int i = 0; i < numbers; i++){
            imageMoney[i] = Bitmap.createBitmap(res, i*32 ,0,32,64);

        }

        randomize();


        //setBitmap(GamePanel.getArray().get(0),GamePanel.getArray().get(1));
        //name = "King";
        //spritesheet = BitmapFactory.decodeResource(getResources(), R.drawable.diamond);
        //y = GamePanel.HEIGHT;

        //number1 =res1;
        //number2 = res2;
        //startTime = System.nanoTime();
    }

    public void randomize(){
        int count = 5;
        int sum = 100;
        Random g = new Random();


        sum -= count;

        for (int i = 0; i < count-1; ++i) {
            vals[i] = g.nextInt(sum);
        }
        vals[count-1] = sum;

        java.util.Arrays.sort(vals);
        for (int i = count-1; i > 0; --i) {
            vals[i] -= vals[i-1];
        }
        for (int i = 0; i < count; ++i) { ++vals[i]; }

        for (int i = 0; i < count; ++i) {
            System.out.printf("%4d", vals[i]);
        }
        System.out.printf("\n");
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
