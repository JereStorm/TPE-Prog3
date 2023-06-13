public class DatasetMain {

        public static void main(String[] args) {

            String path = "PATH/AL/ARCHIVO";
            CSVReader reader = new CSVReader(path);
            reader.read();

        }

}
