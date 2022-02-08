package com.example.chess_mobile;

import android.util.Log;

public class Driver {
    //Attributes
    int a = 0;
    int b = 1;
    int c = 2;
    int d = 3;
    int e = 4;
    int f = 5;
    int g = 6;
    int h = 7;
    char[] letters = new char[]{'a', 'b', 'c','d','e','f','g','h'};
    Box[][] board = new Box[8][8];

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
    //
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
                board[i][u] = new Box(""+letters[u]+""+(i+1));
                Log.i("prueba"+ "cassilla: "+i,""+letters[i]+ "::"+u);
            }
        }
    }
    //Pone la piezas
    public void buildPieces(){
        //A---1||
        //Towers-----------
        board[a][0].setPiece(new Tower());  //W
        board[h][0].setPiece(new Tower());  //B
        board[a][7].setPiece(new Tower());  //W
        board[h][7].setPiece(new Tower());  //B
        //Horses-----------
        board[b][0].setPiece(new Horse());
        board[g][0].setPiece(new Horse());
        board[b][7].setPiece(new Horse());
        board[g][7].setPiece(new Horse());
        //Kings------------
        board[e][0].setPiece(new King());
        board[e][7].setPiece(new King());
        //Queens-----------
        board[d][0].setPiece(new Queen());
        board[d][7].setPiece(new Queen());
        //Pawns------------
        for (int i = 0; i < 8; i++) {
            board[i][1].setPiece(new Pawn());
        }
        for (int i = 0; i < 8; i++) {
            board[i][6].setPiece(new Pawn());
        }
    }
    //Lista las posiciones a las que la pieza en su casilla puede moverse
    //public int[]
}
