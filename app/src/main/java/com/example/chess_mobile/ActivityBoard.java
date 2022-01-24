package com.example.chess_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ActivityBoard extends AppCompatActivity {
    //Attributes
    ImageView[][] boxes = new ImageView[2][2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

    }


    public void clickBoard(View view) {
        Log.i("testboard", "hola click");

    }
}