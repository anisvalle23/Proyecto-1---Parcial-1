package MainComponents;

import MainMenu.MarvelUniverse;
import MainMenu.MyAccount;
import StartMenu.StartMenu;
import javax.swing.*;
import java.awt.*;
import player.GameModeSelector;
import player.OpponentSelector;
import player.User;
import player.UserRegistration;

public class MainMenu extends JFrame {

    private UserRegistration userRegistration;
    private User loggedInUser;

    public MainMenu(UserRegistration userRegistration, User loggedInUser) {
        if (loggedInUser == null) {
            throw new IllegalArgumentException("MainMenu requires a logged-in user.");
        }
        this.userRegistration = userRegistration;
        this.loggedInUser = loggedInUser;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        System.out.println("MainMenu initialized for user: " + loggedInUser);
    }

    private void initComponents() {
        // Panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(1200, 850));
        mainPanel.setSize(new Dimension(1200, 850));

        // Labels
        JLabel strategoLabel = new JLabel("STRATEGO - MARVEL HEROES!");
        strategoLabel.setFont(new Font("Skia", Font.BOLD, 18));
        strategoLabel.setBounds(500, 340, 370, 50);
        mainPanel.add(strategoLabel);

        JLabel accountLabel = new JLabel("MY ACCOUNT");
        accountLabel.setFont(new Font("Skia", Font.BOLD, 18));
        accountLabel.setBounds(570, 440, 140, 30);
        mainPanel.add(accountLabel);

        JLabel marvelLabel = new JLabel("MARVEL UNIVERSE");
        marvelLabel.setFont(new Font("Skia", Font.BOLD, 18));
        marvelLabel.setBounds(550, 530, 180, 30);
        mainPanel.add(marvelLabel);

        JLabel logoutLabel = new JLabel("LOG OUT");
        logoutLabel.setFont(new Font("Skia", Font.BOLD, 18));
        logoutLabel.setBounds(590, 620, 110, 50);
        mainPanel.add(logoutLabel);

        // Buttons
        JButton strategoButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        strategoButton.setBounds(480, 340, 300, 50);
        strategoButton.addActionListener(this::StrategoButtonActionPerformed);
        mainPanel.add(strategoButton);

        JButton accountButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        accountButton.setBounds(480, 430, 300, 50);
        accountButton.addActionListener(this::AccountButtonActionPerformed);
        mainPanel.add(accountButton);

        JButton marvelButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        marvelButton.setBounds(480, 520, 300, 50);
        marvelButton.addActionListener(this::MarvelButtonActionPerformed);
        mainPanel.add(marvelButton);

        JButton logoutButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        logoutButton.setBounds(520, 620, 210, 50);
        logoutButton.addActionListener(this::LogOutButtonActionPerformed);
        mainPanel.add(logoutButton);

        // Background Image
        JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/resourcesmain/mainnn 2.png")));
        backgroundLabel.setBounds(-10, -50, 1230, 970);
        mainPanel.add(backgroundLabel);

        // Add panel to frame
        getContentPane().add(mainPanel);
        pack();
    }

    private void StrategoButtonActionPerformed(java.awt.event.ActionEvent evt) {
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);

        if (userRegistration.getUserCount() < 2) {
            JOptionPane.showMessageDialog(this,
                    "You need at least two users registered to play the game.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Not enough users to start the game.");
            return;
        }

        if (loggedInUser == null) {
            JOptionPane.showMessageDialog(this,
                    "No user is currently logged in. Please log in first.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Attempt to start game without a logged-in user.");
            return;
        }

        User opponent = OpponentSelector.selectOpponent(loggedInUser, userRegistration);
        if (opponent == null) {
            return;
        }

        String selectedMode = GameModeSelector.selectGameMode();
        if (selectedMode == null) {
            return;
        }

        String playerTeam = selectedMode.equals("Heroes") ? "good" : "bad";
        String opponentTeam = playerTeam.equals("good") ? "bad" : "good";

        JOptionPane.showMessageDialog(this,
                "Starting game against " + opponent.getUsername()
                + "\nYour team: " + playerTeam
                + "\nOpponent's team: " + opponentTeam,
                "Game Start",
                JOptionPane.INFORMATION_MESSAGE);

        System.out.println("Game started against " + opponent.getUsername()
                + " with teams: " + playerTeam + " vs " + opponentTeam);

        Board gameBoard = new Board(userRegistration, loggedInUser, opponent, playerTeam, opponentTeam);
        gameBoard.setVisible(true);
        this.setVisible(false);
    }

    private void AccountButtonActionPerformed(java.awt.event.ActionEvent evt) {
        MyAccount account = new MyAccount(userRegistration, loggedInUser);
        account.setVisible(true);
        this.setVisible(false);
    }

    private void MarvelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        MarvelUniverse marvel = new MarvelUniverse(userRegistration, loggedInUser);
        marvel.setVisible(true);
        this.setVisible(false);
    }

    private void LogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartMenu menu = new StartMenu(userRegistration);
        menu.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            UserRegistration userRegistration = new UserRegistration();
            User loggedInUser = null;  // No user logged in initially
            new MainMenu(userRegistration, loggedInUser).setVisible(true);
        });
    }
}
