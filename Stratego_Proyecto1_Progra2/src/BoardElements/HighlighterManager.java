package BoardElements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.Border;

public class HighlighterManager {
    private JButton[][] tilesBTN;
    private List<JButton> highlightedButtons = new ArrayList<>();
    private Color electricBlue = new Color(0, 0, 255);
    private Color gold = new Color(255, 215, 0);

    public HighlighterManager(JButton[][] tilesBTN) {
        this.tilesBTN = tilesBTN;
    }

    public void highlightPossibleMoves(int row, int col, PieceType pieceType) {
        clearHighlightedMoves();
        System.out.println("Highlighting possible moves for piece at (" + row + ", " + col + ") of type " + pieceType);
        
        if (pieceType != null && pieceType.name().contains("RANGO2")) {
            highlightOrthogonalMoves(row, col);
        } else {
            highlightAdjacentMoves(row, col, pieceType);
        }
        
        for (JButton btn : highlightedButtons) {
            btn.repaint();
            btn.revalidate();
        }
    }

    private void highlightOrthogonalMoves(int row, int col) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] direction : directions) {
            int currentRow = row + direction[0];
            int currentCol = col + direction[1];
            while (isWithinBounds(currentRow, currentCol) && tilesBTN[currentRow][currentCol].getIcon() == null) {
                highlightButton(currentRow, currentCol);
                currentRow += direction[0];
                currentCol += direction[1];
            }
            // Highlight the button if it contains an opponent piece
            if (isWithinBounds(currentRow, currentCol) && tilesBTN[currentRow][currentCol].getIcon() != null) {
                highlightButton(currentRow, currentCol);
            }
        }
    }

    private void highlightAdjacentMoves(int row, int col, PieceType pieceType) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] direction : directions) {
            int targetRow = row + direction[0];
            int targetCol = col + direction[1];
            if (isWithinBounds(targetRow, targetCol)) {
                JButton targetButton = tilesBTN[targetRow][targetCol];
                ImageIcon icon = (ImageIcon) targetButton.getIcon();
                if (icon == null) {
                    highlightButton(targetRow, targetCol);
                } else {
                    PieceType targetType = getPieceType(targetButton);
                    if (targetType != null && !isSameTeam(tilesBTN[row][col], targetButton)) {
                        highlightButton(targetRow, targetCol);
                    }
                }
            }
        }
    }

    private void highlightButton(int row, int col) {
        System.out.println("Highlighting possible move at: (" + row + ", " + col + ")");
        tilesBTN[row][col].putClientProperty("originalBorder", tilesBTN[row][col].getBorder());
        tilesBTN[row][col].setContentAreaFilled(false);
        tilesBTN[row][col].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255, 100), 3));
        highlightedButtons.add(tilesBTN[row][col]);
    }

    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < tilesBTN.length && col >= 0 && col < tilesBTN[0].length;
    }

    public void clearHighlightedMoves() {
        for (JButton btn : highlightedButtons) {
            Border originalBorder = (Border) btn.getClientProperty("originalBorder");
            if (originalBorder != null) {
                btn.setBorder(originalBorder);
            } else {
                btn.setBorder(BorderFactory.createEmptyBorder());
            }
            btn.setBackground(null);
            btn.setContentAreaFilled(false);
            btn.setOpaque(false);
            btn.repaint();
            btn.revalidate();
        }
        highlightedButtons.clear();
        System.out.println("Cleared all highlighted moves");
    }

    public Color getElectricBlue() {
        return electricBlue;
    }

    public Color getGold() {
        return gold;
    }

    private PieceType getPieceType(JButton button) {
        ImageIcon icon = (ImageIcon) button.getIcon();
        if (icon == null || icon.getDescription() == null) return null;
        String description = icon.getDescription();
        System.out.println("Icon description: " + description);

        int underscoreIndex = description.indexOf('_');
        if (underscoreIndex == -1) {
            System.out.println("Unexpected icon description format: " + description);
            return null;
        }

        String pieceTypePart = description.substring(underscoreIndex + 1);
        try {
            PieceType pieceType = PieceType.valueOf(pieceTypePart);
            System.out.println("Determined piece type: " + pieceType);
            return pieceType;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid piece type in description: " + pieceTypePart);
            return null;
        }
    }

    private boolean isSameTeam(JButton button1, JButton button2) {
        ImageIcon icon1 = (ImageIcon) button1.getIcon();
        ImageIcon icon2 = (ImageIcon) button2.getIcon();
        if (icon1 == null || icon2 == null) return false;
        return (icon1.getDescription().startsWith("good") && icon2.getDescription().startsWith("good"))
                || (icon1.getDescription().startsWith("bad") && icon2.getDescription().startsWith("bad"));
    }
}
