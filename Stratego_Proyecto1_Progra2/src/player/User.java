package player;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
       private int points;
    private int gamesWithGood; // Contador de victorias con héroes
    private int gamesWithBad;  // Contador de victorias con villanos
    private int totalGamesPlayed; // Total de partidas jugadas
    private LocalDateTime registrationDate;
    private boolean active;
    private String lastPlayedTeam;
    private List<String> gameLogs;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
        this.gamesWithGood = 0;
        this.gamesWithBad = 0;
        this.totalGamesPlayed = 0;
        this.registrationDate = LocalDateTime.now();
        this.active = true;
        this.gameLogs = new ArrayList<>();
        this.lastPlayedTeam = "None";
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

    // Nuevo método para añadir puntos al usuario
    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
        System.out.println("Added " + pointsToAdd + " points to user " + username + ". Total points: " + this.points);
    }

  public void incrementGamesWithGood() {
        this.gamesWithGood++;
        this.totalGamesPlayed++; // Incrementar el total de partidas jugadas
        this.lastPlayedTeam = "Good"; // Actualizar el equipo jugado
        System.out.println("Incrementando victorias de héroes para " + this.username + ". Total de victorias de héroes: " + this.gamesWithGood);
    }

    public void incrementGamesWithBad() {
        this.gamesWithBad++;
        this.totalGamesPlayed++; // Incrementar el total de partidas jugadas
        this.lastPlayedTeam = "Bad"; // Actualizar el equipo jugado
        System.out.println("Incrementando victorias de villanos para " + this.username + ". Total de victorias de villanos: " + this.gamesWithBad);
    }

    // Método getter para obtener el total de partidas jugadas
    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }
    public String getLastPlayedTeam() {
    return lastPlayedTeam;
}

    // Métodos getters
    public int getGamesWithGood() {
        return gamesWithGood;
    }

    public int getGamesWithBad() {
        return gamesWithBad;
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

    public void incrementGoodWins() {
        this.gamesWithGood++;
    }

    public void incrementBadWins() {
        this.gamesWithBad++;
    }

    public List<String> getLogs() {
        return new ArrayList<>(gameLogs);
    }

    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", points=" + points
                + ", gamesWithGood=" + gamesWithGood
                + ", gamesWithBad=" + gamesWithBad
                + ", registrationDate=" + registrationDate
                + ", active=" + active
                + '}';
    }
}
