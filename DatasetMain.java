public class DatasetMain {

        public static void main(String[] args) {
            // PATH/AL/ARCHIVO
            String path = "Datasets/dataset1.txt";
            CSVReader reader = new CSVReader(path);
            reader.read();

        }

}
