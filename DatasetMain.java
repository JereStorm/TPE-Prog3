
import Grafo.Arco;

import java.util.ArrayList;
import java.util.List;

public class DatasetMain {

	public static void main(String[] args) {
		// PATH/AL/ARCHIVO
		String pathLau = "./Datasets/dataset1.txt";
		String path = "src/Datasets/dataset3.txt";

		CSVReader reader = new CSVReader(path);

		ArrayList<Arco<Integer>> arcosDataSet = reader.getArcos();
		ArrayList<Arco<Integer>> arcosDataSet2 = reader.getArcos();

		// ------------------------ RESOLUCION POR ALGORITMO GREEDY
		// --------------------------------------------------------
		// --------------------------------------------------------

		Greedy greedy = new Greedy(arcosDataSet);

		System.out.println("Greedy");
		
		List<Arco<Integer>> solucion = greedy.greedy();
		
		System.out.print(" ");
		for (Arco<Integer> a : solucion) {
			int origen = a.getVerticeOrigen();
			int destino = a.getVerticeDestino();
			System.out.print("E" + origen + "-E" + destino + ",");
		}
		System.out.print(";");
		
		System.out.println(" ");
		
		System.out.println("Kilometros totales: " + greedy.getKmActuales());
		System.out.println("Metrica: " + greedy.getMetrica());
		
		System.out.println(" ");

		// ------------------------ RESOLUCION POR ALGORITMO BACKTRACKING
		// --------------------------------------------------------------
		// --------------------------------------------------------------

		/*
		 * Complejidad: A la hora de calcualr la complejidad , SERA 2 a la n - 1, pq si
		 * tenemos 4 estaciones con un minimo de 3 conexiones, ya alcanza y no vamos a
		 * explorar mas q eso
		 */
		
		Backtracking backtracking = new Backtracking(arcosDataSet2);

		System.out.println("Backtracking");
		
		List<Arco<Integer>> solucion2 = backtracking.backtracking();
		
		System.out.print(" ");
		for (Arco<Integer> a : solucion2) {
			int origen = a.getVerticeOrigen();
			int destino = a.getVerticeDestino();
			System.out.print("E" + origen + "-E" + destino + ",");
		}
		System.out.print(";");
		
		System.out.println(" ");
		
		System.out.println("Kilometros totales: " + backtracking.getKmTotales());
		System.out.println("Metrica: " + backtracking.getMetrica());

	}
}
