import Grafo.Arco;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {

	private List<Arco<Integer>> mejorSolucion, dataSet;

	private Integer kmTotales, metrica;

	private UnionFind unionFind;
	private Estado estado;
	private int cantEstaciones;

	private ArrayList<Integer> estacionesAccesibles;

	public Backtracking(List<Arco<Integer>> dataSet) {
		this.dataSet = dataSet;
		this.mejorSolucion = new ArrayList<Arco<Integer>>();
		this.kmTotales = -1;
		this.metrica = 0;
		this.estacionesAccesibles = this.getEstaciones();
		this.cantEstaciones = this.estacionesAccesibles.size();
		this.unionFind = new UnionFind(this.estacionesAccesibles.size());// le pasamos la cant de estaciones

	}

	List<Arco<Integer>> backtracking() {
		this.estado = new Estado(); // inicializamos el estado inicial
		this.backtracking(estado); // Y le pasamos el estado inicial al metodo backtracking
		return this.mejorSolucion;
	}

	private void backtracking(Estado estado) {
		this.metrica++;
		System.out.println("Metrica: " + this.metrica + " Sol Parcial: " + estado.getParcial());
		if (estado.getPosActual() == this.dataSet.size()) {
			System.out.println("-------------------- DECISIONES TOMADAS");
			System.out.println("Num Sets: " + this.unionFind.numberOfSets() + " y UnionFind = " + this.unionFind);
			if (this.unionFind.numberOfSets() == 1 && estado.getParcial().size() == this.cantEstaciones - 1) {
				System.out.println("-------------------- UN SOLO SET DE UNION FIND");
				if (this.mejorSolucion.isEmpty()) {
					System.out.println("Primer solucion" + estado.getParcial());
					this.mejorSolucion.addAll(estado.getParcial());
					this.setKmTotales(estado.getKmActuales());
				}
				if (estado.getKmActuales() < this.getKmTotales()) {
					System.out.println("---------------SOLUCION OPTIMA" + estado.getKmActuales());
					this.mejorSolucion.clear();
					this.mejorSolucion.addAll(estado.getParcial());
					System.out.println(this.mejorSolucion + " SOLUCION");
					this.setKmTotales(estado.getKmActuales());
				}
			}
		} else {
			Integer posActual = estado.getPosActual(); // obtenemos la posActual del estado
			Integer kmActuales = estado.getKmActuales();
			Arco<Integer> arco = this.dataSet.get(posActual); // obtenemos el elemento de la posActual en la lista de
																// dataSet
			if (this.addArcoAccesible(arco, estado)) {

				estado.addArco(arco);// anadimos el arco a la solucion
				estado.setKmActuales(kmActuales + arco.getEtiqueta()); // getEtiqueta nos devuelve el valor de la
																		// etiqueta (osea los km) del arco,sumamos la
																		// cantidad de km a la solucion
				estado.setPosActual(posActual + 1);

				this.backtracking(estado);

				estado.removeArco(arco);
				estado.setKmActuales(kmActuales - arco.getEtiqueta());
				estado.setPosActual(posActual - 1);
			}													// Si utilizamos el arco en la solucion

			// No utilizamos el arco en la solucion
			estado.setPosActual(posActual + 1);
			this.backtracking(estado);
			estado.setPosActual(posActual - 1);
			
			
			
		}
	}

	private boolean addArcoAccesible(Arco<Integer> arco, Estado e) {
		int u = this.unionFind.find(this.estacionesAccesibles.indexOf(arco.getVerticeDestino())); // 2
		int v = this.unionFind.find(this.estacionesAccesibles.indexOf(arco.getVerticeOrigen())); // 1

		if (v != u) {
			System.out.println("ARCO VALIDO: " + arco);
			unionFind.union(this.estacionesAccesibles.indexOf(arco.getVerticeOrigen()),
					this.estacionesAccesibles.indexOf(arco.getVerticeDestino()));
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
		System.out.println(aux.size() + "size estaciones");
		return aux;
	}

	private boolean esConexo(Estado estado) {
		// TODO Auto-generated method stub
		if (this.dataSet.size() == this.estacionesAccesibles.size()) {
			return true;
		}

		return false;
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

//	private boolean esRedundanteArco(Arco<Integer> arco) {
//		boolean destino = this.estacionesAccesibles.contains(arco.getVerticeDestino());
//		boolean origen = this.estacionesAccesibles.contains(arco.getVerticeOrigen());
//		// Si ambos ya estan conectados => redundante
//		if (origen && destino) {
//			return true;
//		}
//		// Si alguno no esta conectado aun =>
//		return false;
//	}

}
