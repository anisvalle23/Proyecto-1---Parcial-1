package BoardElements;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class IconManager {
    private ImageManager imageManager;
    private JButton[][] tilesBTN;
    private int tileWidth;
    private int tileHeight;
    private List<Piece> heroesPieces;
    private List<Piece> villainsPieces;
    private JLabel selectedPieceLabel;
    private Set<String> heroesPieceNames;
    private Set<String> villainsPieceNames;

    public IconManager(JButton[][] tilesBTN, ImageManager imageManager, int tileWidth, int tileHeight, JLabel selectedPieceLabel) {
        this.tilesBTN = tilesBTN;
        this.imageManager = imageManager;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.selectedPieceLabel = selectedPieceLabel;
        this.heroesPieceNames = new HashSet<>();
        this.villainsPieceNames = new HashSet<>();
        initializePieceLists();
    }

    private void initializePieceLists() {
        heroesPieces = new ArrayList<>();
        villainsPieces = new ArrayList<>();

        // Add heroes pieces
        addPieces(heroesPieces, "resourcesplayer/", new String[]{
            "fantastic.png", "captain.png", "professor.png", "nick.png", "spider.png", "wolverine.png", "namor.png", "daredevil.png", "silver.png", "hulk.png",
            "iron.png", "thor.png", "human.png", "cyclops.png", "invisible.png", "ghost.png", "punisher.png", "blade.png", "thing.png", "emma.png",
            "shehulk.png", "giant.png", "beast.png", "colossus.png", "gambit.png", "spidergirl.png", "ice.png", "storm.png", "phoenix.png", "dr.png",
            "elektra.png", "night.png", "blackgood.png", "nova.png", "nova.png", "nova.png", "nova.png", "nova.png", "nova.png", "planetgood.png"
        }, new PieceType[]{
            PieceType.HEROES_RANGO10, PieceType.HEROES_RANGO9, PieceType.HEROES_RANGO8_1, PieceType.HEROES_RANGO8_2, PieceType.HEROES_RANGO7_1, PieceType.HEROES_RANGO7_2, PieceType.HEROES_RANGO7_3, PieceType.HEROES_RANGO6_1, PieceType.HEROES_RANGO6_2, PieceType.HEROES_RANGO6_3,
            PieceType.HEROES_RANGO6_4, PieceType.HEROES_RANGO5_1, PieceType.HEROES_RANGO5_2, PieceType.HEROES_RANGO5_3, PieceType.HEROES_RANGO5_4, PieceType.HEROES_RANGO4_1, PieceType.HEROES_RANGO4_2, PieceType.HEROES_RANGO4_3, PieceType.HEROES_RANGO4_4, PieceType.HEROES_RANGO3_1,
            PieceType.HEROES_RANGO3_2, PieceType.HEROES_RANGO3_3, PieceType.HEROES_RANGO3_4, PieceType.HEROES_RANGO3_5, PieceType.HEROES_RANGO2_1, PieceType.HEROES_RANGO2_2, PieceType.HEROES_RANGO2_3, PieceType.HEROES_RANGO2_4, PieceType.HEROES_RANGO2_5, PieceType.HEROES_RANGO2_6,
            PieceType.HEROES_RANGO2_7, PieceType.HEROES_RANGO2_8, PieceType.HEROES_RANGO1, PieceType.HEROES_BOMBA, PieceType.HEROES_BOMBA, PieceType.HEROES_BOMBA, PieceType.HEROES_BOMBA, PieceType.HEROES_BOMBA, PieceType.HEROES_BOMBA, PieceType.HEROES_EARTH
        }, "heroes");

        // Add villains pieces
        addPieces(villainsPieces, "resourcesplayer/", new String[]{
            "doom.png", "galactus.png", "king.png", "magneto.png", "apoca.png", "green.png", "venom.png", "bull.png", "omega.png", "ons.png",
            "red.png", "mystique.png", "mysterio.png", "octopus.png", "deadpool.png", "abodimi.png", "thanos.png", "cat.png", "sabre.png", "jugger.png",
            "rhino.png", "carnage.png", "mole.png", "lizard.png", "sinister.png", "sentinel1.png", "ultron.png", "sandman.png", "leader.png", "viper.png",
            "sentinel2.png", "electro.png", "blackbad.png", "pumpkin.png", "pumpkin.png", "pumpkin.png", "pumpkin.png", "pumpkin.png", "pumpkin.png", "plantebad.png"
        }, new PieceType[]{
            PieceType.VILLAINS_RANGO10, PieceType.VILLAINS_RANGO9, PieceType.VILLAINS_RANGO8_1, PieceType.VILLAINS_RANGO8_2, PieceType.VILLAINS_RANGO7_1, PieceType.VILLAINS_RANGO7_2, PieceType.VILLAINS_RANGO7_3, PieceType.VILLAINS_RANGO6_1, PieceType.VILLAINS_RANGO6_2, PieceType.VILLAINS_RANGO6_3,
            PieceType.VILLAINS_RANGO6_4, PieceType.VILLAINS_RANGO5_1, PieceType.VILLAINS_RANGO5_2, PieceType.VILLAINS_RANGO5_3, PieceType.VILLAINS_RANGO5_4, PieceType.VILLAINS_RANGO4_1, PieceType.VILLAINS_RANGO4_2, PieceType.VILLAINS_RANGO4_3, PieceType.VILLAINS_RANGO4_4, PieceType.VILLAINS_RANGO3_1,
            PieceType.VILLAINS_RANGO3_2, PieceType.VILLAINS_RANGO3_3, PieceType.VILLAINS_RANGO3_4, PieceType.VILLAINS_RANGO3_5, PieceType.VILLAINS_RANGO2_1, PieceType.VILLAINS_RANGO2_2, PieceType.VILLAINS_RANGO2_3, PieceType.VILLAINS_RANGO2_4, PieceType.VILLAINS_RANGO2_5, PieceType.VILLAINS_RANGO2_6,
            PieceType.VILLAINS_RANGO2_7, PieceType.VILLAINS_RANGO2_8, PieceType.VILLAINS_RANGO1, PieceType.VILLAINS_BOMBA, PieceType.VILLAINS_BOMBA, PieceType.VILLAINS_BOMBA, PieceType.VILLAINS_BOMBA, PieceType.VILLAINS_BOMBA, PieceType.VILLAINS_BOMBA, PieceType.VILLAINS_EARTH
        }, "villains");
    }

    private void addPieces(List<Piece> pieces, String basePath, String[] filenames, PieceType[] types, String team) {
        for (int i = 0; i < filenames.length; i++) {
            ImageIcon icon = imageManager.scaleImage(basePath + filenames[i], tileWidth, tileHeight);
            icon.setDescription(team + "_" + types[i].name());
            System.out.println("Assigned icon description: " + icon.getDescription()); // Mensaje de depuración
            pieces.add(new Piece(types[i], icon));
        }
    }

    public void assignIcons() {
        // Colocar Tierra y Bombas en la fila 0 para los héroes y fila 9 para los villanos
        placeTierraAndBombas(heroesPieces, 0);
        placeTierraAndBombas(villainsPieces, 9);

        // Colocar las Bombas restantes en las filas 0-1 para los héroes y filas 8-9 para los villanos
        placeRemainingBombas(heroesPieces, 0, 1);
        placeRemainingBombas(villainsPieces, 8, 9);

        // Colocar las piezas de rango 2 en las filas 2-3 para los héroes y filas 6-7 para los villanos
        placeRango2Pieces(heroesPieces, 2, 3);
        placeRango2Pieces(villainsPieces, 6, 7);

        // Colocar las piezas de la primera fila en la fila 3 para los héroes y fila 6 para los villanos
        placeFirstRowPieces(heroesPieces, 3);
        placeFirstRowPieces(villainsPieces, 6);

        // Rellenar los espacios restantes en las filas 0-3 para los héroes y filas 6-9 para los villanos
        fillRemainingSpaces(heroesPieces, 0, 3);
        fillRemainingSpaces(villainsPieces, 6, 9);
    }

    private void placeTierraAndBombas(List<Piece> pieces, int row) {
        PieceType earthType = row == 0 ? PieceType.HEROES_EARTH : PieceType.VILLAINS_EARTH;
        PieceType bombType = row == 0 ? PieceType.HEROES_BOMBA : PieceType.VILLAINS_BOMBA;

        // Colocar la pieza Tierra
        int tierraIndex = findPieceIndex(pieces, earthType);
        if (tierraIndex == -1) {
            throw new IllegalStateException((row == 0 ? "Heroes" : "Villains") + " Earth piece not found");
        }
        Piece tierra = pieces.remove(tierraIndex);
        int tierraCol = 1 + (int) (Math.random() * 8);
        tilesBTN[row][tierraCol].setIcon(tierra.getIcon());

        // Colocar las bombas alrededor de Tierra
        int[] bombCols = {tierraCol - 1, tierraCol + 1};
        for (int col : bombCols) {
            int bombaIndex = findPieceIndex(pieces, bombType);
            if (bombaIndex != -1) {
                Piece bomba = pieces.remove(bombaIndex);
                tilesBTN[row][col].setIcon(bomba.getIcon());
            }
        }
    }

    private void placeRemainingBombas(List<Piece> pieces, int startRow, int endRow) {
        PieceType bombType = startRow == 0 ? PieceType.HEROES_BOMBA : PieceType.VILLAINS_BOMBA;

        List<Integer> availablePositions = new ArrayList<>();
        for (int row = startRow; row <= endRow; row++) {
            for (int col = 0; col < 10; col++) {
                if (tilesBTN[row][col].getIcon() == null) {
                    availablePositions.add(row * 10 + col);
                }
            }
        }
        Collections.shuffle(availablePositions);
        while (true) {
            int bombaIndex = findPieceIndex(pieces, bombType);
            if (bombaIndex == -1) break;
            int pos = availablePositions.remove(0);
            int row = pos / 10;
            int col = pos % 10;
            tilesBTN[row][col].setIcon(pieces.remove(bombaIndex).getIcon());
        }
    }

    private void placeRango2Pieces(List<Piece> pieces, int startRow, int endRow) {
        List<Integer> availablePositions = new ArrayList<>();
        for (int row = startRow; row <= endRow; row++) {
            for (int col = 0; col < 10; col++) {
                if (tilesBTN[row][col].getIcon() == null) {
                    availablePositions.add(row * 10 + col);
                }
            }
        }
        Collections.shuffle(availablePositions);
        while (true) {
            int rango2Index = findRango2PieceIndex(pieces, startRow == 2 ? "heroes" : "villains");
            if (rango2Index == -1) break;
            int pos = availablePositions.remove(0);
            int row = pos / 10;
            int col = pos % 10;
            tilesBTN[row][col].setIcon(pieces.remove(rango2Index).getIcon());
        }
    }

    private void placeFirstRowPieces(List<Piece> pieces, int row) {
        List<Integer> availablePositions = new ArrayList<>();
        for (int col = 0; col < 10; col++) {
            if (tilesBTN[row][col].getIcon() == null) {
                availablePositions.add(row * 10 + col);
            }
        }
        Collections.shuffle(availablePositions);
        while (!availablePositions.isEmpty() && !pieces.isEmpty()) {
            int pos = availablePositions.remove(0);
            int col = pos % 10;
            tilesBTN[row][col].setIcon(pieces.remove(0).getIcon());
        }
    }

    private void fillRemainingSpaces(List<Piece> pieces, int startRow, int endRow) {
        List<Integer> availablePositions = new ArrayList<>();
        for (int row = startRow; row <= endRow; row++) {
            for (int col = 0; col < 10; col++) {
                if (tilesBTN[row][col].getIcon() == null) {
                    availablePositions.add(row * 10 + col);
                }
            }
        }
        Collections.shuffle(availablePositions);
        while (!availablePositions.isEmpty() && !pieces.isEmpty()) {
            int pos = availablePositions.remove(0);
            int row = pos / 10;
            int col = pos % 10;
            tilesBTN[row][col].setIcon(pieces.remove(0).getIcon());
        }
    }

    private int findPieceIndex(List<Piece> pieces, PieceType type) {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getType() == type) {
                return i;
            }
        }
        return -1;
    }

    private int findRango2PieceIndex(List<Piece> pieces, String team) {
        for (int i = 0; i < pieces.size(); i++) {
            if ((team.equals("heroes") && pieces.get(i).getType().name().startsWith("GOOD_RANGO2")) ||
                (team.equals("villains") && pieces.get(i).getType().name().startsWith("BAD_RANGO2"))) {
                return i;
            }
        }
        return -1;
    }

    public Set<String> getHeroesPieceNames() {
        return heroesPieceNames;
    }

    public Set<String> getVillainsPieceNames() {
        return villainsPieceNames;
    }
}
