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
    //Devuelve el número de posiciones pobibles
    public int getPossiblesBoxesNumber(Box[][] board, int x, int y) {
        int index = 0;
        while (true) {
            int posicionArray = Integer.parseInt(x + "" + y);
            posicionArray += Tools.direcciones[index+=2];//Probar
            String conversion = Integer.toString(posicionArray);
            x = conversion.charAt(0);
            y = conversion.charAt(1);
            if (board[x][y].isEmpty()) {//Si está vacío
                return 1 + getPossiblesBoxesNumber(board, x, y, Tools.);
            } else {//sino está vacío
                return 1;
            }
        }
    }
    //Devuelve un array con las casillas posibles
    @Override
    public Box[] getPossibleBoxes(Box[][] board, int x, int y) {
        int index = 0;
        int lenght = getPossiblesBoxesNumber(board, x, y);
        Box[] boxes = new Box[lenght];

        for (int i = 0; i < lenght; i++) {
            int posicionArray = Integer.parseInt(x + "" + y);
            posicionArray += Tools.direcciones[index+=2];//Probar
            String conversion = Integer.toString(posicionArray);
            x = conversion.charAt(0);
            y = conversion.charAt(1);
            boxes[i] = board[x][y];
        }
        return boxes;
    }
}
 /*                             +9  +10  +11
                                -1    2   +1     +10 -1 -10 +1
                                -9  -10  -11
            º +1, -10, -1, +10
            º24 25 26 27 28 29 30 31     30, 31, 32, 33, 34, 35, 36, 37
            º16 17 18 19 20 21 22 23     20, 21, 22, 23, 24, 25, 26, 27
            º08 09 10 11 12 13 14 15     10, 11, 12, 13, 14, 15, 16, 17
            º00 01 02 03 04 05 06 07     00, 01, 02, 03, 04, 05, 06, 07
          y ºººªºººººººººººººººººººº
             x
         */