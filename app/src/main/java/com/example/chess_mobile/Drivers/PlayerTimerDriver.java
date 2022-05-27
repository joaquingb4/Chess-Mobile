package com.example.chess_mobile.Drivers;

import static com.example.chess_mobile.Tools.TranslationTools.translateBooleanToColor;
import static com.example.chess_mobile.Tools.TranslationTools.translateColorToBoolean;

import android.util.Log;

import com.example.chess_mobile.ActivityBoard;
import com.example.chess_mobile.PlayerTimer;

public class PlayerTimerDriver implements Runnable {
    //_______________________      [ATTRIBUTES]      _____________________________

    private PlayerTimer whitePlayerTimer = null;
    private PlayerTimer blackPlayerTimer = null;
    private boolean timerRunningTurn = true;
    private boolean running = true;
    private ActivityBoard instance;
    //_______________________      [CONSTRUCTOR]      _____________________________

    public PlayerTimerDriver(ActivityBoard instance, String color,  int minuts, int seconds){
        this.instance = instance;
        this.timerRunningTurn = translateColorToBoolean(color);
        whitePlayerTimer = new PlayerTimer(minuts,seconds);
        blackPlayerTimer = new PlayerTimer(minuts, seconds);
    }
    //_______________________      [GETTERS&SETTERS]      _____________________________

    public PlayerTimer getWhitePlayerTimer() {
        return whitePlayerTimer;
    }

    public void setWhitePlayerTimer(PlayerTimer whitePlayerTimer) {
        this.whitePlayerTimer = whitePlayerTimer;
    }

    public PlayerTimer getBlackPlayerTimer() {
        return blackPlayerTimer;
    }

    public void setBlackPlayerTimer(PlayerTimer blackPlayerTimer) {
        this.blackPlayerTimer = blackPlayerTimer;
    }

    public boolean isTimerRunningTurn() {
        return timerRunningTurn;
    }

    public void setTimerRunningTurn(boolean timerRunningTurn) {
        this.timerRunningTurn = timerRunningTurn;
    }
    //_______________________      [FUNCTIONS]      _____________________________

    public void changeTurn(){
        if (timerRunningTurn)//Si está corriendo en tiempo de las blancas
            timerRunningTurn = false;
        else
            timerRunningTurn = true;
    }

    public void stop (){
        running=false;
    }

    @Override
    public void run() {
        while (running){
            try {
                Thread.sleep(1000);
                Log.i("INFO", "1sec");
                if (timerRunningTurn) {
                    whitePlayerTimer.substraction();
                    instance.runOnUiThread(new Runnable() {
                        public void run() {
                    instance.uptadeTime(whitePlayerTimer.getTime(), "white");
                        }});

                }else {
                    blackPlayerTimer.substraction();
                    instance.runOnUiThread(new Runnable() {
                        public void run() {
                            instance.uptadeTime(blackPlayerTimer.getTime(), "black");
                    }});
            }
                if (whitePlayerTimer.getTime().equals("00:00") || blackPlayerTimer.getTime().equals("00:00"))
                instance.runOnUiThread(new Runnable() {
                    public void run() {
                        instance.endGame("Victory by time", translateBooleanToColor(instance.getDriver().getTurn())+" losses by time ");
                    }});
                    //instance.endGame();
            }catch (Exception e){
                Log.i("INFO", "THREAD ERROR");
                Log.i("INFO",""+e);
            }
        }
    }
    //ERROR EN EL TIEMPO

}
