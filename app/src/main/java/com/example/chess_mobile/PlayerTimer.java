package com.example.chess_mobile;

public class PlayerTimer  {
    private int minuts;
    private int seconds;
    private String time;
    private boolean threadIsRunnig = false;


    public PlayerTimer(int minuts, int seconds ){
        this.minuts = minuts;
        this.seconds = seconds;
    }

    //Devuelve el tiempo actual del reloj
    public String getTime(){
        return time;
    }
    //___________________________[Functions]_________________________

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
        setTime(minuts, seconds);

    }
    public void setTime(int min, int sec){
        time = convertTime(min)+":"+convertTime(sec);
    }

}