package ChessPieces;

import android.util.Log;

import com.example.chess_mobile.Box;
import com.example.chess_mobile.Driver;
import com.example.chess_mobile.Tools;

import java.util.ArrayList;
import java.util.Optional;

public class Tower extends Piece {
    //Attributes
    private final String name = "Tower";
    private String color;

    //Methods
    public Tower(String color) {
        super();
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
    //Devuelve el número de posiciones pobibles
    public int getPossiblesBoxesNumber(Box[][] board, int x, int y) {
        int totalNumber = 0;
        totalNumber += metodo(board, x, y, +10);
        totalNumber += metodo(board, x, y, +1);
        totalNumber += metodo(board, x, y, -1);
        totalNumber += metodo(board, x, y, -10);
        return totalNumber;
    }
    //Método
    public ArrayList<Box> metodo(Box[][] board, int x, int y, int direccion){
        ArrayList<Box> boxes = new ArrayList<>();
        int nextNumber = nextPosition(x, y, direccion);
        boolean canOccupy = checker(board, nextNumber);
        boolean itsBusy = board[x][y].isEmpty();
        boolean isTheFinal = isTheEnd(x, y);
        if(isInsideTheBoard(x, y)){
            //ESTOY AQUÍ
        }
        if (canOccupy){//¿Se puede ocupar?
            //ESTOY AQUÍ: PONER CONDICIONES PARA OTRAS CIRCUNSTANCIAS
            if(){ //Esta ocupado con otra pieza

            }
            int[] array = getXYOfANumber(nextNumber);
            x = array[0];
            y = array[1];
            boxes.add(board[x][y]);
        }else {

        }

        if (!isInsideTheBoard(x, y))return boxes;
//Volvemos a hacer lo mismo
        position = (x*10)+y;//junta la x con y en un solo número
        position += direccion;
       // char[] conversion = new char[2];
        String conversion;
        if (posicionArray<10){
            conversion = '0'+Integer.toString(posicionArray);
        }else {
            conversion = Integer.toString(posicionArray);
        }
    }
    //Calcula la siguiente posición
    public int nextPosition(int x, int y, int direccion){
        int position = (x*10)+y;//junta la x con y en un solo número
        position += direccion;
        return position;
    }

    //convierte un número en dos números
    public int[] getXYOfANumber(int position){
        int x = position / 10;
        int y = position % 10;
        return new int[]{x, y};
    }
    //Devuelve un ArrayList con las casillas posibles
    @Override
    public ArrayList<Box> getPossiblesBoxes(Box[][] board, int x, int y) {
        ArrayList<Box> boxes = new ArrayList<>();
        int index = 0;
        while (metodo()){
        }
        for (int i = 0; i <; i++) {
            int posicionArray = Integer.parseInt(x + "" + y);
            index += 2;
            if (index > Tools.direcciones.length){
                break;
            }
            posicionArray += Tools.direcciones[index+=2];//Probar
            String conversion;
            if (posicionArray<10){
                conversion = '0'+Integer.toString(posicionArray);
            }else {
                conversion = Integer.toString(posicionArray);
            }
            x = conversion.charAt(0) - '0';
            y = conversion.charAt(1) - '0';
            boxes.add(board[x][y]);
        }
        return boxes;
    }
    //____________________________CONDICIONES_________________________
    //Comprueba si una casilla está dentro del tablero
    public boolean isInsideTheBoard(int x, int y){
        if (x<0 || x>7 || y<0 || y>7){
            return false;
        }else {
            return true;
        }
    }
    //Comprueba si una casilla puede ser ocupada
    public boolean itsOccupyable(Box[][] board, int position){
        int[] array = getXYOfANumber(position);
        int x = array[0];
        int y = array[1];
        Box box = board[x][y];
        return box.isEmpty();
    }
    //Comprueba si una casilla es la última
    public boolean isTheEnd(int x, int y){
        if (x == 0 || y == 7){
            return true;
        }else{
            return false;
        }
    }
}
 /*                             +9  +10  +11
                                -1    2   +1     +10 -1 -10 +1
                                -9  -10  -11
            º +1, -10, -1, +10
            º24 25 26 27 28 29 30 31     30, 31, 32, 33, 34, 35, 36, 37
            º16 17 18 19 20 21 22 23     20, 21, 22, 23, 24, 25, 26, 27
            º08 09 10 11 12 13 14 15     10, 11, 12, 13, 14, 15, 16, 17
            º00 01 02 03 04 05 06 07     00, 01, 02, 03, 04, 05, 06, 07
          y ºººªºººººººººººººººººººº
             x

             4{[0][1][2][3][4][5][6][7]}
             3{[0][1][2][3][4][5][6][7]}
             2{[0][1][2][3][4][5][6][7]}
             1{[0][1][2][3][4][5][6][7]}
           X 0{[0][1][2][3][4][5][6][7]}
                Y

             if ()

         */