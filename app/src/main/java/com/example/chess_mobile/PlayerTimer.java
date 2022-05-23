package com.example.chess_mobile;

import android.util.Log;

public class PlayerTimer implements Runnable {
    private int minuts;
    private int seconds;
    private Thread thread = new Thread(this);
    private boolean threadIsRunnig = false;
    private ActivityBoard instance;


    public PlayerTimer(int minuts, int seconds, ActivityBoard instance){
        this.instance = instance;
        this.minuts = minuts;
        this.seconds = seconds;
    }
    /*
    public PlayerTimer(String time, ActivityBoard instance ){
        this.instance = instance;
        if (time.charAt(0)=='0')
        this.minuts = minuts;
        this.seconds = seconds;
    }
    */


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
                Thread.sleep(500);
                substraction();
                instance.uptadeTime(convertTime(minuts)+":"+convertTime(seconds));

            }
        }catch (Exception e){
            Log.i("INFO", "THREAD ERROR");
        }
    }
}
