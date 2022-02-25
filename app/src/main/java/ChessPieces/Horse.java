package ChessPieces;

public class Horse extends Piece {
    //Attributes
    private final String name = "Horse";
    private String color;
    //Methods
    public Horse(String color){
        setColor(color);
    }
    private void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String setColor() {
        return this.color;
    }
}
