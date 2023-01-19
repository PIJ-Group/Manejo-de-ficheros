package ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

import com.csvreader.CsvWriter;

public class Principal {
	static Scanner sc = new Scanner(System.in);
	static final String NFICHERO = "coches.dat";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		int opcion;
		Coche cocheAux;
		DaoCoche dC = new DaoCoche();
		String idAux;
		File fn = new File(NFICHERO);

		if (fn.exists()) {
			try (FileInputStream fis = new FileInputStream(fn); ObjectInputStream ois = new ObjectInputStream(fis);) {

				dC.listaCoches = (List<Coche>) ois.readObject();

				System.out.println("Objeto leido");
				System.out.println("Imprimiendo Coches");

				for (Coche c : dC.listaCoches) {
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
		} else
			System.out.println("El fichero " + NFICHERO + " no existe");

		do {
			opcion = menu();

			switch (opcion) {

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
				dC.buscarCoche(idAux);
				break;

			case 4:				
				for (Coche c : dC.listaCoches)
					System.out.println(c);
				break;

			case 5:
				System.out.println("Exportando a archivo CSV...");
				extraerCSV(dC.listaCoches);

				break;

			case 6:
				System.out.println("\nIntroduciendo Coches \n");

				try (FileOutputStream fos = new FileOutputStream(fn);
						ObjectOutputStream oos = new ObjectOutputStream(fos)) {
					oos.writeObject(dC.listaCoches);
					System.out.println("Cerrando aplicación");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("No se han podido cargar los datos");
				}

				break;

			default:
				System.out.println("Elige una opción correcta por favor");
				break;
			}
		} while (opcion != 6);
	}

	public static int menu() {

		System.out.println("\n --------- CONCESIONARIO PIJ ---------- \n");
		System.out.println("1. Añadir vehículo");
		System.out.println("2. Dar de baja un vehículo por ID");
		System.out.println("3. Obtener un vehículo por ID");
		System.out.println("4. Listar todos los vehículos");
		System.out.println("5. Exportar coches a archivo CSV");
		System.out.println("6. Salir de la aplicación");
		int option = sc.nextInt();
		System.out.println("Has elegido la opción: " + option);
		return option;
	}

	public static void extraerCSV(List<Coche> coches) {
		String archCSV = "coches.csv";
		File ch = new File(archCSV);
		if(ch.exists()) 
			ch.delete();		
				
		try {			
			
			CsvWriter salidaCSV = new CsvWriter(new FileWriter(archCSV, true), ';');

			// Datos para identificar las columnas
			salidaCSV.write("ID");
			salidaCSV.write("Matricula");
			salidaCSV.write("Marca");
			salidaCSV.write("Modelo");
			salidaCSV.write("Color");
		

			// Deja de escribir en el archivo
			salidaCSV.endRecord();

			// Recorremos la lista y lo insertamos en el archivo
			for (Coche c : coches) {
				salidaCSV.write(c.getId());
				salidaCSV.write(c.getMatricula());
				salidaCSV.write(c.getMarca());
				salidaCSV.write(c.getModelo());
				salidaCSV.write(c.getColor());
				
				// Deja de escribir en el archivo
				salidaCSV.endRecord();
			}

			salidaCSV.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}