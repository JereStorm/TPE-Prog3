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
                //* guardamos el recorrido completo en la fila de la clase bfsForest
                //* ya que en el bfs_visit la utilizamos para ir recorriendo los niveles del grafo
                this.fila.addAll(this.bfs_visit(v));
            }
        }

        return this.fila;
    }

    //*     VERTICES
    private ArrayList<Integer> bfs_visit(Integer v) {
        this.vertices.put(v,true);
        ArrayList<Integer> fila_visit = new ArrayList<>();

        //* Si la fila no esta vacio
        if(!this.fila.isEmpty()){
            this.fila.add(this.fila.size()-1,v);
            fila_visit.add(this.fila.size()-1,v);
        }else{
            //* Si la fila esta vacia
            this.fila.add(v);
            fila_visit.add(v);
        }

        //* Mientras  la fila no este vacia
        while(!this.fila.isEmpty()){
            Iterator<Integer> itFila = this.fila.iterator();
            int x = itFila.next();// guardamos el vertice y avanzamos
            this.fila.remove(0);// lo removemos
            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(x);//* iteramos los adyacentes del vertice
            while(adyacentes.hasNext() ) {
                Integer a = adyacentes.next();// obtenemos el vertice de su adyacente
                if (!this.vertices.get(a)) {//* si no esta visitado
                    this.vertices.put(a, true);//* lo marcamos como visitado
                    if (!this.fila.isEmpty()) {//* si la fila no esta vacia
                        this.fila.add(this.fila.size(), a); //* lo agregamos al fondo de su vertice origen
                        fila_visit.add(fila_visit.size(), a);//* lo agregamos a la fila que guarda todo el recorrido
                    } else {
                        //* si la fila esta vacia
                        this.fila.add(a);//* lo agregamos al princio
                        fila_visit.add(a);//* lo agregamos a la fila que guarda todo el recorrido
                    }
                }

            }
        }
        //* retornamos la fila con el recorrido completo
        return fila_visit;
    }

}