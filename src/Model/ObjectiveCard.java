package Model;

public class ObjectiveCard {
    private String description;
    private boolean completed;
    private String target;

    public ObjectiveCard(String description, String target) {
        this.description = description;
        this.completed = false;
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTarget() {
        return target;
    } // Retorna o nome do território alvo (nao sei ainda se vai ser utilizado)
}

