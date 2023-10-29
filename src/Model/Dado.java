package Model;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

 public class Dado{
  static int dado;
  public static void jogaDado(){
	  
    dado = Math.abs((ThreadLocalRandom.current().nextInt())%6) + 1;
  }
  
  public static int getDado() {
	  return dado;
  }
}
