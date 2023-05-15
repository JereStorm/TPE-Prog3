public class pruebaServicio {
    public static void main(String[] args) {
        Grafo<String> grafoDirigido = new GrafoDirigido<String>();
        ServicioDFS dfs = new ServicioDFS(grafoDirigido);
        ServicioBFS bfs = new ServicioBFS(grafoDirigido);

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

//        System.out.println(dfs.dfsForest());
        System.out.println(bfs.bfsForest());
    }
}
