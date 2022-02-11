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
                Log.i("hola",""+i);
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
    public static int[] withNotation(String A1){
        int[] position = new int[2];
        position[0] = getInt(A1.charAt(0));
        position[1] = (Character.getNumericValue(A1.charAt(1)))-1;
        return position;
    }
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


}
