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

    ImageView[][] boxes = new ImageView[24][24];
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

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