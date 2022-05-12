package com.example.chess_mobile;

import static com.example.chess_mobile.Tools.TranslationTools.getInt;

import android.util.Log;

import com.example.chess_mobile.ChessPieces.Piece;

public class Box  {
    //_______________________      [ATTRIBUTES]      _____________________________
    final private String name;
    private Piece piece = null;
    private boolean capturable = false;

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    private boolean color;

    public Box(String name, boolean color){
        this.name = name;
        this.color = color;
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

    public boolean getCapturable() {
        return capturable;
    }

    public void setCapturable(boolean inDanger){
        this.capturable = inDanger;
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

    //_______________________      [ATTRIBUTES]      _____________________________

    //Devuelve si la casilla está vacía
    public boolean isEmpty(){
        return this.piece == null;
    }

    //DEVUELVE UNA COORDENADA DENTRO DEL ARRAY ENCONTRADA POR EL NOMBRE DE
    //LA CASILLA
    public static int getPositionOfABox(String boxName){
        int x = getInt(boxName.charAt(0));
        int y = boxName.charAt(1) - '0';
        return (x *10)+(y-1);
    }
}
