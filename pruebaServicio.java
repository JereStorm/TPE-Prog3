import Grafos.GrafoDirigido;
import Grafos.GrafoNoDirigido;
import Servicios.ServicioBFS;
import Servicios.ServicioCaminos;
import Servicios.ServicioDFS;

public class pruebaServicio {
    public static void main(String[] args) {
        GrafoDirigido<String> grafoDirigido = new GrafoDirigido<String>();
        GrafoNoDirigido<String> grafoNoDirigido  = new GrafoNoDirigido<String>();
        ServicioDFS dfs = new ServicioDFS(grafoDirigido);
        ServicioBFS bfs = new ServicioBFS(grafoDirigido);
        ServicioCaminos caminos = new ServicioCaminos(grafoDirigido,1,10,7);

        //*Agregando Vertices
        grafoDirigido.agregarVertice(1);
        grafoDirigido.agregarVertice(2);
        grafoDirigido.agregarVertice(3);
        grafoDirigido.agregarVertice(4);
        grafoDirigido.agregarVertice(5);
        grafoDirigido.agregarVertice(6);
        grafoDirigido.agregarVertice(7);
        grafoDirigido.agregarVertice(8);
        grafoDirigido.agregarVertice(9);
        grafoDirigido.agregarVertice(10);

        grafoNoDirigido.agregarVertice(1);
        grafoNoDirigido.agregarVertice(2);
        grafoNoDirigido.agregarVertice(3);
        grafoNoDirigido.agregarVertice(4);
        grafoNoDirigido.agregarVertice(5);
        grafoNoDirigido.agregarVertice(6);
        grafoNoDirigido.agregarVertice(7);
        grafoNoDirigido.agregarVertice(8);
        grafoNoDirigido.agregarVertice(9);
        grafoNoDirigido.agregarVertice(10);

        //*Agregando Arcos
        grafoDirigido.agregarArco(1, 2, " uno + dos = tres");
        grafoDirigido.agregarArco(1, 3, " uno + tres = cuatro");
        grafoDirigido.agregarArco(1, 9, " uno + nueve = diez");
        grafoDirigido.agregarArco(9, 7, " uno + nueve = diez");
        grafoDirigido.agregarArco(2, 4, " dos + cuatro = seis");
        grafoDirigido.agregarArco(2, 3, " dos + tres = cinco");
        grafoDirigido.agregarArco(3, 8, " tres + ocho = once");
        grafoDirigido.agregarArco(3, 4, " tres + cuatro = siete");
        grafoDirigido.agregarArco(3, 10, " tres + diez = trece");
        grafoDirigido.agregarArco(3, 7, " cuatro + siete = siete");
        grafoDirigido.agregarArco(7, 10, " uno + nueve = diez");
        grafoDirigido.agregarArco(4, 1, " cuatro + uno = cinco");
        grafoDirigido.agregarArco(4, 5, " cuatro + siete = siete");
        grafoDirigido.agregarArco(4, 6, " cuatro + seis = siete");
        grafoDirigido.agregarArco(6, 10, " seis + diez = diesiciete");

        grafoNoDirigido.agregarArco(1, 2, " uno + dos = tres");
        grafoNoDirigido.agregarArco(1, 3, " uno + tres = cuatro");
        grafoNoDirigido.agregarArco(1, 9, " uno + nueve = diez");
        grafoNoDirigido.agregarArco(2, 4, " dos + cuatro = seis");
        grafoNoDirigido.agregarArco(3, 8, " tres + ocho = once");
        grafoNoDirigido.agregarArco(3, 4, " tres + cuatro = siete");
        grafoNoDirigido.agregarArco(3, 10, " tres + diez = trece");
        grafoNoDirigido.agregarArco(3, 7, " cuatro + siete = siete");
        grafoNoDirigido.agregarArco(4, 5, " cuatro + siete = siete");
        grafoNoDirigido.agregarArco(4, 6, " cuatro + seis = siete");
        grafoNoDirigido.agregarArco(6, 10, " seis + diez = diesiciete");
        grafoNoDirigido.agregarArco(10, 6, " seis + diez = diesiciete");

        //PRUEBAS SERVICIOS CON GRADO DIRIGIDO
//        System.out.println("dfs "+dfs.dfsForest());
        System.out.println("bfs " +bfs.bfsForest());
        System.out.println("bfs " +bfs.bfsForest());

//        System.out.println(caminos.caminos());

        //PRUEBAS SERVICIOS CON GRADO  NO DIRIGIDO
        dfs = new ServicioDFS(grafoNoDirigido);
        bfs = new ServicioBFS(grafoNoDirigido);
        caminos = new ServicioCaminos(grafoNoDirigido,2,10,3);
//        System.out.println("dfs "+dfs.dfsForest());
        System.out.println("bfs " +bfs.bfsForest());
//        System.out.println(caminos.caminos());


    }
}
