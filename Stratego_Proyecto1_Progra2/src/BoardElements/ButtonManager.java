package BoardElements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import player.User;
import player.UserRegistration;
import MainComponents.MainMenu;

public class ButtonManager implements ActionListener {
    private JButton[][] tilesBTN;
    private JLabel selectedPieceLabel;
    private JPanel destroyedPiecesPanelHeroes;
    private JPanel destroyedPiecesPanelVillains;
    private ImageManager imageManager;
    private JButton previousSelectedButton = null;
    private Border previousBorder = null;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private JButton selectedButton = null;
    private HighlighterManager highlighterManager;
    private ZoneManager zoneManager;
    private MessageManager messageManager;
    private PieceInfoPanel pieceInfoPanel;
    private boolean isPlayer1Turn; // Indica si es el turno del jugador 1
    private String player1Name; // Nombre del jugador 1
    private String player2Name; // Nombre del jugador 2
    private JLabel turnLabel; // Label para mostrar el turno actual
    private UserRegistration userRegistration; // Variable para manejar la información de los usuarios

    public ButtonManager(JButton[][] tilesBTN, JLabel selectedPieceLabel, JPanel destroyedPiecesPanelHeroes,
                         JPanel destroyedPiecesPanelVillains, ImageManager imageManager, PieceInfoPanel pieceInfoPanel,
                         JLabel turnLabel, String player1Name, String player2Name, boolean isPlayer1Turn,
                         UserRegistration userRegistration) { // Añadir userRegistration aquí
        this.tilesBTN = tilesBTN;
        this.selectedPieceLabel = selectedPieceLabel;
        this.destroyedPiecesPanelHeroes = destroyedPiecesPanelHeroes;
        this.destroyedPiecesPanelVillains = destroyedPiecesPanelVillains;
        this.imageManager = imageManager;
        this.highlighterManager = new HighlighterManager(tilesBTN);
        this.zoneManager = new ZoneManager();
        this.messageManager = new MessageManager();
        this.pieceInfoPanel = pieceInfoPanel;
        this.turnLabel = turnLabel;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.isPlayer1Turn = isPlayer1Turn;
        this.userRegistration = userRegistration; // Asignar la instancia de userRegistration

        // Se imprime en consola el turno inicial
        System.out.println("Inicialización completa. Turno inicial: " + (isPlayer1Turn ? player1Name : player2Name));
        updateTurnLabel();
        addListeners();
    }

    // Método para manejar la rendición
    public void handleSurrender(boolean isPlayer1Surrender) {
        if ((isPlayer1Surrender && !isPlayer1Turn) || (!isPlayer1Surrender && isPlayer1Turn)) {
            messageManager.showMessage("You cannot surrender, it's not your turn!");
            return;
        }

        // Confirmación de rendición
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to surrender?", "Confirm Surrender", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String winnerName = isPlayer1Surrender ? player2Name : player1Name;
            String loserName = isPlayer1Surrender ? player1Name : player2Name;

            // Mostrar mensaje de ganador y cerrar la ventana del juego
            JOptionPane.showMessageDialog(null, loserName + " has surrendered! " + winnerName + " wins!\nThe game will now end.", "Game Over", JOptionPane.INFORMATION_MESSAGE);

            // Cerrar la ventana del juego
            SwingUtilities.getWindowAncestor(turnLabel).dispose();

            // Obtener el User ganador
            User winner = userRegistration.getUser(winnerName);

            // Volver al menú principal usando la instancia existente de User y UserRegistration
            MainMenu mainMenu = new MainMenu(userRegistration, winner);
            mainMenu.setVisible(true);
        }
    }

    // Método para agregar listeners a los botones del tablero
    private void addListeners() {
        for (int row = 0; row < tilesBTN.length; row++) {
            for (int col = 0; col < tilesBTN[row].length; col++) {
                tilesBTN[row][col].addActionListener(this);
            }
        }
    }

    // Método de actualización de turnos
    private void updateTurnLabel() {
        String currentPlayer = isPlayer1Turn ? player1Name : player2Name;
        String team = isPlayer1Turn ? "HEROES TURN" : "VILLAINS TURN";
        turnLabel.setText(currentPlayer + " - " + team);

        // Imprimir en consola para verificación
        System.out.println("Turno actualizado: " + currentPlayer + " - " + team);
    }

    // Método para cambiar de turno
    private void changeTurn() {
        isPlayer1Turn = !isPlayer1Turn;
        updateTurnLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        int targetRow = -1, targetCol = -1;

        // Encuentra la posición del botón presionado en la matriz tilesBTN
        for (int row = 0; row < tilesBTN.length; row++) {
            for (int col = 0; col < tilesBTN[row].length; col++) {
                if (tilesBTN[row][col] == pressedButton) {
                    targetRow = row;
                    targetCol = col;
                    break;
                }
            }
        }

        // Obtén el icono y el tipo de pieza de la casilla presionada
        ImageIcon buttonIcon = (ImageIcon) pressedButton.getIcon();
        PieceType selectedType = getPieceType(pressedButton);

        // Si no hay un botón seleccionado actualmente y el botón presionado tiene un icono
        if (selectedButton == null && buttonIcon != null) {
            // Verificar si la pieza seleccionada es una bomba o una pieza de tierra, que no pueden moverse
            if (selectedType == PieceType.HEROES_BOMBA || selectedType == PieceType.VILLAINS_BOMBA) {
                messageManager.showBombCannotMoveMessage();
                return;
            } else if (selectedType == PieceType.HEROES_EARTH || selectedType == PieceType.VILLAINS_EARTH) {
                messageManager.showTierraCannotMoveMessage();
                return;
            }

            // Verificar si es el turno del jugador correcto
            if (isPlayer1Turn && !buttonIcon.getDescription().startsWith("heroes")) {
                messageManager.showMessage("Not your turn!", "Turn Error");
                return;
            } else if (!isPlayer1Turn && !buttonIcon.getDescription().startsWith("villains")) {
                messageManager.showMessage("Not your turn!", "Turn Error");
                return;
            }

            // Guardar la selección actual y actualizar la interfaz
            if (previousSelectedButton != null) {
                previousSelectedButton.setBorder(previousBorder);
            }
            previousBorder = pressedButton.getBorder();
            pressedButton.setBorder(BorderFactory.createLineBorder(highlighterManager.getElectricBlue(), 3));

            selectedPieceLabel.setIcon(imageManager.scaleImageForDisplay(buttonIcon, selectedPieceLabel.getWidth(), selectedPieceLabel.getHeight()));
            pieceInfoPanel.updateInfo(selectedType, buttonIcon);  // Actualiza la información en PieceInfoPanel
            previousSelectedButton = pressedButton;
            selectedButton = pressedButton;
            selectedRow = targetRow;
            selectedCol = targetCol;

            if (selectedType != null) {
                highlighterManager.highlightPossibleMoves(selectedRow, selectedCol, selectedType);
            }

        // Si ya hay un botón seleccionado (segundo clic)
        } else if (selectedButton != null) {
            PieceType targetType = getPieceType(pressedButton);

            // Si se presionó el mismo botón, resetea la selección
            if (selectedButton == pressedButton) {
                resetSelection();
            // Si se presiona una pieza del mismo equipo
            } else if (buttonIcon != null && isSameTeam(selectedButton, pressedButton)) {
                messageManager.showFriendlyFireMessage();
            // Si la ruta es clara, mueve la pieza o realiza un ataque
            } else if (zoneManager.isPathClear(tilesBTN, selectedRow, selectedCol, targetRow, targetCol, selectedType)) {
                if (targetType == null) {
                    movePiece(selectedButton, pressedButton, targetRow, targetCol);
                    changeTurn();
                } else {
                    handlePieceMovement(pressedButton, selectedType, targetType, targetRow, targetCol);
                    changeTurn();
                }
            } else {
                messageManager.showInvalidMoveMessage();
            }
        }
    }

    private void movePiece(JButton fromButton, JButton toButton, int targetRow, int targetCol) {
        ImageIcon selectedIcon = (ImageIcon) fromButton.getIcon();
        toButton.setIcon(selectedIcon);
        fromButton.setIcon(null);

        if (previousSelectedButton != null) {
            previousSelectedButton.setBorder(previousBorder);
        }

        selectedPieceLabel.setIcon(null);
        pieceInfoPanel.updateInfo(null, null);
        highlighterManager.clearHighlightedMoves();
        selectedButton.setBorder(BorderFactory.createLineBorder(highlighterManager.getGold(), 2));
        resetSelection();
    }

    private void handlePieceMovement(JButton pressedButton, PieceType selectedType, PieceType targetType, int targetRow, int targetCol) {
        String attackerName = selectedType.getName();
        String defenderName = targetType != null ? targetType.getName() : "empty";
        ImageIcon attackerIcon = (ImageIcon) selectedButton.getIcon();
        ImageIcon defenderIcon = (ImageIcon) pressedButton.getIcon();

        // Movimiento a una casilla vacía
        if (targetType == null) {
            movePiece(selectedButton, pressedButton, targetRow, targetCol);
        }
        // Rango 1 puede atacar Rango 10
        else if ((selectedType == PieceType.HEROES_RANGO1 || selectedType == PieceType.VILLAINS_RANGO1) 
                    && (targetType == PieceType.HEROES_RANGO10 || targetType == PieceType.VILLAINS_RANGO10)) {
            showBattleMessage(attackerName, defenderName, attackerIcon, defenderIcon, selectedType, targetType);
            updateDestroyedPieceLabel(pressedButton);
            movePiece(selectedButton, pressedButton, targetRow, targetCol);
        }
        // Bombas
        else if (targetType == PieceType.HEROES_BOMBA || targetType == PieceType.VILLAINS_BOMBA) {
            showBattleMessage(attackerName, defenderName, attackerIcon, defenderIcon, selectedType, targetType);
            if (!selectedType.name().contains("RANGO3")) {
                updateDestroyedPieceLabel(selectedButton);
                selectedButton.setIcon(null);
                resetSelection();
            } else {
                updateDestroyedPieceLabel(pressedButton);
                movePiece(selectedButton, pressedButton, targetRow, targetCol);
            }
        }
        // Misma clase de rango se destruyen mutuamente
        else if (selectedType.getRank() == targetType.getRank()) {
            showBattleMessage(attackerName, defenderName, attackerIcon, defenderIcon, selectedType, targetType);
            updateDestroyedPieceLabel(selectedButton);
            updateDestroyedPieceLabel(pressedButton);
            selectedButton.setIcon(null);
            pressedButton.setIcon(null);
            resetSelection();
        }
        // Ataque válido: Rango mayor ataca a rango menor
        else if (selectedType.getRank() > targetType.getRank()) {
            showBattleMessage(attackerName, defenderName, attackerIcon, defenderIcon, selectedType, targetType);
            updateDestroyedPieceLabel(pressedButton);
            movePiece(selectedButton, pressedButton, targetRow, targetCol);
        }
        // Movimiento inválido: Rango menor no puede atacar a rango mayor
        else if (selectedType.getRank() < targetType.getRank()) {
            messageManager.showCannotAttackHigherRankMessage(attackerName, defenderName);
        }
    }

    private void resetSelection() {
        if (selectedButton != null) {
            selectedButton.setBorder(previousBorder);
        }
        selectedPieceLabel.setIcon(null);
        pieceInfoPanel.updateInfo(null, null); 
        highlighterManager.clearHighlightedMoves();
        selectedButton = null;
        selectedRow = -1;
        selectedCol = -1;
    }

    private void showBattleMessage(String attacker, String defender, ImageIcon attackerIcon, ImageIcon defenderIcon, PieceType attackerType, PieceType defenderType) {
        BattleMessagePanel panel = new BattleMessagePanel(attacker, defender, attackerIcon, defenderIcon, attackerType, defenderType);
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = optionPane.createDialog("Battle!");

        dialog.setVisible(true);
    }

    private void updateDestroyedPieceLabel(JButton destroyedButton) {
        ImageIcon icon = (ImageIcon) destroyedButton.getIcon();
        if (icon != null) {
            JLabel destroyedPieceLabel = new JLabel(imageManager.scaleImageForDisplay(icon, 80, 80));

            if (icon.getDescription().startsWith("heroes")) {
                destroyedPiecesPanelHeroes.add(destroyedPieceLabel);
            } else if (icon.getDescription().startsWith("villains")) {
                destroyedPiecesPanelVillains.add(destroyedPieceLabel);
            }

            destroyedPiecesPanelHeroes.revalidate();
            destroyedPiecesPanelHeroes.repaint();
            destroyedPiecesPanelVillains.revalidate();
            destroyedPiecesPanelVillains.repaint();
        }
    }

    private boolean isSameTeam(JButton button1, JButton button2) {
        ImageIcon icon1 = (ImageIcon) button1.getIcon();
        ImageIcon icon2 = (ImageIcon) button2.getIcon();
        if (icon1 == null || icon2 == null) return false;
        return (icon1.getDescription().startsWith("heroes") && icon2.getDescription().startsWith("heroes"))
                || (icon1.getDescription().startsWith("villains") && icon2.getDescription().startsWith("villains"));
    }

    private PieceType getPieceType(JButton button) {
        ImageIcon icon = (ImageIcon) button.getIcon();
        if (icon == null || icon.getDescription() == null) return null;
        String description = icon.getDescription();
        int underscoreIndex = description.indexOf('_');
        if (underscoreIndex == -1) {
            return null;
        }
        String pieceTypePart = description.substring(underscoreIndex + 1);
        try {
            return PieceType.valueOf(pieceTypePart);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
