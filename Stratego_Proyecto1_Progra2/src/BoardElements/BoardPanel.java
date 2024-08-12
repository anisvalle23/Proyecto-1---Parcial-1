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
    private PieceInfoPanel pieceInfoPanel;  // Panel para mostrar información de las piezas

    public BoardPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        initBackground();
        initLabels();
        initBoard();
        initDestroyedPiecesPanels();
        initSurrenderButtons(); // Inicializar los botones de rendición
        initTurnLabel(); // Inicializar el label de turno
        initPieceInfoPanel(); // Inicializar el panel de información de las piezas
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
        destroyedPiecesPanelVillains = createDestroyedPiecesPanel("Player 1 Destroyed", 25, 320, GREEN);
        destroyedPiecesPanelHeroes = createDestroyedPiecesPanel("Player 2 Destroyed", 980, 320, DARK_RED);
    }

    private JPanel createDestroyedPiecesPanel(String title, int x, int y, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 5, 5, 5));
        panel.setBounds(x, y, DESTROYED_PANEL_WIDTH, DESTROYED_PANEL_HEIGHT);
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GOLD), title);
        border.setTitleColor(GOLD);
        panel.setBorder(border);
        panel.setBackground(bgColor);  // Set panel background color
        panel.setOpaque(true);  // Make the panel opaque to show the background color
        add(panel);
        setComponentZOrder(panel, 0); // Ensure the panel is above the background
        return panel;
    }

    private void initSurrenderButtons() {
        surrenderButtonPlayer1 = new JButton("Player 1 Surrender");
        surrenderButtonPlayer1.setBounds(25, 850, 200, 40); // Ajustar la posición y tamaño según sea necesario
        surrenderButtonPlayer1.addActionListener(e -> {
            if (surrenderListener != null) surrenderListener.onSurrender(true);
        });
        add(surrenderButtonPlayer1);
        setComponentZOrder(surrenderButtonPlayer1, 0); // Ensure the button is above the background

        surrenderButtonPlayer2 = new JButton("Player 2 Surrender");
        surrenderButtonPlayer2.setBounds(980, 850, 200, 40); // Ajustar la posición y tamaño según sea necesario
        surrenderButtonPlayer2.addActionListener(e -> {
            if (surrenderListener != null) surrenderListener.onSurrender(false);
        });
        add(surrenderButtonPlayer2);
        setComponentZOrder(surrenderButtonPlayer2, 0); // Ensure the button is above the background
    }

    private void initTurnLabel() {
        turnLabel = new JLabel("Turn: Heroes - Player 1", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        turnLabel.setForeground(Color.WHITE);
        turnLabel.setBounds(400, 10, 400, 40);
        add(turnLabel);
        setComponentZOrder(turnLabel, 0); // Ensure the label is above the background
    }

    private void initPieceInfoPanel() {
        pieceInfoPanel = new PieceInfoPanel();
        pieceInfoPanel.setBounds(30, 75, 200, 200); // Ajusta la posición y tamaño del panel
        add(pieceInfoPanel);
        setComponentZOrder(pieceInfoPanel, 0); // Asegurar que el panel esté sobre el fondo
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

    public PieceInfoPanel getPieceInfoPanel() {
        return pieceInfoPanel;
    }

    // Listener para manejar la rendición
    private SurrenderListener surrenderListener;

    public void setSurrenderListener(SurrenderListener surrenderListener) {
        this.surrenderListener = surrenderListener;
    }

    public interface SurrenderListener {
        void onSurrender(boolean isPlayer1Surrender);
    }
}
