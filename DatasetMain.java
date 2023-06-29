
import Grafo.Arco;

import java.util.ArrayList;
import java.util.List;

public class DatasetMain {

	public static void main(String[] args) {
		// PATH/AL/ARCHIVO
		String pathIntellij = "./Datasets/dataset2.txt";
		String pathEclipse = "src/Datasets/dataset2.txt";

		CSVReader reader = new CSVReader(pathIntellij);

		ArrayList<Arco<Integer>> arcosDataSet = reader.getArcos();
		ArrayList<Arco<Integer>> arcosDataSet2 = reader.getArcos();

		// ------------------------ RESOLUCION POR ALGORITMO GREEDY
		// --------------------------------------------------------
		// --------------------------------------------------------

		Greedy greedy = new Greedy(arcosDataSet);

		System.out.println("Resolucion por Greedy");
		System.out.print("\n");

		List<Arco<Integer>> solucion = greedy.greedy();


		for (Arco<Integer> a : solucion) {
			int origen = a.getVerticeOrigen();
			int destino = a.getVerticeDestino();
			System.out.print("E" + origen + "-E" + destino + ",");
		}
		System.out.println(";");


		System.out.println(greedy.getKmActuales() + " Kms");
		System.out.println(greedy.getMetrica() + " Metrica");

		System.out.println(" ");

		// ------------------------ RESOLUCION POR ALGORITMO BACKTRACKING
		// --------------------------------------------------------------
		// --------------------------------------------------------------
		
		Backtracking backtracking = new Backtracking(arcosDataSet2);

		System.out.println("Resolucion por Backtracking");
		System.out.print("\n");
		List<Arco<Integer>> solucion2 = backtracking.backtracking();


		for (Arco<Integer> a : solucion2) {
			int origen = a.getVerticeOrigen();
			int destino = a.getVerticeDestino();
			System.out.print("E" + origen + "-E" + destino + ",");
		}
		System.out.println(";");
	

		System.out.println(backtracking.getKmTotales()+ " Kms");
		System.out.println(backtracking.getMetrica() + " Metrica");

	}
}
