package com.example.chess_mobile;

import ChessPieces.Piece;

public class Box {
    //Attibutes
    private String name;
    private Piece piece;

    public Box(String name){
        this.name = name;
        this.piece = null;
    }
    public Box(String name, Piece piece){
        this.name = name;
        this.piece = piece;
    }

    //Getters y setters
    public String getName() {
        return name;
    }
    public char getFirstNameCharacter() {
        return name.charAt(0);
    }
    public char getSecondNameCharacter() {
        return name.charAt(1);
    }


    public void setName(String name) {
        this.name = name;
    }
    public Piece getPiece() {
        if (this.piece == null){
            return null;
        }else{
            return piece;
        }
    }



    public void setPiece(Piece piece) {
        this.piece = piece;
    }


    //Methods
    public boolean isEmpty(){
        return this.piece==null;
    }


}
