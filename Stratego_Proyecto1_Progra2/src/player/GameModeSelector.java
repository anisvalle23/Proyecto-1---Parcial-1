package player;

import javax.swing.JOptionPane;

public class GameModeSelector {

    public static String selectGameMode() {
        String[] options = {"Heroes", "Villains"};
        String selectedMode = (String) JOptionPane.showInputDialog(null, 
                "Select your game mode:", "Game Mode Selection", 
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedMode != null) {
            System.out.println("Game mode selected: " + selectedMode);
        } else {
            System.out.println("No game mode selected.");
        }
        
        return selectedMode;
    }
}
