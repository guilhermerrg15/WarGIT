package Model;

    public enum PlayerColor {
        BLUE,
        YELLOW,
        RED,
        BLACK,
        WHITE,
        GREEN;

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
}

