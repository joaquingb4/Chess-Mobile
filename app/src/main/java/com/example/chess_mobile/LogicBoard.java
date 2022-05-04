package com.example.chess_mobile;

import static com.example.chess_mobile.Tools.TranslationTools.getContraryColor;
import static com.example.chess_mobile.Tools.TranslationTools.translatePositionToName;
import  static  com.example.chess_mobile.Tools.CalculTools.getContraryBoolean;

import android.util.Log;

import java.util.ArrayList;

public class LogicBoard {
    //Attributes
    private Box[][] board = new Box[8][8];

    //Builder
    public LogicBoard(){
        buildBoxes();
    }
    //Getters And Setters
    public Box[][] getBoard() {
        return board;
    }

    //Devulve el nombre de la pieza, si es que tiene
    public String getBoxPieceName(String boxName){
        Box box = getBox(boxName);
        if (!getBox(boxName).isEmpty()){
            return box.getPiece().getName();
        }else{
            return "empty";
        }
    }

    //Devuelve una casilla determinada
    public Box getBox(String boxName){
        int x = Box.unknownBoxGetX(boxName);
        int y = Box.unknownBoxGetY(boxName);
        return board[x][y];
    }
    public Box getBox(int column, int row){
        return board[column][row];
    }

    //Devuelve las casillas con piezas de un jugador
    public ArrayList<Box> getPlayerBoxes(String color){
        ArrayList<Box> enemyBoxes = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].isEmpty()&&board[i][j].getPiece().getColor().equals(color)){
                    enemyBoxes.add(board[i][j]);
                }
            }
        }
        return enemyBoxes;
    }

    //Busca la casilla donde esta un rey determinado
    public Box getKing(String color){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Box box = board[i][j];
                if (!box.isEmpty() && box.getPiece().getName().equals("King") && box.getPiece().getColor().equals(color)){
                    return box;
                }
            }
        }
        return null;
    }

    //DEVUELVE SI EL REY DE EL COLOR CONTRARIO ESTÁ EN JAQUE
    public boolean kingISInCheck(String myColor){
        String enemyColor = getContraryColor(myColor);
        //Obtenemos la casilla con el rey
        Box kingBox = getKing(enemyColor);
        //Obtengo las casillas con piezas del contrario
        ArrayList<Box> contraryBoxes = getPlayerBoxes(myColor);
        //Las recorro y guardo las casillas que el otro puede ocupar en un ArrayList
        ArrayList<Box> occupybleBoxes = new ArrayList<>();
        for (int i = 0; i < contraryBoxes.size(); i++) {
            ArrayList <Box> contraryMoves =
                    contraryBoxes.get(i).getPiece().getAvailableMoves(
                            board,
                            contraryBoxes.get(i).getX(),
                            contraryBoxes.get(i).getY()
                    );
            //Comprobamos si una de las piezas del otro jugador amenaza a mi rey
            for (int j = 0; j < contraryMoves.size() ; j++) {
                if (!contraryMoves.get(j).isEmpty() && contraryMoves.contains(kingBox)){
                    Log.i("INFO","EL REY ["+ enemyColor+ "] ESTA EN JAQUE");
                    return true;
                }
            }
        }
        return false;
    }
    //Todas las casillas las vuelve no capturables
    public void setAllBoxesNotCapturable(){
        for (int x = 0; x < board.length; x++){
            for (int y = 0; y < board[x].length; y++) {
                board[x][y].setCapturable(false);
            }
        }
    }



    //OPERATIONS
    public void move(Box boxOrigin, Box boxDestiny){
        if (!boxOrigin.isEmpty()){
            boxDestiny.setPiece(boxOrigin.getPiece());//ERROR
            boxOrigin.setPiece(null);
        }else{
            Log.i("ERROR", "No hay un atacante");
        }
    }

    //Llena el tablero de casillas
    public void buildBoxes(){
        boolean boxColor = false;
        for (int x = 0; x < board.length ; x++) {
            if ((x%2)==0){
                boxColor = false;
            }else{
                boxColor=true;
            }
            for (int y = 0; y < board[x].length; y++) {
                boxColor = getContraryBoolean(boxColor);
                board[x][y] = new Box(translatePositionToName(x,y), boxColor);//CAMBIO

            }
        }
    }


}
