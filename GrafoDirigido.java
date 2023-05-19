import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {

    Map<Integer, ArrayList<Arco<T>>> vertices = new HashMap<Integer, ArrayList<Arco<T>>>();

    /**
     * Complejidad: O(1) ya que la operacion del metodo .put() de hashMap y del metodo
     * contieneVertice() son constantes.
     */
    @Override
    public void agregarVertice(int verticeId) {
        if(!this.contieneVertice(verticeId)){
            this.vertices.put(verticeId, new ArrayList<Arco<T>>());
        }
    }

    /**
     * Complejidad: O(v * a) donde v es la cantidad de vertices y a es la cantidad de arcos a
     * borrar de ese vertice (this.borrarArco(itVertices.next(), verticeId)).
     */
    @Override
    public void borrarVertice(int verticeId) {
        if (this.contieneVertice(verticeId)) {
            this.vertices.remove(verticeId);
            Iterator<Integer> itVertices = this.obtenerVertices();
            while (itVertices.hasNext()) {
                this.borrarArco(itVertices.next(), verticeId);
            }
        }
    }

    /**
     * Complejidad: O(a) porque recorre los arcos especificos de un vertice, utilizando
     * el metodo contains() que implicitamente implementa el indexOf() que es igual a O(n).
     */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2)){
            Arco<T> aux = new Arco<T>(verticeId1,verticeId2,etiqueta);
            if(!this.vertices.get(verticeId1).contains(aux)){
                this.vertices.get(verticeId1).add(aux);
            }
        }
    }

    /**
     * Complejidad: O(a) donde a son los arcos de un vertice especifico.
     */
    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if(this.existeArco(verticeId1,verticeId2)){
            this.vertices.get(verticeId1).remove(this.obtenerArco(verticeId1, verticeId2));
        }
    }

    /**
     * Complejidad: O(1) ya que el unico acceso de memoria que hay
     * se debe al .get() del hashMap
     */
    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.get(verticeId) != null;
    }

    /**
     * Complejidad: O(a) donde a son la cantidad de arcos salientes de verticeId1.
     */
    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        if(this.contieneVertice(verticeId1)){
            ArrayList<Arco<T>> arcos = this.vertices.get(verticeId1);
            for(Arco<T> arco : arcos){
                if(arco.getVerticeDestino() == verticeId2){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Complejidad: O(a) donde a son los arcos adyacentes a vertice 1.
     */
    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        if(this.contieneVertice(verticeId1)){
            ArrayList<Arco<T>> arcos = this.vertices.get(verticeId1);
            for(Arco<T> arco : arcos){
                if(arco.getVerticeDestino() == verticeId2){
                    return arco;
                }
            }
        }
        return null;
    }
    /**
     * Complejidad: O(1) ya que el metodo .size() de hashMap es una operacion constante.
     */
    @Override
    public int cantidadVertices() {
        return this.vertices.size();
    }

    /**
     * Complejidad: O(V) donde V es la cantidad de Vertices.
     */
    @Override
    public int cantidadArcos() {
        int cantArcos = 0;
        for (Integer integer : this.vertices.keySet()) {
            cantArcos += this.vertices.get(integer).size();
        }
        return cantArcos;
    }

    /**
     * Complejidad: O(1) ya que la operacion del keySet() es constante.
     */
    @Override
    public Iterator<Integer> obtenerVertices() {
        return this.vertices.keySet().iterator();
    }

    /**
     * Complejidad: O(a) donde a es la cantidad de adyacentes de verticeId.
     */
    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        ArrayList<Integer> adyacentes = new ArrayList<Integer>();
        if(contieneVertice(verticeId)){
            for (Arco<T> arco: this.vertices.get(verticeId)) {
                adyacentes.add(arco.getVerticeDestino());
            }
        }
        return adyacentes.iterator();
    }

    /**
     * Complejidad: O(V * a) donde V es la cantidad de Vertices Y a la cantidad de arcos saliente de V.
     */
    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> arcos = new ArrayList<Arco<T>>();
        for(Integer key: this.vertices.keySet()){
            arcos.addAll(this.vertices.get(key));
        }
        return arcos.iterator();
    }

    /**
     * Complejidad: O(1) donde V es la cantidad de Vertices Y A la cantidad de arcos.
     */
    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        ArrayList<Arco<T>> arcos = new ArrayList<Arco<T>>();
            if(this.contieneVertice(verticeId)){
                return this.vertices.get(verticeId).iterator();
            }
        return arcos.iterator();
    }

}