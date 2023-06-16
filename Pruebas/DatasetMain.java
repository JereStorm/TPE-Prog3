import Grafo.Arco;

import java.util.ArrayList;
import java.util.Iterator;

public class DatasetMain {

        public static void main(String[] args) {
            // PATH/AL/ARCHIVO
            String path = "Datasets/dataset1.txt";
            CSVReader reader = new CSVReader(path);
            //reader.read();



            ArrayList<Arco<Integer>> arcosDataSet  = reader.getArcos();
            System.out.println(arcosDataSet);
            Backtracking backtracking = new Backtracking(arcosDataSet);
            System.out.println("Backtracking");
            Iterator<Arco<Integer>>  a =  backtracking.backtracking().iterator();
            while (a.hasNext()) {
                System.out.print(a.next());// Como obtener la metrica
            }
            System.out.println(backtracking.getKmTotales());
            System.out.println(backtracking.getMetrica());


        }

}
