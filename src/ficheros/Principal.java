package ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

public class Principal {
	static Scanner sc = new Scanner(System.in);
	static final String NFICHERO = "coches.dat";
	
	 

	public static void main(String[] args) {
		
		int opcion;
		Coche cocheAux;
		DaoCoche dC = new DaoCoche();
		String idAux;
		File fn = new File(NFICHERO);
		
		if(fn.exists()) {
			try (FileInputStream fis = new FileInputStream(fn);
					 ObjectInputStream ois = new ObjectInputStream(fis);) {
					
					dC.listaCoches = (List<Coche>)ois.readObject();
					
					System.out.println("Objeto leido");
					System.out.println("Imprimiendo Coches");
					
					for(Coche c : dC.listaCoches){
						System.out.println(c);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else 
			System.out.println("El fichero " + NFICHERO + " no existe");
		
		
		do {
			opcion = menu();
			
			switch(opcion) {
			
			case 1:
				cocheAux = new Coche();
				
				System.out.println("Añade el ID: ");
				cocheAux.setId(sc.next());
				
				System.out.println("Añade la matricula");
				cocheAux.setMatricula(sc.next());				
				
				System.out.println("Añade la marca");
				cocheAux.setMarca(sc.next());
				
				System.out.println("Añade el modelo");
				cocheAux.setModelo(sc.next());
				
				System.out.println("Añade el color");
				cocheAux.setColor(sc.next());
				
				dC.añadirCoche(cocheAux);
				
				break;
				
			case 2:
				System.out.println("Escribe el ID del vehículo a dar de baja: ");
				idAux = sc.next();
				dC.borrarCoche(idAux);
				break;
				
			case 3:
				System.out.println("Escribe el ID del vehículo a buscar: ");
				idAux = sc.next();
				cocheAux = dC.buscarCoche(idAux);
				System.out.println(cocheAux);
				break;
				
			case 4:
				dC.listaCoches = dC.listarCoches();
				for(Coche c : dC.listaCoches )
				System.out.println(c);
				break;
			
			case 5:
				System.out.println("\nIntroduciendo Coches \n");
								
				try (FileOutputStream fos = new FileOutputStream(fn);
						ObjectOutputStream oos = new ObjectOutputStream(fos)) {
						oos.writeObject(dC.listaCoches);
						System.out.println("Cerrando aplicación");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						
					}
				
				break;
				
			default:
				System.out.println("Elige una opción correcta por favor");
				break;
			}
		}while(opcion != 5);
	}
	
public static int menu() {
		
		System.out.println("\n --------- CONCESIONARIO PIJ ---------- \n");
		System.out.println("1. Añadir vehículo");
		System.out.println("2. Dar de baja un vehículo por ID");
		System.out.println("3. Obtener un vehículo por ID");
		System.out.println("4. Listar todos los vehículos");
		System.out.println("5. Salir de la aplicación");
		int option = sc.nextInt();
		System.out.println("Has elegido la opción: " + option);
		return option;
	}



}