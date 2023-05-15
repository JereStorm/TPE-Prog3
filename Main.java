import java.util.Iterator;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //*Creacion Del Grafo
        Grafo<String> grafoDirigido = new GrafoDirigido<String>();

        //*Agregando Vertices
        grafoDirigido.agregarVertice(1);
        grafoDirigido.agregarVertice(2);
        grafoDirigido.agregarVertice(3);
        grafoDirigido.agregarVertice(4);
        grafoDirigido.agregarVertice(5);
        grafoDirigido.agregarVertice(6);
        grafoDirigido.agregarVertice(7);

        //*Agregando Arcos
        grafoDirigido.agregarArco(1, 2, " uno + dos = tres");
        grafoDirigido.agregarArco(1, 3, " uno + tres = cuatro");
        grafoDirigido.agregarArco(2, 4, " dos + cuatro = seis");
        grafoDirigido.agregarArco(3, 4, " tres + cuatro = siete");
        grafoDirigido.agregarArco(3, 7, " cuatro + siete = siete");
        grafoDirigido.agregarArco(4, 5, " cuatro + siete = siete");
        grafoDirigido.agregarArco(4, 6, " cuatro + siete = siete");

        //--------------------------Probando Metodos--------------------------//

        //Cantidad De Vertices
        System.out.println("Cantidad de vertices: " + grafoDirigido.cantidadVertices());
        System.out.println();

        //*Cantidad De Arcos
        System.out.println("Cantidad de arcos: " + grafoDirigido.cantidadArcos());
        System.out.println();

        //*Contiene Vertice
        System.out.println("Existe el vertice 16: " + grafoDirigido.contieneVertice(16));
        System.out.println("Existe el vertice 3: " + grafoDirigido.contieneVertice(3));
        System.out.println();

        //*Existe Arco
        System.out.println("¿Existe un arco con origen: 1 y destino: 4? " + grafoDirigido.existeArco(1, 4));
        System.out.println("¿Existe un arco con origen: 4 y destino: 2? " + grafoDirigido.existeArco(4, 2));
        System.out.println();

        //*Obtener Arco
        System.out.println("Arco, con punto O en V 1 y punto D en V 2: " + grafoDirigido.obtenerArco(1, 2));
        System.out.println("Arco, con punto O en V 4 y punto D en V 2: " + grafoDirigido.obtenerArco(4, 2));
        System.out.println();

        //*Obtener Vertices
        System.out.println("Vertices con iterator ");
        Iterator<Integer> vertices = grafoDirigido.obtenerVertices();
        while (vertices.hasNext()) {
            System.out.println(" vertice : " + vertices.next());
        }
        System.out.println();

        //*Obtener Adyacentes
        System.out.println("Adyacentes con iterator ");
        Iterator<Integer> adyacentes = grafoDirigido.obtenerAdyacentes(1);
        while (adyacentes.hasNext()) {
            System.out.println(" Adyacentes : " + adyacentes.next());
        }
        System.out.println();

        //*Obtener Arcos
        System.out.println("Arcos con iterator ");
        Iterator<Arco<String>> arcos = grafoDirigido.obtenerArcos();
        while (arcos.hasNext()) {
            System.out.println((" arcos : " + arcos.next()));
        }
        System.out.println();

        //*Obtener Arco(X)
        System.out.println("Arcos con iterator ");
        Iterator<Arco<String>> arco = grafoDirigido.obtenerArcos(2);
        while (arco.hasNext()) {
            System.out.println((" arcos de x vertice : " + arco.next()));
        }
        System.out.println();

        //*Borrar
        System.out.println("Cantidad de vertices: " + grafoDirigido.cantidadVertices());
        System.out.println("Cantidad de arcos: " + grafoDirigido.cantidadArcos());
        System.out.println();
        grafoDirigido.borrarVertice(4);
        System.out.println("Cantidad de vertices: " + grafoDirigido.cantidadVertices());
        System.out.println("Cantidad de arcos: " + grafoDirigido.cantidadArcos());
        System.out.println();

        System.out.println("Arcos con iterator ");
        Iterator<Arco<String>> arcos2 = grafoDirigido.obtenerArcos();
        while (arcos2.hasNext()) {
            System.out.println((" arcos : " + arcos2.next()));
        }
        System.out.println();

        //*Obtener Vertices
        System.out.println("Vertices con iterator ");
        Iterator<Integer> vertices2 = grafoDirigido.obtenerVertices();
        while (vertices2.hasNext()) {
            System.out.println(" vertice : " + vertices2.next());
        }
        System.out.println();

    }
}