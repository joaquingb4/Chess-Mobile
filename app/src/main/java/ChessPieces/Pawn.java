package ChessPieces;

import static com.example.chess_mobile.Tools.getXYOfANumber;
import static com.example.chess_mobile.Tools.haveAPiece;
import static com.example.chess_mobile.Tools.isInsideTheBoard;
import static com.example.chess_mobile.Tools.isOfTheSameColor;
import static com.example.chess_mobile.Tools.nextPosition;

import com.example.chess_mobile.Box;

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
        ArrayList<Box> boxes = new ArrayList<>();

        int nextNumber;
        int nextNumberX;
        int nextNumberY;
        Box nextBox;

        int originalX = x;
        int originalY = y;

        int[] directions; //new int[]{+1};
   //     int[] directionOfCapture = new int[]{-10, +10, +9, -9};
/*
        if(isFirstMovement(y, this.color)){
            directions = new int[]{+10, +20};
        }if (!color.equals("white")){
            for (int i = 0; i < directions.length ; i++) {
                directions[i] = directions[i]*-1;
            }
        }
 */
        if (isFirstMovement(y, this.color)){
            directions = new int[]{+1,+2};
        }else {
            directions = new int[]{+1};
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
                        break;
                    }
                } else {
                    break;
                }
            }
            x = originalX;
            y = originalY;
        }
        return boxes;
    }

    public boolean isFirstMovement(int y, String color) {
        if (color == "white") {
            return y == 1;
        } else {
            return  y == 6; //ESTOY AQUÍ
        }
    }
}