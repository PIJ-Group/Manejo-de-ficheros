package ficheros;

import java.util.ArrayList;
import java.util.List;

public class DaoCoche {

List<Coche> listaCoches;
	
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
		
		boolean auxCocheEncontrado = false;

		for(Coche c : listaCoches) {	
			if(c.getId().equals(id)){
				int vAux = listaCoches.indexOf(c);
				System.out.println("Borrar => " + c + "");
				auxCocheEncontrado = true;
				return listaCoches.remove(vAux);
				
			}		
		}
		
		if (!auxCocheEncontrado)
			System.out.println("Borrar => El coche no se encuentra en la lista");
	
		return null;	
	}
	
	public Coche buscarCoche(String id) {
		
		boolean auxCocheEncontrado = false;
		
		for(Coche c : listaCoches) {
			if(c.getId().equals(id)) {
				System.out.println(c);
				auxCocheEncontrado = true;

			}
		}
		
		if (!auxCocheEncontrado)
			System.out.println("Buscar => El coche no se encuentra en la lista");
		
		return null;
	}
	
}
