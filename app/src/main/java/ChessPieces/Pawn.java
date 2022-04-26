package ChessPieces;

import static com.example.chess_mobile.Tools.calculTheNextBox;
import static com.example.chess_mobile.Tools.getXYOfANumber;
import static com.example.chess_mobile.Tools.haveAPiece;
import static com.example.chess_mobile.Tools.isInsideTheBoard;
import static com.example.chess_mobile.Tools.isOfTheSameColor;
import static com.example.chess_mobile.Tools.nextPosition;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Tools;

import java.util.ArrayList;

public class Pawn extends Piece {
    //Attributes
    private final String name = "Pawn";
    private String color;
    //Methods
    public Pawn(String color){
        setColor(color);
    }
    private void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String setColor() {
        return this.color;
    }

    @Override
    //Devuelve un ArrayList con las casillas posibles
    
    public ArrayList<Box> getAvailableMoves(Box[][] board, int x, int y) {
        ArrayList<Box> moves = new ArrayList<>();
        int direction = 0;
        //OBTENGO LA DIRECCIÓN
        if (this.color.equals("white")){
            direction = +1;
        }else{
            direction = -1;
        }

        Box nextBox = calculTheNextBox(board, x, y, direction);
        Box rightBox = null;
        Box leftBox = null;

        if (nextBox!=null){
            if (nextBox.isEmpty()){

            }else{

            }
        }else{

        }

        //AÑADO LA PRIMERA CASILLA PARA AÑADIR
        if (this.color.equals("white")){
            nextBox = Tools.getUpBox(board, x, y);
            if (filter(moves, nextBox)){
                filter(moves, )
            }
            moves.add(nextBox);

        }else{
            nextBox = Tools.getDownBox(board ,x ,y);
            if (nextBox!=null){
                moves.add(nextBox);
            }
        }
        //BUSCO LAS DIAGONALES


        if (isFirstMovement(y, this.getColor())){
            nextBox
        }else{
            nextBox =
        }


        int nextNumber;
        int nextNumberX;
        int nextNumberY;
        Box nextBox;

        int originalX = x;
        int originalY = y;

        int[] directions; //new int[]{+1};
        int[] directionOfCapture = new int[]{+11, -9};
        
        if (!color.equals("white")){
            for (int i = 0; i < directionOfCapture.length ; i++) {
                directionOfCapture[i] = directionOfCapture[i]*-1;
            }
        }
        ArrayList<Box>diagonals = getdiagonalBoxes(board, Tools.thisPositionInNumber(x,y) , directionOfCapture);
        for (Box box: diagonals) {
            boxes.add(box);
        }

        if (isFirstMovement(y, this.color)){
            directions = new int[]{+1,+2};
        }else {
            directions = new int[]{+1};
        }

        for (int i = 0; i < directionOfCapture.length; i++) {

            int diagonal = Tools.nextPosition(x,y,directionOfCapture[i]);
            if (diagonal>0){
                if (!board[getXYOfANumber(diagonal)[0]][getXYOfANumber(diagonal)[1]].isEmpty()){
                    boxes.add(board[getXYOfANumber(diagonal)[0]][getXYOfANumber(diagonal)[1]]);
                }
            }
        }
            //AQUÍ REFACTOR
        for (int i = 0; i < directions.length; i++) {
            while (true) {
                nextNumber = nextPosition(x, y, directions[i]);
                nextNumberX = getXYOfANumber(nextNumber)[0];
                nextNumberY = getXYOfANumber(nextNumber)[1];

                if (isInsideTheBoard(nextNumberX, nextNumberY)) {
                    nextBox = board[nextNumberX][nextNumberY];

                    if (!haveAPiece(nextBox)) {
                        boxes.add(nextBox);
                        x = nextNumberX;
                        y = nextNumberY;
                    }
                }
                break;
            }
            x = originalX;
            y = originalY;
        }
        return boxes;
    }

    public ArrayList<Box> getdiagonalBoxes(Box[][] board, int position, int[] directions){
        ArrayList<Box> result = new ArrayList();
        for (int i = 0; i < directions.length; i++) {
            int newPosition = position+directions[i];
            int x = Tools.getXYOfANumber(newPosition)[0];
            int y = Tools.getXYOfANumber(newPosition)[1];
            if (Tools.isInsideTheBoard(x, y)){
                if (!board[x][y].isEmpty()){
                    result.add(board[x][y]);
                }
            }
        }
        return result;
    }
    /*
    public ArrayList<Box> getdiagonalBoxes(Box[][] board, int position, int[] directions){
        ArrayList<Box> result = new ArrayList();
        int direction = 0;
        if (this.color.equals("white")){
            direction = +1
        }else{
            Tools.isInsideTheBoard()
        }
        for (int i = 0; i < directions.length; i++) {
            int newPosition = position+directions[i];
            int x = Tools.getXYOfANumber(newPosition)[0];
            int y = Tools.getXYOfANumber(newPosition)[1];
            if (Tools.isInsideTheBoard(x, y)){
                if (!board[x][y].isEmpty()){
                    result.add(board[x][y]);
                }
            }
        }
        return result;
    }

     */

    public boolean isFirstMovement(int y, String color) {
        if (color.equals("white")) {
            return y == 1;
        } else {
            return  y == 6; //ESTOY AQUÍ
        }
    }
    //AÑADE A UNA LISTA SI ES QUE LA CASILLA NO ES NULL
    public boolean filter(ArrayList list, Box box){
        if (box != null){
            list.add(box);
        }
        return box!=null;
    }
}