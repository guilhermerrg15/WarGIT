package Controller;
import java.util.ArrayList;
import java.util.List;

public class TabuleiroObservado {
    private List<TabuleiroObservador> observers = new ArrayList<TabuleiroObservador>();

    public void addObserver(TabuleiroObservador observer) {
        this.observers.add(observer);
    }
    public void removeObserver(TabuleiroObservador observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (TabuleiroObservador observer : this.observers) {
            observer.update();
        }
    }
}
