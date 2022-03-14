package ChessPieces;

import android.util.Log;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Driver;

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
    public int getPossiblesBoxesNumber(Box[][] board, int x, int y) {
        return 0;
    }

    @Override
    public Box[] getPossiblesBoxes(Box[][] board, int x, int y) {
        return new Box[0];
    }
    /*
    @Override
    public int getAvailableMovements(Box[][] board, int x, int y) {
        return 0;
    }


    public void getMovements(Driver b, int x, int y){
        Log.i("testBoard", b.getBoard()[x][y].getPiece().getName());

        Box[][] board =b.getBoard();

        if(x-1>=0 && y-2>=0){
            if(board[x-1][y-2].isEmpty()){
                board[x-1][y-2].setPiece("");
            }
        }

    }
    */

}
