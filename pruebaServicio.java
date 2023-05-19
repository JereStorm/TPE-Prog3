
public class pruebaServicio {
    public static void main(String[] args) {
        Grafo<String> grafoDirigido = new GrafoDirigido<String>();
        ServicioDFS dfs = new ServicioDFS(grafoDirigido);
        ServicioBFS bfs = new ServicioBFS(grafoDirigido);
        ServicioCaminos caminos = new ServicioCaminos(grafoDirigido,1,6,4);

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

        //*Agregando Arcos
        grafoDirigido.agregarArco(1, 2, " uno + dos = tres");
        grafoDirigido.agregarArco(1, 3, " uno + tres = cuatro");
        grafoDirigido.agregarArco(1, 9, " uno + nueve = diez");
        grafoDirigido.agregarArco(2, 4, " dos + cuatro = seis");
        grafoDirigido.agregarArco(3, 8, " tres + ocho = once");
        grafoDirigido.agregarArco(3, 4, " tres + cuatro = siete");
        grafoDirigido.agregarArco(3, 10, " tres + diez = trece");
        grafoDirigido.agregarArco(3, 7, " cuatro + siete = siete");
        grafoDirigido.agregarArco(4, 1, " cuatro + uno = cinco");
        grafoDirigido.agregarArco(4, 5, " cuatro + siete = siete");
        grafoDirigido.agregarArco(4, 6, " cuatro + seis = siete");
        grafoDirigido.agregarArco(6, 10, " seis + diez = diesiciete");

        System.out.println("dfs "+dfs.dfsForest());
        System.out.println("bfs " +bfs.bfsForest());
        System.out.println(caminos.caminos());
    }
}
