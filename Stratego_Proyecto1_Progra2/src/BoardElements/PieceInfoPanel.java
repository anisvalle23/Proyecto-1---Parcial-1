package BoardElements;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class PieceInfoPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel rankLabel;
    private JTextArea abilitiesText;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BORDER_COLOR = Color.WHITE;

    public PieceInfoPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 200)); // Ajustar el tamaño según sea necesario

        // Panel para la información textual
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        infoPanel.setBackground(BACKGROUND_COLOR); // Fondo negro

        nameLabel = new JLabel("Name: ");
        nameLabel.setForeground(TEXT_COLOR); // Texto blanco para contraste
        nameLabel.setFont(new Font("Verdana", Font.BOLD, 14)); // Fuente personalizada más pequeña

        rankLabel = new JLabel("Rank: ");
        rankLabel.setForeground(TEXT_COLOR);
        rankLabel.setFont(new Font("Verdana", Font.BOLD, 14));

        infoPanel.add(nameLabel);
        infoPanel.add(rankLabel);

        // Área de texto para las habilidades y restricciones
        abilitiesText = new JTextArea();
        abilitiesText.setEditable(false);
        abilitiesText.setLineWrap(true);
        abilitiesText.setWrapStyleWord(true);
        abilitiesText.setForeground(TEXT_COLOR);
        abilitiesText.setBackground(BACKGROUND_COLOR);
        abilitiesText.setFont(new Font("Verdana", Font.PLAIN, 12));
        abilitiesText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(infoPanel, BorderLayout.NORTH);
        add(new JScrollPane(abilitiesText), BorderLayout.CENTER);

        setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2),
            "Piece Info",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Verdana", Font.BOLD, 16),
            TEXT_COLOR
        ));
        setBackground(BACKGROUND_COLOR); // Fondo del panel principal
    }

    public void updateInfo(PieceType pieceType, ImageIcon icon) {
        if (pieceType != null) {
            nameLabel.setText("Name: " + pieceType.getName());
            rankLabel.setText("Rank: " + pieceType.getRank());
            abilitiesText.setText(getPieceAbilities(pieceType));
        } else {
            nameLabel.setText("Name: ");
            rankLabel.setText("Rank: ");
            abilitiesText.setText("");
        }
    }

    private String getPieceAbilities(PieceType pieceType) {
        // Añadir la lógica para obtener las habilidades y restricciones de la pieza
        int rank = pieceType.getRank();
        String abilities = "";
        switch (rank) {
            case 10:
                abilities = "This piece is the highest rank and can only be defeated by a Rank 1 piece.";
                break;
            case 9:
                abilities = "This piece is a high rank and can defeat any lower rank except bombs.";
                break;
            case 8:
                abilities = "This piece is a strong rank and can defeat most lower ranks.";
                break;
            case 7:
                abilities = "This piece is a solid rank and can defeat many lower ranks.";
                break;
            case 6:
                abilities = "This piece is a mid-tier rank and can defeat lower ranks.";
                break;
            case 5:
                abilities = "This piece is a decent rank and can defeat lower ranks.";
                break;
            case 4:
                abilities = "This piece is a lower rank and can defeat lower ranks.";
                break;
            case 3:
                abilities = "This piece is a low rank and can defeat lower ranks.";
                break;
            case 2:
                abilities = "This piece can move any number of spaces in a straight line.";
                break;
            case 1:
                abilities = "This piece is a spy and can defeat the highest rank piece.";
                break;
            case 0:
                if (pieceType == PieceType.HEROES_BOMBA || pieceType == PieceType.VILLAINS_BOMBA) {
                    abilities = "This piece is a bomb and cannot move.";
                } else if (pieceType == PieceType.HEROES_EARTH || pieceType == PieceType.VILLAINS_EARTH) {
                    abilities = "This piece is immovable (Earth).";
                }
                break;
            default:
                abilities = "This piece can move one step in any orthogonal direction.";
                break;
        }
        return abilities;
    }
}
