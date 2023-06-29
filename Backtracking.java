import Grafo.Arco;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {

	private List<Arco<Integer>> mejorSolucion, dataSet;

	private Integer kmTotales, metrica, cantEstaciones;

	private ArrayList<Integer> estaciones;

	public Backtracking(List<Arco<Integer>> dataSet) {
		this.dataSet = dataSet;
		this.mejorSolucion = new ArrayList<Arco<Integer>>();
		this.kmTotales = -1;
		this.metrica = 0;
		this.estaciones = this.getEstaciones();
		this.cantEstaciones = this.estaciones.size();

	}

	public List<Arco<Integer>> backtracking() {
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

			// si las decisiones que me quedan por tomar son menores
			// que la cantidad minima de conexiones que requiero
			// y ya no llego a la solucion optima dejo de generar estados
			if (this.noFaltanCandidatos(posActual, e)) {
				e.setPosActual(posActual + 1);
				this.backtracking(e);
				e.setPosActual(posActual);
			}

			// Si el tunel es optimo y no sobrepasa a los km de la mejor solucion
			if (this.addArcoAccesible(tunel, e) && this.esSolucionFactible(e, tunel)) {

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

	/*
	 * --------- PODAS ---------
	 */

	private boolean esSolucionFactible(Estado e, Arco<Integer> tunel) {
		return e.getKmActuales() + tunel.getEtiqueta() < this.kmTotales;
	}

	private boolean noFaltanCandidatos(Integer posActual, Estado e) {
		// calculamos la cantidad de decisiones disponibles
		int decisionesPorTomar = this.dataSet.size() - posActual;
		// verificamos que estas decisiones disponibles con el minimo de
		boolean poda = decisionesPorTomar < this.cantEstaciones - 1;

		if (poda && e.getParcial().size() - decisionesPorTomar < 0) {
			return false;
		}

		return true;

	}

	private boolean addArcoAccesible(Arco<Integer> tunel, Estado e) {
		int u = e.getUnionFind().find(this.estaciones.indexOf(tunel.getVerticeDestino()));
		int v = e.getUnionFind().find(this.estaciones.indexOf(tunel.getVerticeOrigen()));

		if (v != u) {
			return true;
		}
		return false;
	}

	/*
	 * --------- METODOS DE CLASE ---------
	 */

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
		for (Arco<Integer> x : tuneles) {
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
