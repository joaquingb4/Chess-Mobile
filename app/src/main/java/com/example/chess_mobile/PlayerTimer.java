package com.example.chess_mobile;

import android.util.Log;

public class PlayerTimer implements Runnable {
    private int minuts;
    private int seconds;
    private Thread thread = new Thread(this);
    private boolean threadIsRunnig = false;

    private PlayerTimer(int minuts, int seconds){
        this.minuts = minuts;
        this.seconds = seconds;
    }
    private PlayerTimer(String time){
        if (time.charAt(0)=='0')
        this.minuts = minuts;
        this.seconds = seconds;
    }

    //Devuelve el tiempo actual del reloj
    public String getTime(){
        String min = convertTime(minuts);
        String sec = convertTime(seconds);

        return min+":"+sec;
    }

    //___________________________[Functions]______________________
    public void stop (){
        threadIsRunnig=false;
    }

    public String convertTime(int time){
        if (time < 10)
            return "0"+time;
        else
            return ""+time;
    }

    public void substraction(){
        if ((this.seconds - 1)==-1){
            if (minuts==0){
                this.seconds=0;
                threadIsRunnig=false;
            }else{
                minuts-=1;
                this.seconds=59;
            }
        }else{
            seconds-=1;
        }
    }

    public void start(){
        threadIsRunnig = true;
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (threadIsRunnig) {
                Thread.sleep(1000);
                substraction();
            }
        }catch (Exception e){
            Log.i("INFO", "THREAD ERROR");
        }
    }
}
