package Servicios;

import  Grafo.Grafo;
import java.util.*;

public class ServicioDFS {

    private Grafo<?> grafo;
    private Map<Integer,String> vertices;

    public ServicioDFS(Grafo<?> grafo) {
        this.grafo = grafo;
        this.vertices = new HashMap<>();
    }

    public List<Integer> dfsForest() {
        Iterator<Integer> aux = this.grafo.obtenerVertices();
        while(aux.hasNext()){
            this.vertices.put(aux.next(),"blanco");
        }
        ArrayList<Integer> list_dfs = new ArrayList<>();
        for(Integer v : this.vertices.keySet()){
            if(vertices.get(v).equals("blanco")){
                 list_dfs.addAll(this.dfs_visit(v));
            }
        }
        return list_dfs;
    }

    private ArrayList<Integer> dfs_visit(Integer v) {
        this.vertices.put(v,"amarillo");
        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(v);
        ArrayList<Integer> aux = new ArrayList<>();
        aux.add(v);
        while(adyacentes.hasNext()){
            int x = adyacentes.next();
            if(this.vertices.get(x).equals("blanco")){
               aux.addAll(this.dfs_visit(x));
            }
        }
        return aux;
    }
}