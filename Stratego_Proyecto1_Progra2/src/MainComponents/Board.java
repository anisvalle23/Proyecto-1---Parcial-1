package MainComponents;

import javax.swing.*;
import player.User;
import player.UserRegistration;
import BoardElements.*;

public class Board extends JFrame {

    private BoardPanel boardPanel;
    private ImageManager imageManager;
    private ButtonManager tileButtonManager;
    private IconManager tileIconAssigner;

    public Board(UserRegistration userRegistration, User loggedInUser, User opponent, String playerTeam, String opponentTeam) {
        this.imageManager = new ImageManager();
        this.boardPanel = new BoardPanel(loggedInUser.getUsername(), opponent.getUsername());

        User player1, player2;
        String player1Name, player2Name;
        boolean isPlayer1Turn = true; // Por defecto, los Héroes inician el turno

        // Determinar quién es Player 1 (Héroes) y quién es Player 2 (Villanos)
        if ("good".equals(playerTeam)) {
            player1 = loggedInUser;  // loggedInUser juega como Héroes
            player2 = opponent;      // Oponente juega como Villanos
        } else {
            player1 = opponent;      // Oponente juega como Héroes
            player2 = loggedInUser;  // loggedInUser juega como Villanos
        }

        player1Name = player1.getUsername();
        player2Name = player2.getUsername();

        // Actualizar labels y botones según los equipos y nombres seleccionados
        boardPanel.updateLabelsAndButtons(player1Name, player2Name);

        // Configurar el ButtonManager con los jugadores y turnos correctos
        this.tileButtonManager = new ButtonManager(
                boardPanel.getTilesBTN(),
                boardPanel.getSelectedPieceLabel(),
                boardPanel.getDestroyedPiecesPanelHeroes(),
                boardPanel.getDestroyedPiecesPanelVillains(),
                imageManager,
                boardPanel.getTurnLabel(),
                player1Name, // Nombre del jugador Héroes
                player2Name, // Nombre del jugador Villanos
                isPlayer1Turn,
                userRegistration,
                boardPanel.getSurrenderButtonPlayer1(),
                boardPanel.getSurrenderButtonPlayer2(),
                boardPanel.getPieceInfoLabel()
        );

        // Inicializar el turno correctamente en el Panel
        boardPanel.setPlayerTurn(isPlayer1Turn);

        // Inicializar visibilidad de las piezas (ocultar las piezas del equipo Villains al inicio)
        tileButtonManager.initializePieceVisibility();

        // Inicializar el IconManager para asignar íconos a las piezas
        this.tileIconAssigner = new IconManager(boardPanel.getTilesBTN(), imageManager, 70, 70, boardPanel.getSelectedPieceLabel());

        // Configurar la ventana del juego
        setIconImage(imageManager.scaleImage("resourcesmain/back.png", 32, 32).getImage());
        setTitle("STRATEGO - MARVEL HEROES!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(boardPanel);
        pack();
        setLocationRelativeTo(null);

        // Asignar íconos al tablero basado en los equipos de los jugadores
        tileIconAssigner.assignIcons();

        boardPanel.setSurrenderListener(isPlayer1Surrender -> {
            User winner;
            User loser;

            if (isPlayer1Surrender) {
                loser = player1;
                winner = player2;
            } else {
                loser = player2;
                winner = player1;
            }

            // Confirmar rendición
            int confirm = JOptionPane.showConfirmDialog(this, loser.getUsername() + " has surrendered! " + winner.getUsername() + " wins! You gain 3 points.\nDo you want to return to the main menu?", "Surrender", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Asignar puntos al ganador
                winner.addPoints(3);

                // Registrar la partida en los logs del usuario logueado
                String logEntry = "Game: " + winner.getUsername() + " defeated " + loser.getUsername() + " by surrender on " + java.time.LocalDateTime.now();
                loggedInUser.addGameLog(logEntry);

                // Cerrar la ventana del juego
                dispose();

                // Volver al menú principal usando siempre al loggedInUser
                new MainMenu(userRegistration, loggedInUser).setVisible(true);
            }
        });
    }
}
