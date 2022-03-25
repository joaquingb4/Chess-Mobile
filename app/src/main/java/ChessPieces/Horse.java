package ChessPieces;

import android.util.Log;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Driver;

import java.util.ArrayList;

public class Horse extends Piece {
    //Attributes
    private final String name = "Horse";
    private String color;
    //Methods
    public Horse(String color){
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
