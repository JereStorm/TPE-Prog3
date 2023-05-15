import java.util.*;

public class ServicioBFS {

    private Grafo<?> grafo;
    private Map<Integer, Boolean>vertices;
    private ArrayList<Integer> fila;

    public ServicioBFS(Grafo grafo) {
        this.grafo = grafo;
        this.fila = new ArrayList<>();
        this.vertices = new HashMap<>();
    }

    public List<Integer> bfsForest() {
        Iterator<Integer> aux = this.grafo.obtenerVertices();
        while(aux.hasNext()){
            this.vertices.put(aux.next(),false);
        }
        aux = this.grafo.obtenerVertices();
//        this.fila.addAll(this);
        while(aux.hasNext()){
            int v = aux.next();
            if (!this.vertices.get(v)) {
                this.bfs_visit(v);
            }
        }
//        for (Integer v: this.vertices.keySet()) {
//            if(!this.vertices.get(v)){
//               this.fila.addAll(this.bfs_visit(v));
//            }
//        }
        return this.fila;
    }

    private void bfs_visit(Integer v) {
        this.vertices.put(v,true);
        this.fila.add(v);
        Iterator<Integer> itFila = this.fila.iterator();
        boolean bandera = false;
        while(!fila.isEmpty() && !bandera){
            int x = itFila.next();
            this.vertices.remove(x);
            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(x);
            while(adyacentes.hasNext()){
                int a = adyacentes.next();
                if(!this.vertices.get(a)){
                    this.vertices.put(a,true);
                    this.fila.add(a);
                    System.out.println(this.fila);
                }
            }
            bandera = true;
        }
    }

}