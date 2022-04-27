package com.example.chess_mobile;

import static com.example.chess_mobile.Box.getInt;

import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

import ChessPieces.Piece;

//Herrarmientas
public class Tools {
    public static int[] direcciones={+11 ,+10, +9, +1, 0, -1, -9, -10, -11};
    //                                0    1    2   3  4   5   6   7    8
    static char[] lett = new char[]{'a', 'b', 'c','d','e','f','g','h'};
    static ArrayList<Character> letters =  new ArrayList(){{
    }};


    //De un tag extraigo la x
    public static int tagGetX(String tag){
        char x = tag.charAt(0);
        int result = getInt(x);
        return result;
    }
    //De un tag extraigo la y
    public static int tagGetY(String tag){
        char y = tag.charAt(1);
        int result = y - '0';
        result--;
        return result;
    }

    //Le doy una letra y me devuelve un número

    //Le doy un número y me devuelve una letra
    public int getX(String letter){
        String abc =  "ABCDEFG";
        if (abc.contains(letter)){
            return abc.indexOf(abc);
        }else {
            return -1;
        }
    }
    //Traduce de la notación del ajedrez a coordenadas en el array del tablero
    public static int[] tagToArrayNotation(String A1){
        int[] position = new int[2];
        position[0] = getInt(A1.charAt(0));
        position[1] = (Character.getNumericValue(A1.charAt(1)))-1;
        return position;
    }
    //De notatación de ajedrez a posición en el array de casillas

    //
    public static int movementRulesPiece(Piece piece, Box box){
        switch(piece.getName()) {
            case "Pawn":
                if (box.getName().charAt(1)=='1'||box.getName().charAt(1)=='6'){
                    return 2;
                }else{
                    return 1;
                }
        }
        return 0;
    }
    //Traduce de notación de ajedrez a posición de array
    public static int getPositionOfABox(String boxName){
        int x = getInt(boxName.charAt(0));
        int y = boxName.charAt(1) - '0';
        return (x *10)+(y-1);
    }

    //empareja un tag de la parte lógica con la gráfica
    public static ImageView getImageView(Box box, ImageView[][] visualBoard){
        String tag = box.getName();
        int x = tagGetX(tag);
        int y = tagGetY(tag);
        Log.i("puente", x+""+y);
        return visualBoard[x][y];
    }
    //________________________
    //Calcula la siguiente posición
    public static int nextPosition(int x, int y, int direccion){
        int position = (x*10)+y;//junta la x con y en un solo número
        position += direccion;
        return position;
    }
    public static int thisPositionInNumber(int x, int y){
        int position = (x*10)+y;//junta la x con y en un solo número
        return position;
    }

    //convierte un número en dos números
    public static int[] getXYOfANumber(int position){
        int x = position / 10;
        int y = position % 10;
        return new int[]{x, y};
    }
    public  static int getFirstDigitOfANumber(int position){
        return position / 10;
    }
    public  static int getSecondDigitOfANumber(int position){
        return position % 10;
    }
    //____________________________CONDICIONES_________________________
    //Comprueba si una casilla está dentro del tablero
    public static boolean isInsideTheBoard(int x, int y){
        if (x<0 || x>7 || y<0 || y>7){
            return false;
        }else {
            return true;
        }
    }
    //Comprueba si una casilla puede ser ocupada
    public static boolean haveAPiece(Box box){//refactoring
        return !box.isEmpty();
    }
    //compruba si una pieza es del mismo color que esta
    public static boolean isOfTheSameColor(Piece myPiece, Piece unknownPiece){//Refactoring
        String unknownPieceColor = unknownPiece.getColor();
        String myColor = myPiece.getColor();
        return myColor.equals(unknownPieceColor);
    }

    //CALCULOS_______________________
    public static Box getUpBox(Box[][] board, int x, int y){
        return calculTheNextBox(board, x, y, +1);
    }
    public static Box getDownBox(Box[][] board, int x, int y){
        return calculTheNextBox(board, x, y, -1);
    }
    public static Box getRightBox(Box[][] board, int x, int y){
        return calculTheNextBox(board, x, y, +10);
    }
    public static Box getLeftBox(Box[][] board, int x, int y){
        return calculTheNextBox(board, x, y, -10);
    }
    //EN base a una dirección calcula la siguiente casilla
    public static Box calculTheNextBox (Box[][] board, int x, int y, int direcction){
        if (isInsideTheBoard(x, y)){
            int position = thisPositionInNumber(x, y);
            position += direcction;
            int  nextX = getFirstDigitOfANumber(position);
            int nextY = getSecondDigitOfANumber(position);
            if (isInsideTheBoard(nextX, nextY)){
                Box box = board[nextX][nextY];
                return  box;
            }else {
                return null;
            }
        }else{
            return null;
        }
    }


}
//
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
             03, 13, 23, 33, 43, 53
             02, 12, 22, 32, 42, 52
             01, 11, 21, 31, 41, 51
             00, 10, 20, 30, 40, 50

             4{[0][1][2][3][4][5][6][7]}
             3{[0][1][2][3][4][5][6][7]}
             2{[0][1][2][3][4][5][6][7]}
             1{[0][1][2][3][4][5][6][7]}
           X 0{[0][1][2][3][4][5][6][7]}
                Y

  */