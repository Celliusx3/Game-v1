package com.example.coyanoh.quizgame;

/**
 * Created by coyanoh on 1/6/16.
 */
public class Compare {
    private String player1,player2;
    private boolean diamond1, diamond2;
    private int value[] = new int[2];
    private String player[] = new String[2];
    private boolean diamond[] = new boolean[2];

    public void setCompare(String player1,boolean diamond1, String player2, boolean diamond2){
        player[0] = player1;
        //System.out.println(player[0]);
        player[1] = player2;
        //System.out.println(player[1]);
        diamond[0] = diamond1;
        //System.out.println(diamond[0]);
        diamond[1] = diamond2;
        //System.out.println(diamond[1]);
        for (int i = 0; i<2;i++){
            switch (player[i]){
                case "Civilian":
                    value[i] = 1;
                    break;
                case "Soldier":
                    value[i] = 3;
                    break;
                case "Knight":
                    value[i] = 5;
                    break;
                case "Captain":
                    value[i] = 7;
                    break;
                case "King":
                    value[i] = 9;
                    break;
                default:
                    value[i] = -1;
                    break;
            }
            if (diamond[i]){
                value[i] = value[i]+1;
            }

        }
    }

    public int comparator(){
    /*-1 noChoose
    1 civilian
    2 civilian+diamond
    3 soldier
    4 soldier+diamond
    5 knight
    6 knight+diamond
    7 captain
    8 captain+diamond
    9 king
    10 king+diamond*/
        if ((value[0] - value[1])>0){
            if ((value[0] == 10 && value[1] == 2)||(value[0] == 9 && value[1] == 1)){
                return 2;
            }
            else if((value[0] == 10 && value[1] == 1)||(value[0] == 3 && value[1] == 2)||(value[0] == 5 && value[1] == 4)||(value[0] == 7 && value[1] == 6)||(value[0] == 9 && value[1] == 8)){
                return 0;
            }
            else {
                return 1;
            }
        }
        if ((value[1] - value[0])> 0){
            if ((value[1] == 10 && value[0] == 2)||(value[1] == 9 && value[0] == 1)){
                return 1;
            }
            else if((value[1] == 10 && value[0] == 1)||(value[1] == 3 && value[0] == 2)||(value[1] == 5 && value[0] == 4)||(value[1] == 7 && value[0] == 6)||(value[1] == 9 && value[0] == 8)){
                return 0;
            }
            else {
                return 2;
            }

        }
        if(value[1] == value[0]){
            return 0;
        }
        return 0;
    }




}
