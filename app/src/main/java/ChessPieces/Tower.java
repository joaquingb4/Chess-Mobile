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
        int totalNumber = 0;
        totalNumber += metodo(board, x, y, +10);
        totalNumber += metodo(board, x, y, +1);
        totalNumber += metodo(board, x, y, -1);
        totalNumber += metodo(board, x, y, -10);
        return totalNumber;
    }
    //Método
    public int metodo(Box[][] board, int x, int y, int direccion){
        int posicionArray = Integer.parseInt(x + "" + y);  //Int
        posicionArray += direccion; //Nueva posición        //Int
        char[] conversion = new char[2];
        conversion = Integer.toString(posicionArray).toCharArray();
        for (int i = 0; i < conversion.length; i++) {
            
        }
        x = conversion[0] - '0';
        y = conversion[1] - '0';

        //Próxima posición
        if (!isInsideTheBoard(x, y)){   //Está dentro
            return 0;
        } else if (isTheEnd(x, y)){ //Es la última casilla      Añadir filtro del color
            return 1;
        }else{      //
            return 1 + metodo(board, x, y, direccion);
        }
    }
    //Devuelve un array con las casillas posibles
    @Override
    public Box[] getPossiblesBoxes(Box[][] board,int x, int y) {
        int index = 0;
        int lenght = getPossiblesBoxesNumber(board, x, y);
        Box[] boxes = new Box[lenght];

        for (int i = 0; i < lenght; i++) {
            int posicionArray = Integer.parseInt(x + "" + y);
            posicionArray += Tools.direcciones[index+=2];//Probar
            String conversion = Integer.toString(posicionArray);
            x = conversion.charAt(0) - '0';
            y = conversion.charAt(1) - '0';
            boxes[i] = board[x][y];
        }
        return boxes;
    }

    public boolean insideBoard(int x, int y){
        if (x<1||x>6||y<1||y>6){
            return false;
        }else{
            return true;
        }
    }
    public boolean isInsideTheBoard(int x, int y){
        if (x<0 || x>7 || y<0 || y>7){
            return false;
        }else {
            return true;
        }
    }
    public boolean isTheEnd(int x, int y){
        if (x == 0 || y == 7){
            return true;
        }else{
            return false;
        }
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

             4{[0][1][2][3][4][5][6][7]}
             3{[0][1][2][3][4][5][6][7]}
             2{[0][1][2][3][4][5][6][7]}
             1{[0][1][2][3][4][5][6][7]}
           X 0{[0][1][2][3][4][5][6][7]}
                Y

             if ()

         */