package com.example.chess_mobile;

public class Box {
    //Attibutes
    private String name;
    private Piece piece;
    //Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    //Methods
    public Box(String name){
        this.name = name;
        this.piece = null;
    }
    public Box(String name, Piece piece){
        this.name = name;
        this.piece = piece;
    }

    //Methods
    public boolean haveAPiece(){
        if (this.piece != null){
            return true;
        }else{
            return false;
        }
    }


}
