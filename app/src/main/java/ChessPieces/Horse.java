package ChessPieces;

import static com.example.chess_mobile.Tools.getXYOfANumber;
import static com.example.chess_mobile.Tools.haveAPiece;
import static com.example.chess_mobile.Tools.isInsideTheBoard;
import static com.example.chess_mobile.Tools.isOfTheSameColor;
import static com.example.chess_mobile.Tools.nextPosition;

import android.util.Log;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Driver;

import java.util.ArrayList;

public class Horse extends Piece {
    //Attributes
    private final String name = "Horse";
    private String color;
    //Methods
    public Horse(String color){
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

        int[] directions = new int[]{+21, +12, -8, -19, -21, -12, +8, +19};

        for (int i = 0; i < directions.length; i++) {
            while (true) {
                nextNumber = nextPosition(x, y, directions[i]);
                nextNumberX = getXYOfANumber(nextNumber)[0];
                nextNumberY = getXYOfANumber(nextNumber)[1];

                if (isInsideTheBoard(nextNumberX, nextNumberY)) {
                    nextBox = board[nextNumberX][nextNumberY];

                    if (haveAPiece(nextBox)) {
                        if (!isOfTheSameColor(this, nextBox.getPiece())) {
                            boxes.add(board[nextNumberX][nextNumberY]);
                            x = nextNumberX;
                            y = nextNumberY;
                        }
                    } else {
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
    }}
