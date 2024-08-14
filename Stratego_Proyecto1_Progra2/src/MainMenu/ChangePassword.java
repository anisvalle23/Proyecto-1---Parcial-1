package MainMenu;

import player.User;
import player.UserRegistration;

import javax.swing.*;

public class ChangePassword extends javax.swing.JFrame {
    private UserRegistration userRegistration;
    private User loggedInUser;

    public ChangePassword(UserRegistration userRegistration, User loggedInUser) {
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
        jLabel4 = new JLabel("NEW PASSWORD:");
        jLabel4.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setBounds(520, 370, 270, 50);
        jPanel1.add(jLabel4);

        jLabel5 = new JLabel("ACCEPT");
        jLabel5.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel5.setBounds(680, 550, 140, 50);
        jPanel1.add(jLabel5);

        jLabel3 = new JLabel("BACK");
        jLabel3.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel3.setBounds(490, 550, 120, 50);
        jPanel1.add(jLabel3);

        jLabel2 = new JLabel("CURRENT PASSWORD:");
        jLabel2.setFont(new java.awt.Font("Skia", java.awt.Font.BOLD, 24));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setBounds(490, 240, 310, 40);
        jPanel1.add(jLabel2);

        // Text Fields
        jPasswordField1 = new JPasswordField();
        jPasswordField1.setBounds(510, 300, 240, 40);
        jPanel1.add(jPasswordField1);

        jFormattedTextField1 = new JFormattedTextField();
        jFormattedTextField1.setBounds(510, 430, 240, 40);
        jPanel1.add(jFormattedTextField1);

        // Buttons
        AcceptButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        AcceptButton.setBounds(650, 550, 150, 50);
        AcceptButton.addActionListener(this::AcceptButtonActionPerformed);
        jPanel1.add(AcceptButton);

        BackButton = new JButton(new ImageIcon(getClass().getResource("/resourcesmain/botomain1.png")));
        BackButton.setBounds(450, 550, 150, 50);
        BackButton.addActionListener(this::BackButtonActionPerformed);
        jPanel1.add(BackButton);

        // Background Image
        jLabel1 = new JLabel(new ImageIcon(getClass().getResource("/resourcesmain/change.png")));
        jLabel1.setBounds(0, -20, 1200, 870);
        jPanel1.add(jLabel1);

        // Set up JFrame content
        getContentPane().add(jPanel1);
        pack();
    }

    private void AcceptButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String currentPassword = new String(jPasswordField1.getPassword());
        String newPassword = jFormattedTextField1.getText();

        // Validar la contraseña actual
        if (!userRegistration.validateUser(loggedInUser.getUsername(), currentPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Current password is incorrect.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar la nueva contraseña
        if (newPassword.length() != 5) {
            JOptionPane.showMessageDialog(this,
                    "New password must be exactly 5 characters long.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Actualizar la contraseña
        loggedInUser.setPassword(newPassword);
        JOptionPane.showMessageDialog(this,
                "Password changed successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        // Cerrar la ventana actual y volver a Mi Perfil
        MyAccount myAccount = new MyAccount(userRegistration, loggedInUser);
        myAccount.setVisible(true);
        this.dispose();
    }

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {
        MyAccount back = new MyAccount(userRegistration, loggedInUser);
        back.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            UserRegistration userRegistration = new UserRegistration();
            User loggedInUser = null;
            new ChangePassword(userRegistration, loggedInUser).setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton AcceptButton;
    private javax.swing.JButton BackButton;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration
}
