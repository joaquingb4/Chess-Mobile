package ChessPieces;

public abstract class Piece {
    //Attributes
    private String name;
    private String color;

    protected Piece() {
    }
    //Methods
    public abstract String getName();

    public abstract String getColor();
    public abstract String setColor();

}
