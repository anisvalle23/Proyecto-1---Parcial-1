package BoardElements;

import javax.swing.JButton;

public class ZoneManager {

    public boolean isOrthogonallyAdjacent(int row1, int col1, int row2, int col2) {
        return (Math.abs(row1 - row2) == 1 && col1 == col2) || (Math.abs(col1 - col2) == 1 && row1 == row2);
    }
    

    public boolean isProhibitedZone(int row, int col) {
        return (row == 4 && (col == 2 || col == 3 || col == 6 || col == 7)) || 
               (row == 5 && (col == 2 || col == 3 || col == 6 || col == 7));
    }

public boolean isPathClear(JButton[][] tilesBTN, int startRow, int startCol, int endRow, int endCol, PieceType pieceType) {
    if (pieceType == null) {
        System.out.println("PieceType is null, cannot check path");
        return false;
    }

    System.out.println("Checking path clear from (" + startRow + ", " + startCol + ") to (" + endRow + ", " + endCol + ") for piece type: " + pieceType);

    if (pieceType.name().contains("RANGO2")) {
        // Permitir movimiento ortogonal extendido para RANGO2
        if (startRow != endRow && startCol != endCol) {
            return false;
        }

        // Revisar si el camino está despejado
        if (startRow == endRow) {
            int colStep = (startCol < endCol) ? 1 : -1;
            for (int col = startCol + colStep; col != endCol; col += colStep) {
                if (tilesBTN[startRow][col].getIcon() != null || isProhibitedZone(startRow, col)) {
                    return false;
                }
            }
        } else if (startCol == endCol) {
            int rowStep = (startRow < endRow) ? 1 : -1;
            for (int row = startRow + rowStep; row != endRow; row += rowStep) {
                if (tilesBTN[row][startCol].getIcon() != null || isProhibitedZone(row, startCol)) {
                    return false;
                }
            }
        }

        // Permitir el movimiento a una casilla vacía
        if (tilesBTN[endRow][endCol].getIcon() == null && !isProhibitedZone(endRow, endCol)) {
            return true;
        }

        // Permitir el ataque solo a una casilla adyacente ortogonalmente
        return isOrthogonallyAdjacent(startRow, startCol, endRow, endCol);
    } else {
        // Permitir ataques en movimiento ortogonal adyacente para otras piezas
        return isOrthogonallyAdjacent(startRow, startCol, endRow, endCol);
    }
}




}
