package ChessPieces;

import com.example.chess_mobile.Box;

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
    public int getPossiblesBoxesNumber(Box[][] board, int x, int y) {
        return 0;
    }

    @Override
    public Box[] getPossiblesBoxes(Box[][] board, int x, int y) {
        return new Box[0];
    }

}
