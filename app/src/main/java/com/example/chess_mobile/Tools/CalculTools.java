package com.example.chess_mobile.Tools;

public class CalculTools {
    public static int[] getXYOfANumber(int position){
        int x = position / 10;
        int y = position % 10;
        return new int[]{x, y};
    }
    public  static int getFirstDigitOfANumber(int position){
        return position / 10;
    }
    public  static int getSecondDigitOfANumber(int position){
        return position % 10;
    }
    public static boolean getContraryBoolean(boolean state){
        if (state){
            return false;
        }
        return true;
    }
}
