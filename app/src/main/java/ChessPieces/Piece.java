package ChessPieces;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Driver;

public abstract class Piece {
    //Attributes
    private String name;
    private String color;

    //Methods
    public abstract String getName();

    public abstract String getColor();

    public abstract String setColor();

    public abstract int getPossibleBoxesNumber(Box[][] board, int x, int y, int direction);

    public abstract Box[] getPossibleBoxes(Box[][] board,int x, int y, int direction);
}
