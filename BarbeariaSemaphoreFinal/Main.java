
public class Main {
	public static void main(String[] args){
		Barbeiro barbeiro = new Barbeiro();
		Barbearia barbearia = new Barbearia(barbeiro);
		int numeroDeClientes = 30;
		
		for (int i=0; i<numeroDeClientes ; i++ ){
			 Cliente cliente = new Cliente(i, barbearia);
		     cliente.start(); 
		    }
	}
}
