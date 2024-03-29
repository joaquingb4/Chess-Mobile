package com.example.chess_mobile;

import static com.example.chess_mobile.Tools.TranslationTools.getContraryColor;
import static com.example.chess_mobile.Tools.TranslationTools.translatePositionToName;
import  static  com.example.chess_mobile.Tools.CalculTools.getContraryBoolean;

import android.util.Log;

import com.example.chess_mobile.ChessPieces.King;
import com.example.chess_mobile.ChessPieces.Piece;

import java.util.ArrayList;

//Tablero lógico con dos reyes por defecto
public class LogicBoard {
    //_______________________      [ATTRIBUTES]      _____________________________

    private Box[][] board = new Box[8][8];
    private Box whiteKing = null;
    private Box blackKing = null;

    //Builder
    //Constructor con tablero por defecto
    public LogicBoard(){
        buildBoxes();
        board[7][0].setPiece(new King("white"));
        board[7][7].setPiece(new King("black"));
        updateBoardStatus();
    }
    //Constructor con tablero externo
    public LogicBoard(Box[][] board){
        this.board = board;
        buildBoxes();
        board[7][0].setPiece(new King("white"));
        board[7][7].setPiece(new King("black"));
        updateBoardStatus();
    }
    //_______________________      [GETTERS&&SETTERS]      _____________________________
    public Box[][] getBoard() {
        return board;
    }
    public void setBoard(Box[][] board){
        this.board = board;
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
    //Busco si una casilla apunta a esta pieza
    public boolean boxInDanger(Box box){
        if (box.isEmpty()){
            Log.i("INFO", "casilla "+ box +" está vacía");
            return false;
        }
        String enemyColor = getContraryColor(box.getPiece().getColor());
        //Obtengo las casillas con piezas del contrario
        ArrayList<Box> contraryBoxes = getPlayerBoxes(enemyColor);
        //Las recorro y busco si alguna apunta a la pieza indicada
        ArrayList<Box> occupybleBoxes = new ArrayList<>();
        for (int i = 0; i < contraryBoxes.size(); i++){
            boolean answer =
                    contraryBoxes.get(i).getPiece()
                    .getAvailableMoves(board
                            , contraryBoxes.get(i).getX(),
                            contraryBoxes.get(i).getY())
                    .contains(box);
            if (answer){
                return true;
            }
        }
        return false;
    }
    //Si uno de los dos reyes del tablero está en jaque, lo devuelve
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
    //OPERATIONS
    public void move(Box boxOrigin, Box boxDestiny){
        if (!boxOrigin.isEmpty()){
            boxDestiny.setPiece(boxOrigin.getPiece());//ERROR
            boxOrigin.setPiece(null);
        }else{
            Log.i("ERROR", "No hay un atacante");
        }
        updateBoardStatus();
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
    //Actualiza todas las casillas del tablero a no capturables, excepto los reyes
    public void setNoCapturable(){
        for (int x = 0; x < board.length; x++){
            for (int y = 0; y < board[x].length; y++) {
                Box logicBox = board[x][y];
                if (!logicBox.getName().equals(blackKing.getName()) && !logicBox.getName().equals(whiteKing.getName())){
                    logicBox.setCapturable(false);
                    Log.i("INFO", "box "+ logicBox.getName() + ": false");
                }else{
                    Log.i("INFO", "box "+ logicBox.getName() + ": true");
                }
            }
        }
    }

    //Actualiza los datos de las características del tablero
    public void updateBoardStatus(){
        //Se actualizan las ubicaciones
        whiteKing = getKing("white");
        blackKing = getKing("black");
        //Se actualizan sus estados
        whiteKing.setCapturable(boxInDanger(whiteKing));
        blackKing.setCapturable(boxInDanger(blackKing));
        Log.i("INFO", ""+whiteKing.getCapturable());
        Log.i("INFO", ""+blackKing.getCapturable());

    }

    public boolean simulation(Box boxA, Box boxB){
        //ESTOY AQUÍ BORRAR CLONE
        Piece pieceA = boxA.getPiece();
        Piece pieceB = boxB.getPiece();
        boxB.setPiece(boxA.getPiece());
        boxA.setPiece(null);
        if (boxInDanger(getKing(pieceA.getColor()))){
            boxA.setPiece(pieceA);
            boxB.setPiece(pieceB);
            return false;
        }else{
            boxA.setPiece(pieceA);
            boxB.setPiece(pieceB);
            return true;
        }
    }
    //Devuelve una casilla si hay una casilla final o inicial que contenga un peón
    public Box pawnPromotion(){
        //Obtenemos la primera línea de cada jugador
        ArrayList<Box> firstLines = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            firstLines.add(board[i][0]);
            firstLines.add(board[i][7]);
        }
        //Las recorremos y si hay un peón devolvemos la casilla
        for (int i = 0; i < firstLines.size(); i++) {
            Box box = firstLines.get(i);
            if (!firstLines.get(i).isEmpty() && firstLines.get(i).getPiece().getName().equals("Pawn")){
                 return box;
            }
        }
        return null;
    }

}
