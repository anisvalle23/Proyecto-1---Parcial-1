package MainMenu;

import MainComponents.MainMenu;
import player.User;
import player.UserRegistration;

import javax.swing.*;
import java.util.List;

public class MyAccount extends javax.swing.JFrame {

    private UserRegistration userRegistration;
    private User loggedInUser;

    public MyAccount(UserRegistration userRegistration, User loggedInUser) {
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
        jLabel2 = new JLabel("LOG OF LATEST GAMES");
        jLabel2.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel2.setBounds(490, 240, 270, 40);
        jPanel1.add(jLabel2);

        jLabel5 = new JLabel("MAIN MENU");
        jLabel5.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel5.setBounds(550, 600, 170, 50);
        jPanel1.add(jLabel5);

        jLabel3 = new JLabel("CHANGE PASSWORD");
        jLabel3.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel3.setBounds(500, 337, 260, 50);
        jPanel1.add(jLabel3);

        jLabel4 = new JLabel("DELETE ACCOUNT");
        jLabel4.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel4.setBounds(520, 440, 240, 40);
        jPanel1.add(jLabel4);

        // Buttons
        LogGamesButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        LogGamesButton.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 18));
        LogGamesButton.setBounds(460, 230, 330, 60);
        LogGamesButton.addActionListener(this::LogGamesButtonActionPerformed); // Activar el botón
        jPanel1.add(LogGamesButton);

        ChangePassButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        ChangePassButton.setBounds(460, 330, 330, 60);
        ChangePassButton.addActionListener(this::ChangePassButtonActionPerformed);
        jPanel1.add(ChangePassButton);

        DeleteButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        DeleteButton.setBounds(460, 430, 330, 60);
        DeleteButton.addActionListener(this::DeleteButtonActionPerformed);
        jPanel1.add(DeleteButton);

        MainMenuButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        MainMenuButton.setBounds(500, 600, 240, 50);
        MainMenuButton.addActionListener(this::MainMenuButtonActionPerformed);
        jPanel1.add(MainMenuButton);

        // Background Image
        jLabel1 = new JLabel(new ImageIcon(getClass().getResource("/resourcesmain/MYACCOUNT.png")));
        jLabel1.setBounds(0, -40, 1200, 910);
        jPanel1.add(jLabel1);

        // Set up JFrame content
        getContentPane().add(jPanel1);
        pack();
    }

    private void LogGamesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        List<String> logs = loggedInUser.getLogs();

        if (logs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No games played yet.", "Game Logs", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder logsMessage = new StringBuilder("<html><body><h2>Latest Games</h2><ul>");
            for (String log : logs) {
                logsMessage.append("<li>").append(log).append("</li>");
            }
            logsMessage.append("</ul></body></html>");

            JLabel logsLabel = new JLabel(logsMessage.toString());
            JScrollPane scrollPane = new JScrollPane(logsLabel);
            scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Game Logs", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void MainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        MainMenu main = new MainMenu(userRegistration, loggedInUser);
        main.setVisible(true);
        this.setVisible(false);
    }

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DeleteAccount delete = new DeleteAccount(userRegistration, loggedInUser);
        delete.setVisible(true);
        this.setVisible(false);
    }

    private void ChangePassButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ChangePassword change = new ChangePassword(userRegistration, loggedInUser);
        change.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            UserRegistration userRegistration = new UserRegistration();
            User loggedInUser = null;
            new MyAccount(userRegistration, loggedInUser).setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton ChangePassButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton LogGamesButton;
    private javax.swing.JButton MainMenuButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration
}
