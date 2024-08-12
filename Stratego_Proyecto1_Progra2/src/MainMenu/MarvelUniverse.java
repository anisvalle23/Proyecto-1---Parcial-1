package MainMenu;

import MainComponents.MainMenu;
import player.User;
import player.UserRegistration;

import javax.swing.*;

public class MarvelUniverse extends javax.swing.JFrame {

    private UserRegistration userRegistration;
    private User loggedInUser;

    public MarvelUniverse(UserRegistration userRegistration, User loggedInUser) {
        this.userRegistration = userRegistration;
        this.loggedInUser = loggedInUser;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Panel setup
        jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 850));
        jPanel1.setLayout(null);

        // Labels
        jLabel4 = new JLabel("MAIN MENU");
        jLabel4.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel4.setBounds(540, 590, 180, 30);
        jPanel1.add(jLabel4);

        jLabel3 = new JLabel("BATTLES");
        jLabel3.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel3.setBounds(570, 420, 150, 40);
        jPanel1.add(jLabel3);

        jLabel2 = new JLabel("RANKING");
        jLabel2.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel2.setBounds(560, 300, 140, 40);
        jPanel1.add(jLabel2);

        // Buttons
        MainMenuButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        MainMenuButton.setBounds(510, 580, 210, 50);
        MainMenuButton.addActionListener(this::MainMenuActionPerformed);
        jPanel1.add(MainMenuButton);

        BattleButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        BattleButton.setBounds(460, 410, 320, 60);
        BattleButton.addActionListener(this::BattleButtonActionPerformed);
        jPanel1.add(BattleButton);

        RankingButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        RankingButton.setBounds(460, 290, 320, 60);
        RankingButton.addActionListener(this::RankingButtonActionPerformed);
        jPanel1.add(RankingButton);

        // Background Image
        jLabel1 = new JLabel(new ImageIcon(getClass().getResource("/resourcesmain/MARVELUNIVERSE.png")));
        jLabel1.setBounds(0, -20, 1200, 870);
        jPanel1.add(jLabel1);

        // Set up JFrame content
        getContentPane().add(jPanel1);
        pack();
    }

    private void RankingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Implement the action for the Ranking button here
    }

    private void BattleButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Implement the action for the Battle button here
    }

    private void MainMenuActionPerformed(java.awt.event.ActionEvent evt) {
        MainMenu main = new MainMenu(userRegistration, loggedInUser);
        main.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            UserRegistration userRegistration = new UserRegistration();
            User loggedInUser = null;
            new MarvelUniverse(userRegistration, loggedInUser).setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BattleButton;
    private javax.swing.JButton MainMenuButton;
    private javax.swing.JButton RankingButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration
}
