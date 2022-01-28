package com.example.chess_mobile;

import android.util.Log;

public class Driver {
    //Attributes
    int[] letters = new int[]{'a', 'b', 'c'};
    Piece[][] board = new Piece[3][3];

    //
    public  int letterToInt(char letter){
        for (int i = 0; i < letters.length; i++) {
            if (letters[i]==letter){
                Log.i("hola",""+i);
                return i;
            }
        }
        return 0;
    }
    public String getDataBox(String tag){

        int column = letterToInt(tag.charAt(0));
        int row = Character.getNumericValue(tag.charAt(1))-1;//Se le quita uno por la nomenclatura del ajedrez
        return getDataBox(column, row);
    }
    public String getDataBox(int column , int row ){
        Log.i("hola",""+column + " ___ "+ row );
        return board[column][row].getName();
    }

    public void buildPieces(){
        //A---1||
        //Towers
        board[0][0] = new Tower();
        board[1][0] = new Tower();
        board[2][0] = new Tower();
        //Pawns
        board[0][1] = new Pawn();
        board[1][1] = new Pawn();
        board[2][1] = new Pawn();
    }

}
