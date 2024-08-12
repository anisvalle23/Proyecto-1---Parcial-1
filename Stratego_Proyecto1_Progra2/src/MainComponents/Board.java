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
        this.boardPanel = new BoardPanel();

        // Asignar jugadores y equipos asegurando que el equipo Héroes siempre inicie
        User player1, player2;
        String player1Name, player2Name;
        boolean isPlayer1Turn = true; // Por defecto, los Héroes inician el turno

        // Determinar quién es Player 1 (Héroes) y Player 2 (Villanos)
        if ("good".equals(playerTeam)) {
            player1 = loggedInUser;  // loggedInUser juega como Héroes
            player2 = opponent;      // Oponente juega como Villanos
        } else {
            player1 = opponent;      // Oponente juega como Héroes
            player2 = loggedInUser;  // loggedInUser juega como Villanos
        }

        player1Name = player1.getUsername();
        player2Name = player2.getUsername();

        // Imprimir en consola para verificar la configuración de los turnos
        System.out.println("Player 1 (Héroes): " + player1Name);
        System.out.println("Player 2 (Villanos): " + player2Name);
        System.out.println("Turno inicial: " + (isPlayer1Turn ? player1Name : player2Name));

        // Configurar el ButtonManager con los jugadores y turnos correctos
        this.tileButtonManager = new ButtonManager(
                boardPanel.getTilesBTN(),
                boardPanel.getSelectedPieceLabel(),
                boardPanel.getDestroyedPiecesPanelHeroes(),
                boardPanel.getDestroyedPiecesPanelVillains(),
                imageManager,
                new PieceInfoPanel(),
                boardPanel.getTurnLabel(),
                player1Name, // Nombre del jugador Héroes
                player2Name, // Nombre del jugador Villanos
                isPlayer1Turn,
                userRegistration // Pasar la instancia de UserRegistration aquí
        );

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

        // Confirmación de la inicialización en consola
        System.out.println("Inicialización completa. Turno inicial: " + (isPlayer1Turn ? player1Name : player2Name));
    }
}
