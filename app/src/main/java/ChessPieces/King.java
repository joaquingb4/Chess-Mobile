package ChessPieces;

import com.example.chess_mobile.Box;

import java.util.ArrayList;

public class King extends Piece {
    //Attributes
    private final String name = "King";
    private String color;
    //Methods
    public King(String color){
        this.color = color;
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
