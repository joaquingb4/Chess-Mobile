package com.example.chess_mobile;

import android.util.Log;

public class Driver {
    //Attributes
    Box[][] board = new Box[8][8];

    //Devulve el nombre de la pieza, si es que tiene
    public String getBoxPieceName(String tag){
        Box box = getBox(tag);
        int column = box.getFirstNameCharacter();//Se le quita uno por la notación del ajedrez
        int row = Character.getNumericValue(box.getSecondNameCharacter());

        if (!getBox(tag).isEmpty()){
            return board[column][row].getPiece().getName();
        }else{
            return "empty";        }
    }
    //Devuelve una casilla determinada
    public Box getBox(String a1){
        int[] box = Tools.tagToChessNotation(a1);
        return board[box[0]][box[1]];
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
        getBox("a1").setPiece(new Tower());
        getBox("h1").setPiece(new Tower());
        getBox("a8").setPiece(new Tower());
        getBox("h8").setPiece(new Tower());
        //Horses-----------
        //Estoy aquí haciendo refactorin
        board[b][0].setPiece(new Horse());
        board[g][0].setPiece(new Horse());
        board[b][7].setPiece(new Horse());
        board[g][7].setPiece(new Horse());
        //Kings------------
        board[e][0].setPiece(new King());
        board[e][7].setPiece(new King());
        //Queens-----------
        board[d][0].setPiece(new Queen());
        board[d][7].setPiece(new Queen());
        //Pawns------------
        for (int i = 0; i < 8; i++) {
            board[i][1].setPiece(new Pawn());
        }
        for (int i = 0; i < 8; i++) {
            board[i][6].setPiece(new Pawn());
        }
    }
    //Lista las posiciones a las que la pieza en su casilla puede moverse

    //Refactorización
    //Devuelve la casilla que se le pide
    public Box getBox(int column, int row){
        return board[column][row];
    }
    public Box getBox(int[] position){ return board[position[0]][position[1]]; }


    //Devuelve las dos casillas que tiene delante una pieza
    public Box[] canMoveTo(Box box){
        int[] boxNumbers = Tools.tagToChessNotation(box.getName());

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
