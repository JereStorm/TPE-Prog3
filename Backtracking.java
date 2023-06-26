import Grafo.Arco;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {

	private List<Arco<Integer>> mejorSolucion, dataSet;

	private Integer kmTotales, metrica;

	private int cantEstaciones;

	private ArrayList<Integer> estacionesAccesibles;

	public Backtracking(List<Arco<Integer>> dataSet) {
		this.dataSet = dataSet;
		this.mejorSolucion = new ArrayList<Arco<Integer>>();
		this.kmTotales = -1;
		this.metrica = 0;
		this.estacionesAccesibles = this.getEstaciones();
		this.cantEstaciones = this.estacionesAccesibles.size();

	}

	List<Arco<Integer>> backtracking() {
		Estado estado = new Estado(this.cantEstaciones);
		this.backtracking(estado);
		return this.mejorSolucion;
	}

	private void backtracking(Estado e) {
		this.metrica++;
		
		if (e.getPosActual() == this.dataSet.size()) {

			if (e.getUnionFind().numberOfSets() == 1 && e.getParcial().size() == this.cantEstaciones - 1) {
				
				if (this.mejorSolucion.isEmpty()) {
					this.mejorSolucion.addAll(e.getParcial());
					this.setKmTotales(e.getKmActuales());
				}
				
				if (e.getKmActuales() < this.getKmTotales()) {	
					this.mejorSolucion.clear();
					this.mejorSolucion.addAll(e.getParcial());
					this.setKmTotales(e.getKmActuales());
				}
			}
		} else {
			Integer posActual = e.getPosActual(); 
			Integer kmActuales = e.getKmActuales();
			Arco<Integer> arco = this.dataSet.get(posActual); 

			
			if(this.addArcoAccesible(arco, e)) {
		
				UnionFind aux = (UnionFind) e.getUnionFind().clone();
				e.getUnionFind().union(this.estacionesAccesibles.indexOf(arco.getVerticeOrigen()),
						this.estacionesAccesibles.indexOf(arco.getVerticeDestino()));

				e.addArco(arco);
				e.setKmActuales(kmActuales + arco.getEtiqueta());
				e.setPosActual(posActual + 1);

				this.backtracking(e);

				e.setUnionFind(aux);

				e.removeArco(arco);
				e.setKmActuales(kmActuales);
				e.setPosActual(posActual);
			}
		
			e.setPosActual(posActual + 1);
			this.backtracking(e);
			e.setPosActual(posActual);
		}
	}

	private boolean addArcoAccesible(Arco<Integer> arco, Estado e) {
		int u = e.getUnionFind().find(this.estacionesAccesibles.indexOf(arco.getVerticeDestino()));
		int v = e.getUnionFind().find(this.estacionesAccesibles.indexOf(arco.getVerticeOrigen())); 

		if (v != u) {
			return true;
		}
		return false;
	}

	public ArrayList<Integer> getEstaciones() {
		ArrayList<Integer> aux = new ArrayList<>();
		for (Arco<Integer> a : this.dataSet) {
			if (!aux.contains(a.getVerticeOrigen())) {
				aux.add(a.getVerticeOrigen());
			}
			if (!aux.contains(a.getVerticeDestino())) {
				aux.add(a.getVerticeDestino());
			}
		}

		return aux;
	}

	private void setKmTotales(Integer kmActuales) {
		this.kmTotales = kmActuales;
	}

	public Integer getKmTotales() {
		return this.kmTotales;
	}

	public int getMetrica() {
		return this.metrica;
	}

}
