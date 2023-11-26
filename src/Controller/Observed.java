package Controller;


public interface Observed {
    public void add(Observer o);
    public void remove(Observer o);
    public int get(int i);
}
