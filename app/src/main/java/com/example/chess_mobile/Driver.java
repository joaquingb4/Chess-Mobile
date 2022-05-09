package com.example.chess_mobile;

import static com.example.chess_mobile.Tools.BoardTools.getImageView;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

import com.example.chess_mobile.ChessPieces.King;
import com.example.chess_mobile.ChessPieces.Pawn;
import com.example.chess_mobile.ChessPieces.Piece;
import com.example.chess_mobile.ChessPieces.Queen;
import com.example.chess_mobile.ChessPieces.Tower;
//Pueente entre ActivityBoard y Board
public class Driver {
    //Attributes
    //Crea un tablero lógico
    LogicBoard logicBoard = new LogicBoard();
    Box[][] board = logicBoard.getBoard();

    ArrayList<Box> potentialMovesList = new ArrayList<>();
    //Puntuación
    ArrayList<Piece> whiteUserCaptures = new ArrayList<>();
    ArrayList<Piece> blackUserCaptures = new ArrayList<>();
    String kingInCheck = null;
    private Box lastCLickedBox = null;
    private Box kingIncheck = null;

    //Getters And Setters
    private boolean turn = true;
    public Box getLastCLickedBox(){
        return lastCLickedBox;
    }

    public void setLastCLickedBox(Box lastCLickedBox) {
        this.lastCLickedBox = lastCLickedBox;
    }
    //Functions

    public void clickDesition(Box clickedBox){
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
         //   logicBoard.updateKingStates();
            //ERROR ES NECESARIO QUE EL REY SE QUEDE ILUMINADO SI ESTÁ AMENAZADO
            //BUG: SI ES QUE DESPUES DE CALCULAR LAS AMENAZAS DE UNA PIEZA SE QUEDA EL ILUMINADO
        }
    }

    public void searchPostions(Box[][] board, int x, int y){
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
        getBox("h1").setPiece(new King("white"));
        getBox("h8").setPiece(new King("black"));
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
    public void simulation(ArrayList<Box> possbilesMoves, int x, int y){
        Box[][] testBoard = this.board.clone();
        Box boxToMove = testBoard[x][y];
        for (int i = 0; i < possbilesMoves.size(); i++) {
            Box potentialMove = testBoard[possbilesMoves.get(i).getX()][possbilesMoves.get(i).getY()];
            move(boxToMove, potentialMove);
            //if ()
        }
    }

}
