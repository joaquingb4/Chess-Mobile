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
    int d = 3;
    int e = 4;
    int f = 5;
    int g = 6;
    int h = 7;
    char[] letters = new char[]{'a', 'b', 'c','d','e','f','g','h'};

    ImageView[][] visualBoxes = new ImageView[8][8];
    Driver driver = new Driver();

    //Actualiza la parte visual de las piezas
    public void updateImages(){
        for (int i = 0; i < visualBoxes.length ; i++) {
            for (int u = 0; u < visualBoxes[i].length; u++) {
                Log.i("Miramos  ", u+ " :: " + i);
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
                    return getDrawable(R.drawable.t);
                case ("Pawn"):
                    return getDrawable(R.drawable.p);
            }
        }
        return null;
    }
    //Crea la parte visual de las casillas
    public void buildBoxes(){
        visualBoxes[a][0] = findViewById(R.id.box1);
        visualBoxes[a][1] = findViewById(R.id.box2);
        visualBoxes[a][2] = findViewById(R.id.box3);
        visualBoxes[a][3] = findViewById(R.id.box4);
        visualBoxes[a][4] = findViewById(R.id.box5);
        visualBoxes[a][5] = findViewById(R.id.box6);
        visualBoxes[a][6] = findViewById(R.id.box7);
        visualBoxes[a][7] = findViewById(R.id.box8);
        //boxes[3][0] = findViewById(R.id.box);
        visualBoxes[b][0] = findViewById(R.id.box9);
        visualBoxes[b][1] = findViewById(R.id.box10);
        visualBoxes[b][2] = findViewById(R.id.box11);
        visualBoxes[b][3] = findViewById(R.id.box12);
        visualBoxes[b][4] = findViewById(R.id.box13);
        visualBoxes[b][5] = findViewById(R.id.box14);
        visualBoxes[b][6] = findViewById(R.id.box15);
        visualBoxes[b][7] = findViewById(R.id.box16);

        visualBoxes[c][0] = findViewById(R.id.box17);
        visualBoxes[c][1] = findViewById(R.id.box18);
        visualBoxes[c][2] = findViewById(R.id.box19);
        visualBoxes[c][3] = findViewById(R.id.box20);
        visualBoxes[c][4] = findViewById(R.id.box21);
        visualBoxes[c][5] = findViewById(R.id.box22);
        visualBoxes[c][6] = findViewById(R.id.box23);
        visualBoxes[c][7] = findViewById(R.id.box24);

        visualBoxes[d][0] = findViewById(R.id.box25);
        visualBoxes[d][1] = findViewById(R.id.box26);
        visualBoxes[d][2] = findViewById(R.id.box27);
        visualBoxes[d][3] = findViewById(R.id.box28);
        visualBoxes[d][4] = findViewById(R.id.box29);
        visualBoxes[d][5] = findViewById(R.id.box30);
        visualBoxes[d][6] = findViewById(R.id.box31);
        visualBoxes[d][7] = findViewById(R.id.box32);

        visualBoxes[e][0] = findViewById(R.id.box33);
        visualBoxes[e][1] = findViewById(R.id.box34);
        visualBoxes[e][2] = findViewById(R.id.box35);
        visualBoxes[e][3] = findViewById(R.id.box36);
        visualBoxes[e][4] = findViewById(R.id.box37);
        visualBoxes[e][5] = findViewById(R.id.box38);
        visualBoxes[e][6] = findViewById(R.id.box39);
        visualBoxes[e][7] = findViewById(R.id.box40);

        visualBoxes[f][0] = findViewById(R.id.box41);
        visualBoxes[f][1] = findViewById(R.id.box42);
        visualBoxes[f][2] = findViewById(R.id.box43);
        visualBoxes[f][3] = findViewById(R.id.box44);
        visualBoxes[f][4] = findViewById(R.id.box45);
        visualBoxes[f][5] = findViewById(R.id.box46);
        visualBoxes[f][6] = findViewById(R.id.box47);
        visualBoxes[f][7] = findViewById(R.id.box48);

        visualBoxes[g][0] = findViewById(R.id.box49);
        visualBoxes[g][1] = findViewById(R.id.box50);
        visualBoxes[g][2] = findViewById(R.id.box51);
        visualBoxes[g][3] = findViewById(R.id.box52);
        visualBoxes[g][4] = findViewById(R.id.box53);
        visualBoxes[g][5] = findViewById(R.id.box54);
        visualBoxes[g][6] = findViewById(R.id.box55);
        visualBoxes[g][7] = findViewById(R.id.box56);

        visualBoxes[h][0] = findViewById(R.id.box57);
        visualBoxes[h][1] = findViewById(R.id.box58);
        visualBoxes[h][2] = findViewById(R.id.box59);
        visualBoxes[h][3] = findViewById(R.id.box60);
        visualBoxes[h][4] = findViewById(R.id.box61);
        visualBoxes[h][5] = findViewById(R.id.box62);
        visualBoxes[h][6] = findViewById(R.id.box63);
        visualBoxes[h][7] = findViewById(R.id.box64);
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
        String tag = driver.getBoxPieceName(view.getTag().toString());
        Log.i("testboard", "Has hecho click en la casilla: " + view.getTag()+ ", Que tiene un "+driver.getBoxPieceName(tag));

        if (driver.getBox(view.getTag().toString()).isEmpty()){
            //Con el tag obtengo las posiciones
            int[] postions = driver.getBoxPosition(tag);
            //Obtengo la casilla con ello
            Box box = driver.getBox(postions);
            //Calculo las posiciones posibles
            Box[] positions = driver.canMoveTo(box);
            //Las imprimo por el log
            for (int i = 0; i < positions.length ; i++) {
                Log.i("Posición disponible ["+ i + "]", positions[i].getName());
            }
            //Si resulta que no hay salta un mensaje
        }else{
            Log.i("Alerta: ", "No hay movimientos disponibles");
        }
    }
}