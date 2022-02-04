package com.example.chess_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ActivityBoard extends AppCompatActivity {
    //Attributes
    int a = 0;
    int b = 1;
    int c = 2;

    ImageView[][] boxes = new ImageView[8][8];
    Driver driver = new Driver();

    //Actualiza la parte visual de las fichas
    public void updateImages(){
        for (int i = 0; i <boxes.length ; i++) {
            for (int u = 0; u < boxes[i].length; u++) {
                Log.i("Miramos  ", u+ " :: " + i);
                if (driver.board[u][i].getPiece()==null) {
                    boxes[u][i].setImageDrawable(null);
                } else {
                    boxes[u][i].setImageDrawable(getImage(driver.getDataBox(u, i)));
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

    public void buildBoxes(){
        boxes[0][0] = findViewById(R.id.box1);
        boxes[1][0] = findViewById(R.id.box2);
        boxes[2][0] = findViewById(R.id.box3);
        boxes[3][0] = findViewById(R.id.box4);
        boxes[4][0] = findViewById(R.id.box5);
        boxes[5][0] = findViewById(R.id.box6);
        boxes[6][0] = findViewById(R.id.box7);
        boxes[7][0] = findViewById(R.id.box8);
        //boxes[3][0] = findViewById(R.id.box);
        boxes[0][1] = findViewById(R.id.box9);
        boxes[1][1] = findViewById(R.id.box10);
        boxes[2][1] = findViewById(R.id.box11);
        boxes[3][1] = findViewById(R.id.box12);
        boxes[4][1] = findViewById(R.id.box13);
        boxes[5][1] = findViewById(R.id.box14);
        boxes[6][1] = findViewById(R.id.box15);
        boxes[7][1] = findViewById(R.id.box16);

        boxes[0][2] = findViewById(R.id.box17);
        boxes[1][2] = findViewById(R.id.box18);
        boxes[2][2] = findViewById(R.id.box19);
        boxes[3][2] = findViewById(R.id.box20);
        boxes[4][2] = findViewById(R.id.box21);
        boxes[5][2] = findViewById(R.id.box22);
        boxes[6][2] = findViewById(R.id.box23);
        boxes[7][2] = findViewById(R.id.box24);

        boxes[0][3] = findViewById(R.id.box25);
        boxes[1][3] = findViewById(R.id.box26);
        boxes[2][3] = findViewById(R.id.box27);
        boxes[3][3] = findViewById(R.id.box28);
        boxes[4][3] = findViewById(R.id.box29);
        boxes[5][3] = findViewById(R.id.box30);
        boxes[6][3] = findViewById(R.id.box31);
        boxes[7][3] = findViewById(R.id.box32);

        boxes[0][4] = findViewById(R.id.box33);
        boxes[1][4] = findViewById(R.id.box34);
        boxes[2][4] = findViewById(R.id.box35);
        boxes[3][4] = findViewById(R.id.box36);
        boxes[4][4] = findViewById(R.id.box37);
        boxes[5][4] = findViewById(R.id.box38);
        boxes[6][4] = findViewById(R.id.box39);
        boxes[7][4] = findViewById(R.id.box40);

        boxes[0][5] = findViewById(R.id.box41);
        boxes[1][5] = findViewById(R.id.box42);
        boxes[2][5] = findViewById(R.id.box43);
        boxes[3][5] = findViewById(R.id.box44);
        boxes[4][5] = findViewById(R.id.box45);
        boxes[5][5] = findViewById(R.id.box46);
        boxes[6][5] = findViewById(R.id.box47);
        boxes[7][5] = findViewById(R.id.box48);

        boxes[0][6] = findViewById(R.id.box49);
        boxes[1][6] = findViewById(R.id.box50);
        boxes[2][6] = findViewById(R.id.box51);
        boxes[3][6] = findViewById(R.id.box52);
        boxes[4][6] = findViewById(R.id.box53);
        boxes[5][6] = findViewById(R.id.box54);
        boxes[6][6] = findViewById(R.id.box55);
        boxes[7][6] = findViewById(R.id.box56);

        boxes[0][7] = findViewById(R.id.box57);
        boxes[1][7] = findViewById(R.id.box58);
        boxes[2][7] = findViewById(R.id.box59);
        boxes[3][7] = findViewById(R.id.box60);
        boxes[4][7] = findViewById(R.id.box61);
        boxes[5][7] = findViewById(R.id.box62);
        boxes[6][7] = findViewById(R.id.box63);
        boxes[7][7] = findViewById(R.id.box64);
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
        Log.i("testboard", "Has hecho click en la casilla: " + view.getTag()+ ", Que tiene un " + driver.getDataBox(view.getTag().toString()));
    }

}