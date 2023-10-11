package Model;

import java.util.HashMap;

    public enum PlayerColor {
        BLUE,
        YELLOW,
        RED,
        BLACK,
        WHITE,
        GREEN;
    
    private static HashMap<String, PlayerColor> colorNamesToColors;

    public String getName() {
        switch (this) {
            case BLUE:
                return "Azul";
            case YELLOW:
                return "Amarelo";
            case RED:
                return "Vermelho";
            case BLACK:
                return "Preto";
            case WHITE:
                return "Branco";
            case GREEN:
                return "Verde";
            default:
                return "Preto";
        }
    }
    @Override
    public String toString() {
        return getName();
    }
    public static PlayerColor getColorByName(String name) {
        if (colorNamesToColors == null) {
            colorNamesToColors = new HashMap<String, PlayerColor>();

            colorNamesToColors.put("azul", PlayerColor.BLUE);
            colorNamesToColors.put("amarelo", PlayerColor.YELLOW);
            colorNamesToColors.put("vermelho", PlayerColor.RED);
            colorNamesToColors.put("preto", PlayerColor.BLACK);
            colorNamesToColors.put("branco", PlayerColor.WHITE);
            colorNamesToColors.put("verde", PlayerColor.GREEN);
        }
        return colorNamesToColors.get(name.toLowerCase());
    }
}

