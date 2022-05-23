package com.example.chess_mobile;

import android.util.Log;

public class PlayerTimer  {
    private int minuts;
    private int seconds;
    private boolean threadIsRunnig = false;


    public PlayerTimer(int minuts, int seconds ){
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

    /*
    @Override
    public void run() {
        try {
            while (threadIsRunnig) {
                Thread.sleep(1000);
                substraction();
                //instance.uptadeTime(convertTime(minuts)+":"+convertTime(seconds));
            }
        }catch (Exception e){
            Log.i("INFO", "THREAD ERROR");
        }
    }

     */
}