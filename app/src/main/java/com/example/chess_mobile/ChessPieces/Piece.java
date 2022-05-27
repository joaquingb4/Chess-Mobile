package com.example.chess_mobile.ChessPieces;

import com.example.chess_mobile.Box;

import java.util.ArrayList;

public abstract class Piece {
    //Attributes
    private String name;
    private String color;
    private boolean firstMovement;

    //Methods
    public abstract String getName();

    public abstract String getColor();

    public abstract String setColor();

    public abstract boolean isFirstMovement();

    public abstract ArrayList<Box> getAvailableMoves(Box[][] board, int x, int y);
}
