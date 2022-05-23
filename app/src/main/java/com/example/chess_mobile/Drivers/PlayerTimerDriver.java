package com.example.chess_mobile.Drivers;

import android.util.Log;

import com.example.chess_mobile.ActivityBoard;
import com.example.chess_mobile.PlayerTimer;

public class PlayerTimerDriver implements Runnable {
    private PlayerTimer whitePlayerTimer = null;
    private PlayerTimer blackPlayerTimer = null;
    private boolean timerRunningTurn = true;
    private boolean running = false;
    private ActivityBoard instance;
    private Thread thread = new Thread();

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

    public PlayerTimerDriver(ActivityBoard instance, int minuts, int seconds){
        this.instance = instance;
        whitePlayerTimer = new PlayerTimer(minuts,seconds);
        blackPlayerTimer = new PlayerTimer(minuts, seconds);
    }

    public void changeTurn(){
        if (timerRunningTurn)//Si está corriendo en tiempo de las blancas
            timerRunningTurn = false;
        else
            timerRunningTurn = true;
    }
    /*
    public void end(){
        whitePlayerTimer.stop();
        blackPlayerTimer.stop();
    }

     */
    public void start(String color){
        running = true;
        if (color.equals("white"))
            timerRunningTurn =true;
        else
            timerRunningTurn =false;
        run();

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
                    instance.uptadeTime(whitePlayerTimer.getTime(), "white");
                }else {
                    blackPlayerTimer.substraction();
                    instance.uptadeTime(blackPlayerTimer.getTime(), "black");
                }
            }catch (Exception e){
                Log.i("INFO", "THREAD ERROR");
            }
        }
    }
    //ERROR EN EL TIEMPO

}
