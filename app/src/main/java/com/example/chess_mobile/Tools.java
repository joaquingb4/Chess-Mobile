package com.example.chess_mobile;

import ChessPieces.Piece;

//Herrarmientas
public class Tools {
    public static int[] direcciones={+11 ,+10, +9, +1, 0, -1, -9, -10, -11};
    //                          0    1   2   3  4   5   6   7    8
    static char[] letters = new char[]{'a', 'b', 'c','d','e','f','g','h'};
    //De un tag extraigo la x
    public static int tagGetX(String tag){
        char x = tag.charAt(0);
        int result = x - '0';
        return result;
    }
    //De un tag extraigo la y
    public static int tagGetY(String tag){
        char y = tag.charAt(1);
        int result = y - '0';
        return result;
    }

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
    public static int[] translate(String tag){
        int column = getInt(tag.charAt(0));
        int row = getInt(tag.charAt(1));
        return new int[]{column, row};
    }
    //Traduce de una posición a notación de ajedrez
    public static String translate(int[] coordinates){
        return new String(getLetter(coordinates[0])+""+(coordinates[1]+1));
    }
}