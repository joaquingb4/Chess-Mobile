package com.example.chess_mobile.ChessPieces;



import static com.example.chess_mobile.Tools.BoardTools.isInsideTheBoard;
import static com.example.chess_mobile.Tools.BoardTools.nextPosition;
import static com.example.chess_mobile.Tools.BoxTools.haveAPiece;
import static com.example.chess_mobile.Tools.BoxTools.isOfTheSameColor;
import static com.example.chess_mobile.Tools.CalculTools.getXYOfANumber;

import com.example.chess_mobile.Box;

import java.util.ArrayList;

public class Queen extends Piece {
    //Attributes
    private final String name = "Queen";
    private String color;
    //Methods
    public Queen(String color){
        setColor(color);
    }
    private void setColor(String color) {
        this.color = color;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String setColor() {
        return this.color;
    }

    @Override
    public boolean isFirstMovement() {
        return false;
    }

    //Devuelve un ArrayList con las casillas posibles
    public ArrayList<Box> getAvailableMoves(Box[][] board, int x, int y) {
        ArrayList<Box> boxes = new ArrayList<>();

        int nextNumber;
        int nextNumberX;
        int nextNumberY;
        Box nextBox;

        int originalX = x;
        int originalY = y;

        int[] directions = new int[]{+9, +10, +11, +1, -11, -10, -9, -1, -10};

        for (int i = 0; i < directions.length; i++) {
            while (true) {
                nextNumber = nextPosition(x, y, directions[i]);
                nextNumberX = getXYOfANumber(nextNumber)[0];
                nextNumberY = getXYOfANumber(nextNumber)[1];

                if (isInsideTheBoard(nextNumberX, nextNumberY)) {
                    nextBox = board[nextNumberX][nextNumberY];

                    if (haveAPiece(nextBox)) {
                        if (!isOfTheSameColor(this, nextBox.getPiece())) {
                            boxes.add(board[nextNumberX][nextNumberY]);
                            x = nextNumberX;
                            y = nextNumberY;
                        }
                        break;
                    } else {
                        boxes.add(nextBox);
                        x = nextNumberX;
                        y = nextNumberY;
                    }
                } else {
                    break;
                }
            }
            x = originalX;
            y = originalY;
        }
        return boxes;
    }
}