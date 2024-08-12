package MainMenu;

import StartMenu.StartMenu;
import player.User;
import player.UserRegistration;

import javax.swing.*;

public class DeleteAccount extends javax.swing.JFrame {

    private UserRegistration userRegistration;
    private User loggedInUser;

    public DeleteAccount(UserRegistration userRegistration, User loggedInUser) {
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
        jLabel4 = new JLabel("PASSWORD REQUIRED:");
        jLabel4.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setBounds(490, 310, 300, 50);
        jPanel1.add(jLabel4);

        jLabel3 = new JLabel("BACK");
        jLabel3.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel3.setBounds(490, 530, 70, 20);
        jPanel1.add(jLabel3);

        jLabel1 = new JLabel("ACCEPT");
        jLabel1.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel1.setBounds(670, 520, 110, 40);
        jPanel1.add(jLabel1);

        // Password Field
        jPasswordField1 = new JPasswordField();
        jPasswordField1.setBounds(500, 400, 260, 30);
        jPanel1.add(jPasswordField1);

        // Buttons
        AcceptButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        AcceptButton.setBounds(650, 520, 140, 40);
        AcceptButton.addActionListener(this::AcceptButtonActionPerformed);
        jPanel1.add(AcceptButton);

        BackButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        BackButton.setBounds(450, 520, 140, 40);
        BackButton.addActionListener(this::BackButtonActionPerformed);
        jPanel1.add(BackButton);

        // Background Image
        jLabel5 = new JLabel(new ImageIcon(getClass().getResource("/resourcesmain/deleteaccount.png")));
        jLabel5.setBounds(0, -90, 1200, 1010);
        jPanel1.add(jLabel5);

        // Set up JFrame content
        getContentPane().add(jPanel1);
        pack();
    }

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {
        MyAccount back = new MyAccount(userRegistration, loggedInUser);
        back.setVisible(true);
        this.setVisible(false);
    }

    private void AcceptButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String password = new String(jPasswordField1.getPassword());

        // Validate the password and delete the account if valid
        if (userRegistration.validateUser(loggedInUser.getUsername(), password)) {
//            userRegistration.deleteUser(loggedInUser.getUsername());
            JOptionPane.showMessageDialog(this,
                    "Account deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            StartMenu startMenu = new StartMenu(userRegistration);
            startMenu.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid password. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            UserRegistration userRegistration = new UserRegistration();
            User loggedInUser = null;
            new DeleteAccount(userRegistration, loggedInUser).setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton AcceptButton;
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration
}
