package com.example.chess_mobile;

public class Driver {
    //Attributes
    Piece[][] board = new Piece[3][3];
    int A = 0;
    int B = 1;
    int C = 2;

    //
    public void buildPieces(){
        board[0][0] = new Tower();
        board[2][0] = new Tower();
        //Pawns
        board[0][1] = new Pawn();
        board[1][1] = new Pawn();
        board[2][1] = new Pawn();
    }

}
