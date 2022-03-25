package com.example.chess_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import ChessPieces.Piece;

public class ActivityBoard extends AppCompatActivity {
    //Attributes
    ImageView[][] visualBoxes = new ImageView[8][8];
    Driver driver = new Driver();
    ArrayList<Box> availablePositions = new ArrayList();

    //Actualiza la parte visual de las piezas
    public void updateImages(){
        for (int i = 0; i < visualBoxes.length ; i++) {
            for (int u = 0; u < visualBoxes[i].length; u++) {
              //  Log.i("Miramos  ", u+ " :: " + i);
                visualBoxes[i][u].setImageDrawable(getImage(driver.getBox(new int[]{i, u})));
            }
        }
    }

    public Drawable getImage(Box box){
        if (box.isEmpty()){
            return null;
        }else{
            switch (box.getPiece().getName()){
                case ("Tower"):
                    if (box.getPiece().getColor().equals("white")){
                        return getDrawable(R.drawable.whitetower);
                    }
                    return getDrawable(R.drawable.blacktower);
                case ("Horse"):
                    if (box.getPiece().getColor().equals("white")){
                        return getDrawable(R.drawable.whitehorse);
                    }
                    return getDrawable(R.drawable.blackhorse);
                case ("Pawn"):
                    if (box.getPiece().getColor().equals("white")){
                        return getDrawable(R.drawable.whitepawn);
                    }
                    return getDrawable(R.drawable.blackpawn);
                case ("King"):
                    if (box.getPiece().getColor().equals("white")){
                        return getDrawable(R.drawable.whiteking);
                    }
                    return getDrawable(R.drawable.blackking);
                case ("Queen"):
                    if (box.getPiece().getColor().equals("white")){
                        return getDrawable(R.drawable.whitequeen);
                    }
                    return getDrawable(R.drawable.blackqueen);
                case ("Bishop"):
                    if (box.getPiece().getColor().equals("white")){
                        return getDrawable(R.drawable.whitebishop);
                    }
                    return getDrawable(R.drawable.blackbishop);

            }
        }
        return null;
    }
    //Crea la parte visual de las casillas
    public void buildBoxes(){
        visualBoxes[0][0] = findViewById(R.id.box1);
        visualBoxes[0][1] = findViewById(R.id.box2);
        visualBoxes[0][2] = findViewById(R.id.box3);
        visualBoxes[0][3] = findViewById(R.id.box4);
        visualBoxes[0][4] = findViewById(R.id.box5);
        visualBoxes[0][5] = findViewById(R.id.box6);
        visualBoxes[0][6] = findViewById(R.id.box7);
        visualBoxes[0][7] = findViewById(R.id.box8);
        //boxes[3][0] = findViewById(R.id.box);
        visualBoxes[1][0] = findViewById(R.id.box9);
        visualBoxes[1][1] = findViewById(R.id.box10);
        visualBoxes[1][2] = findViewById(R.id.box11);
        visualBoxes[1][3] = findViewById(R.id.box12);
        visualBoxes[1][4] = findViewById(R.id.box13);
        visualBoxes[1][5] = findViewById(R.id.box14);
        visualBoxes[1][6] = findViewById(R.id.box15);
        visualBoxes[1][7] = findViewById(R.id.box16);

        visualBoxes[2][0] = findViewById(R.id.box17);
        visualBoxes[2][1] = findViewById(R.id.box18);
        visualBoxes[2][2] = findViewById(R.id.box19);
        visualBoxes[2][3] = findViewById(R.id.box20);
        visualBoxes[2][4] = findViewById(R.id.box21);
        visualBoxes[2][5] = findViewById(R.id.box22);
        visualBoxes[2][6] = findViewById(R.id.box23);
        visualBoxes[2][7] = findViewById(R.id.box24);

        visualBoxes[3][0] = findViewById(R.id.box25);
        visualBoxes[3][1] = findViewById(R.id.box26);
        visualBoxes[3][2] = findViewById(R.id.box27);
        visualBoxes[3][3] = findViewById(R.id.box28);
        visualBoxes[3][4] = findViewById(R.id.box29);
        visualBoxes[3][5] = findViewById(R.id.box30);
        visualBoxes[3][6] = findViewById(R.id.box31);
        visualBoxes[3][7] = findViewById(R.id.box32);

        visualBoxes[4][0] = findViewById(R.id.box33);
        visualBoxes[4][1] = findViewById(R.id.box34);
        visualBoxes[4][2] = findViewById(R.id.box35);
        visualBoxes[4][3] = findViewById(R.id.box36);
        visualBoxes[4][4] = findViewById(R.id.box37);
        visualBoxes[4][5] = findViewById(R.id.box38);
        visualBoxes[4][6] = findViewById(R.id.box39);
        visualBoxes[4][7] = findViewById(R.id.box40);

        visualBoxes[5][0] = findViewById(R.id.box41);
        visualBoxes[5][1] = findViewById(R.id.box42);
        visualBoxes[5][2] = findViewById(R.id.box43);
        visualBoxes[5][3] = findViewById(R.id.box44);
        visualBoxes[5][4] = findViewById(R.id.box45);
        visualBoxes[5][5] = findViewById(R.id.box46);
        visualBoxes[5][6] = findViewById(R.id.box47);
        visualBoxes[5][7] = findViewById(R.id.box48);

        visualBoxes[6][0] = findViewById(R.id.box49);
        visualBoxes[6][1] = findViewById(R.id.box50);
        visualBoxes[6][2] = findViewById(R.id.box51);
        visualBoxes[6][3] = findViewById(R.id.box52);
        visualBoxes[6][4] = findViewById(R.id.box53);
        visualBoxes[6][5] = findViewById(R.id.box54);
        visualBoxes[6][6] = findViewById(R.id.box55);
        visualBoxes[6][7] = findViewById(R.id.box56);

        visualBoxes[7][0] = findViewById(R.id.box57);
        visualBoxes[7][1] = findViewById(R.id.box58);
        visualBoxes[7][2] = findViewById(R.id.box59);
        visualBoxes[7][3] = findViewById(R.id.box60);
        visualBoxes[7][4] = findViewById(R.id.box61);
        visualBoxes[7][5] = findViewById(R.id.box62);
        visualBoxes[7][6] = findViewById(R.id.box63);
        visualBoxes[7][7] = findViewById(R.id.box64);
        //Pintamos el tablero
        paintBoard("#855E42", "#FFCB94" );
    }
    //Pinta el tablero
    public void paintBoard(String negras, String blancas){
        for (int i = 0; i < visualBoxes.length; i++) {
            int u;
            int o;
            if ((i%2)==0){
                u = 0;
                o = 1;
            }else{
                u = 1;
                o = 0;
            }
            for (; u < visualBoxes[i].length; u+=2) {
                //Oscuras
                visualBoxes[i][u].setBackgroundColor(Color.parseColor(negras));
            }
            for (; o < visualBoxes[i].length; o+=2) {
                //Blancas
                visualBoxes[i][o].setBackgroundColor(Color.parseColor(blancas));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        buildBoxes();

        Log.i("hola","funciona" + visualBoxes[0][1]);
        driver.buildBoxes();
        driver.buildPieces();
        updateImages();
    }

    //Write on the log the box clicked
    public void clickBoard(View view) {

        paintBoard("#855E42", "#FFCB94" );
        String tag = view.getTag().toString();
        //Con el tag obtengo las posiciones
        int x = Tools.tagGetX(tag);
        int y = Tools.tagGetY(tag);

        String pieceName = driver.getBoxPieceName(view.getTag().toString());
        //ESTOY AQUÍ : El movimiento pertenece a la parte lógica
        if (availablePositions.isEmpty()){

        }
        for (Box box: availablePositions) {
            if (box.getName().equals(tag)){

            }
        }

        Log.i("Info", " Has hecho click en la casilla: " + view.getTag()+ ", Que tiene un ["+pieceName+"]");
        Box[][] board = driver.getBoard();

        if (!driver.getBox(view.getTag().toString()).isEmpty()){
            //Obtengo la casilla con ello
            Box box = driver.board[x][y];
            Piece piece = box.getPiece();
            //Posiciones posibles
            ArrayList<Box> casillas = piece.getAvailableMoves(board, x, y);
            for (Box boxes : casillas) {
                Log.i("casillas[]",boxes.getName());
                ImageView imageView = Tools.getImageView(boxes,visualBoxes);
                imageView.setBackgroundColor(Color.WHITE);
            }
            availablePositions = casillas;
            //Repintamos el tablero
            updateImages();
            Log.i("I", "Acabo");
        }else{
            Log.i("Alerta: ", "No hay movimientos disponibles");
        }
    }

}