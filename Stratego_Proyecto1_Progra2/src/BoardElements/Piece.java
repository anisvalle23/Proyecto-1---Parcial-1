package BoardElements;

import javax.swing.ImageIcon;

public class Piece {
    private PieceType type;
    private ImageIcon icon;

    public Piece(PieceType type, ImageIcon icon) {
        this.type = type;
        this.icon = icon;
    }

    public PieceType getType() {
        return type;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
