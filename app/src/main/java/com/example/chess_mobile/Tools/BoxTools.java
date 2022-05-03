package com.example.chess_mobile.Tools;

import static com.example.chess_mobile.Tools.TranslationTools.getInt;

import com.example.chess_mobile.Box;

import com.example.chess_mobile.ChessPieces.Piece;

public class BoxTools {

    static char[] lett = new char[]{'a', 'b', 'c','d','e','f','g','h'};


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

    //Le doy un número y me devuelve una letra
    public int getX(String letter){
        String abc =  "ABCDEFG";
        if (abc.contains(letter)){
            return abc.indexOf(abc);
        }else {
            return -1;
        }
    }

    //Traduce de notación de ajedrez a posición de array
    public static int getPositionOfABox(String boxName){
        int x = getInt(boxName.charAt(0));
        int y = boxName.charAt(1) - '0';
        return (x *10)+(y-1);
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

}
