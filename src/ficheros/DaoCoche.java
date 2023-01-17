package ficheros;

import java.util.ArrayList;
import java.util.List;

public class DaoCoche {

private List<Coche> listaCoches;
//File file = new File(EscrituraListaCoches.nFichero);	
	public DaoCoche () {
		super();
		listaCoches = new ArrayList<Coche>();
	
	}
	
	public Coche añadirCoche(Coche c){
		
		int cont = 0;
		if(listaCoches.contains(c)) {
	    	System.out.println("Añadir => Coche en lista");
	        return null;
	}
		else {
			listaCoches.add(c);
			cont = listaCoches.size()-1;
			System.out.println("Coche añadido");
			return listaCoches.get(cont);
		}

	}
	
	public Coche borrarCoche(String id) {
		
		try {
			for(Coche c : listaCoches) {	
				if(c.getId().equals(id)){
					int vAux = listaCoches.indexOf(c);
					System.out.println("Borrar => " + c + "");
					return listaCoches.remove(vAux);
				}		
			}
		
		}catch (UnsupportedOperationException e) {
			System.out.println("Borrar => El Coche no se encuentra en la lista ");
			return null ;
		}		
		return null;	
	}
	
	public Coche buscarCoche(String id) {
		for(Coche c : listaCoches) {
			if(c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}
	
	public List<Coche> listarCoches(){
	    return listaCoches;
	}
}
