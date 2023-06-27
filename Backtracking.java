import Grafo.Arco;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Backtracking {

	private List<Arco<Integer>> mejorSolucion, dataSet;

	private Integer kmTotales, metrica;

	private int cantEstaciones;

	private ArrayList<Integer> estaciones;

	public Backtracking(List<Arco<Integer>> dataSet) {
		this.dataSet = dataSet;
		this.mejorSolucion = new ArrayList<Arco<Integer>>();
		this.kmTotales = -1;
		this.metrica = 0;
		this.estaciones = this.getEstaciones();
		this.cantEstaciones = this.estaciones.size();

	}

	List<Arco<Integer>> backtracking() {
		this.setKmTotales(this.dataSet);
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
			Arco<Integer> tunel = this.dataSet.get(posActual);

			int decisionesPorTomar = this.dataSet.size() - posActual;
			boolean poda = decisionesPorTomar < this.cantEstaciones - 1; //si las decisiones que me quedan por tomar son menores
																		 //que la cantidad minima de conexiones que requiero
			if(!poda || e.getParcial().size() - decisionesPorTomar >= 0) { // y
				e.setPosActual(posActual + 1);
				this.backtracking(e);				
				e.setPosActual(posActual);
			}

			if (this.addArcoAccesible(tunel, e) && (e.getKmActuales() + tunel.getEtiqueta() < this.kmTotales)) {

				UnionFind aux = (UnionFind) e.getUnionFind().clone();
				e.getUnionFind().union(this.estaciones.indexOf(tunel.getVerticeOrigen()),
						this.estaciones.indexOf(tunel.getVerticeDestino()));

				e.addArco(tunel);
				e.setKmActuales(kmActuales + tunel.getEtiqueta());
				e.setPosActual(posActual + 1);
				
								
				this.backtracking(e);
				

				e.setUnionFind(aux);

				e.removeArco(tunel);
				e.setKmActuales(kmActuales);
				e.setPosActual(posActual);
			}

		}
	}

	private boolean addArcoAccesible(Arco<Integer> tunel, Estado e) {
		int u = e.getUnionFind().find(this.estaciones.indexOf(tunel.getVerticeDestino()));
		int v = e.getUnionFind().find(this.estaciones.indexOf(tunel.getVerticeOrigen()));

		if (v != u) {
			return true;
		}
		return false;
	}

	private ArrayList<Integer> getEstaciones() {
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
	
	private void setKmTotales(List<Arco<Integer>> tuneles) {
		int sumaTotal = 0;
		for(Arco<Integer> x : tuneles) {
			sumaTotal += x.getEtiqueta();
		}
		this.kmTotales = sumaTotal;
		
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
