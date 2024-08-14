package BoardElements;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class BoardPanel extends JPanel {

    private static final int BOARD_SIZE = 10;
    private static final int TILE_SIZE = 70;
    private static final int PANEL_WIDTH = 1200;
    private static final int PANEL_HEIGHT = 850;
    private static final int DISPLAY_WIDTH = 200;
    private static final int DISPLAY_HEIGHT = 220;
    private static final int DESTROYED_PANEL_WIDTH = 200;
    private static final int DESTROYED_PANEL_HEIGHT = 500;
    private static final Color DARK_RED = new Color(151, 0, 0);
    private static final Color GREEN = new Color(14, 122, 69);
    private static final Color GOLD = new Color(255, 223, 85);
    Font labelFont = new Font("Arial", Font.BOLD, 18);

    private Color color1 = new Color(255, 223, 85);
    private Color color2 = new Color(255, 223, 85);
    private JButton[][] tilesBTN = new JButton[BOARD_SIZE][BOARD_SIZE];
    private JLabel background;
    private JLabel selectedPieceLabel;
    private JPanel destroyedPiecesPanelHeroes;
    private JPanel destroyedPiecesPanelVillains;
    private JLabel turnLabel;  // Label para indicar el turno
    private JButton surrenderButtonPlayer1;
    private JButton surrenderButtonPlayer2;
    private String player1Name;
    private String player2Name;
    private SurrenderListener surrenderListener;
    private boolean isPlayer1Turn;
    private JLayeredPane layeredPane;
    private JLabel pieceInfoLabel;

    public BoardPanel(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        setLayout(null);
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        initBackground();
        initLabels();
        initBoard();
        initDestroyedPiecesPanels();
        initSurrenderButtons(); // Inicializar los botones de rendición
        initTurnLabel(); // Inicializar el label de turno
    }
    

    private void initBackground() {
        background = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resourcesmain/back.png")));
        background.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        add(background);
    }

private void initLabels() {
    selectedPieceLabel = new JLabel();
    selectedPieceLabel.setBounds(980, 75, DISPLAY_WIDTH, DISPLAY_HEIGHT);
    add(selectedPieceLabel);
    setComponentZOrder(selectedPieceLabel, 0); // Ensure the label is above the background


    // Configurar el label de la información de la pieza seleccionada
    pieceInfoLabel = new JLabel();
    pieceInfoLabel.setBounds(25, 15, 250, 150); // Aumentar el tamaño para que sea más visible
    pieceInfoLabel.setFont(labelFont);
    pieceInfoLabel.setForeground(Color.WHITE); // Color del texto
    pieceInfoLabel.setOpaque(true); // Permite que el fondo del label sea visible
    pieceInfoLabel.setBackground(new Color(0, 0, 0, 150)); // Fondo semitransparente para mayor legibilidad
    pieceInfoLabel.setVerticalAlignment(JLabel.TOP); // Alineación del texto en la parte superior
    add(pieceInfoLabel);
    setComponentZOrder(pieceInfoLabel, 0); // Asegurar que el label esté sobre el fondo
}


    private void initBoard() {
        int offsetX = (getPreferredSize().width - BOARD_SIZE * TILE_SIZE) / 2;
        int offsetY = (getPreferredSize().height - BOARD_SIZE * TILE_SIZE) / 2;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                tilesBTN[row][col] = new JButton();
                tilesBTN[row][col].setBounds(offsetX + (col * TILE_SIZE), offsetY + (row * TILE_SIZE), TILE_SIZE, TILE_SIZE);
                tilesBTN[row][col].setFocusable(false);
                add(tilesBTN[row][col]);
                setComponentZOrder(tilesBTN[row][col], 0); // Ensure the buttons are above the background
            }
        }
        setTileBackground();
    }

    private void initDestroyedPiecesPanels() {
        // Heroes (Player 1) con color verde
        destroyedPiecesPanelHeroes = createDestroyedPiecesPanel(player1Name + "  Destroyed", 25, 320, GREEN);

        // Villains (Player 2) con color rojo oscuro
        destroyedPiecesPanelVillains = createDestroyedPiecesPanel(player2Name + " Destroyed", 980, 320, DARK_RED);
    }

    // Método que crea el panel de piezas destruidas
    private JPanel createDestroyedPiecesPanel(String title, int x, int y, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 5, 5, 5));
        panel.setBounds(x, y, 200, 500);
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GOLD), title);
        border.setTitleColor(GOLD);
        panel.setBorder(border);
        panel.setBackground(bgColor);
        panel.setOpaque(true);
        add(panel);
        setComponentZOrder(panel, 0);
        return panel;
    }

    public void updateLabelsAndButtons(String player1Name, String player2Name) {
        // Actualiza los textos de los paneles de piezas destruidas
        TitledBorder heroesBorder = (TitledBorder) destroyedPiecesPanelHeroes.getBorder();
        heroesBorder.setTitle(player1Name + " Destroyed");

        TitledBorder villainsBorder = (TitledBorder) destroyedPiecesPanelVillains.getBorder();
        villainsBorder.setTitle(player2Name + " Destroyed");

        destroyedPiecesPanelHeroes.repaint();
        destroyedPiecesPanelVillains.repaint();

        // Actualiza los textos de los botones de rendición
        surrenderButtonPlayer1.setText("Surrender " + player1Name);
        surrenderButtonPlayer2.setText("Surrender " + player2Name);

        // Actualiza el label de turno
        turnLabel.setText("Turn: " + (isPlayer1Turn ? player1Name : player2Name));
    }

    // Implementación de SurrenderListener
    public interface SurrenderListener {
        void onSurrender(boolean isPlayer1Surrender);
    }

    public void setSurrenderListener(SurrenderListener surrenderListener) {
        this.surrenderListener = surrenderListener;
    }

    // Método para actualizar el turno desde afuera de la clase
    public void setPlayerTurn(boolean isPlayer1Turn) {
        this.isPlayer1Turn = isPlayer1Turn;
    }

    private void initSurrenderButtons() {
        // Botón de rendición para Player 1 (Heroes)
        surrenderButtonPlayer1 = new JButton("Surrender " + player1Name);
        surrenderButtonPlayer1.setBounds(25, 810, 200, 40);  // Colocar debajo del panel de piezas destruidas de Heroes
        surrenderButtonPlayer1.addActionListener(e -> {
            if (!isPlayer1Turn) {  // Verificar si es el turno de Player 1
                JOptionPane.showMessageDialog(this, "It's not your turn!", "Turn Error", JOptionPane.ERROR_MESSAGE);
            } else if (surrenderListener != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to surrender?", "Confirm Surrender", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    surrenderListener.onSurrender(true);  // Player 1 se rinde
                }
            }
        });
        add(surrenderButtonPlayer1);
        setComponentZOrder(surrenderButtonPlayer1, 0); // Asegurar que el botón esté sobre el fondo

        // Botón de rendición para Player 2 (Villains)
        surrenderButtonPlayer2 = new JButton("Surrender " + player2Name);
        surrenderButtonPlayer2.setBounds(980, 810, 200, 40);  // Colocar debajo del panel de piezas destruidas de Villains
        surrenderButtonPlayer2.addActionListener(e -> {
            if (isPlayer1Turn) {  // Verificar si es el turno de Player 2
                JOptionPane.showMessageDialog(this, "It's not your turn!", "Turn Error", JOptionPane.ERROR_MESSAGE);
            } else if (surrenderListener != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to surrender?", "Confirm Surrender", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    surrenderListener.onSurrender(false);  // Player 2 se rinde
                }
            }
        });
        add(surrenderButtonPlayer2);
        setComponentZOrder(surrenderButtonPlayer2, 0); // Asegurar que el botón esté sobre el fondo
    }

    private void initTurnLabel() {
        turnLabel = new JLabel("Turn: Heroes - Player 1", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        turnLabel.setForeground(Color.WHITE);
        turnLabel.setBounds(400, 10, 400, 40);
        add(turnLabel);
        setComponentZOrder(turnLabel, 0); // Ensure the label is above the background
    }

    public void setTileBackground() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                tilesBTN[row][col].setBackground(((row + col) % 2 == 0) ? color1 : color2);
                tilesBTN[row][col].setBorder(BorderFactory.createLineBorder(((row + col) % 2 == 0) ? color2 : color1, 2));
            }
        }
    }

    public JButton[][] getTilesBTN() {
        return tilesBTN;
    }

    public JLabel getSelectedPieceLabel() {
        return selectedPieceLabel;
    }

    public JPanel getDestroyedPiecesPanelHeroes() {
        return destroyedPiecesPanelHeroes;
    }

    public JPanel getDestroyedPiecesPanelVillains() {
        return destroyedPiecesPanelVillains;
    }

    public JButton getSurrenderButtonPlayer1() {
        return surrenderButtonPlayer1;
    }

    public JButton getSurrenderButtonPlayer2() {
        return surrenderButtonPlayer2;
    }

    public JLabel getTurnLabel() {
        return turnLabel;
    }
    public JLabel getPieceInfoLabel() {
    return pieceInfoLabel;
}

}
