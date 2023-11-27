package View;

public interface Observed {
    public void add(Observer o);
    public void remove(Observer o);
    public Object get();
}
