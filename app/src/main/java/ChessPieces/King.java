package ChessPieces;

import com.example.chess_mobile.Box;

public class King extends Piece {
    //Attributes
    private final String name = "King";
    private String color;
    //Methods
    public King(String color){
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
    public int getAvailableMovements(Box[][] board, int x, int y) {
        return 0;

    }
}
