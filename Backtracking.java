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
		// inicializamos el estado inicial
		this.backtracking(estado); // Y le pasamos el estado inicial al metodo backtracking
		return this.mejorSolucion;
	}

	private void backtracking(Estado e) {
		this.metrica++;
		System.out.println("Metrica: " + this.metrica + " Sol Parcial: " + e.getParcial() + " Numero de Sets de UnionFind: "+e.getUnionFind().numberOfSets() + " "+e.getUnionFind());
		if (e.getPosActual() == this.dataSet.size()) {
			System.out.println("-------------------- DECISIONES TOMADAS");
			System.out.println("Num Sets: " + e.getUnionFind().numberOfSets() + " y UnionFind = " + e.getUnionFind());
			System.out.println("Sol Parcial " + e.getParcial());
			if (e.getUnionFind().numberOfSets() == 1 && e.getParcial().size() == this.cantEstaciones - 1) {
				System.out.println("-------------------- UN SOLO SET DE UNION FIND");
				if (this.mejorSolucion.isEmpty()) {
					System.out.println("PRIMER SOLUCION" + e.getParcial());
					this.mejorSolucion.addAll(e.getParcial());
					this.setKmTotales(e.getKmActuales());
				}
				if (e.getKmActuales() < this.getKmTotales()) {
					System.out.println("---------------SOLUCION OPTIMA" + e.getKmActuales());
					this.mejorSolucion.clear();
					this.mejorSolucion.addAll(e.getParcial());
					System.out.println(this.mejorSolucion + " SOLUCION");
					this.setKmTotales(e.getKmActuales());
				}
			}
		} else {
			Integer posActual = e.getPosActual(); // obtenemos la posActual del estado
			Integer kmActuales = e.getKmActuales();
			Arco<Integer> arco = this.dataSet.get(posActual); // obtenemos el elemento de la posActual en la lista de

			
			if(this.addArcoAccesible(arco, e)) {
				try {
					UnionFind aux = (UnionFind) e.getUnionFind().clone();
					e.getUnionFind().union(this.estacionesAccesibles.indexOf(arco.getVerticeOrigen()),
							this.estacionesAccesibles.indexOf(arco.getVerticeDestino()));

					e.addArco(arco);
					e.setKmActuales(kmActuales + arco.getEtiqueta());
					e.setPosActual(posActual + 1);
					
					System.out.println("ANTES DE RECURSION: "+ e);
					
					this.backtracking(e);

					
					e.setUnionFind(aux);

					e.removeArco(arco);
					e.setKmActuales(kmActuales);
					e.setPosActual(posActual);
					System.out.println("DESPUES DE RECURSION: "+ e);
				} catch (Exception e2) {

				}
			}
		
			e.setPosActual(posActual + 1);
			this.backtracking(e);
			e.setPosActual(posActual);
		}
	}

	private boolean addArcoAccesible(Arco<Integer> arco, Estado e) {
		int u = e.getUnionFind().find(this.estacionesAccesibles.indexOf(arco.getVerticeDestino())); // 2
		int v = e.getUnionFind().find(this.estacionesAccesibles.indexOf(arco.getVerticeOrigen())); // 1

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
		System.out.println(aux.size() + " size estaciones");
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
