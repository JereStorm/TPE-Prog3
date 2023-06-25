import Grafo.Arco;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatasetMain {

        public static void main(String[] args) {
            // PATH/AL/ARCHIVO
            String path = "Datasets/dataset1.txt";
            CSVReader reader = new CSVReader(path);
            //reader.read();

            ArrayList<Arco<Integer>> arcosDataSet  = reader.getArcos();
            Backtracking backtracking = new Backtracking(arcosDataSet);
            System.out.println("Backtracking");
            List<Arco<Integer>> solucion = backtracking.backtracking();
            System.out.println(solucion);
            System.out.println( "Kilometros totales: "+backtracking.getKmTotales());
            System.out.println( "Metrica: " + backtracking.getMetrica());


        }

}
