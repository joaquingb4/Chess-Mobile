package com.example.chess_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chess_mobile.Tools.BoardTools;

public class ActivityBoard extends AppCompatActivity {
    //_______________________      [ATTRIBUTES]      _____________________________
    private String colorBlackBoxes = "#737373";
    private String colorWhiteBoxes = "#ffffff";
    private String colorMovements = "#33D17A";
    private String colorCheckKing = "#002147";
    private String pawnPromotionOptionsColor = "#F5F5DC";
    private ImageView[][] visualBoxes = new ImageView[8][8];
    private ImageView[] pawPromotionOptionsArrayWhite = new ImageView[3];
    private ImageView[] pawPromotionOptionsArrayBlack = new ImageView[2];
    private TextView whiteTimer = null;
    private TextView blackTimer = null;
    private Driver driver;
    private PlayerTimer whitePlayerTimer = null;
    private PlayerTimer blackPlayerTimer = null;

    //Actualiza la parte visual de las piezas   //POSIBLE FALLO
    public void updateImages(){
        for (int i = 0; i < visualBoxes.length ; i++) {
            for (int u = 0; u < visualBoxes[i].length; u++) {
                Box box = driver.getLogicBoard().getBox(i, u);
                visualBoxes[i][u].setImageDrawable(getImage(box));
                if (box.getCapturable()){
                    BoardTools.getImageView(box, visualBoxes).setBackgroundColor(Color.parseColor(colorMovements));
                }else {

                }
            }
        }
    }

    public Drawable getImage(Box box){
        if (box.isEmpty()){ //Si está vacío
            if (driver.getPotentialMovesList().contains(box)){
                return getDrawable(R.drawable.punto);
            }else{
                return null;
            }
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
        whiteTimer = findViewById(R.id.txtWhiteTimer);
        blackTimer = findViewById(R.id.txtBlackTimer);
        whitePlayerTimer = new PlayerTimer(30,00, this);
        blackPlayerTimer = new PlayerTimer(30, 00, this);
        pawPromotionOptionsArrayWhite[0] = findViewById(R.id.whiteOptionsPromotion1);
        pawPromotionOptionsArrayWhite[1] = findViewById(R.id.whiteOptionsPromotion2);
        pawPromotionOptionsArrayWhite[2] = findViewById(R.id.whiteOptionsPromotion3);

        pawPromotionOptionsArrayBlack[0] = findViewById(R.id.blackOptionsPromotion1);
        pawPromotionOptionsArrayBlack[1] = findViewById(R.id.blackOptionsPromotion2);
        for (int i = 0; i < pawPromotionOptionsArrayWhite.length; i++) {
            pawPromotionOptionsArrayWhite[i].setVisibility(View.INVISIBLE);
            pawPromotionOptionsArrayWhite[i].setBackgroundColor(Color.parseColor(pawnPromotionOptionsColor));
        }
        for (int i = 0; i < pawPromotionOptionsArrayBlack.length; i++) {
            pawPromotionOptionsArrayBlack[i].setVisibility(View.INVISIBLE);
            pawPromotionOptionsArrayBlack[i].setBackgroundColor(Color.parseColor(pawnPromotionOptionsColor));
        }
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
        paintBoard();
        whitePlayerTimer.start();
        blackPlayerTimer.start();
    }
    //AQUÍ
    public void getBoxColor(Box box){

    }

    //Pinta el tablero
    public void paintBoard(){
        for (int x = 0; x < driver.getBoard().length; x++){
            for (int y = 0; y < driver.getBoard()[x].length; y++) {
                Box logicBox = driver.getLogicBoard().getBox(x, y);
                View visualBox = visualBoxes[x][y];
                if (logicBox.getCapturable()){
                    if (logicBox.equals(driver.getLogicBoard().getKing("black")) || logicBox.equals(driver.getLogicBoard().getKing("white"))){
                        visualBoxes[x][y].setBackgroundColor(Color.parseColor(colorCheckKing));
                    }else{
                        visualBox.setBackgroundColor(Color.parseColor(colorMovements));
                    }
                }else {
                    if (logicBox.getColor()){
                        visualBox.setBackgroundColor(Color.parseColor(colorBlackBoxes));
                    }else {
                        visualBox.setBackgroundColor(Color.parseColor(colorWhiteBoxes));
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        driver = new Driver();
        buildBoxes();
        //Hace que el tablero lógico cree las casillas
        driver.buildPieces();
        updateImages();
    }

    //Write on the log the box clicked
    //QUIERO HACER UN REFACTOR AQUÍ
    public void clickBoard(View view) throws CloneNotSupportedException {
        updateImages();
        paintBoard();//PARA LIMPIAR LAS INDICACIONES
        Box clickedBox = driver.getBox(view.getTag().toString());
        Box[][] board = driver.getBoard();
        //Mostramos datos de la casilla
        //Sí el cache no esta vacío//AQUÍ
        driver.clickDesition(clickedBox);
        updateImages();
        paintBoard();
        checkEvents();
    }
    //Comprueba si se ha ejecutado un evento y lo realiza
    private void checkEvents() {
        Box box  = driver.getLogicBoard().pawnPromotion();
        Log.i("INFO", ""+ (box!=null));
        if (box != null){
            showPromotionOptions(box.getPiece().getColor());
        }else {
            hidePromotionOptions();
        }
    }
    //Oculta las opciones de promoción del peón
    public void hidePromotionOptions(){
        for (int i = 0; i < pawPromotionOptionsArrayBlack.length; i++) {
            pawPromotionOptionsArrayBlack[i].setVisibility(View.INVISIBLE);
        }
    }
    //Muestra las opciones de promoción del peón
    private void showPromotionOptions(String color) {
        int code;
        if (color.equals("white")) {
            for (int i = 0; i < pawPromotionOptionsArrayWhite.length; i++) {
                pawPromotionOptionsArrayWhite[i].setVisibility(View.VISIBLE);
            }
        }else {
            for (int i = 0; i < pawPromotionOptionsArrayBlack.length; i++) {
                pawPromotionOptionsArrayBlack[i].setVisibility(View.VISIBLE);
            }
        }
    }
    //Quita tiempo a los dos contadores
    public void uptadeTime(String time){
        whiteTimer.setText(time);
        blackTimer.setText(time);
       // whitePlayerTimer = new PlayerTimer(whiteTimer.getText().toString());
       // blackPlayerTimer = new PlayerTimer(blackTimer.getText().toString());
       // int whiteTime = Integer.parseInt(this.whiteTimer.getText().toString())-1;
        //int blacktime = Integer.parseInt(this.blackTimer.getText().toString())-1;

        //whiteTimer.setText(whiteTime);
        //blackTimer.setText(blacktime);
    }
}