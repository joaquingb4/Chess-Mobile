package com.example.chess_mobile;

import android.util.Log;

import java.util.ArrayList;

import ChessPieces.Bishop;
import ChessPieces.Horse;
import ChessPieces.King;
import ChessPieces.Pawn;
import ChessPieces.Piece;
import ChessPieces.Queen;
import ChessPieces.Tower;

public class Driver {
    //Attributes
    Box[][] board = new Box[8][8];
    Box boxCache = null;
    ArrayList<Box> cache = new ArrayList<>();
    ArrayList<Piece> whiteUserCaptures = new ArrayList<>();
    ArrayList<Piece> blackUserCaptures = new ArrayList<>();
    boolean turn = true;
    String kingInCheck = null;
    public void setBoxCache(Box box){
        this.boxCache = box;
    }

    public Box[][] getBoard(){
        return board;
    }

    //DEVUELVE TODAS LAS CASILLAS CON PIEZAS DE UN COLOR ESPECÍFICO
    public ArrayList<Box> getAllColorPieces(String color){
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
    //DEVUELVE SI EL REY DE EL COLOR CONTRARIO ESTÁ EN JAQUE
    public boolean kingISInCheck(String myColor){
         String enemyColor = Tools.getEnemyColor(myColor);
         //Obtenemos la casilla con el rey
        Box kingBox = getKing(enemyColor);
        //Obtengo las casillas con piezas del contrario
        ArrayList<Box> contraryBoxes = getAllColorPieces(myColor);
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
    /////    Log.i("prueba","boxName"+boxName +" boxPosition:"+boxPosition);
     ////   Log.i("prueba","X:"+x+" Y:"+y);
        return board[x][y];
    }

    public int[] getBoxPosition(Box box){
        String tag = box.getName();//<--Hacer prueba
        int[] positions = new int[2];
        positions[0] = Tools.tagGetX(tag);
        positions[1] = Tools.tagGetY(tag);
        return positions;//La letra hay que traducirla a la notación del ajedrez
    }

    //Llena el tablero de casillas
    public void buildBoxes(){
        for (int i = 0; i < board.length ; i++) {
            for (int u = 0; u < board[i].length; u++) {
                board[i][u] = new Box(Box.translatePositionToName(i,u));//CAMBIO
            }
        }
    }
    //Pone la piezas
    public void buildPieces(){
        //A---1||  columna - fila
        //Towers-----------
        getBox("a1").setPiece(new Tower("white"));
        //getBox("h1").setPiece(new Tower("white"));
        //getBox("a8").setPiece(new Tower("black"));
        //getBox("h8").setPiece(new Tower("black"));
      //  getBox("d4").setPiece(new Tower("white"));
        //Horses-----------
        /*
        getBox("b1").setPiece(new Horse("white"));
        getBox("g1").setPiece(new Horse("white"));
        getBox("b8").setPiece(new Horse("black"));
        getBox("g8").setPiece(new Horse("black"));
       // getBox("c4").setPiece(new Horse("black"));
        //getBox("c3").setPiece(new Horse("black"));

        //Bishops-----------
        getBox("c1").setPiece(new Bishop("white"));
        getBox("f1").setPiece(new Bishop("white"));
        getBox("c8").setPiece(new Bishop("black"));
        getBox("f8").setPiece(new Bishop("black"));
        //getBox("e4").setPiece(new Bishop("white"));

        //Queens-----------
        getBox("d1").setPiece(new Queen("white"));
        getBox("d8").setPiece(new Queen("black"));
       // getBox("f4").setPiece(new Queen("black"));

        //Kings------------
        getBox("e1").setPiece(new King("white"));
        getBox("e8").setPiece(new King("black"));

         */
        //Pawns------------
        //for (int i = 0; i < 8; i++) {
         //   getBox(Box.getLetter(i)+"2").setPiece(new Pawn("white"));
        //}
        //for (int i = 0; i < 8; i++) {
        //    getBox(Box.getLetter(i)+"7").setPiece(new Pawn("black"));
        //}
        getBox("h8").setPiece(new King("black"));
        getBox("g8").setPiece(new Pawn("black"));
        getBox("g7").setPiece(new Pawn("black"));
        getBox("a1").setPiece(new Queen("white"));
    }
    //Devuelve la casilla que se le pide
    public Box getBox(int column, int row){
        return board[column][row];
    }
    public Box getBox(int[] position){
        return getBox(position[0], position[1]);
    }

    //Mueve una pieza a una posición
    public void move(Box boxOrigin, Box boxDestiny){
        if (!boxDestiny.isEmpty()) {//NO SIMPLIFICAR
            if (boxOrigin.getPiece().getColor().equals("white")) {  //ESTOY AQUÍ
                blackUserCaptures.add(boxDestiny.getPiece());
            } else {
                whiteUserCaptures.add(boxDestiny.getPiece());
            }
            boxDestiny.setPiece(boxOrigin.getPiece());//ERROR
            boxOrigin.setPiece(null);
        }else{
            boxDestiny.setPiece(boxOrigin.getPiece());//ERROR
            boxOrigin.setPiece(null);
        }
        boxCache = null;
        cache.clear();
        //EL rey opuesto esta en jaque
        kingISInCheck(boxDestiny.getPiece().getColor());
    }

    //Cambia el estado del turno cuando se lo llama
    public void changeTurn (){
        if (turn){
            turn = false;
            return;
        }
        turn = true;
    }
    //Comprueba si la pieza tocada es la correspondiente a la del turno

    public boolean checkClickTurn(Piece piece){
        if (turn){
            Log.i("INFO", "Turno de las [blancas]");
            return piece.getColor().equals("white");
        }
        Log.i("INFO", "Turno de las [negras]");
        return piece.getColor().equals("black");
    }
    //Devuelve si haciendo un movimiento se quita el jaque a un rey determinado
    public boolean canThisMovementSaveTheKing(Box[][] board, Box boxToMove, Box boxToOccupy ){
        Piece boxToMovePiece = boxToMove.getPiece() ;
        Piece boxtoOccupyPiece =  boxToOccupy.getPiece();
      //  move(boxToMove, boxToOccupy);
       // if (enemyKingISInJaque()){}
        return false;
    }

}
