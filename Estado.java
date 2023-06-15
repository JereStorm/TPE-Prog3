import Grafo.Arco;

import java.util.ArrayList;
import java.util.List;

public class Estado {

    private List<Arco<Integer>> parcial;
    Integer kmActuales, posActual;

    public Estado(){
        this.kmActuales = 0;
        this.posActual = 0;
        this.parcial = new ArrayList<Arco<Integer>>();
    }

    public Integer getKmActuales() {
        return kmActuales;
    }

    public Integer getPosActual() {
        return posActual;
    }

    public List<Arco<Integer>> getParcial() {
        List<Arco<Integer>> aux = new ArrayList<>();
        aux.addAll(this.parcial);
        return aux;
    }

    public void setParcial(List<Arco<Integer>> parcial) {
        this.parcial = parcial;
    }

    public void setPosActual(Integer posActual) {
        this.posActual = posActual;
    }

    public void setKmActuales(Integer kmActuales) {
        this.kmActuales = kmActuales;
    }
}
