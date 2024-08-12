package BoardElements;

import javax.swing.*;
import java.awt.*;

public class MessageManager {

    public void showMessage(String message) {
        showMessage(message, "Message");
    }

    public void showMessage(String message, String title) {
        // Configurar las propiedades de UIManager para asegurar que los colores sean los correctos
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Button.background", Color.DARK_GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        
        // Crear una etiqueta con el mensaje y configurarla
        JLabel label = new JLabel("<html><body style='color:white;'>" + message + "</body></html>");
        label.setForeground(Color.WHITE);
        
        // Mostrar el diálogo
        JOptionPane.showMessageDialog(null, label, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showProhibitedZoneMessage() {
        showMessage("This is a prohibited zone.");
    }

    public void showInvalidMoveMessage() {
        showMessage("Invalid move.");
    }

    public void showBombCannotMoveMessage() {
        showMessage("Bombs cannot move!");
    }

    public void showTierraCannotMoveMessage() {
        showMessage("Earth cannot move!");
    }

    public void showFriendlyFireMessage() {
        showMessage("You cannot attack a friendly piece!");
    }

    public void showCannotAttackHigherRankMessage(String attacker, String defender) {
        showMessage(attacker + " cannot attack " + defender + " because of rank difference.");
    }
}
