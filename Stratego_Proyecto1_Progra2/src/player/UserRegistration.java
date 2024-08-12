package player;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.util.ArrayList;
import java.util.List;

public class UserRegistration {

    private List<User> users;
    private User loggedInUser;

    public UserRegistration() {
        this.users = new ArrayList<>();
        this.loggedInUser = null;
        System.out.println("UserRegistration initialized.");
    }

    public User registerUser(String username, String password) {
        if (password.length() != 5) {
            showMessage("Password must be exactly 5 characters long.", "Error");
            return null;
        }

        if (isUsernameTaken(username)) {
            showMessage("Username already taken. Please choose another one.", "Error");
            return null;
        }

        User newUser = new User(username, password);
        users.add(newUser);
        showWelcomeMessage(newUser);
        System.out.println("User registered: " + newUser);
        return newUser;
    }

    private boolean isUsernameTaken(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    private void showWelcomeMessage(User user) {
        String message = "<html><body style='background-color:black;color:white;'><h2>User registered successfully!</h2>"
                + "<p>Welcome, <strong>" + user.getUsername() + "</strong>!</p></body></html>";
        JLabel label = new JLabel(message);

        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        JOptionPane.showMessageDialog(null, label, "Registration Successful", JOptionPane.PLAIN_MESSAGE);
    }

    private void showMessage(String message, String title) {
        JLabel label = new JLabel("<html><body style='color:white;'>" + message + "</body></html>");

        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        JOptionPane.showMessageDialog(null, label, title, JOptionPane.ERROR_MESSAGE);
    }

    public boolean validateUser(String username, String password) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    public User getUser(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void loginUser(String username) {
        this.loggedInUser = getUser(username);
        System.out.println("User logged in: " + loggedInUser);
    }

    public void logoutUser() {
        System.out.println("User logged out: " + loggedInUser);
        this.loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public int getUserCount() {
        return users.size();
    }

    public List<User> getOpponents(User currentUser) {
        if (currentUser == null) {
            throw new IllegalArgumentException("Current user cannot be null");
        }

        List<User> opponents = new ArrayList<>();
        for (User user : users) {
            if (!user.getUsername().equals(currentUser.getUsername())) {
                opponents.add(user);
            }
        }
        System.out.println("Opponents for " + currentUser.getUsername() + ": " + opponents);
        return opponents;
    }

    public List<String> getUsernamesExcept(String currentUsername) {
        List<String> usernames = new ArrayList<>();
        for (User user : users) {
            if (!user.getUsername().equals(currentUsername)) {
                usernames.add(user.getUsername());
            }
        }
        return usernames;
    }
}
