package com.example.chess_mobile;

public class Pawn extends Piece {
    //Attributes
    private String name = "Pawn";
    private int up =2;
    private int down =0;
    private int left =0;
    private int right =0;

    @Override
    public String getName() {
        return this.name;
    }

    /*@Override
    public int[] movility() {
        int[] instructions;
        instructions = new int[]{up,down,left,right};
        return instructions ;
    }

     */
}
