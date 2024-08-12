package player;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private int points;
    private int gamesWithGood;
    private int gamesWithBad;
    private LocalDateTime registrationDate;
    private boolean active;
    private List<String> gameLogs;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
        this.gamesWithGood = 0;
        this.gamesWithBad = 0;
        this.registrationDate = LocalDateTime.now();
        this.active = true;
        this.gameLogs = new ArrayList<>();
        System.out.println("User created: " + this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGamesWithGood() {
        return gamesWithGood;
    }

    public void setGamesWithGood(int gamesWithGood) {
        this.gamesWithGood = gamesWithGood;
    }

    public int getGamesWithBad() {
        return gamesWithBad;
    }

    public void setGamesWithBad(int gamesWithBad) {
        this.gamesWithBad = gamesWithBad;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

   public void addGameLog(String log) {
        gameLogs.add(log);
        System.out.println("Game log added for user " + username + ": " + log);
    }

    public List<String> getLogs() {
        return new ArrayList<>(gameLogs);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", points=" + points +
                ", gamesWithGood=" + gamesWithGood +
                ", gamesWithBad=" + gamesWithBad +
                ", registrationDate=" + registrationDate +
                ", active=" + active +
                '}';
    }
}
