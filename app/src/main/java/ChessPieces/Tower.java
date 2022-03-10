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
        super();
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


    public int getPossiblesBoxesNumber(Box[][] board, int x, int y, int direccion) {
        int cantidad = 0;
        while (true) {
            int posicionArray = Integer.parseInt(x + "" + y);
            posicionArray += direccion;
            String conversion = Integer.toString(posicionArray);
            x = conversion.charAt(0);
            y = conversion.charAt(1);
            if (board[x][y].isEmpty()) {//Si está vacío
                return 1 + getPossibleBoxesNumber(board, x, y, direccion);
            } else {//sino está vacío
                return 1;
            }
        }
        /*
            - 1, - 10, - -1, - -10
            24 25 26 27 28 29 30 31     30, 31, 32, 33, 34, 35, 36, 37
            16 17 18 19 20 21 22 23     20, 21, 22, 23, 24, 25, 26, 27
            08 09 10 11 12 13 14 15     10, 11, 12, 13, 14, 15, 16, 17
            00 01 02 03 04 05 06 07     00, 01, 02, 03, 04, 05, 06, 07
            xy
         */
    }
    @Override
    public Box[] getPossibleBoxes(Box[][] board, int x, int y, int direccion) {
        int lenght = getPossiblesBoxesNumber(board, x, y, direccion);
        Box[] boxes = new Box[lenght];
        while (true) {
            int posicionArray = Integer.parseInt(x + "" + y);
            posicionArray += direccion;
            String conversion = Integer.toString(posicionArray);
            x = conversion.charAt(0);
            y = conversion.charAt(1);
            for (int i = 0; i < lenght; i++) {
                //Estoy aquí
            }

            if (board[x][y].isEmpty()) {//Si está vacío
                return 1 + getPossibleBoxesNumber(board, x, y, direccion);
            } else {//sino está vacío
                return 1;
            }
        }
    }
}

