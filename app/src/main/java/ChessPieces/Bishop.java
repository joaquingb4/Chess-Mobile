package ChessPieces;

import com.example.chess_mobile.Box;

import java.util.ArrayList;

public class Bishop extends Piece {
    //Attributes
    private final String name = "Bishop";
    private String color;
    //Methods
    public Bishop(String color){
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
    public ArrayList<Box> getAvailableMoves(Box[][] board, int x, int y) {
        return null;
    }
}
