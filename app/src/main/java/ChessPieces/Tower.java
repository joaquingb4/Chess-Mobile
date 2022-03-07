package ChessPieces;

import android.util.Log;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Driver;
import com.example.chess_mobile.Tools;

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
    public int getAvailableMovements(Box[][] board, int x, int y) {
        int numberOfAvailablePositions = 0;

        //Movimentos a la derecha
        boolean emptyBox = true;
        int cord1 = x;
        while (emptyBox && (cord1 + 1) < 8) {
            if (board[cord1 + 1][y].isEmpty()) {
            //    board[cord1 + 1][y].setPiece(new Tower("white"));
                cord1 = cord1 + 1;
                numberOfAvailablePositions ++;

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
                numberOfAvailablePositions ++;

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
                numberOfAvailablePositions ++;

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
                numberOfAvailablePositions ++;

            } else {
                emptyBox = false;
            }
        }

        Log.i("torre", ""+ numberOfAvailablePositions+ "Estoy aquí");
        return numberOfAvailablePositions;
    }

    //Recibe un número que es el tamaño del array y devuelve una array de casillas
    public Box[] getBoxesOccupybleCells(int ArrayLenght, Box[][] board, int x, int y){
        Box[] boxes = new Box[ArrayLenght];
        int index = 0;
        //Movimentos a la derecha
        boolean emptyBox = true;
        int cord1 = x;
        while (emptyBox && (cord1 + 1) < 8) {
            if (board[cord1 + 1][y].isEmpty()) {
                boxes[index] = board[cord1 + 1][y];//Almacena la casilla
                index++;
                cord1 = cord1 + 1;
            } else {
                emptyBox = false;
            }
        }

        //Movimientos a la izquierda
        emptyBox = true;
        cord1 = x;
        while (emptyBox && (cord1 - 1) >= 0) {
            if (board[cord1 - 1][y].isEmpty()) {
                boxes[index] = board[cord1 + 1][y];//Almacena la casilla
                index++;                cord1 = cord1 - 1;
            } else {
                emptyBox = false;
            }
        }

        //Movimientos arriba
        emptyBox = true;
        int cord2 = y;
        while (emptyBox && (cord2 + 1) < 8) {
            if (board[x][cord2 + 1].isEmpty()) {
                boxes[index] = board[cord1 + 1][y];//Almacena la casilla
                index++;                cord2 = cord2 + 1;
            } else {
                emptyBox = false;
            }
        }

        //Movimientos abajo
        emptyBox = true;
        cord2 = y;
        while (emptyBox && (cord2 - 1)>=0) {
            if (board[x][cord2 - 1].isEmpty()) {
                boxes[index] = board[cord1 + 1][y];//Almacena la casilla
                index++;                cord2 = cord2 - 1;
            } else {
                emptyBox = false;
            }
        }
        Log.i("pruebita", ""+boxes.length);
        return boxes;
    }
}