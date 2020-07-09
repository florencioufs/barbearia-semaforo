import java.util.concurrent.*;

public class Barbearia {
	private static final int cadeiras = 3; 
	private int cadeirasLivres = cadeiras; 
	public Barbeiro barbeiro;
	
	public Semaphore semaforo;
	
	public Barbearia (Barbeiro b){
		semaforo = new Semaphore (1);
		barbeiro= b;
	}
	
	public void incrementarCadeirasLivres(){
		cadeirasLivres++;
	}
	
	public void decrementarCadeirasLivres(){
		cadeirasLivres--;
	}
	
	public int getCadeirasLivres (){
		return cadeirasLivres;
	}
	
	public boolean empty(){
		return (cadeirasLivres==cadeiras);
	}
}
