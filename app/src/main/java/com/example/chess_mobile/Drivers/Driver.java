package com.example.chess_mobile.Drivers;

import static com.example.chess_mobile.Tools.TranslationTools.translateIntToLetter;

import android.app.AlertDialog;
import android.util.Log;
import java.util.ArrayList;
import com.example.chess_mobile.Tools.TranslationTools.*;
import com.example.chess_mobile.ActivityBoard;
import com.example.chess_mobile.Box;
import com.example.chess_mobile.ChessPieces.Bishop;
import com.example.chess_mobile.ChessPieces.Horse;
import com.example.chess_mobile.ChessPieces.King;
import com.example.chess_mobile.ChessPieces.Pawn;
import com.example.chess_mobile.ChessPieces.Piece;
import com.example.chess_mobile.ChessPieces.Queen;
import com.example.chess_mobile.ChessPieces.Tower;
import com.example.chess_mobile.LogicBoard;
/**
 * Clase que controla el tablero lógico
 */
public class Driver {
    /**_______________________      [ATTRIBUTES]      _____________________________
     */
    private LogicBoard logicBoard = new LogicBoard();
    private Box[][] board = logicBoard.getBoard();
    private ArrayList<Box> potentialMovesList = new ArrayList<>();
    private Box lastCLickedBox = null;
    private Box kingIncheck = null;
    private Box pawnPromoted = null;
    private boolean turn = true;
    private ActivityBoard instance;
    //.CONDITIONS
    private boolean isGameOver = false;
    /**_______________________      [BUILDER]      _____________________________
     */
    public Driver(ActivityBoard instance, boolean turn){
        this.instance = instance;
        this.turn = turn;
    }
    /**_______________________      [GETTERS&&SETTERS]      _____________________________
     */
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

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
    /**
     * _______________________      [FUNCTIONS]      _____________________________
     */

 /**
  * Recibe el click del tablero gráfico y ejecuta las acciones correspondientes
  * */
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
                //Comprueba si hay un peón que entre en promoción
                if ((lastCLickedBox.getPiece().getName().equals("Pawn")) && (lastCLickedBox.getY() == 6 || lastCLickedBox.getY() == 1)
                        && (clickedBox.getY() == 0 || clickedBox.getY() == 7)) {
                    cancel();
                    instance.showPromotionOptions(lastCLickedBox.getPiece().getColor());
                    //move(lastCLickedBox, clickedBox);
                    setPawnPromoted(lastCLickedBox);
                    setLastCLickedBox(clickedBox);
                    return;
                }
                move(lastCLickedBox, clickedBox);//CAPTURA //POSIBLE ERROR
                changeTurn();
            //Sino vacía la lista de posibles movimientos y busca posiciones
            }else{
                potentialMovesList.clear();
                searchPostions(board, clickedBox.getX(), clickedBox.getY());
            }
            cancel();
        }
    }

    //ME QUEDO AQUÍ:
    //PROBLEMA: NO SÉ COMO PASARLE A ESTA FUCNIÓN LA CASILLA DE PROMOCIÓN
    //POSIBLE SOLUCIÓN <LASTCLCIKEDBOX SI QUE ES LA CASILLA DE PROMOCIÓN>

    /**
     * Decide que pieza devolver en base a su tag
     * -Solo funciona con las opciones de promoción
     */
    public void selectionPawnPromotionTree(String tag){
        String color;
        if (tag.charAt(tag.length()-2)=='w')
            color= "white";
        else
            color = "black";

        switch (tag.charAt(tag.length()-1)){
            case ('t'):
                move(pawnPromoted, lastCLickedBox);
                pawnPromoted.setPiece(new Tower(color));
                instance.updateImages();
                break;
            case ('h'):
                move(pawnPromoted, lastCLickedBox);
                pawnPromoted.setPiece(new Horse(color));
                instance.updateImages();
                break;
            case ('q'):
                move(pawnPromoted, lastCLickedBox);
                pawnPromoted.setPiece(new Queen(color));
                instance.updateImages();
                break;
        }
    }

    public Box getAPawnOnPromotion(){
        for (int x = 0; x < board.length; x++) {
            Box box = board[x][0];
            if (!box.isEmpty())
                if (box.getPiece().getName().equals("Pawn"))
                    return box;
        }
        for (int y = 0; y < board.length; y++) {
            Box box = board[y][7];
            if (!box.isEmpty())
                if (box.getPiece().getName().equals("Pawn"))
                    return box;
        }
        return null;
    }
    /**
     * Marca todas las casillas no capturables y vacía la lista de posibles movimientos
     */
    public void cancel() {
        logicBoard.setNoCapturable();//Me he quedado [AQUÍ]
        potentialMovesList.clear();
        setPawnPromoted(null);
        instance.hidePromotionOptions();
    }
    /**
     * Busca movientos en una determinada casilla
     * - solo devuleve movimientos en los que el rey no
     *  acabe o siga en jaque
     */
    public void searchPostions(Box[][] board, int x, int y)  {
       // updateImages();
        //Obtengo la casilla con ello
        Box box = board[x][y];
        Piece piece = box.getPiece();

        if (box.isEmpty()){
            Log.i("ERROR","Casilla vacía, no hay movimientos");
        }else{
            if (!checkClickTurn(box.getPiece()) || isGameOver){
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
    /**
     * IMPRIME POR PANTALLA LA INFO DE UNA CASILLA
     */
    public void printBoxInfo(Box clickedBox){
        Log.i("Info", " Has hecho click en la casilla: " + clickedBox.getName()+
                ", Que tiene un ["+ getBoxPieceName(clickedBox.getName())+"]");
    }
    /**
     * Devulve el nombre de la pieza, si es que tiene
     */
    public String getBoxPieceName(String boxName){
        Box box = getBox(boxName);
        if (!getBox(boxName).isEmpty()){
            return box.getPiece().getName();
        }else{
            return "empty";
        }
    }
    /**
     * Devuelve una casilla determinada
     */
    public Box getBox(String boxName){
        int x = Box.unknownBoxGetX(boxName);
        int y = Box.unknownBoxGetY(boxName);
        return board[x][y];
    }
    /**
     * Construye las piezas del tablero por defecto
     */
    public void buildPieces(){
        //A---1||  columna - fila
        //Towers-----------
        getBox("a1").setPiece(new Tower("white"));
        getBox("h1").setPiece(new Tower("white"));
        getBox("a8").setPiece(new Tower("black"));
        getBox("h8").setPiece(new Tower("black"));
      //  getBox("d4").setPiece(new Tower("white"));
        //Horses-----------

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


        //Pawns------------
        for (int i = 0; i < 8; i++) {
            getBox(translateIntToLetter(i)+"2").setPiece(new Pawn("white"));
        }
        for (int i = 0; i < 8; i++) {
            getBox(translateIntToLetter(i)+"7").setPiece(new Pawn("black"));
        }
        getBox("g8").setPiece(new Pawn("black"));
        getBox("g7").setPiece(new Pawn("black"));
        getBox("a1").setPiece(new Queen("white"));
    }

    /**
     * Mueve una pieza a una posición
     */
    public void move(Box boxOrigin, Box boxDestiny){
        //Me muevo
        logicBoard.move(boxOrigin, boxDestiny);
        //Última casilla es NUll
        lastCLickedBox = null;
            setPawnPromoted(logicBoard.pawnPromotion());
    }
    /**
     * Cambia el turno cuando se lo llama
     */
    public void changeTurn (){
        if (turn){
            turn = false;
            return;
        }
        turn = true;
    }
    /**
     * Devuelve de quien es el turno
     */
    public boolean checkClickTurn(Piece piece){
        if (turn){
            Log.i("INFO", "Turno de las [blancas]");
            return piece.getColor().equals("white");
        }
        Log.i("INFO", "Turno de las [negras]");
        return piece.getColor().equals("black");
    }

    /**
     * QUITA LOS MOVIMENTOS NO VALIDOS DE LA LISTA DE MOVIMENTOS DE UNA PIEZA
     * @param possibilesMoves : LISTA DE MOVIMENTOS
     * @param x :posición x de la pieza
     * @param y : posición y de la pieza
     * @return : ArrayList de las casillas a las que es válido que me mueva
     */
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

    /**
     * DEVUELVE TRUE O FALSE EN BASE A SI UN JUGADOR TIENE MOVIMENTOS DISPONIBLES
     * @param color : Color del jugador
     * @return
     */
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
    /*
    //Falta modificar el move para que cambie la variables firstMove
    //________________________________________________
    public ArrayList<Box> checkSpecialMoves(Box box){
        //Se que no está vacío
        if (box.isEmpty())
            return null;
        Piece piece = box.getPiece();
        switch (piece.getName()){
            case "King":
                return canEnroll(box);
            case "Tower":
                return canEnroll(box);
        }
        return null;
    }
    //Añadir el setFirst move = false en move()
    //Devuelve las casillas con las que rey puede enrocar
    public ArrayList<Box> canEnroll(Box box){
        Box kingBox;
        int postionY;
        String color = box.getPiece().getColor();
        ArrayList<Box> towers = new ArrayList();
        if (box.getPiece().getName().equals("King")){
            kingBox = box;
            towers = getEnroqueTowers(kingBox);
        }else {
            kingBox = logicBoard.getKing(color);
        }
        if (!kingBox.getPiece().isFirstMovement())
            return null;
        //Ya tengo la s torres y el rey y se que es su primer movimiento
        ArrayList<Box> positionsToMove = new ArrayList<>();
        for (int i = 0; i < towers.size(); i++) {
            if (getBoxEnroque(kingBox, towers.get(i))!=null){
                positionsToMove.add(getBoxEnroque(kingBox, towers.get(i)));
            }
        }

    }

    /**
     *En base un rey se devuelven sus torres si cumplen los requisitos para un enroque
     */
    /*
    public ArrayList<Box> getEnroqueTowers(Box kingBox) {
        ArrayList<Box> towers = new ArrayList<>();
        Box rightTower = board[0][kingBox.getY()];
        Box leftTower = board[7][kingBox.getY()];

        if (rightTower.isEmpty() && rightTower.getPiece().getName().equals("Tower") && rightTower.getPiece().isFirstMovement()){
            towers.add(rightTower);
        }
        if (leftTower.isEmpty() && leftTower.getPiece().getName().equals("Tower") && leftTower.getPiece().isFirstMovement()){
            towers.add(leftTower);
        }
        return towers;
    }
    public Box getBoxEnroque(Box kingBox, Box towerBox){
        int Y = kingBox.getY();
        int kingX = kingBox.getX();
        int towerX = towerBox.getX();
        int i = 1;
        boolean allowed = true;
        Box finalBox = null;
        if (towerX>kingX)
            i=i*-1;

        for (int x = kingX+i; x != towerX; x+=i) {
            Box box = board[x][Y];
            if (!box.isEmpty()){
                allowed=false;
            }
            if (x==1||x==2){
                if (logicBoard.boxInDanger(box)){
                    allowed=false;
                    finalBox = box;
                }
            }
        }

        if (allowed)
            return finalBox;
        else
            return null;
    }
    */
}