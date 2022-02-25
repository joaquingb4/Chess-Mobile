package com.example.chess_mobile;

import ChessPieces.Bishop;
import ChessPieces.Horse;
import ChessPieces.King;
import ChessPieces.Pawn;
import ChessPieces.Queen;
import ChessPieces.Tower;

public class Driver {
    //Attributes
    Box[][] board = new Box[8][8];

    //Devulve el nombre de la pieza, si es que tiene
    public String getBoxPieceName(String tag){
        Box box = getBox(tag);
        if (!getBox(tag).isEmpty()){
            return box.getPiece().getName();
        }else{
            return "empty";        }
    }
    //Devuelve una casilla determinada
    public Box getBox(String a1){
        int[] boxCoordinates = Tools.tagToArrayNotation(a1);
        return board[boxCoordinates[0]][boxCoordinates[1]];//>ERROR
    }
    //Devuelve las posiciones de una determinada pieza
    public int[] getBoxPosition(String tag){
        int[] positions = new int[2];
        positions[0] = Tools.getInt(tag.charAt(0));
        positions[1] = Tools.getInt(tag.charAt(1));
        return positions;//La letra hay que traducirla a la nomenclatura del ajedrez
    }

    public int[] getBoxPosition(Box box){
        String tag = box.getName();//<--Hacer prueba
        int[] positions = new int[2];
        positions[0] = Tools.getInt(tag.charAt(0));
        positions[1] = Tools.getInt(tag.charAt(1));
        return positions;//La letra hay que traducirla a la notación del ajedrez
    }

    //Llena el tablero de casillas
    public void buildBoxes(){
        for (int i = 0; i < board.length ; i++) {
            for (int u = 0; u < board[i].length; u++) {
                board[i][u] = new Box(Tools.translate(new int[]{i, u}));
            }
        }
    }
    //Pone la piezas
    public void buildPieces(){
        //A---1||
        //Towers-----------
        getBox("a1").setPiece(new Tower("white"));
        getBox("h1").setPiece(new Tower("white"));
        getBox("a8").setPiece(new Tower("black"));
        getBox("h8").setPiece(new Tower("black"));
        //Horses-----------
        getBox("b1").setPiece(new Horse("white"));
        getBox("g1").setPiece(new Horse("white"));
        getBox("b8").setPiece(new Horse("black"));
        getBox("g8").setPiece(new Horse("black"));
        //Bishops-----------
        getBox("a3").setPiece(new Bishop("black"));
        getBox("a6").setPiece(new Bishop("black"));
        getBox("h3").setPiece(new Bishop("black"));
        getBox("h6").setPiece(new Bishop("black"));
        //Kings------------
        getBox("e1").setPiece(new King("white"));
        getBox("e7").setPiece(new King("black"));
        //Queens-----------
        getBox("d1").setPiece(new Queen("white"));
        getBox("d8").setPiece(new Queen("black"));
        //Pawns------------
        for (int i = 0; i < 8; i++) {
            getBox(Tools.getLetter(i)+"2").setPiece(new Pawn("white"));
        }
        for (int i = 0; i < 8; i++) {
            getBox(Tools.getLetter(i)+"7").setPiece(new Pawn("black"));
        }
    }
    //Lista las posiciones a las que la pieza en su casilla puede moverse

    //Refactorización
    //Devuelve la casilla que se le pide
    public Box getBox(int column, int row){
        return board[column][row];
    }
    public Box getBox(int[] position){
        return getBox(position[0], position[1]);
    }
    //Devuelve las dos casillas que tiene delante una pieza
    public Box[] canMoveTo(Box box){
        int[] boxNumbers = Tools.tagToArrayNotation(box.getName());

        int column = boxNumbers[0];
        int row = boxNumbers[1];

        Box[] boxes = new Box[2];
        int tmp = 0;
        for (int i = row+1; i <= row+2; i++) {
            boxes[tmp] = getBox(column,i);
            tmp++;
        }
        return boxes;
    }
    // ¿Puede moverse una pieza determinada?
}
