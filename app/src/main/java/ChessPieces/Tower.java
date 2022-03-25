package ChessPieces;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Tools;

import java.util.ArrayList;

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

    //Devuelve un ArrayList con las casillas posibles
    public ArrayList<Box> getAvailableMoves(Box[][] board, int x, int y) {
        ArrayList<Box> boxes = new ArrayList<>();

        int nextNumber;
        int nextNumberX;
        int nextNumberY;
        Box nextBox;

        int originalX = x;
        int originalY = y;

        int[] directions = new int[]{+10, +1, -1, -10};

        for (int i = 0; i < directions.length; i++) {
            while (true) {
                nextNumber = nextPosition(x, y, directions[i]);
                nextNumberX = getXYOfANumber(nextNumber)[0];
                nextNumberY = getXYOfANumber(nextNumber)[1];


                if (isInsideTheBoard(nextNumberX, nextNumberY)) {
                    nextBox = board[nextNumberX][nextNumberY];

                    if (haveAPiece(nextBox)) {
                        if (isOfTheSameColor(nextBox)) {
                            break;
                        } else {
                            boxes.add(board[nextNumberX][nextNumberY]);
                            x = nextNumberX;
                            y = nextNumberY;
                            break;
                        }
                    } else {
                        boxes.add(nextBox);
                        x = nextNumberX;
                        y = nextNumberY;
                    }
                } else {
                    break;
                }
            }
            x = originalX;
            y = originalY;
        }
        return boxes;
    }

    //Calcula la siguiente posición
    public int nextPosition(int x, int y, int direccion){
        int position = (x*10)+y;//junta la x con y en un solo número
        position += direccion;
        return position;
    }

    //convierte un número en dos números
    public int[] getXYOfANumber(int position){
        int x = position / 10;
        int y = position % 10;
        return new int[]{x, y};
    }
    //____________________________CONDICIONES_________________________
    //Comprueba si una casilla está dentro del tablero
    public boolean isInsideTheBoard(int x, int y){
        if (x<0 || x>7 || y<0 || y>7){
            return false;
        }else {
            return true;
        }
    }
    //Comprueba si una casilla puede ser ocupada
    public boolean haveAPiece(Box box){//refactoring
        return !box.isEmpty();
    }
    //compruba si una pieza es del mismo color que esta
    public boolean isOfTheSameColor(Box box){//Refactoring
        String unknownPiece = box.getPiece().getColor();
        return this.color.equals(unknownPiece);
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