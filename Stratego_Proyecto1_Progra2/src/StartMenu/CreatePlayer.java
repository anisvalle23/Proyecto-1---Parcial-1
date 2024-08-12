package StartMenu;

import java.awt.Color;
import javax.swing.*;
import player.UserRegistration;

public class CreatePlayer extends javax.swing.JFrame {

    private UserRegistration userRegistration;

    public CreatePlayer(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
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
        jLabel2 = new JLabel("CREATE PLAYER");
        jLabel2.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 18));
        jLabel2.setBounds(650, 580, 160, 40);
        jPanel1.add(jLabel2);

        jLabel4 = new JLabel("MENU");
        jLabel4.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel4.setBounds(490, 580, 120, 40);
        jPanel1.add(jLabel4);

        jLabel5 = new JLabel("PASSWORD:");
        jLabel5.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel5.setForeground(Color.WHITE);
        jLabel5.setBounds(550, 350, 200, 50);
        jPanel1.add(jLabel5);

        jLabel3 = new JLabel("USER:");
        jLabel3.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setBounds(580, 240, 140, 40);
        jPanel1.add(jLabel3);

        // Text Fields
        txtuser = new JTextField();
        txtuser.setBounds(510, 290, 220, 40);
        jPanel1.add(txtuser);

        txtpass = new JTextField();
        txtpass.setBounds(510, 410, 220, 40);
        jPanel1.add(txtpass);

        // Buttons
        MainButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        MainButton.setBounds(430, 580, 190, 40);
        MainButton.addActionListener(this::MainButtonActionPerformed);
        jPanel1.add(MainButton);

        CreateButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        CreateButton.setBounds(640, 580, 170, 40);
        CreateButton.addActionListener(this::CreateButtonActionPerformed);
        jPanel1.add(CreateButton);

        // Background Image
        jLabel1 = new JLabel(new ImageIcon(getClass().getResource("/resourcesmain/createp.png")));
        jLabel1.setBounds(0, -40, 1200, 910);
        jPanel1.add(jLabel1);

        // Set up JFrame content
        getContentPane().add(jPanel1);
        pack();
    }

    private void MainButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartMenu menu = new StartMenu(userRegistration);
        menu.setVisible(true);
        this.setVisible(false);
    }

    private void CreateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);

        String username = txtuser.getText();
        String password = txtpass.getText();

        // User registration
        if (userRegistration.registerUser(username, password) != null) {
            JOptionPane.showMessageDialog(this,
                    "User registered successfully!",
                    "Success",
                    JOptionPane.PLAIN_MESSAGE);
            Login startMenu = new Login(userRegistration);
            startMenu.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register user. Check the details.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            UserRegistration userRegistration = new UserRegistration();
            new CreatePlayer(userRegistration).setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton CreateButton;
    private javax.swing.JButton MainButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtpass;
    private javax.swing.JTextField txtuser;
    // End of variables declaration
}
