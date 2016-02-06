package com.example.coyanoh.quizgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by coyanoh on 12/31/15.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    private MainThread thread;
    private Bitmap scaledSoldier,scaledDiamond,scaledCivilian,scaledKing,scaledKnight,scaledCaptain,scaledGameWord,scaledBg,scaledMoneyPanel,sulMoney,scaledMiddle,scaledDone, scaledPlayer1,scaledPlayer2,scaledDraw, scaledNew;
    private Middle mid;
    private Money money;
    private GameWord gameWord1,gameWord2,done,player1,player2,draw;
    private MoneyPanel moneyPanel;
    private Background bg;
    private Timer timer;
    private Score score11;
    private Score score22;
    private float xTouch, yTouch;
    private float scaleFactorX;
    private float scaleFactorY;
    private ArrayList<GameObject> gameObject1 = new ArrayList<GameObject>();
    private ArrayList<GameObject> gameObject2 = new ArrayList<GameObject>();
    private ArrayList<GameObject> gameObject3 = new ArrayList<GameObject>();
    private ArrayList<GameObject> gameObject4 = new ArrayList<GameObject>();
    private ArrayList<Bitmap> scaledMoney = new ArrayList<>();
    private ArrayList<Bitmap> Money1 = new ArrayList<>();
    private ArrayList<Bitmap> Money2 = new ArrayList<>();
    private ArrayList<Bitmap> scaledTimer = new ArrayList<>();
    private String gameObjectName;
    private int num = -1;
    private boolean usedDiamond,usedDiamond2;
    private String choose = "Nope";
    private String choose2 = "Nope";
    private Compare compare = new Compare();
   // private int savedState1,savedState2;
    private int state = 1;
    private int round = 0;
    private int winner = 0;
    private int score1 = 0;
    private int score2 = 0;
    private int totalWinner = 0;



    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        scaleFactorX = getWidth() / (WIDTH * 1.f);
        scaleFactorY = getHeight() / (HEIGHT * 1.f);

        createPics();

        scaledMoney.add(BitmapFactory.decodeResource(getResources(), R.drawable.money));
        scaledMoney.add(BitmapFactory.decodeResource(getResources(), R.drawable.kingword));
        Money1.add(BitmapFactory.decodeResource(getResources(), R.drawable.money));
        Money1.add(BitmapFactory.decodeResource(getResources(), R.drawable.kingword));
        Money2.add(BitmapFactory.decodeResource(getResources(), R.drawable.money));
        Money2.add(BitmapFactory.decodeResource(getResources(), R.drawable.kingword));
        scaledTimer.add(BitmapFactory.decodeResource(getResources(), R.drawable.civilian));
        scaledTimer.add(BitmapFactory.decodeResource(getResources(), R.drawable.captain));

        setupVar();

        newMoney(scaledMoney,money.drawUpdate(money.vals[0]));
        newMoney(Money1,score11.drawUpdate(0));
        newMoney(Money2,score22.drawUpdate(0));
        newMoney(scaledTimer,timer.drawUpdate());

        score11.setBitmap(Money1.get(0),Money1.get(1));
        score22.setBitmap(Money2.get(0),Money2.get(1));
        money.setBitmap(scaledMoney.get(0),scaledMoney.get(1));
        timer.setBitmap(scaledTimer.get(0),scaledTimer.get(1));

        thread = new MainThread(getHolder(), this);
        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000)
        {
            counter++;
            try{thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;

            }catch(InterruptedException e){e.printStackTrace();}

        }
    }

    public void imageWord(GameWord gameWord,String s){
        switch(s){
            case "Diamond":
                //System.out.println("test");
                scaledGameWord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.diamondword),192,64,true);
                gameWord.setBitmap(scaledGameWord);
                break;
            case "Civilian":
                scaledGameWord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.civilianword),192,64,true);
                gameWord.setBitmap(scaledGameWord);
                break;
            case "Soldier":
                scaledGameWord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.soldierword),192,64,true);
                gameWord.setBitmap(scaledGameWord);
                break;
            case "Captain":
                scaledGameWord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.captainword),192,64,true);
                gameWord.setBitmap(scaledGameWord);

                break;
            case "Knight":
                scaledGameWord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.knightword),192,64,true);
                gameWord.setBitmap(scaledGameWord);
                break;
            case "King":
                scaledGameWord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.kingword),192,64,true);
                gameWord.setBitmap(scaledGameWord);
                break;
            default:
                //scaledGameWord = null;
                //gameWord.setBitmap(scaledGameWord);

        }

    }

    public void newMoney(ArrayList<Bitmap> j,ArrayList<Integer> i) {
        for (int s = 0; s < 2; s++) {
            if (s == 0) {
                switch (i.get(s)) {
                    case 1:
                        j.set(0, money.getImage(0));
                        break;
                    case 2:
                        j.set(0, money.getImage(1));
                        break;
                    case 3:
                        j.set(0, money.getImage(2));
                        break;
                    case 4:
                        j.set(0, money.getImage(3));
                        break;
                    case 5:
                        j.set(0, money.getImage(4));
                        break;
                    case 6:
                        j.set(0, money.getImage(5));
                        break;
                    case 7:
                        j.set(0, money.getImage(6));
                        break;
                    case 8:
                        j.set(0, money.getImage(7));
                        break;
                    case 9:
                        j.set(0, money.getImage(8));
                        break;
                    case 0:
                        j.set(0, money.getImage(9));
                        break;
                }
            }
                if (s == 1){
                    switch (i.get(s)){
                        case 1:
                            j.set(1,money.getImage(0));
                            break;
                        case 2:
                            j.set(1,money.getImage(1));
                            break;
                        case 3:
                            j.set(1,money.getImage(2));
                            break;
                        case 4:
                            j.set(1,money.getImage(3));
                            break;
                        case 5:
                            j.set(1,money.getImage(4));
                            break;
                        case 6:
                            j.set(1,money.getImage(5));
                            break;
                        case 7:
                            j.set(1,money.getImage(6));
                            break;
                        case 8:
                            j.set(1,money.getImage(7));
                            break;
                        case 9:
                            j.set(1,money.getImage(8));
                            break;
                        case 0:
                            j.set(1,money.getImage(9));
                            break;
                    }
                }
            }
        }

    public String collision(int xcoor, int ycoor, GameObject b)
    {
        String collide = new String();
        Rect rekt = b.getRectangle();
        //int testX = rekt.centerX();
        //int testY = rekt.centerX();
        if(rekt.contains(xcoor,ycoor))
        {
            //System.out.println("X: "+testX+ " Y: "+testY);
            collide = b.getString();
            return collide;
            //return true;
        }
        else {
            //System.out.println("X: "+testX+ " Y: "+testY);
            return null;
            //return false;
        }
    }

    public boolean collBetObj(GameObject a, GameObject b)
    {
        if(Rect.intersects(a.getRectangle(), b.getRectangle()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        ArrayList<GameObject> gameObject = new ArrayList<GameObject>();
        String player = null;
        GameWord gameWord = gameWord1;
        boolean playerDiamond = false;
        if (state == 1) {
            gameObject = gameObject1;
            playerDiamond = usedDiamond;
            player = choose;
            gameWord = gameWord1;
        }
        if (state == 2) {
            gameObject = gameObject2;
            playerDiamond = usedDiamond2;
            player = choose2;
            gameWord = gameWord2;
        }
        if (state ==3){
            gameObject = gameObject3;
        }
        if(state == 4){
            gameObject = gameObject4;
        }

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                playerDiamond = false;
                xTouch = event.getX();
                yTouch = event.getY();

                //System.out.println("x: "+ (int)(xTouch/scaleFactorX)+" "+scaleFactorX);
                //System.out.println("y: "+ (int)(yTouch/scaleFactorY)+" "+scaleFactorY);
                for (int i = 0; i < gameObject.size(); i++){
                    gameObjectName = collision((int)(xTouch/scaleFactorX),(int)(yTouch/scaleFactorY),gameObject.get(i));

                    if (gameObjectName != null){
                        imageWord(gameWord,gameObjectName);
                        System.out.println(gameObjectName);
                        num = i;
                        //gameObject.get(i).setX((int)(xTouch/scaleFactorX));
                        //gameObject.get(i).setY((int)(yTouch/scaleFactorY));
                        break;
                    }

                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (state ==1 || state ==2 ) {
                    if (num != -1) {
                        if (gameObjectName != "Done") {
                            xTouch = event.getX();
                            //System.out.println("X: " + xTouch);
                            yTouch = event.getY();
                            //System.out.println("Y: " + yTouch);

                            if (num >= 0) {
                                gameObject.get(num).setX((int) (xTouch / scaleFactorY) - (gameObject.get(num).getWidth() / 2));
                                gameObject.get(num).setY((int) (yTouch / scaleFactorY) - (gameObject.get(num).getHeight() / 2));
                            }
                        }
                    }
                }
                    break;

            case MotionEvent.ACTION_UP:
                /*if (collBetObj(gameObject.get(num),mid)){
                    System.out.println("collide");
                    gameObject.remove(num);
                    overlayBitmap.eraseColor(Color.TRANSPARENT);
                }*/
                if (num!=-1) {
                    if ((collBetObj(gameObject.get(num),mid)&& player == "Nope")||(collBetObj(gameObject.get(num),mid))&&(gameObject.get(num).getString() == "Diamond")) {
                        if (gameObject.get(num).getString() != "Diamond") {
                            player = gameObject.get(num).getString();
                            //System.out.println("Choose: " + player);
                            gameObject.remove(num);
                        }

                        else if(gameObject.get(num).getString() == "Diamond"&& player == "Nope"){
                            //System.out.println("Choose: " + player);
                            gameObject.get(num).setX(gameObject.get(num).getInitialX());
                            gameObject.get(num).setY(gameObject.get(num).getInitialY());
                        }

                        else{
                            playerDiamond = true;
                            //System.out.println("Diamond used: " + playerDiamond);
                            gameObject.remove(num);
                        }
                        if (state ==1){
                            usedDiamond = playerDiamond;
                            choose =  player;
                        }
                        else if (state ==2){
                            usedDiamond2 = playerDiamond;
                            choose2 = player;
                        }
                        //gameObject.get(num).getBit().eraseColor(Color.TRANSPARENT);


                    }
                    else{

                        if (gameObject.get(num).getString() != "Done") {
                            gameObject.get(num).setX(gameObject.get(num).getInitialX());
                            gameObject.get(num).setY(gameObject.get(num).getInitialY());
                        }
                        else {
                            if (state ==1){
                                //usedDiamond = playerDiamond;
                                //choose =  player;
                                state = 2;
                                scaledGameWord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.player2),192,64,true);
                                gameWord2.setBitmap(scaledGameWord);
                                timer.resetTimer();
                            }
                            else if (state ==2){
                                //usedDiamond2 = playerDiamond;
                                //choose2 = player;
                                state = 3;
                                timer.resetTimer();


                                compare.setCompare(choose,usedDiamond,choose2,usedDiamond2);
                                winner = compare.comparator();
                                System.out.println("Winner: " + winner);
                                choose = "Nope";
                                choose2 = "Nope";
                                usedDiamond = false;
                                usedDiamond2 = false;
                            }
                            else if(state ==3){
                                state = 1;
                                scaledGameWord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.player1),192,64,true);
                                gameWord1.setBitmap(scaledGameWord);
                                //gameWord2.setBitmap(scaledGameWord);
                                if (winner == 1){
                                    score1 = score1+money.vals[round];
                                    newMoney(Money1,score11.drawUpdate(score1));
                                    score11.setBitmap(Money1.get(0),Money1.get(1));
                                }
                                else if ( winner ==2){
                                    score2 = score2+money.vals[round];
                                    newMoney(Money2,score22.drawUpdate(score2));
                                    score22.setBitmap(Money2.get(0),Money2.get(1));
                                }
                                if (round < 5){
                                    round++;

                                    if (round ==5){
                                        state = 4;
                                    }
                                    else {

                                        newMoney(scaledMoney, money.drawUpdate(money.vals[round]));
                                        money.setBitmap(scaledMoney.get(0), scaledMoney.get(1));
                                    }

                                }

                                int remaining = 0;
                                for (int i = 0;i<((money.vals.length)-round);i++){
                                    remaining = remaining+money.vals[4-i];
                                    System.out.println("Money: "+money.vals[4-i]);
                                }
                                System.out.println(remaining);
                                System.out.println((score1+remaining) <score2);
                                System.out.println((score2+remaining) < score1);

                                if (((score1+remaining) <score2 )||( (score2+remaining) < score1)){
                                    state = 4;
                                    System.out.println("Sad");
                                }

                                timer.resetTimer();
                                winner = 0;

                            }
                            else if(state ==4){
                                newGame();
                            }
                            //System.out.println("State: "+ state);
                            //System.out.println("Score1: "+ score1);
                            //System.out.println("Score2: "+ score2);
                        }
                    }
                    num = -1;
                    gameObjectName = null;
                }
                break;
        }

        return true;
    }

    public void update(){
        bg.update();
        int remaining = 0;

        if (state!=4) {
            done.setBitmap(scaledDone);
            if (state == 1) {
                for (int i = 0; i < gameObject1.size(); i++) {
                    gameObject1.get(i).update();
                    score11.update();
                    gameWord1.update();
                }

            }
            if (state == 2) {
                for (int i = 0; i < gameObject2.size(); i++) {
                    gameObject2.get(i).update();
                    score22.update();
                    gameWord2.update();
                }
            }
        }
        if (state == 4){
            done.setBitmap(scaledNew);
            if (score1>score2){
                totalWinner = 1;
            }
            else if (score1<score2){
                totalWinner = 2;
            }
            else{
                totalWinner = 0;
            }
        }

        done.update();
        moneyPanel.update();
        timer.update();
        mid.update();
        newMoney(scaledTimer, timer.drawUpdate());
        timer.setBitmap(scaledTimer.get(0), scaledTimer.get(1));
        if (timer.getGG()&& state ==1){
            state = 2;

            timer.resetTimer();
        }

        if (timer.getGG()&& state ==2){
            state = 3;
            compare.setCompare(choose,usedDiamond,choose2,usedDiamond2);
            winner = compare.comparator();
            choose = "Nope";
            choose2 = "Nope";
            usedDiamond = false;
            usedDiamond2 = false;
            timer.resetTimer();
        }

        //System.out.println("test");
    }

    @Override
    public void draw(Canvas canvas) {

        if (canvas != null) {
            //final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            bg.draw(canvas);
            if (state ==1 || state ==2) {

                moneyPanel.draw(canvas);
                mid.draw(canvas);
                money.draw(canvas);
                timer.draw(canvas);
                if (state == 1) {
                    for (int i = 0; i < gameObject1.size(); i++) {
                        gameObject1.get(i).draw(canvas);


                    }
                    score11.draw(canvas);
                    gameWord1.draw(canvas);
                }
                if (state == 2) {
                    for (int i = 0; i < gameObject2.size(); i++) {
                        gameObject2.get(i).draw(canvas);

                    }
                    score22.draw(canvas);
                    gameWord2.draw(canvas);

                }

            }
            done.draw(canvas);
            if (state ==3){
                if(winner ==1){
                    player1.draw(canvas);
                }
                else if(winner ==2){
                    player2.draw(canvas);
                }
                else if (winner == 0){
                    draw.draw(canvas);
                }
            }
            if (state == 4){
                if ( totalWinner == 1){
                    player1.draw(canvas);
                }
                else if(totalWinner ==2){
                    player2.draw(canvas);
                }
                else if (totalWinner == 0){
                    draw.draw(canvas);
                }
                //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            }
           // canvas.restoreToCount(savedState);
        }

    }

    public void createPics(){
        scaledCivilian = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.civilian),64,64,true);
        scaledSoldier = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.soldier),64,64,true);
        scaledKnight = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.knight),64,64,true);
        scaledCaptain = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.captain),64,64,true);
        scaledKing = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.king),64,64,true);
        scaledDiamond = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.diamond), 64, 64, true);
        scaledBg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.background),(int)(getWidth()/scaleFactorX),(int)(getHeight()/scaleFactorY),true);
        scaledMoneyPanel = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.moneypanel),256,128,true);

        sulMoney = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.money),320,64,true);
        scaledMiddle = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.hole),128,128,true);
        scaledDone = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.done),192,64,true);
        scaledNew = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new1),192,64,true);
        scaledDraw = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.draw),192,64,true);
        scaledPlayer1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.player1),192,64,true);
        scaledPlayer2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.player2),192,64,true);

    }

    public void setupVar(){
        moneyPanel = new MoneyPanel(scaledMoneyPanel,256,128);
        money = new Money(sulMoney,312,64);
        timer = new Timer(sulMoney,312,64);
        score11 = new Score(sulMoney,312,64);
        score22 = new Score(sulMoney,312,64);
        bg = new Background(scaledBg);

        gameWord1 = new GameWord(scaledGameWord,192,64,10,400, "gameWord1");
        scaledGameWord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.player1),192,64,true);
        gameWord1.setBitmap(scaledGameWord);
        gameWord2 = new GameWord(scaledGameWord,192,64,10,400, "gameWord2");
        scaledGameWord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.player2),192,64,true);
        gameWord2.setBitmap(scaledGameWord);
        done = new GameWord(scaledDone,192,64,200,400,"Done");
        player1 = new GameWord(scaledPlayer1,192,64,300,300,"Player1");
        player2 = new GameWord(scaledPlayer2,192,64,300,300,"Player2");
        draw = new GameWord(scaledDraw,192,64,300,300,"Draw");

        mid = new Middle(scaledMiddle,(int)(128/scaleFactorX),(int)(128/scaleFactorY),364,176);
        gameObjectadd();
    }

    public void gameObjectadd(){
        gameObject1.add(new Civilian(scaledCivilian,64,64));
        gameObject2.add(new Civilian(scaledCivilian,64,64));
        gameObject1.add(new Soldier(scaledSoldier,64,64));
        gameObject2.add(new Soldier(scaledSoldier,64,64));
        gameObject1.add(new Knight(scaledKnight,64,64));
        gameObject2.add(new Knight(scaledKnight,64,64));
        gameObject1.add(new Captain(scaledCaptain,64,64));
        gameObject2.add(new Captain(scaledCaptain,64,64));
        gameObject1.add(new King(scaledKing,64,64));
        gameObject2.add(new King(scaledKing,64,64));
        gameObject1.add(new Diamond(scaledDiamond,64,64));
        gameObject2.add(new Diamond(scaledDiamond,64,64));
        gameObject1.add(done);
        gameObject2.add(done);
        gameObject3.add(done);
        gameObject4.add(done);
    }

    public void newGame(){

        gameObject1.clear();
        gameObject2.clear();
        gameObject3.clear();
        gameObject4.clear();


        gameObjectadd();
        timer.resetTimer();
        score1 = 0;
        score2 = 0;
        totalWinner = 0;
        winner = 0;
        money.randomize();
        state = 1;
        round = 0;
        choose = "Nope";
        choose2 = "Nope";
        newMoney(scaledMoney,money.drawUpdate(money.vals[0]));
        newMoney(Money1,score11.drawUpdate(0));
        newMoney(Money2,score22.drawUpdate(0));
        newMoney(scaledTimer,timer.drawUpdate());


        score11.setBitmap(Money1.get(0),Money1.get(1));
        score22.setBitmap(Money2.get(0),Money2.get(1));
        money.setBitmap(scaledMoney.get(0),scaledMoney.get(1));
        timer.setBitmap(scaledTimer.get(0),scaledTimer.get(1));

    }
}
