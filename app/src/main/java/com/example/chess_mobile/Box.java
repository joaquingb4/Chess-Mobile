package com.example.chess_mobile;

import java.util.function.ToLongBiFunction;

import ChessPieces.Piece;

public class Box {
    static String abc =  "ABCDEFGH";
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
        return abc.indexOf(name.substring(0, 1));
    }
    //Devuelve la Y
    public int getY() {
        return name.charAt(1) - '0';
    }//____________________________________
    //Methods

    //Devuelve si la casilla está vacía
    public boolean isEmpty(){
        return this.piece == null;
    }
    //Recibe una posición y devuelve su equivalente en nombre de casilla
    public static String translatePositionToName(int x ,int y){
        if (Tools.isInsideTheBoard(x, y)){
            char firstCh = abc.charAt(x);
            char secondCh = abc.charAt(y);
            return firstCh+""+secondCh;
        }else{
            return null;
        }
    }
    //ESTOY HACIENDO REFACTOR TOOLS LO PASO UN POCO AQUÍ

}
