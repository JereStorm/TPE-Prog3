import java.util.*;

public class ServicioCaminos {

    private Grafo<?> grafo;
    private int origen;
    private int destino;
    private int lim;

    private List<List<Integer>> soluciones;

    private ArrayList<Arco<?>> arcosVisitados;
    private ArrayList<Integer> fila;

    // Servicio caminos
    public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
        this.soluciones = new ArrayList<>();
        this.arcosVisitados  = new ArrayList<Arco<?>>();
        this.fila = new ArrayList<Integer>();
    }

    public List<List<Integer>> caminos() {
        Iterator<Integer> adyacentesOrigen = this.grafo.obtenerAdyacentes(this.origen);
        this.obtenerCaminoDFS(new ArrayList<>(this.origen),this.origen);


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
        if(inicio == this.destino){
                caminoParcial.add(inicio);
                //ENCONTRO DESTINO
        }else{
            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(inicio);
            while(adyacentes.hasNext()){
                int x = adyacentes.next();
                if(!this.arcosVisitados.contains(x)){
                    Arco<?> arcoActual = this.grafo.obtenerArco(inicio,x);
                    this.arcosVisitados.add(arcoActual);
                    caminoParcial.add(x);
                    if(x == this.destino){
                        //ENCONTRO DESTINO
                    }
                    this.obtenerCaminoDFS(caminoParcial,x);
                    this.arcosVisitados.remove(arcoActual);
                    caminoParcial.remove(x);
                }
            }
        }
    }


}