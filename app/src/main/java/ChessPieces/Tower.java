package ChessPieces;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Driver;

public class Tower extends Piece {
    //Attributes
    private final String name = "Tower";
    private String color;

    //Methods
    public Tower(String color) {
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


    public void getMovements(Box[][] board, int x, int y) {

        //Movimentos a la derecha
        boolean emptyBox = true;
        int cord1 = x;
        while (emptyBox == true && (cord1 + 1) < 8) {
            if (board[cord1 + 1][y].isEmpty()) {
                board[cord1 + 1][y].setPiece(new Tower("white"));
                cord1 = cord1 + 1;
            } else {
                emptyBox = false;
            }
        }

        //Movimientos a la izquierda
        emptyBox = true;
        cord1 = x;
        while (emptyBox == true && (cord1 -1) >= 0) {
            if (board[cord1 - 1][y].isEmpty()) {
                board[cord1 - 1][y].setPiece(new Tower("white"));
                cord1 = cord1 - 1;
            } else {
                emptyBox = false;
            }
        }

        //Movimientos arriba
        emptyBox = true;
        int cord2 = y;
        while (emptyBox == true && (cord2 + 1) < 8) {
            if (board[x][cord2 + 1].isEmpty()) {
                board[x][cord2 + 1].setPiece(new Tower("white"));
                cord2 = cord2 + 1;
            } else {
                emptyBox = false;
            }
        }

        //Movimientos abajo
        emptyBox = true;
        cord2 = y;
        while (emptyBox == true && (cord2 - 1)>=0) {
            if (board[x][cord2 - 1].isEmpty()) {
                board[x][cord2 - 1].setPiece(new Tower("white"));
                cord2 = cord2 - 1;
            } else {
                emptyBox = false;
            }
        }
    }





}