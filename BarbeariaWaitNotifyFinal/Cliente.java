
public class Cliente extends Thread {
	
	private int iD;
	private boolean atendido;
	private boolean sentado;
	private Barbearia barbearia; //barbearia que frequenta
	
	public Cliente (int i, Barbearia b){
		iD = i;
		atendido = false;
		barbearia = b;
		sentado = false;
		try {
			sleep (50);
		} catch (InterruptedException e){}
		}
	
	
	public synchronized void atendimento(){
		if (barbearia.barbeiro.getOcupado()){
			try{
				barbearia.decrementarCadeirasLivres();
				System.out.println("Cliente " + iD + " se sentou. Resta(m) " + barbearia.getCadeirasLivres() + " cadeiras livres.");
				sentado=true;
				wait();
			} catch (InterruptedException e){}
		}
		barbearia.barbeiro.setOcupado(true);
		if (sentado){
			barbearia.incrementarCadeirasLivres();
			System.out.println("Cliente " + iD + " se levantou. Está sendo atendido");
		}
		else{
			System.out.println("Cliente " + iD + " está sendo atendido");
		}
	}
	
	public synchronized void finalizeAtendimento(){
		atendido=true;
		System.out.println("Cliente " + iD + " terminou de ser atendido.");
		barbearia.barbeiro.setOcupado(false);
		notify();
		
	}
	
	
	
	public void run (){
		while (!atendido){
			System.out.println("Cliente " + iD + " entrou.");
			if (barbearia.getCadeirasLivres()>0){
				atendimento();
				try{
					sleep (100);
				}catch (InterruptedException e){}
			    finalizeAtendimento();
			}
			else {
				System.out.println("Não há vagas. Cliente " + iD + " foi embora.");
				atendido = true;
			}
		}
	}
	
			
	

}
