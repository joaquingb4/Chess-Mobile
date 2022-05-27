package com.example.chess_mobile.Tools;

import static com.example.chess_mobile.Tools.BoardTools.isInsideTheBoard;

public class TranslationTools {
    static char[] abc = new char[]{
            'a','b','c','d','e','f','g','h'
    };
    public static String getContraryColor(String color){
        if (color.equals("white")){
            return "black";
        }else{
            return "white";
        }
    }

    //Recibe una posición y devuelve su equivalente en nombre de casilla
    public static String translatePositionToName(int x ,int y){
        if (isInsideTheBoard(x, y)){
            //char firstCh = abc.charAt(x);
            //char secondCh = y+1;
            return abc[x]+""+(y+1);
        }else{
            return null;
        }
    }
    //LE DOY UNA LETRA Y ME DEVUELVE UN NÚMERO
    public static int getInt(char letter){
        for (int i = 0; i < abc.length; i++) {
            if (abc[i]==letter){
                return i;
            }
        }
        return -1;
    }
    public static String translateBooleanToColor(boolean color){
        if (color)
            return "white";
        else
            return "black";
    }
    public static boolean translateColorToBoolean(String color){
        if (color.equals("white"))
            return true;
        else
            return false;
    }
    public static char translateIntToLetter(int i){
        return abc[i];
    }
}
