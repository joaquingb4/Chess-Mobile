package com.example.chess_mobile;

import android.util.Log;

public class Driver {
    //Attributes
    int[] letters = new int[]{'a', 'b', 'c','d','e','f','g','h'};
    Box[][] board = new Box[24][24];

    //Traduce de letra a número
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
    //Devuelve el nombre de la pieza que tiene una casilla determinada
    public String getDataBox(int column , int row ) {
        Log.i("hola", "" + column + " ___ " + row);

        if (board[column][row].getPiece()==null){
            return "empty";
        }else{
            return board[column][row].getPiece().getName();
        }
    }

    //Llena el tablero de casillas
    public void buildBoxes(){
        for (int i = 0; i < board.length ; i++) {
            for (int u = 0; u < board[i].length; u++) {
                board[i][u] = new Box(""+letters[u]+""+i);
            }
        }
    }
    //Pone la piezas
    public void buildPieces(){
        //A---1||
        //Towers
        board[0][0].setPiece(new Tower());
        board[7][0].setPiece(new Tower());
        //Horses
        board[1][0].setPiece(new Horse());
        board[6][0].setPiece(new Horse());

        //Pawns
        board[0][1].setPiece(new Pawn());
        board[1][1].setPiece(new Pawn());
        board[2][1].setPiece(new Pawn());
        board[3][1].setPiece(new Pawn());
        board[4][1].setPiece(new Pawn());
        board[5][1].setPiece(new Pawn());
        board[6][1].setPiece(new Pawn());





    }

}
