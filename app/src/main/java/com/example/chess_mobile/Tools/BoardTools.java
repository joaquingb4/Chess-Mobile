package com.example.chess_mobile.Tools;

import static com.example.chess_mobile.Tools.BoxTools.tagGetX;
import static com.example.chess_mobile.Tools.BoxTools.tagGetY;
import static com.example.chess_mobile.Tools.CalculTools.getFirstDigitOfANumber;
import static com.example.chess_mobile.Tools.CalculTools.getSecondDigitOfANumber;

import android.util.Log;
import android.widget.ImageView;

import com.example.chess_mobile.Box;

public class BoardTools {
    public static int[] direcciones={+11 ,+10, +9, +1, 0, -1, -9, -10, -11};
    //                                0    1    2   3  4   5   6   7    8
    //Empareja un tag de la parte lógica con la gráfica
    public static ImageView getImageView(Box box, ImageView[][] visualBoard){
        String tag = box.getName();
        int x = tagGetX(tag);
        int y = tagGetY(tag);
        Log.i("puente", x+""+y);
        return visualBoard[x][y];
    }

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
    public static boolean isInsideTheBoard(int x, int y){
        if (x<0 || x>7 || y<0 || y>7){
            return false;
        }else {
            return true;
        }
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
