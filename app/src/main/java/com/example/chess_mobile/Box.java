package com.example.chess_mobile;

import java.util.ArrayList;
import java.util.function.ToLongBiFunction;

import ChessPieces.Piece;

public class Box {
    //static String abc =  "ABCDEFGH";
   static char[] abc = new char[]{
           'a','b','c','d','e','f','g','h'
   };
   //Attibutes
    final private String name;
    private Piece piece = null;

    public Box(String name){
        this.name = name;
    }
    public Box(String name, Piece piece){
        this.name = name;
        this.piece = piece;
    }
    //________________________________
    //Getters y setters
    public String getName() {
        return name;
    }
    public Piece getPiece() {
        return this.piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    //______________
    //Devuelve la posición dentro del array
    public int getPosition(){
        int x = getX();
        int y = getY();
        return (x *10)+(y-1);
    }
    //Devuelve la X
    public int getX(){
        return getInt(name.charAt(0));
    }
    //Devuelve la Y
    public int getY() {
        return (name.charAt(1) - '0')-1;

    }
    public static int unknownBoxGetX(String boxName){
        return getInt(boxName.charAt(0));
    }
    public static int unknownBoxGetY(String boxName){
        return (boxName.charAt(1) - '0')-1;
    }

    //____________________________________

    //Methods

    //Devuelve si la casilla está vacía
    public boolean isEmpty(){
        return this.piece == null;
    }
    //Recibe una posición y devuelve su equivalente en nombre de casilla
    public static String translatePositionToName(int x ,int y){
        if (Tools.isInsideTheBoard(x, y)){
            //char firstCh = abc.charAt(x);
            //char secondCh = y+1;
            return abc[x]+""+(y+1);
        }else{
            return null;
        }
    }
    //DEVUELVE UNA COORDENADA DENTRO DEL ARRAY ENCONTRADA POR EL NOMBRE DE
    //LA CASILLA
    public static int getPositionOfABox(String boxName){
        int x = getInt(boxName.charAt(0));
        int y = boxName.charAt(1) - '0';
        return (x *10)+(y-1);
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
    //A LA INVERSA
    public static char getLetter(int index){
        return abc[index];
    }
}
