package com.example.chess_mobile;

import android.util.Log;
import android.widget.Switch;

import java.util.ArrayList;

//Herrarmientas
public class Tools {
    static int a = 0;
    static int b = 1;
    static int c = 2;
    static int d = 3;
    static int e = 4;
    static int f = 5;
    static int g = 6;
    static int h = 7;
    static char[] letters = new char[]{'a', 'b', 'c','d','e','f','g','h'};

    //Le doy una letra y me devuelve un número
    public static int getInt(char letter){
        for (int i = 0; i < letters.length; i++) {
            if (letters[i]==letter){
                return i;
            }
        }
        return 0;
    }
    //Le doy un número y me devuelve una letra
    public static char getLetter(int x){
        return letters[x];
    }
    //Traduce de la notación del ajedrez a una posición numérica
    public static int[] tagToChessNotation(String A1){
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
    public static int[] translate(String tag){
        int column = getInt(tag.charAt(0));
        int row = getInt(tag.charAt(1));
        return new int[]{column, row};
    }
    //Traduce de una posición a notación de ajedrez
    public static String translate(int[] axes){
        char column = getLetter(axes[0]);
        char row = Character.forDigit(axes[1],10);
        return new String(column+""+row);
    }





}
