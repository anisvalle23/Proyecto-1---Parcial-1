package BoardElements;

import javax.swing.*;
import java.awt.*;

public class BattleMessagePanel extends JPanel {
    public BattleMessagePanel(String attacker, String defender, ImageIcon attackerIcon, ImageIcon defenderIcon, PieceType attackerType, PieceType defenderType) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK); // Background color

        // Define fixed colors for the gradient
        Color greenColor = new Color(0, 128, 0); // Verde
        Color redColor = new Color(139, 0, 0); // Rojo

        // Title
        JLabel titleLabel = new JLabel("Battle!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE); // White color for the title
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Center panel for images and names
        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                // Left side (green)
                GradientPaint greenGradient = new GradientPaint(0, 0, greenColor.darker(), width / 2, 0, greenColor.brighter());
                g2d.setPaint(greenGradient);
                g2d.fillRect(0, 0, width / 2, height);

                // Right side (red)
                GradientPaint redGradient = new GradientPaint(width / 2, 0, redColor.darker(), width, 0, redColor.brighter());
                g2d.setPaint(redGradient);
                g2d.fillRect(width / 2, 0, width, height);
            }
        };
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setOpaque(false); // Allow the custom painting to show through

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Attacker
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel attackerImageLabel = new JLabel(attackerIcon);
        centerPanel.add(attackerImageLabel, gbc);

        gbc.gridy = 1;
        JLabel attackerLabel = new JLabel(attacker, SwingConstants.CENTER);
        attackerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        attackerLabel.setForeground(Color.WHITE); // White color for the text
        centerPanel.add(attackerLabel, gbc);

        // VS Label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        JLabel vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        vsLabel.setForeground(Color.WHITE);
        centerPanel.add(vsLabel, gbc);

        // Defender
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        JLabel defenderImageLabel = new JLabel(defenderIcon);
        centerPanel.add(defenderImageLabel, gbc);

        gbc.gridy = 1;
        JLabel defenderLabel = new JLabel(defender, SwingConstants.CENTER);
        defenderLabel.setFont(new Font("Arial", Font.BOLD, 20));
        defenderLabel.setForeground(Color.WHITE); // White color for the text
        centerPanel.add(defenderLabel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Determine battle result
        String battleResult = determineBattleResult(attackerType, defenderType);

        // Result label
        JLabel resultLabel = new JLabel(battleResult, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(resultLabel, BorderLayout.SOUTH);

        // Increase the size of the panel
        setPreferredSize(new Dimension(600, 400));
    }

      private String determineBattleResult(PieceType attackerType, PieceType defenderType) {
        if ((attackerType == PieceType.HEROES_RANGO1 || attackerType == PieceType.VILLAINS_RANGO1) && 
            (defenderType == PieceType.HEROES_RANGO10 || defenderType == PieceType.VILLAINS_RANGO10)) {
            return attackerType.getName() + " wins! Rank 1 defeats Rank 10.";
        } else if ((defenderType == PieceType.HEROES_RANGO1 || defenderType == PieceType.VILLAINS_RANGO1) && 
                   (attackerType == PieceType.HEROES_RANGO10 || attackerType == PieceType.VILLAINS_RANGO10)) {
            return defenderType.getName() + " wins! Rank 1 defeats Rank 10.";
        } else if (defenderType == PieceType.HEROES_BOMBA || defenderType == PieceType.VILLAINS_BOMBA) {
            if (attackerType.name().contains("RANGO3")) {
                return attackerType.getName() + " wins! Rank 3 defeats bomb.";
            } else {
                return defenderType.getName() + " wins! Bomb defeats " + attackerType.getName() + ".";
            }
        } else if (attackerType.getRank() == defenderType.getRank()) {
            return "Draw: Both pieces are of the same rank.";
        } else if (attackerType.getRank() > defenderType.getRank()) {
            return attackerType.getName() + " wins!";
        } else {
            return defenderType.getName() + " wins!";
        }
    }
}
