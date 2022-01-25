package com.example.chess_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ActivityBoard extends AppCompatActivity {
    //Attributes
    ImageView[][] boxes = new ImageView[3][3];
    Driver driver = new Driver();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        boxes[0][0] = findViewById(R.id.box1);
        boxes[1][0] = findViewById(R.id.box2);
        boxes[2][0] = findViewById(R.id.box3);
        boxes[0][1] = findViewById(R.id.box4);
        boxes[1][1] = findViewById(R.id.box5);
        boxes[2][1] = findViewById(R.id.box6);
        boxes[0][2] = findViewById(R.id.box7);
        boxes[1][2] = findViewById(R.id.box8);
        boxes[2][2] = findViewById(R.id.box9);
        Log.i("hola","funciona" + boxes[0][1]);
        driver.buildPieces();
    }


    //Write on the log the box clicked
    public void clickBoard(View view) {
        Log.i("testboard", "Has hecho click en la casilla: " + view.getTag()+ ", Que tiene un " + driver. );
    }

}