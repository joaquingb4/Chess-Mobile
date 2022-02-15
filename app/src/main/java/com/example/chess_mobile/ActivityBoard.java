package com.example.chess_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ActivityBoard extends AppCompatActivity {
    //Attributes
    int a = 0;
    int b = 1;
    int c = 2;
    int d = 3;
    int e = 4;
    int f = 5;
    int g = 6;
    int h = 7;
    char[] letters = new char[]{'a', 'b', 'c','d','e','f','g','h'};

    ImageView[][] boxes = new ImageView[8][8];
    Driver driver = new Driver();

    //Actualiza la parte visual de las fichas
    public void updateImages(){
        for (int i = 0; i <boxes.length ; i++) {
            for (int u = 0; u < boxes[i].length; u++) {
                Log.i("Miramos  ", u+ " :: " + i);
                if (driver.board[u][i].getPiece()==null) {
                    boxes[i][u].setImageDrawable(null);
                } else {
                    boxes[i][u].setImageDrawable(getImage(driver.getBoxPieceName(u, i)));
                }
            }
        }
            //a 1
        /*
        boxes[0][0].setImageDrawable(getDrawable(R.drawable.t));
        boxes[1][0].setImageDrawable(getDrawable(R.drawable.t));
        boxes[2][0].setImageDrawable(getDrawable(R.drawable.t));
         */
    }

    public Drawable getImage(String pieceName){
        switch (pieceName){
            case ("Tower"):
                return getDrawable(R.drawable.t);
            case ("Pawn"):
                return getDrawable(R.drawable.p);
        }
        return null;
    }
    //Crea la parte visual de las casillas
    public void buildBoxes(){
        boxes[a][0] = findViewById(R.id.box1);
        boxes[a][1] = findViewById(R.id.box2);
        boxes[a][2] = findViewById(R.id.box3);
        boxes[a][3] = findViewById(R.id.box4);
        boxes[a][4] = findViewById(R.id.box5);
        boxes[a][5] = findViewById(R.id.box6);
        boxes[a][6] = findViewById(R.id.box7);
        boxes[a][7] = findViewById(R.id.box8);
        //boxes[3][0] = findViewById(R.id.box);
        boxes[b][0] = findViewById(R.id.box9);
        boxes[b][1] = findViewById(R.id.box10);
        boxes[b][2] = findViewById(R.id.box11);
        boxes[b][3] = findViewById(R.id.box12);
        boxes[b][4] = findViewById(R.id.box13);
        boxes[b][5] = findViewById(R.id.box14);
        boxes[b][6] = findViewById(R.id.box15);
        boxes[b][7] = findViewById(R.id.box16);

        boxes[c][0] = findViewById(R.id.box17);
        boxes[c][1] = findViewById(R.id.box18);
        boxes[c][2] = findViewById(R.id.box19);
        boxes[c][3] = findViewById(R.id.box20);
        boxes[c][4] = findViewById(R.id.box21);
        boxes[c][5] = findViewById(R.id.box22);
        boxes[c][6] = findViewById(R.id.box23);
        boxes[c][7] = findViewById(R.id.box24);

        boxes[d][0] = findViewById(R.id.box25);
        boxes[d][1] = findViewById(R.id.box26);
        boxes[d][2] = findViewById(R.id.box27);
        boxes[d][3] = findViewById(R.id.box28);
        boxes[d][4] = findViewById(R.id.box29);
        boxes[d][5] = findViewById(R.id.box30);
        boxes[d][6] = findViewById(R.id.box31);
        boxes[d][7] = findViewById(R.id.box32);

        boxes[e][0] = findViewById(R.id.box33);
        boxes[e][1] = findViewById(R.id.box34);
        boxes[e][2] = findViewById(R.id.box35);
        boxes[e][3] = findViewById(R.id.box36);
        boxes[e][4] = findViewById(R.id.box37);
        boxes[e][5] = findViewById(R.id.box38);
        boxes[e][6] = findViewById(R.id.box39);
        boxes[e][7] = findViewById(R.id.box40);

        boxes[f][0] = findViewById(R.id.box41);
        boxes[f][1] = findViewById(R.id.box42);
        boxes[f][2] = findViewById(R.id.box43);
        boxes[f][3] = findViewById(R.id.box44);
        boxes[f][4] = findViewById(R.id.box45);
        boxes[f][5] = findViewById(R.id.box46);
        boxes[f][6] = findViewById(R.id.box47);
        boxes[f][7] = findViewById(R.id.box48);

        boxes[g][0] = findViewById(R.id.box49);
        boxes[g][1] = findViewById(R.id.box50);
        boxes[g][2] = findViewById(R.id.box51);
        boxes[g][3] = findViewById(R.id.box52);
        boxes[g][4] = findViewById(R.id.box53);
        boxes[g][5] = findViewById(R.id.box54);
        boxes[g][6] = findViewById(R.id.box55);
        boxes[g][7] = findViewById(R.id.box56);

        boxes[h][0] = findViewById(R.id.box57);
        boxes[h][1] = findViewById(R.id.box58);
        boxes[h][2] = findViewById(R.id.box59);
        boxes[h][3] = findViewById(R.id.box60);
        boxes[h][4] = findViewById(R.id.box61);
        boxes[h][5] = findViewById(R.id.box62);
        boxes[h][6] = findViewById(R.id.box63);
        boxes[h][7] = findViewById(R.id.box64);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
       buildBoxes();


        Log.i("hola","funciona" + boxes[0][1]);
        driver.buildBoxes();
        driver.buildPieces();
        updateImages();
    }

    //Write on the log the box clicked
    public void clickBoard(View view) {
        Log.i("testboard", "Has hecho click en la casilla: " + view.getTag()+ ", Que tiene un " + driver.getBoxPieceName(view.getTag().toString()));
        /*
        if (!driver.getBox(view.getTag().toString()).haveAPiece()){

        }else{
            int[] postion = Tools.withNotation(view.getTag().toString());
            Box[] positions = driver.canMoveto(postion[0],postion[1]);
            for (int i = 0; i < positions.length ; i++) {
                Log.i("Posicion" + (i), "" + positions[i].getName());
            }
        }

         */
    }
}