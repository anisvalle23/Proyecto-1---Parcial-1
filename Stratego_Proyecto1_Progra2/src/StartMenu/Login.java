package StartMenu;

import MainComponents.MainMenu;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import player.User;
import player.UserRegistration;

public class Login extends javax.swing.JFrame {

    private UserRegistration userRegistration;

    public Login(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Panel setup
        jPanel2 = new javax.swing.JPanel();
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 850));
        jPanel2.setLayout(null);

        // Labels
        jLabel5 = new javax.swing.JLabel("MENU");
        jLabel5.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel5.setBounds(480, 610, 140, 20);
        jPanel2.add(jLabel5);

        jLabel4 = new javax.swing.JLabel("LOGIN");
        jLabel4.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel4.setBounds(670, 600, 90, 40);
        jPanel2.add(jLabel4);

        jLabel3 = new javax.swing.JLabel("PASSWORD:");
        jLabel3.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel3.setForeground(new Color(255, 255, 255));
        jLabel3.setBounds(560, 410, 230, 40);
        jPanel2.add(jLabel3);

        jLabel2 = new javax.swing.JLabel("USER:");
        jLabel2.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel2.setForeground(new Color(255, 255, 255));
        jLabel2.setBounds(590, 220, 220, 110);
        jPanel2.add(jLabel2);

        // Text Fields
        txtpass = new javax.swing.JPasswordField();
        txtpass.setBounds(500, 470, 250, 40);
        jPanel2.add(txtpass);

        txtuser = new javax.swing.JFormattedTextField();
        txtuser.setBounds(500, 310, 250, 50);
        jPanel2.add(txtuser);

        // Buttons
        LogButton = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        LogButton.setBounds(630, 600, 170, 40);
        LogButton.addActionListener(this::LogButtonActionPerformed);
        jPanel2.add(LogButton);

        MainButton = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        MainButton.setBounds(440, 600, 180, 40);
        MainButton.addActionListener(this::MainButtonActionPerformed);
        jPanel2.add(MainButton);

        // Background Image
        jLabel1 = new javax.swing.JLabel(new javax.swing.ImageIcon(getClass().getResource("/resourcesmain/LOG.png")));
        jLabel1.setBounds(0, -40, 1200, 910);
        jPanel2.add(jLabel1);

        // Set up JFrame content
        getContentPane().add(jPanel2);
        pack();
    }

    private void MainButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartMenu menu = new StartMenu(userRegistration);
        menu.setVisible(true);
        this.setVisible(false);
    }

    private void LogButtonActionPerformed(java.awt.event.ActionEvent evt) {
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);

        String username = txtuser.getText();
        String password = new String(txtpass.getPassword());

        // Validate credentials
        if (userRegistration.validateUser(username, password)) {
            userRegistration.loginUser(username);  // Log the user in
            User loggedInUser = userRegistration.getLoggedInUser();

            if (loggedInUser != null) {
                MainMenu mainMenu = new MainMenu(userRegistration, loggedInUser);  // Pass the logged-in user to MainMenu
                mainMenu.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Login failed. Please try again.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                System.out.println("Login failed for username: " + username);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Login failed for username: " + username);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            UserRegistration userRegistration = new UserRegistration();
            new Login(userRegistration).setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton LogButton;
    private javax.swing.JButton MainButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JFormattedTextField txtuser;
    // End of variables declaration
}
