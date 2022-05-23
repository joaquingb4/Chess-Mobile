package com.example.chess_mobile.Drivers;

import android.app.AlertDialog;
import android.util.Log;
import java.util.ArrayList;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.ChessPieces.King;
import com.example.chess_mobile.ChessPieces.Pawn;
import com.example.chess_mobile.ChessPieces.Piece;
import com.example.chess_mobile.ChessPieces.Queen;
import com.example.chess_mobile.ChessPieces.Tower;
import com.example.chess_mobile.LogicBoard;

//Esta clase Son el conjunto de instrucciones
// con las que el VisualBoard y el LogicBoard se comunican
public class Driver {
    //_______________________      [ATTRIBUTES]      _____________________________
    private LogicBoard logicBoard = new LogicBoard();
    private Box[][] board = logicBoard.getBoard();
    private ArrayList<Box> potentialMovesList = new ArrayList<>();
    private Box lastCLickedBox = null;
    private Box kingIncheck = null;
    private Box pawnPromoted = null;
    private boolean turn = true;

    //CREAR FUNCIÓN QUE CALCULE TODOS LOS MOVIMIENTOS POSIBLES DE UN TURNO
    //_______________________      [GETTERS&&SETTERS]      _____________________________

    public LogicBoard getLogicBoard() {
        return logicBoard;
    }

    public void setLogicBoard(LogicBoard logicBoard) {
        this.logicBoard = logicBoard;
    }

    public Box[][] getBoard() {
        return board;
    }

    public void setBoard(Box[][] board) {
        this.board = board;
    }

    public ArrayList<Box> getPotentialMovesList() {
        return potentialMovesList;
    }

    public void setPotentialMovesList(ArrayList<Box> potentialMovesList) {
        this.potentialMovesList = potentialMovesList;
    }

    public Box getKingIncheck() {
        return kingIncheck;
    }

    public void setKingIncheck(Box kingIncheck) {
        this.kingIncheck = kingIncheck;
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Box getPawnPromoted() {
        return pawnPromoted;
    }

    public void setPawnPromoted(Box pawnPromoted) {
        this.pawnPromoted = pawnPromoted;
    }


    public Box getLastCLickedBox(){
        return lastCLickedBox;
    }

    public void setLastCLickedBox(Box lastCLickedBox) {
        this.lastCLickedBox = lastCLickedBox;
    }
    //_______________________      [FUNCTIONS]      _____________________________

    public void clickDesition(Box clickedBox)  {
        printBoxInfo(clickedBox);
        //Si es que no hay posibles movimientos
        if (potentialMovesList.isEmpty()){
            //Busca movimientos
            searchPostions(board, clickedBox.getX(), clickedBox.getY());
       //Sino comprueba si ha hecho click en uno
        }else{
            //Si es así ejecuta un movimiento
            if (potentialMovesList.contains(clickedBox)){
                move(lastCLickedBox, clickedBox);//CAPTURA //POSIBLE ERROR
                changeTurn();
            //Sino vacía la lista de posibles movimientos y busca posiciones
            }else{
                potentialMovesList.clear();
                searchPostions(board, clickedBox.getX(), clickedBox.getY());
            }
            logicBoard.setNoCapturable();//Me he quedado [AQUÍ]
            potentialMovesList.clear();
        }
    }

    public void searchPostions(Box[][] board, int x, int y)  {
       // updateImages();
        //Obtengo la casilla con ello
        Box box = board[x][y];
        Piece piece = box.getPiece();

        if (box.isEmpty()){
            Log.i("ERROR","Casilla vacía, no hay movimientos");
        }else{
            if (!checkClickTurn(box.getPiece())){
                Log.i("INFO","TURNO: "+turn);
                return;
            }
            piece = box.getPiece();
            //Posiciones posibles
            ArrayList<Box> casillas = piece.getAvailableMoves(board, x, y);
            casillas = removeNotAllowedMoves(casillas, x ,y);
            //Quito las casillas que no me sirvan para salir de un jaque

            //Compruebo si tengo algún movimiento
            if (casillas.isEmpty()){
                Log.i("Alerta: ", "No hay movimientos disponibles");
            }else {
                for (int i = 0; i<casillas.size(); i++) {
                    Box arrayBox = casillas.get(i);
                    if (arrayBox.getPiece() == null) {
                        Log.i("icono", "funciona");
                    } else {
                        arrayBox.setCapturable(true);
                    }
                }
            }
            potentialMovesList = casillas;
            setLastCLickedBox(box);
            //Repintamos el tablero
            Log.i("Info", "Acabo de buscar posiciones");
        }
    }

    public void printBoxInfo(Box clickedBox){
        Log.i("Info", " Has hecho click en la casilla: " + clickedBox.getName()+
                ", Que tiene un ["+ getBoxPieceName(clickedBox.getName())+"]");
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
        getBox("g8").setPiece(new Pawn("black"));
        getBox("g7").setPiece(new Pawn("black"));
        getBox("a1").setPiece(new Queen("white"));
    }

    //Mueve una pieza a una posición
    public void move(Box boxOrigin, Box boxDestiny){
        //Me muevo
        logicBoard.move(boxOrigin, boxDestiny);
        //Última casilla es NUll
        lastCLickedBox = null;
            setPawnPromoted(logicBoard.pawnPromotion());
    }
    //Cambia el estado del turno cuando se lo llama
    public void changeTurn (){
        if (turn){
            turn = false;
            return;
        }
        turn = true;
    }


    //Devuelve de quíen es el turno
    public boolean checkClickTurn(Piece piece){
        if (turn){
            Log.i("INFO", "Turno de las [blancas]");
            return piece.getColor().equals("white");
        }
        Log.i("INFO", "Turno de las [negras]");
        return piece.getColor().equals("black");
    }

    //BOARD FUNCTIONS
    public ArrayList<Box> removeNotAllowedMoves(ArrayList<Box> possibilesMoves, int x, int y)  {
        //Creamos un nuevo logicBoard
        // x y mi equipo
        Box[][] originalBoard = logicBoard.getBoard().clone();
        logicBoard.setBoard(originalBoard.clone());

        //Creamos una Lista para guardar las casillas que pasen el test
        ArrayList<Box> allowedMoves = new ArrayList<>();
        LogicBoard copyLogicBoard = null;
        Box boxToMove = logicBoard.getBoard()[x][y];
        for (int i = 0; i < possibilesMoves.size(); i++) {
            boolean valid = logicBoard.simulation(boxToMove, possibilesMoves.get(i));

            if (valid){
                allowedMoves.add(possibilesMoves.get(i));
            }
        }
        return allowedMoves;
    }
    //Calcula si hay movimientos posibles en un turno___Por probar
    public boolean playerHaveMoves (String color){
        int totalAvaliableMoves = 0;
        ArrayList<Box> myPieces = logicBoard.getPlayerBoxes(color);
        for (int i = 0; i < myPieces.size(); i++) {
            ArrayList<Box> capturablesBoxes = myPieces.get(i).getPiece().getAvailableMoves(board, myPieces.get(i).getX(), myPieces.get(i).getY());
            int totalPieceMoves = removeNotAllowedMoves(capturablesBoxes, myPieces.get(i).getX(), myPieces.get(i).getY()).size();
            totalAvaliableMoves+= totalPieceMoves;
        }
        Log.i("INFO", ":"+color+"::"+totalAvaliableMoves);
        if (totalAvaliableMoves>0)
            return true;
        else
            return false;
    }
}