
public class Cliente extends Thread {
	
	private int iD;
	private boolean atendido;
	private Barbearia barbearia; //barbearia que frequenta
	private boolean foiEmbora; // foi embora por não haver vagas
	
	public Cliente (int i, Barbearia b){
		iD = i;
		atendido = false;
		barbearia = b;
		foiEmbora=false;
		try{
			sleep(50);
		}catch (InterruptedException e){}
		}
	
	public void atendimento(){
		if (!atendido && !barbearia.barbeiro.getOcupado() && barbearia.empty()){
			barbearia.barbeiro.setOcupado(true);
			//System.out.println("Cliente " + iD + " entrou. Está sendo atendido.");
			try{
				barbearia.semaforo.acquire();
				System.out.println("Cliente " + iD + "  entrou e requisitou atendimento.");
			}catch (InterruptedException e){}
			System.out.println("Cliente " + iD + ". Está sendo atendido.");
			atendido=true;
		}
		if (!atendido && barbearia.getCadeirasLivres()>0 && barbearia.barbeiro.getOcupado()){
			barbearia.decrementarCadeirasLivres();
			System.out.println("Cliente " + iD + " entrou e se sentou. Restam " + barbearia.getCadeirasLivres() + " cadeiras livres");
			try{
				barbearia.semaforo.acquire();
				System.out.println("Cliente " + iD + " requisitou atedimento.");
			}catch (InterruptedException e){}
			atendido=true;
			barbearia.incrementarCadeirasLivres();
			System.out.println("Cliente " + iD + " se levantou. Está sendo atendido. " + barbearia.getCadeirasLivres() + " cadeiras livres");
		}
		if (!atendido && barbearia.getCadeirasLivres()==0){
			System.out.println("Cliente " + iD + " entrou e foi embora por não haver mais vagas.");
			atendido=true;
			foiEmbora=true;
		}
		if(atendido && !foiEmbora){
			try{
				sleep(100);
			}catch (InterruptedException e){}
			if (barbearia.empty())
			 barbearia.barbeiro.setOcupado(false);
			System.out.println("Cliente " + iD + " saiu. Atendimento concluido");
			barbearia.semaforo.release();
		}		
			
	} 
	
	
	public void run (){
		//System.out.println("Cliente " + iD + " entrou na barbearia
		if (!atendido){
			atendimento();
		}
	}
	
			
	

}
