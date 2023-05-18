import java.util.*;

public class ServicioCaminos {

    private Grafo<?> grafo;
    private int origen;
    private int destino;
    private int lim;
    private List<List<Integer>> soluciones;
    private ArrayList<Arco<?>> arcosVisitados;
//    private ArrayList<Integer> fila;

    // Servicio caminos
    public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
        this.soluciones = new ArrayList<>();
        this.arcosVisitados = new ArrayList<Arco<?>>();
//        this.fila = new ArrayList<Integer>();
    }

    public List<List<Integer>> caminos() {
        Iterator<Integer> adyacentesOrigen = this.grafo.obtenerAdyacentes(this.origen);
        //* el primer parametro seria el estado(camino_parcial)
        ArrayList<Integer> camino_parcial = new ArrayList<Integer>();
        camino_parcial.add(this.origen);//Agregamos el origen al camino parcial
        this.obtenerCaminoDFS(camino_parcial, this.origen);


//        while(adyacentesOrigen.hasNext()){
//            ArrayList<Integer> solucion = this.obtenerCaminoDFS(new ArrayList<Integer>(), adyacentesOrigen.next());
//            if(solucion.size() > 0 && solucion.get(solucion.size() - 1) == this.destino){
//                solucion.add(this.origen);
//                caminos.add(solucion);
//            }
//        }
        return this.soluciones;
    }

    private void obtenerCaminoDFS(ArrayList<Integer> caminoParcial, int inicio) {
        if (inicio == this.destino) {
            if (caminoParcial.get(caminoParcial.size() - 1).equals(this.destino) && caminoParcial.size() <= this.lim  ) {
                //*Si el ultimo elemento es igual al destino lo agregamos
                ArrayList<Integer> aux = new ArrayList<>();
                aux.addAll(caminoParcial);//*Guardamos la solucion
                caminoParcial.clear();//* borramos el camino para reiniciarlo
                this.soluciones.add(aux);//*agregamos la solucion
                caminoParcial.add(this.origen);// volvemos a agregar el origen
            }
        } else {
            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(inicio);
            while (adyacentes.hasNext()) {
                int x = adyacentes.next();
                Arco<?> arcoActual = this.grafo.obtenerArco(inicio, x);//* arco entre inicio y su adyacente actual
                if (!this.arcosVisitados.contains(arcoActual)) {//* si todavia no visitamos ese arco
                    this.arcosVisitados.add(arcoActual);//* lo visitamos
                    caminoParcial.add(x);//* lo agregamos al camino
                    this.obtenerCaminoDFS(caminoParcial, x);// llamamos a camino parcial con el nuevo inicio
                    /*
                    * Si no puede remover al elemento una ves que regresa de obtenerCaminos, significa que el camino
                    * fue exitoso(osea que el ultimo elem fue igual al destino), por lo tanto, lo removemos de arcos visitados.
                    * En cambio si no fue exitoso, no lo removemos de arcos visitados, ya que ese camino no te lleva a la solucion. */
                    if(!caminoParcial.remove(Integer.valueOf(x))){
                        this.arcosVisitados.remove(arcoActual);
                    };
                }
            }
        }
    }


}