package BoardElements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import player.User;
import player.UserRegistration;
import MainComponents.MainMenu;

public class ButtonManager implements ActionListener, PhantomInterface {

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
    private boolean isPlayer1Turn; // Indica si es el turno del jugador 1
    private String player1Name; // Nombre del jugador 1
    private String player2Name; // Nombre del jugador 2
    private JLabel turnLabel; // Label para mostrar el turno actual
    private UserRegistration userRegistration; // Variable para manejar la información de los usuarios
    private JButton surrenderButtonPlayer1;
    private JButton surrenderButtonPlayer2;
    private JLabel pieceInfoLabel; 

    public ButtonManager(JButton[][] tilesBTN, JLabel selectedPieceLabel, JPanel destroyedPiecesPanelHeroes,
            JPanel destroyedPiecesPanelVillains, ImageManager imageManager,
            JLabel turnLabel, String player1Name, String player2Name, boolean isPlayer1Turn,
            UserRegistration userRegistration, JButton surrenderButtonPlayer1, JButton surrenderButtonPlayer2, JLabel pieceInfoLabel) {
        this.tilesBTN = tilesBTN;
        this.selectedPieceLabel = selectedPieceLabel;
        this.destroyedPiecesPanelHeroes = destroyedPiecesPanelHeroes;
        this.destroyedPiecesPanelVillains = destroyedPiecesPanelVillains;
        this.imageManager = imageManager;
        this.highlighterManager = new HighlighterManager(tilesBTN);
        this.zoneManager = new ZoneManager();
        this.messageManager = new MessageManager();
        this.turnLabel = turnLabel;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.isPlayer1Turn = isPlayer1Turn;
        this.userRegistration = userRegistration;
        this.surrenderButtonPlayer1 = surrenderButtonPlayer1;
        this.surrenderButtonPlayer2 = surrenderButtonPlayer2;
        this.pieceInfoLabel = pieceInfoLabel; 
        this.selectedPieceLabel = selectedPieceLabel;
         this.tilesBTN = tilesBTN;

        surrenderButtonPlayer1.addActionListener(e -> handleSurrender(true));
        surrenderButtonPlayer2.addActionListener(e -> handleSurrender(false));

        // Se imprime en consola el turno inicial
        System.out.println("Inicialización completa. Turno inicial: " + (isPlayer1Turn ? player1Name : player2Name));
        updateTurnLabel();
        addListeners();
    }
    
    

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

            // Llamar a endGame para manejar la victoria y registrar el log
            endGame(winnerName, !isPlayer1Surrender, true); // true indica que la victoria fue por rendición
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

    private void handleVictory(String winnerName, boolean isHero) {
        User winner = userRegistration.getUser(winnerName);

        if (isHero) {
            winner.incrementGamesWithGood(); // Incrementa victorias para héroes
        } else {
            winner.incrementGamesWithBad(); // Incrementa victorias para villanos
        }

        winner.addPoints(3); // Suma puntos al ganador

        // Aquí puedes agregar cualquier lógica adicional, como guardar el estado del juego.
    }

    private void endGame(String winnerName, boolean isHero, boolean surrender) {
        User winner = userRegistration.getUser(winnerName);
        String logEntry;

        // Incrementar las victorias y actualizar el equipo jugado
        if (isHero) {
            winner.incrementGamesWithGood();
            logEntry = surrender ? "Victory by surrender as Heroes" : "Victory by capturing Earth as Heroes";
        } else {
            winner.incrementGamesWithBad();
            logEntry = surrender ? "Victory by surrender as Villains" : "Victory by capturing Earth as Villains";
        }

        winner.addPoints(3); // Suma puntos al ganador
        winner.addGameLog(logEntry); // Añadir log del juego

        // Imprimir la información actualizada
        System.out.println("Juego terminado. Ganador: " + winnerName + ". Equipo: " + winner.getLastPlayedTeam()
                + ". Total de puntos: " + winner.getPoints()
                + ". Victorias con héroes: " + winner.getGamesWithGood()
                + ". Victorias con villanos: " + winner.getGamesWithBad()
                + ". Total de partidas jugadas: " + winner.getTotalGamesPlayed());

        // Cerrar la ventana del juego y volver al menú principal
        SwingUtilities.getWindowAncestor(turnLabel).dispose();
        MainMenu mainMenu = new MainMenu(userRegistration, winner);
        mainMenu.setVisible(true);
    }

    private void updateTurnLabel() {
        String currentPlayer = isPlayer1Turn ? player1Name : player2Name;
        String team = isPlayer1Turn ? "HEROES TURN" : "VILLAINS TURN";
        turnLabel.setText(currentPlayer + " - " + team);

        // Imprimir en consola para verificación
        System.out.println("Turno actualizado: " + currentPlayer + " - " + team);

        // Actualizar el estado de los botones de rendición
        updateSurrenderButtons();
    }

//public void togglePieceVisibility() {
//    for (int row = 0; row < tilesBTN.length; row++) {
//        for (int col = 0; col < tilesBTN[row].length; col++) {
//            JButton button = tilesBTN[row][col];
//            ImageIcon icon = (ImageIcon) button.getIcon();
//
//            if (icon != null) {
//                String description = icon.getDescription();
//
//                if (description != null) {
//                    // Si es el turno del jugador 1 (Héroes)
//                    if (isPlayer1Turn) {
//                        if (description.startsWith("villains")) {
//                            // Ocultar todas las piezas de Villains
//                            button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resourcesmain/crash.png")));
//                        } else if (description.startsWith("heroes")) {
//                            // Asegurarse de que las piezas de Héroes se muestren correctamente
//                            button.setIcon(icon);
//                        }
//                    } 
//                    // Si es el turno del jugador 2 (Villains)
//                    else {
//                        if (description.startsWith("heroes")) {
//                            // Ocultar todas las piezas de Héroes
//                            button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resourcesmain/crash.png")));
//                        } else if (description.startsWith("villains")) {
//                            // Asegurarse de que las piezas de Villains se muestren correctamente
//                            button.setIcon(icon);
//                        }
//                    }
//                    // Mantener el botón habilitado para permitir interacción
//                    button.setEnabled(true);
//                }
//            }
//        }
//    }
//}


    // Este método se llama al cambiar el turno
    private void changeTurn() {
        isPlayer1Turn = !isPlayer1Turn;
        updateTurnLabel();
        updateSurrenderButtons(); // Actualizar el estado de los botones de rendición según el turno
//        togglePieceVisibility();  // Ocultar/Mostrar las piezas según el turno
    }

    // Método para inicializar visibilidad de las piezas al inicio
    public void initializePieceVisibility() {
//        togglePieceVisibility();
    }

    private void updateSurrenderButtons() {
        surrenderButtonPlayer1.setEnabled(isPlayer1Turn);
        surrenderButtonPlayer2.setEnabled(!isPlayer1Turn);
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

    // Si no se encontró la posición del botón, salir
    if (targetRow == -1 || targetCol == -1) {
        return;
    }

    // Obtén el icono y el tipo de pieza de la casilla presionada
    Icon buttonIcon = pressedButton.getIcon();  // Cambié a Icon para manejar cualquier tipo de ícono
    PieceType targetType = getPieceType(pressedButton);

    // Si no hay un botón seleccionado actualmente y el botón presionado tiene un icono
    if (selectedButton == null && buttonIcon != null) {

        PieceType selectedType = getPieceType(pressedButton);

        // Mostrar la información de la pieza seleccionada
        if (selectedType != null) {
            String pieceInfo = String.format(
                "<html>Name:<br>%s<br>Rank:<br>%d<br>Ability:<br>%s</html>",
                selectedType.getName(), 
                selectedType.getRank(), 
                getPieceAbility(selectedType)
            );
            pieceInfoLabel.setText(pieceInfo);  // Aquí se actualiza el label con la información de la pieza
        }

        // Verificar si la pieza seleccionada es una bomba o tierra, que no pueden moverse
        if (selectedType == PieceType.HEROES_BOMBA || selectedType == PieceType.VILLAINS_BOMBA) {
            messageManager.showBombCannotMoveMessage();
            return;
        } else if (selectedType == PieceType.HEROES_EARTH || selectedType == PieceType.VILLAINS_EARTH) {
            messageManager.showTierraCannotMoveMessage();
            return;
        }

        // Verificar si es el turno del jugador correcto
        if (selectedButton == null && buttonIcon != null) {
            if (isPlayer1Turn && !isHeroPiece(buttonIcon)) {
                messageManager.showMessage("Not your turn!", "Turn Error");
                return;
            } else if (!isPlayer1Turn && !isVillainPiece(buttonIcon)) {
                messageManager.showMessage("Not your turn!", "Turn Error");
                return;
            }
        }

        // Guardar la selección actual y actualizar la interfaz
        if (previousSelectedButton != null) {
            previousSelectedButton.setBorder(previousBorder);
        }

        previousBorder = pressedButton.getBorder();
        pressedButton.setBorder(BorderFactory.createLineBorder(highlighterManager.getElectricBlue(), 3));

        selectedPieceLabel.setIcon(imageManager.scaleImageForDisplay((ImageIcon) buttonIcon, selectedPieceLabel.getWidth(), selectedPieceLabel.getHeight()));

        previousSelectedButton = pressedButton;
        selectedButton = pressedButton;
        selectedRow = targetRow;
        selectedCol = targetCol;

        if (selectedType != null) {
            highlighterManager.highlightPossibleMoves(selectedRow, selectedCol, selectedType);
        }

        // Si ya hay un botón seleccionado (segundo clic)
    } else if (selectedButton != null) {
        PieceType selectedType = getPieceType(selectedButton);

        // Si se presionó el mismo botón, resetea la selección
        if (selectedButton == pressedButton) {
            resetSelection();
            // Si se presiona una pieza del mismo equipo
        } else if (buttonIcon != null && isSameTeam(selectedButton.getIcon(), buttonIcon)) {
            messageManager.showFriendlyFireMessage();
            // Si la ruta es clara, mueve la pieza o realiza un ataque
        } else if (zoneManager.isPathClear(tilesBTN, selectedRow, selectedCol, targetRow, targetCol, selectedType)) {
            if (targetType == null) { // Casilla de destino vacía
                movePiece(selectedButton, pressedButton, targetRow, targetCol);
                changeTurn();  // Cambiar de turno después de un movimiento válido
                resetSelection(); // Resetear la selección después de un movimiento válido
            } else { // Casilla de destino ocupada
                handlePieceMovement(pressedButton, selectedType, targetType, targetRow, targetCol);
                changeTurn();  // Cambiar de turno después de un movimiento válido
                resetSelection(); // Resetear la selección después de un movimiento válido
            }
        } else {
            // Si el movimiento es inválido o se intenta mover a una zona prohibida
            if (zoneManager.isProhibitedZone(targetRow, targetCol)) {
                messageManager.showMessage("Prohibited Zone", "Move Error");
            } else {
                messageManager.showInvalidMoveMessage();
            }
            return; // No cambiar turno en caso de movimiento inválido
        }
    }
}

// Método auxiliar para determinar si un icono pertenece a una pieza de héroes
private boolean isHeroPiece(Icon icon) {
    return icon instanceof ImageIcon && ((ImageIcon) icon).getDescription().startsWith("heroes");
}

// Método auxiliar para determinar si un icono pertenece a una pieza de villanos
private boolean isVillainPiece(Icon icon) {
    return icon instanceof ImageIcon && ((ImageIcon) icon).getDescription().startsWith("villains");
}

// Método para obtener una descripción de la habilidad de la pieza según su tipo
private String getPieceAbility(PieceType pieceType) {
    switch (pieceType) {
        case HEROES_RANGO10:
            return "Can move any number of spaces in a straight line.";
        case HEROES_BOMBA:
            return "Explodes when attacked, destroying the attacker.";
        case HEROES_EARTH:
            return "If captured, the game is won by the opposing player.";
        // Agrega más casos según las piezas y sus habilidades
        default:
            return "Standard movement and attack.";
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
        highlighterManager.clearHighlightedMoves();
        selectedButton.setBorder(BorderFactory.createLineBorder(highlighterManager.getGold(), 2));
        resetSelection();

        // Ocultar piezas del oponente después del movimiento
//        togglePieceVisibility();
    }

    private void handlePieceMovement(JButton pressedButton, PieceType selectedType, PieceType targetType, int targetRow, int targetCol) {
        String attackerName = selectedType.getName();
        String defenderName = targetType != null ? targetType.getName() : "empty";
        ImageIcon attackerIcon = (ImageIcon) selectedButton.getIcon();
        ImageIcon defenderIcon = (ImageIcon) pressedButton.getIcon();

        // Verificar si la pieza capturada es Earth
        if (targetType == PieceType.HEROES_EARTH || targetType == PieceType.VILLAINS_EARTH) {
            showBattleMessage(attackerName, defenderName, attackerIcon, defenderIcon, selectedType, targetType);
            updateDestroyedPieceLabel(pressedButton, isPlayer1Turn);

            // Mostrar mensaje de victoria y finalizar el juego
            String winnerName = isPlayer1Turn ? player1Name : player2Name;
            JOptionPane.showMessageDialog(null, winnerName + " has captured Earth and wins the game!", "Victory!", JOptionPane.INFORMATION_MESSAGE);

            // Llamar a endGame para manejar la victoria y registrar el log
            endGame(winnerName, isPlayer1Turn, false); // false indica que la victoria fue por captura de "Earth"
            return; // Finalizar el método para evitar más movimientos
        }

        // Resto de la lógica de movimiento y captura
        if (targetType == null) {
            movePiece(selectedButton, pressedButton, targetRow, targetCol);
        } else if ((selectedType == PieceType.HEROES_RANGO1 || selectedType == PieceType.VILLAINS_RANGO1)
                && (targetType == PieceType.HEROES_RANGO10 || targetType == PieceType.VILLAINS_RANGO10)) {
            showBattleMessage(attackerName, defenderName, attackerIcon, defenderIcon, selectedType, targetType);
            updateDestroyedPieceLabel(pressedButton, isPlayer1Turn);
            movePiece(selectedButton, pressedButton, targetRow, targetCol);
        } else if (targetType == PieceType.HEROES_BOMBA || targetType == PieceType.VILLAINS_BOMBA) {
            showBattleMessage(attackerName, defenderName, attackerIcon, defenderIcon, selectedType, targetType);
            if (!selectedType.name().contains("RANGO3")) {
                updateDestroyedPieceLabel(selectedButton, !isPlayer1Turn);
                selectedButton.setIcon(null);
                resetSelection();
            } else {
                updateDestroyedPieceLabel(pressedButton, isPlayer1Turn);
                movePiece(selectedButton, pressedButton, targetRow, targetCol);
            }
        } else if (selectedType.getRank() == targetType.getRank()) {
            showBattleMessage(attackerName, defenderName, attackerIcon, defenderIcon, selectedType, targetType);
            updateDestroyedPieceLabel(selectedButton, !isPlayer1Turn);
            updateDestroyedPieceLabel(pressedButton, isPlayer1Turn);
            selectedButton.setIcon(null);
            pressedButton.setIcon(null);
            resetSelection();
        } else if (selectedType.getRank() > targetType.getRank()) {
            showBattleMessage(attackerName, defenderName, attackerIcon, defenderIcon, selectedType, targetType);
            updateDestroyedPieceLabel(pressedButton, isPlayer1Turn);
            movePiece(selectedButton, pressedButton, targetRow, targetCol);
        } else {
            messageManager.showCannotAttackHigherRankMessage(attackerName, defenderName);
        }
    }

    private void updateDestroyedPieceLabel(JButton destroyedButton, boolean byPlayer1) {
        ImageIcon icon = (ImageIcon) destroyedButton.getIcon();
        if (icon != null) {
            JLabel destroyedPieceLabel = new JLabel(imageManager.scaleImageForDisplay(icon, 80, 80));

            if (byPlayer1) {  // Si la pieza fue destruida por Player 1 (Heroes)
                destroyedPiecesPanelHeroes.add(destroyedPieceLabel);
            } else {  // Si la pieza fue destruida por Player 2 (Villains)
                destroyedPiecesPanelVillains.add(destroyedPieceLabel);
            }

            destroyedPiecesPanelHeroes.revalidate();
            destroyedPiecesPanelHeroes.repaint();
            destroyedPiecesPanelVillains.revalidate();
            destroyedPiecesPanelVillains.repaint();
        }
    }

    private void resetSelection() {
        if (selectedButton != null) {
            selectedButton.setBorder(previousBorder);
        }
        selectedPieceLabel.setIcon(null);
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

private boolean isSameTeam(Icon icon1, Icon icon2) {
    if (icon1 instanceof ImageIcon && icon2 instanceof ImageIcon) {
        String description1 = ((ImageIcon) icon1).getDescription();
        String description2 = ((ImageIcon) icon2).getDescription();
        return (description1 != null && description2 != null && description1.startsWith("heroes") && description2.startsWith("heroes"))
            || (description1 != null && description2 != null && description1.startsWith("villains") && description2.startsWith("villains"));
    }
    return false;
}


    private PieceType getPieceType(JButton button) {
        ImageIcon icon = (ImageIcon) button.getIcon();
        if (icon == null || icon.getDescription() == null) {
            return null;  // Casilla vacía, esto es normal.
        }
        String description = icon.getDescription();
        int underscoreIndex = description.indexOf('_');
        if (underscoreIndex == -1) {
            return null;  // Descripción inválida o mal formada.
        }

        String pieceTypePart = description.substring(underscoreIndex + 1);
        try {
            return PieceType.valueOf(pieceTypePart);  // Devuelve el tipo de pieza correcto.
        } catch (IllegalArgumentException e) {
            return null;  // Descripción de pieza inválida.
        }
    }
}
