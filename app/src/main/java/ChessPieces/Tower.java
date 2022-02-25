package ChessPieces;

public class Tower extends Piece {
    //Attributes
    private final String name = "Tower";
    private String color;
    //Methods
    public Tower(String color){
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
