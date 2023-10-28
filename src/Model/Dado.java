package Model;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

 class Dado{
  static int dado;
  static void jogaDado(){
	  
    dado = Math.abs((ThreadLocalRandom.current().nextInt())%6) + 1;
  }
  static int getDado() {
	  return dado;
  }
}
