import Grafo.Arco;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class Greedy {

    private UnionFind unionFind;
    private List<Integer> estaciones;

    private List<Arco<Integer>> candidatos;

    private Integer kmActuales, metrica;

    public Greedy(ArrayList<Arco<Integer>> candidatos){
        this.candidatos = candidatos;
        this.kmActuales = 0;
        this.metrica = 0;
        this.estaciones = new ArrayList<>();
        this.setEstaciones();
    }

    public Integer getMetrica() {
        return this.metrica;
    }

    public Integer getKmActuales() {
        return this.kmActuales;
    }

    public List<Arco<Integer>> greedy() {
        List<Arco<Integer>> solucion = new ArrayList<>(); // creamos la solucion vacia

        //  O(N log N)  ya que la complejidad de tiempo de Collections.sort() es O(nlog(n)).
        Collections.sort(this.candidatos);

        // O(a) donde a son la cant de estaciones
        this.unionFind = new UnionFind(this.estaciones.size()); // creamos el unionFind con la cant de estaciones

        // O(n * a) donde n es la cantidad de candidatos y a la cantidad de estaciones.
        while(!this.candidatos.isEmpty() && solucion.size() < this.estaciones.size() - 1) {
          
            Arco<Integer> aux = this.candidatos.remove(0);     // tomamos el primer candidato y lo borramos
            this.metrica++;
            
            // si es un arco que genera conexiones
            if(this.addArcoAccesible(aux)){
                solucion.add(aux);
                this.unionFind.union(this.estaciones.indexOf(aux.getVerticeOrigen()), this.estaciones.indexOf(aux.getVerticeDestino()));
                this.kmActuales += aux.getEtiqueta();
            }
        }

        return solucion;
    }

    /*
     * Este metodo se encarga de filtrar las estaciones en funcion de los Tuneles.
     * 
     * O(n * a) donde "n" es la cantidad de tuneles del dataSet y "a" la cantidad de
	 * estaciones que voy incorporando.
     */
    private void setEstaciones() {
        for (Arco<Integer> a : this.candidatos) {
            if (!this.estaciones.contains(a.getVerticeOrigen())) {
                this.estaciones.add(a.getVerticeOrigen());
            }
            if (!this.estaciones.contains(a.getVerticeDestino())) {
                this.estaciones.add(a.getVerticeDestino());
            }
        }

    }

    /*
     *Este Metodo se encarga de corroborar si un arco es redudante o
     * no (osea si agrega una conexion nueva con una estacion).
     * 
     * O(a) donde "a" es la cantidad de accesos a memoria del find().
     */
    private boolean addArcoAccesible(Arco<Integer> tunel) {
        int u = this.unionFind.find(this.estaciones.indexOf(tunel.getVerticeDestino()));
        int v = this.unionFind.find(this.estaciones.indexOf(tunel.getVerticeOrigen()));

        if (v != u) {
            return true;
        }
        return false;
    }
}
