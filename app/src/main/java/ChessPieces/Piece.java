package ChessPieces;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Driver;

import java.util.ArrayList;

public abstract class Piece {
    //Attributes
    private String name;
    private String color;

    //Methods
    public abstract String getName();

    public abstract String getColor();

    public abstract String setColor();

    public abstract ArrayList<Box> getAvailableMoves(Box[][] board, int x, int y);
}
