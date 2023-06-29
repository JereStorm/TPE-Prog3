import Grafo.Arco;

import java.util.ArrayList;

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

        this.ordenarCandidatosPorMenorKM();// ordenamos los candidatos de menor a mayor
        
        this.unionFind = new UnionFind(this.estaciones.size()); // creamos el unionFind con la cant de estaciones

        while(!this.candidatos.isEmpty() && solucion.size() < this.estaciones.size() - 1) {
          
            Arco<Integer> aux = this.candidatos.remove(0); 	// tomamos el primer candidato y lo borramos				
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
     * Este metodo se encarga de ordenar los candidatos de menor a mayor segun la cant de km.
     * 
     * O( n*n ) donde "n" son la cantidad de candidatos.
     */
    private void ordenarCandidatosPorMenorKM(){
        for (int x = 0; x < this.candidatos.size(); x++) {
            for (int y = 0; y < this.candidatos.size() - 1; y++) {
                int elementoActual = this.candidatos.get(y).getEtiqueta();
                int elementoSiguiente = this.candidatos.get(y + 1).getEtiqueta();
                if (elementoActual > elementoSiguiente) {
                    // Intercambiar
                    Arco<Integer> sig = this.candidatos.get(y+1);
                    Arco<Integer> actual = this.candidatos.get(y);
                    this.candidatos.set(y,sig);
                    this.candidatos.set(y + 1, actual);
                }
            }
        }
    }

    /*
     * Este metodo se encarga de filtrar las estaciones en funcion de los Tuneles.
     * 
     * O(n) donde "n" es la cantidad de tuneles.
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

    /* Este Metodo se encarga de corroborar si un arco es redudante o no (osea si agrega una conexion nueva con una estacion).
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
