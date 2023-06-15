import Grafo.Arco;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {

    private List<Arco<Integer>> mejorSolucion, dataSet;
    private Integer kmTotales;

    private Estado estado;

    private ArrayList<String> estacionesAccesibles;


    public Backtracking( List<Arco<Integer>> dataSet){
        this.dataSet = dataSet;
        this.mejorSolucion = new ArrayList<Arco<Integer>>();
        this.kmTotales = 0;
        this.estacionesAccesibles = new ArrayList<String>();
    }


    List<Arco<Integer>> backtracking(){
        this.estado = new Estado(); // inicializamos el estado inicial
        this.backtracking(estado); // Y le pasamos el estado inicial al metodo backtracking

        return this.mejorSolucion;
    }

    private void backtracking(Estado estado){
        if(estado.getPosActual() == this.dataSet.size()-1){
            if(estado.getKmActuales() < this.getKmTotales()){
                this.mejorSolucion = estado.getParcial();
                this.setKmTotales(estado.getKmActuales());
            }
        }else{
                Arco<Integer> arco = this.dataSet.get(estado.getPosActual()); // obtenemos el elemento de la posActual en la lista de dataSet
                int posActual = estado.getPosActual(); // obtenemos la posActual del estado
                int kmActuales = estado.getKmActuales();

                // Si no utilizamos el arco en la solucion
                estado.setPosActual(posActual + 1); // avanzamos
                this.backtracking(estado);
                estado.setPosActual(posActual - 1);

                // Si utilizamos el arco en la solucion
                if(!this.esRedundanteArco(arco)) {
                    estado.getParcial().add(arco);// anadimos el arco a la solucion
                    estado.setKmActuales(kmActuales + arco.getEtiqueta()); //getEtiqueta nos devuelve el valor de la etiqueta (osea los km) del arco,sumamos la cantidad de km a la solucion
                    estado.setPosActual(posActual + 1);
                    this.backtracking(estado);
                    estado.getParcial().remove(arco);
                    estado.setKmActuales(kmActuales - arco.getEtiqueta());
                    estado.setPosActual(posActual - 1);
                }

        }
    }

    private boolean esRedundanteArco(Arco<Integer> arco) {
        boolean destino = this.estacionesAccesibles.contains(arco.getVerticeDestino());// retorna true o false si contiene a destino en la lista
        boolean origen = this.estacionesAccesibles.contains(arco.getVerticeOrigen());// retorna true o false si contiene a origen en la lista
        if(origen && destino){
            return true;
        }
        return false;
    }

    private void setKmTotales(Integer kmActuales) {
        this.kmTotales = kmActuales;
    }

    private Integer getKmTotales() {
        return this.kmTotales;
    }


}
