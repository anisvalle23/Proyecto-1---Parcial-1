package player;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.stream.Collectors;

public class OpponentSelector {

    public static User selectOpponent(User currentUser, UserRegistration userRegistration) {
        List<User> opponents = userRegistration.getOpponents(currentUser);

        if (opponents.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No opponents available.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("No opponents available for " + currentUser.getUsername());
            return null;
        }

        String[] opponentNames = opponents.stream()
                .map(User::getUsername)
                .toArray(String[]::new);

        String selectedOpponent = (String) JOptionPane.showInputDialog(null, 
                "Select your opponent:", "Opponent Selection", 
                JOptionPane.QUESTION_MESSAGE, null, opponentNames, opponentNames[0]);

        if (selectedOpponent != null) {
            User opponent = userRegistration.getUser(selectedOpponent);
            System.out.println(currentUser.getUsername() + " selected " + opponent.getUsername() + " as opponent.");
            return opponent;
        }
        
        System.out.println(currentUser.getUsername() + " did not select an opponent.");
        return null;
    }
}
