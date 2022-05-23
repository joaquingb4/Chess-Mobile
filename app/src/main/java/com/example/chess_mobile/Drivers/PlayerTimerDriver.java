package com.example.chess_mobile.Drivers;

import com.example.chess_mobile.ActivityBoard;
import com.example.chess_mobile.PlayerTimer;

public class PlayerTimerDriver {
    private PlayerTimer whitePlayerTimer = null;
    private PlayerTimer blackPlayerTimer = null;
    private boolean running = true;

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

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public PlayerTimerDriver(ActivityBoard instance, int minuts, int seconds){
        whitePlayerTimer = new PlayerTimer(minuts,seconds, instance);
        blackPlayerTimer = new PlayerTimer(minuts, seconds, instance);
    }

    public void changeTurn(){
        if (running)//Si está corriendo en tiempo de las blancas
            running = false;
        else
            running = true;
        updateStatus();
    }
    public void end(){
        whitePlayerTimer.stop();
        blackPlayerTimer.stop();
    }
    public void start(String color){

        if (color.equals("white"))
            running=true;
        else
            running=false;
        updateStatus();
    }

    public void updateStatus(){
        if (running) {
            whitePlayerTimer.start();
            blackPlayerTimer.stop();
        }else {
            whitePlayerTimer.stop();
            blackPlayerTimer.start();
        }
    }
    //ERROR EN EL TIEMPO

}
