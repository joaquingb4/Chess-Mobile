package ChessPieces;

import static com.example.chess_mobile.Tools.calculTheNextBox;
import static com.example.chess_mobile.Tools.getLeftBox;
import static com.example.chess_mobile.Tools.getRightBox;
import static com.example.chess_mobile.Tools.getUpBox;
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
        if (this.color.equals("white")) {
            direction = +1;
        } else {
            direction = -1;
        }
        Box nextBox = calculTheNextBox(board, x, y, direction);
        Box rightBox = null;
        Box leftBox = null;
        //Añado si puede la siguiente casilla
        addAndCheck(moves, nextBox, false);
        if (nextBox!=null) { //ESTA DENTRO
            rightBox = getRightBox(board, nextBox.getX(), nextBox.getY());
            leftBox = getLeftBox(board, nextBox.getX(), nextBox.getY());
            //Buscamos derecha e izquierda
            addAndCheck(moves, rightBox, true);
            addAndCheck(moves, leftBox, true);
        }
        if (isFirstMovement(y, this.color)) {
            Box secondMovement = calculTheNextBox(board, nextBox.getX(), nextBox.getY(), direction);
            addAndCheck(moves, secondMovement, false);
        }
        return moves;

    }
    //COMPRUEBA SI ES SU PRIMER MOVIMIENTO
    public boolean isFirstMovement(int y, String color) {
        if (color.equals("white")) {
            return y == 1;
        } else {
            return  y == 6; //ESTOY AQUÍ
        }
    }
    //AÑADE A UNA LISTA SI ES QUE LA CASILLA NO ES NULL
    public boolean addAndCheck(ArrayList list, Box box, boolean canICapture){
        if (box != null){
            if (canICapture){
                if (!box.isEmpty()&&!box.getPiece().getColor().equals(this.color)){
                    list.add(box);
                    return true;
                }
            }else{
                if (box.isEmpty()){
                    list.add(box);
                    return true;
                }
            }
        }
        return false;
    }

}