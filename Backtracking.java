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
		this.kmTotales = Integer.MAX_VALUE;// Retorna el valor entero mas grande.
		this.metrica = 0;
		this.estaciones = this.getEstaciones();
		this.cantEstaciones = this.estaciones.size();
	}

	/*
	 * Funcion publica que devolvera la mejor solucion medida en la menor cantidad de kilometros de tunel posibles
	 */
	public List<Arco<Integer>> backtracking() {
		Estado estado = new Estado(this.cantEstaciones);
		this.backtracking(estado);
		return this.mejorSolucion;
	}

	/*
	 * Metodo privado que se encargara de generar todas las soluciones posibles y
	 * filtrar la mejor solucion medida en la menor cantidad de kilometros de tunel posibles.
	 */
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

			/*
			* si las decisiones que me quedan por tomar son menores
			* que la cantidad minima de conexiones que requiero
			* y ya no llego a la solucion optima, dejo de generar estados.
			*/

			if (this.noFaltanCandidatos(posActual, e)) {
				e.setPosActual(posActual + 1);
				this.backtracking(e);
				e.setPosActual(posActual);
			}

			// Si el tunel es optimo y no sobrepasa a los km de la mejor solucion
			if (this.addArcoAccesible(tunel, e) && this.esSolucionFactible(e, tunel)) {

				// Hacemos una copia del UnionFind.
				//O(n) donde n es la cant de estaciones
				UnionFind aux = (UnionFind) e.getUnionFind().clone();

				// El costo del union se ve determinado pro el costo del find()
				e.getUnionFind().union(this.estaciones.indexOf(tunel.getVerticeOrigen()),
						this.estaciones.indexOf(tunel.getVerticeDestino()));

				e.addArco(tunel);
				e.setKmActuales(kmActuales + tunel.getEtiqueta());
				e.setPosActual(posActual + 1);

				this.backtracking(e);

				//Seteamos la copia previa a la union() del unionFind.
				e.setUnionFind(aux);

				e.removeArco(tunel);
				e.setKmActuales(kmActuales);
				e.setPosActual(posActual);
			}

		}
	}

	/*
	 * 
	 * ---------------------------------------------------------------------
	 * ------------------------------------------------------------- PODAS
	 * ---------------------------------------------------------------------
	 */

	/*
	 * Funcion encargada de podar todos aquellos estados que intentarian construir
	 * una solucion que No sobrepasa a la mejor solucion encontrada hasta ese
	 * momento
	 * 
	 * O(1)
	 */
	private boolean esSolucionFactible(Estado e, Arco<Integer> tunel) {
		return e.getKmActuales() + tunel.getEtiqueta() < this.kmTotales;
	}

	/*
	 * Funcion encargada de podar todos aquellos estados que no llegaran a una
	 * solucion factible verificando que los cadidatos que queden por seleccionar
	 * alcanzen para encontrar la minima solucion
	 * 
	 * o(1)
	 */
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

	/*
	 * Funcion encargada de podar todos aquellos estados que agreguen tuneles
	 * redundantes a la solucion parcial, verificando que las estaciones no
	 * pertenezcan al mismo conjunto del unionFind.
	 *
	 * O(a) donde "a" son la cantidad de estaciones (subconjuntos del unionFind).
	 */
	private boolean addArcoAccesible(Arco<Integer> tunel, Estado e) {
		int u = e.getUnionFind().find(this.estaciones.indexOf(tunel.getVerticeDestino()));
		int v = e.getUnionFind().find(this.estaciones.indexOf(tunel.getVerticeOrigen()));

		if (v != u) {
			return true;
		}
		return false;
	}

	/*
	 * ---------------------------------------------------------------------------
	 * ------------------------------------------------------- METODOS DE CLASE
	 * ---------------------------------------------------------------------------
	 */

	/*
	 * Esta funcion obtiene las estaciones correspondientes a los tuneles del
	 * dataSet
	 *
	 * O(n * a) donde "n" es la cantidad de tuneles del dataSet y "a" la cantidad de
	 * estaciones que voy incorporando
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
