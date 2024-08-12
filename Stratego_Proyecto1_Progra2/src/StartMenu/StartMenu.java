package StartMenu;

import StartMenu.CreatePlayer;
import StartMenu.Login;
import player.User;
import player.UserRegistration;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JFrame {

    private UserRegistration userRegistration;

    public StartMenu(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(1200, 850));

        // Labels
        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font("Skia", Font.BOLD, 36));
        loginLabel.setBounds(550, 460, 150, 40);
        mainPanel.add(loginLabel);

        JLabel createPlayerLabel = new JLabel("CREATE PLAYER");
        createPlayerLabel.setFont(new Font("Skia", Font.BOLD, 36));
        createPlayerLabel.setBounds(460, 540, 290, 30);
        mainPanel.add(createPlayerLabel);

        JLabel exitLabel = new JLabel("EXIT");
        exitLabel.setFont(new Font("Skia", Font.BOLD, 36));
        exitLabel.setBounds(570, 620, 110, 30);
        mainPanel.add(exitLabel);

        // Buttons
        JButton loginButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        loginButton.setBounds(440, 450, 340, 60);
        loginButton.setBorder(null);
        loginButton.addActionListener(this::LogBotonActionPerformed);
        mainPanel.add(loginButton);

        JButton createButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        createButton.setBounds(440, 520, 340, 60);
        createButton.setBorder(null);
        createButton.addActionListener(this::CreateBotonActionPerformed);
        mainPanel.add(createButton);

        JButton exitButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        exitButton.setBounds(520, 610, 180, 50);
        exitButton.setBorder(null);
        exitButton.addActionListener(this::ExitBotonActionPerformed);
        mainPanel.add(exitButton);

        // Background Image
        JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/resourcesmain/mainmain.png")));
        backgroundLabel.setBounds(-150, -70, 1460, 1000);
        mainPanel.add(backgroundLabel);

        // Add panel to frame
        getContentPane().add(mainPanel);
        pack();
    }

    private void CreateBotonActionPerformed(java.awt.event.ActionEvent evt) {
        CreatePlayer createPlayer = new CreatePlayer(userRegistration);
        createPlayer.setVisible(true);
        this.setVisible(false);
    }

    private void LogBotonActionPerformed(java.awt.event.ActionEvent evt) {
        Login log = new Login(userRegistration);
        log.setVisible(true);
        this.setVisible(false);
    }

    private void ExitBotonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            UserRegistration userRegistration = new UserRegistration();
            new StartMenu(userRegistration).setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private JButton CreateBoton;
    private JButton ExitBoton;
    private JButton LogBoton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel2;
    // End of variables declaration
}
