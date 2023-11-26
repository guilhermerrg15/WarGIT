package Model;

    // Interface ou classe base comum para os objetivos
    public interface Objective {
        boolean checkStatus();
        void checkOwner(Player player);
    }
