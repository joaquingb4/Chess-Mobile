package ChessPieces;

import android.util.Log;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Driver;

import java.util.Optional;

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

    @Override
    //Probando
    public int[][] getAvailableMovements(Box[][] board, int x, int y, Optional<Integer>size) {
        if ( > 0) {
        } else {//Estoy aquí
            size=1;
        }
        int availablePositions = 0;

        //Movimentos a la derecha
        boolean emptyBox = true;
        int cord1 = x;
        while (emptyBox && (cord1 + 1) < 8) {
            if (board[cord1 + 1][y].isEmpty()) {
            //    board[cord1 + 1][y].setPiece(new Tower("white"));
                cord1 = cord1 + 1;
                availablePositions ++;

                //ESTOY AQUÍ : hacer que las casillas tengan opción de ser disponibles
            } else {
                emptyBox = false;
            }
        }

        //Movimientos a la izquierda
        emptyBox = true;
        cord1 = x;
        while (emptyBox && (cord1 - 1) >= 0) {
            if (board[cord1 - 1][y].isEmpty()) {
            //    board[cord1 - 1][y].setPiece(new Tower("white"));
                cord1 = cord1 - 1;
                availablePositions ++;

            } else {
                emptyBox = false;
            }
        }

        //Movimientos arriba
        emptyBox = true;
        int cord2 = y;
        while (emptyBox && (cord2 + 1) < 8) {
            if (board[x][cord2 + 1].isEmpty()) {
             //   board[x][cord2 + 1].setPiece(new Tower("white"));
                cord2 = cord2 + 1;
                availablePositions ++;

            } else {
                emptyBox = false;
            }
        }

        //Movimientos abajo
        emptyBox = true;
        cord2 = y;
        while (emptyBox && (cord2 - 1)>=0) {
            if (board[x][cord2 - 1].isEmpty()) {
           //     board[x][cord2 - 1].setPiece(new Tower("white"));
                cord2 = cord2 - 1;
                availablePositions ++;

            } else {
                emptyBox = false;
            }
        }
        Log.i("torre", ""+ availablePositions+ "Estoy aquí");
        if (availablePositions==0){
            return null;
        }else{
            return getAvailableMovements(board, x, y, availablePositions );
        }
        return availablePositions;
    }
}