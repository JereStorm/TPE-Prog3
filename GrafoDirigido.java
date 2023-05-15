import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {

    // Map<KEY,VALUE>VERTICES
    Map<Integer, ArrayList<Arco<T>>> vertices = new HashMap<Integer, ArrayList<Arco<T>>>();


    @Override
    public void agregarVertice(int verticeId) {
        if(!this.contieneVertice(verticeId)){
            this.vertices.put(verticeId, new ArrayList<Arco<T>>());
        }
    }

    @Override
    public void borrarVertice(int verticeId) {
        if (this.contieneVertice(verticeId)) {
            this.vertices.remove(verticeId);
            Iterator<Integer> itArcos = this.obtenerVertices();
            while (itArcos.hasNext()) {
                System.out.println("entra aqui");
                this.borrarArco(itArcos.next(), verticeId);
            }
        }
    }


    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2)){
            Arco<T> aux = new Arco<T>(verticeId1,verticeId2,etiqueta);
            if(!this.vertices.get(verticeId1).contains(aux)){
                this.vertices.get(verticeId1).add(aux);
            }
        }
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if(this.existeArco(verticeId1,verticeId2)){
            this.vertices.get(verticeId1).remove(this.obtenerArco(verticeId1, verticeId2));
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.get(verticeId) != null;
    }

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

    @Override
    public int cantidadVertices() {
        return this.vertices.size();
    }

    @Override
    public int cantidadArcos() {
        int cantArcos = 0;
        for (Integer integer : this.vertices.keySet()) {
            cantArcos += this.vertices.get(integer).size();
        }
        return cantArcos;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        ArrayList<Integer> numeros = new ArrayList<Integer>(this.vertices.keySet());
        return numeros.iterator();
    }

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

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> arcos = new ArrayList<Arco<T>>();
        for(Integer key: this.vertices.keySet()){
            arcos.addAll(this.vertices.get(key));
        }
        return arcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        ArrayList<Arco<T>> arcos = new ArrayList<Arco<T>>();
            if(this.contieneVertice(verticeId)){
                arcos.addAll(this.vertices.get(verticeId));
            }
        return arcos.iterator();
    }

}