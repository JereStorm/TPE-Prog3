import Grafo.Arco;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Estado {

    private List<Arco<Integer>> parcial;
    Integer kmActuales, posActual;
	private UnionFind unionFind;

    public Estado(int cantEstaciones){
        this.kmActuales = 0;
        this.posActual = 0;
        this.parcial = new ArrayList<Arco<Integer>>();
		this.unionFind = new UnionFind(cantEstaciones);// le pasamos la cant de estaciones
    }
    public UnionFind getUnionFind() {
    	return this.unionFind;
    }

    public int obtenerCantSets(){
    	return unionFind.numberOfSets();
    }
    public String mostrarUnionFind() {
    	return this.unionFind.toString();
    }
    
    public Integer getKmActuales() {
        return this.kmActuales;
    }

    public Integer getPosActual() {
        return posActual;
    }

    public List<Arco<Integer>> getParcial() {
        return this.parcial;
    }

    public void setUnionFind(UnionFind nuevo) {
    	this.unionFind = nuevo;
    }
    @Override
	public String toString() {
		return "Estado [parcial=" + parcial + ", kmActuales=" + kmActuales + ", posActual=" + posActual
			+", "	+ unionFind + "]";
	}

	public void addArco(Arco<Integer> a){
    
        this.parcial.add(a);
    }

    public void removeArco(Arco<Integer> a){
        this.parcial.remove(a);
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
