

import StartMenu.StartMenu;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import player.UserRegistration;

public static void main(String[] args) {
    // Configurar los mensajes de advertencia con letras blancas y fondo negro
    UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));
    UIManager.put("Panel.background", new ColorUIResource(Color.BLACK));
    UIManager.put("OptionPane.background", new ColorUIResource(Color.BLACK));
    UIManager.put("Button.background", new ColorUIResource(Color.DARK_GRAY));
    UIManager.put("Button.foreground", new ColorUIResource(Color.WHITE));
    
    // El resto de la inicialización de tu aplicación
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            UserRegistration userRegistration = new UserRegistration();
            new StartMenu(userRegistration).setVisible(true);
        }
    });
}
