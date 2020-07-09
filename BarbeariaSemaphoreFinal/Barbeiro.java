
public class Barbeiro {
	private boolean ocupado;
	
	public Barbeiro (){
		ocupado = false;
		System.out.println("Barbeiro dormindo.");
	}
	
	public boolean getOcupado (){
		return ocupado;
	}
	
	public void setOcupado(boolean o){
		ocupado = o;
	}

}
