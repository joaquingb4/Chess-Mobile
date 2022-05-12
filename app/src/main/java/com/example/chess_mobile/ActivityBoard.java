package com.example.chess_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.chess_mobile.Tools.BoardTools;
import com.example.chess_mobile.Tools.CalculTools;
import com.example.chess_mobile.Tools.TranslationTools;

public class ActivityBoard extends AppCompatActivity {
    //_______________________      [ATTRIBUTES]      _____________________________
    private String colorBlackBoxes = "#855E42";
    private String colorWhiteBoxes = "#FFCB94";
    private String colorMovements = "#33D17A";
    private String colorCheckKing = "#002147";
    private ImageView[][] visualBoxes = new ImageView[8][8];
    private  ImageView[] pawPromotionOptionsArray = new ImageView[3];
    private Driver driver = new Driver();

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
        pawPromotionOptionsArray[0] = findViewById(R.id.PromotionOptionView1);
        pawPromotionOptionsArray[1] = findViewById(R.id.PromotionOptionView2);
        pawPromotionOptionsArray[2] = findViewById(R.id.PromotionOptionView3);
        for (int i = 0; i < pawPromotionOptionsArray.length; i++) {
            pawPromotionOptionsArray[i].setVisibility(View.INVISIBLE);
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
    //Pintar la casilla del rey
    /*
    public void paintKingState(String color){
        View viewKing = Tools.getImageView(driver.getKing(color), visualBoxes);
        if (driver.kingISInCheck(color)){
            viewKing.setBackgroundColor(Color.parseColor(colorCheckKing));
        }
    }

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        buildBoxes();

        driver.getLogicBoard().buildBoxes();
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
            showPromotionOptions(true);
            setColorPromotionOption(box.getPiece().getColor());
        }else {
            showPromotionOptions(false);
        }
    }

    private void setColorPromotionOption(String color) {
        for (int i = 0; i < pawPromotionOptionsArray.length; i++) {
            if (color.equals("white")) {
                pawPromotionOptionsArray[i].setBackgroundColor(Color.parseColor(colorWhiteBoxes));
            }else {
                pawPromotionOptionsArray[i].setBackgroundColor(Color.parseColor(colorBlackBoxes));
            }
        }
    }

    private void showPromotionOptions(boolean option) {
        int code;
        if (option)
            code = 0;
        else
            code = 4;

        for (int i = 0; i < pawPromotionOptionsArray.length; i++) {
            pawPromotionOptionsArray[i].setVisibility(code);
        }
    }

}