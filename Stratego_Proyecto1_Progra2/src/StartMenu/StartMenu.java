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
    
        // Clase abstracta temporizador 
    public abstract class temporizador {

        // Parámetros esenciales para un temporizador
        private long startTime;
        private long endTime;
        private long duration;

        public temporizador(long duration) {
            this.duration = duration;
        }

        protected abstract void onTimerStart();
        protected abstract void onTimerEnd();
        protected abstract void onTimerTick(long remainingTime);

        public void start() {
            startTime = System.currentTimeMillis();
            endTime = startTime + duration;
            onTimerStart();

            while (System.currentTimeMillis() < endTime) {
                long remainingTime = endTime - System.currentTimeMillis();
                onTimerTick(remainingTime);
                try {
                    Thread.sleep(1000); // Espera 1 segundo entre cada tick
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            onTimerEnd();
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }
    }

    // herencia: Clase concreta timer que hereda de temporizador
    public class Timer extends temporizador {

        public Timer(long duration) {
            super(duration);
        }

        @Override
        protected void onTimerStart() {
            System.out.println("Temporizador iniciado. Duración: " + getDuration() + " milisegundos.");
        }

        @Override
        protected void onTimerEnd() {
            System.out.println("Temporizador finalizado.");
        }

        @Override
        protected void onTimerTick(long remainingTime) {
            System.out.println("Tiempo restante: " + remainingTime + " milisegundos.");
        }
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
