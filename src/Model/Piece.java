package Model;


public class Piece {

    private PieceColor.PieceColorEnum color;

    public Piece(PieceColor.PieceColorEnum color) {
        this.color = color;
    }
    public PieceColor.PieceColorEnum getColor() {
        return color;
    }
    
    @Override
    public String toString() {
        return color.name();
    }
}
