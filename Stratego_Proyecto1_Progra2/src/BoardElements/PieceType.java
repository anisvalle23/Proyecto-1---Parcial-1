package BoardElements;

public enum PieceType {
    // Piezas de Héroes
    HEROES_RANGO10(10, "Mr. Fantastic"),
    HEROES_RANGO9(9, "Captain America"),
    HEROES_RANGO8_1(8, "Professor X"),
    HEROES_RANGO8_2(8, "Nick Fury"),
    HEROES_RANGO7_1(7, "Spiderman"),
    HEROES_RANGO7_2(7, "Wolverine"),
    HEROES_RANGO7_3(7, "Namor"),
    HEROES_RANGO6_1(6, "Daredevil"),
    HEROES_RANGO6_2(6, "Silver Surfer"),
    HEROES_RANGO6_3(6, "Hulk"),
    HEROES_RANGO6_4(6, "Ironman"),
    HEROES_RANGO5_1(5, "Thor"),
    HEROES_RANGO5_2(5, "Human Torch"),
    HEROES_RANGO5_3(5, "Cyclops"),
    HEROES_RANGO5_4(5, "Invisible Woman"),
    HEROES_RANGO4_1(4, "Ghost Rider"),
    HEROES_RANGO4_2(4, "Punisher"),
    HEROES_RANGO4_3(4, "Blade"),
    HEROES_RANGO4_4(4, "Thing"),
    HEROES_RANGO3_1(3, "Emma Frost"),
    HEROES_RANGO3_2(3, "She-Hulk"),
    HEROES_RANGO3_3(3, "Giant-Man"),
    HEROES_RANGO3_4(3, "Beast"),
    HEROES_RANGO3_5(3, "Colossus"),
    HEROES_RANGO2_1(2, "Gambit"),
    HEROES_RANGO2_2(2, "Spider-Girl"),
    HEROES_RANGO2_3(2, "Iceman"),
    HEROES_RANGO2_4(2, "Storm"),
    HEROES_RANGO2_5(2, "Phoenix"),
    HEROES_RANGO2_6(2, "Dr. Strange"),
    HEROES_RANGO2_7(2, "Elektra"),
    HEROES_RANGO2_8(2, "Nightcrawler"),
    HEROES_RANGO1(1, "Black Widow"),
    HEROES_BOMBA(0, "Nova Blast"),
    HEROES_EARTH(0, "Planet Earth"),

    // Piezas de Villanos
    VILLAINS_RANGO10(10, "Dr. Doom"),
    VILLAINS_RANGO9(9, "Galactus"),
    VILLAINS_RANGO8_1(8, "Kingpin"),
    VILLAINS_RANGO8_2(8, "Magneto"),
    VILLAINS_RANGO7_1(7, "Apocalypse"),
    VILLAINS_RANGO7_2(7, "Green Goblin"),
    VILLAINS_RANGO7_3(7, "Venom"),
    VILLAINS_RANGO6_1(6, "Bullseye"),
    VILLAINS_RANGO6_2(6, "Omega Red"),
    VILLAINS_RANGO6_3(6, "Onslaught"),
    VILLAINS_RANGO6_4(6, "Red Skull"),
    VILLAINS_RANGO5_1(5, "Mystique"),
    VILLAINS_RANGO5_2(5, "Mysterio"),
    VILLAINS_RANGO5_3(5, "Dr. Octopus"),
    VILLAINS_RANGO5_4(5, "Deadpool"),
    VILLAINS_RANGO4_1(4, "Abomination"),
    VILLAINS_RANGO4_2(4, "Thanos"),
    VILLAINS_RANGO4_3(4, "Black Cat"),
    VILLAINS_RANGO4_4(4, "Sabretooth"),
    VILLAINS_RANGO3_1(3, "Juggernaut"),
    VILLAINS_RANGO3_2(3, "Rhino"),
    VILLAINS_RANGO3_3(3, "Carnage"),
    VILLAINS_RANGO3_4(3, "Mole Man"),
    VILLAINS_RANGO3_5(3, "Lizard"),
    VILLAINS_RANGO2_1(2, "Mr. Sinister"),
    VILLAINS_RANGO2_2(2, "Sentinel1"),
    VILLAINS_RANGO2_3(2, "Ultron"),
    VILLAINS_RANGO2_4(2, "Sandman"),
    VILLAINS_RANGO2_5(2, "Leader"),
    VILLAINS_RANGO2_6(2, "Viper"),
    VILLAINS_RANGO2_7(2, "Sentinel2"),
    VILLAINS_RANGO2_8(2, "Electro"),
    VILLAINS_RANGO1(1, "Black Widow"),
    VILLAINS_BOMBA(0, "Pumpkin Bomb"),
    VILLAINS_EARTH(0, "Planet Earth");

    private final int rank;
    private final String name;

    PieceType(int rank, String name) {
        this.rank = rank;
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public static String getDescriptionFromIconDescription(String description) {
        int underscoreIndex = description.indexOf('_');
        if (underscoreIndex == -1) {
            return "Unknown";
        }

        String pieceTypePart = description.substring(underscoreIndex + 1);
        try {
            return PieceType.valueOf(pieceTypePart).getName();
        } catch (IllegalArgumentException e) {
            return "Unknown";
        }
    }
}
