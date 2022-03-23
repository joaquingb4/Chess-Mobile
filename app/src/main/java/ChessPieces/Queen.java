package ChessPieces;

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
    public int getPossiblesBoxesNumber(Box[][] board, int x, int y) {
        return 0;
    }

    @Override
    public ArrayList<Box> getPossiblesBoxes(Box[][] board, int x, int y) {
        return null;
    }}