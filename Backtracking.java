import Grafo.Arco;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {

	private List<Arco<Integer>> mejorSolucion, dataSet;
	private Integer kmTotales, metrica;

	private Estado estado;

	private ArrayList<Integer> estacionesAccesibles;

	public Backtracking(List<Arco<Integer>> dataSet) {
		this.dataSet = dataSet;
		this.mejorSolucion = new ArrayList<Arco<Integer>>();
		this.kmTotales = 0;
		this.metrica = 0;
		this.estacionesAccesibles = new ArrayList<Integer>();
	}

	List<Arco<Integer>> backtracking() {
		this.estado = new Estado(); // inicializamos el estado inicial
		this.backtracking(estado); // Y le pasamos el estado inicial al metodo backtracking

		return this.mejorSolucion;
	}

	private void backtracking(Estado estado) {
		this.metrica++;
		if (estado.getPosActual() == this.dataSet.size() - 1) {
			if (this.esConexo(estado)) {
				if (estado.getKmActuales() < this.getKmTotales()) {
					this.mejorSolucion = estado.getParcial();
					this.setKmTotales(estado.getKmActuales());
				}
			}
		} else {
			Arco<Integer> arco = this.dataSet.get(estado.getPosActual()); // obtenemos el elemento de la posActual en la lista de dataSet
			int posActual = estado.getPosActual(); // obtenemos la posActual del estado
			int kmActuales = estado.getKmActuales();

			// No utilizamos el arco en la solucion
			estado.setPosActual(posActual + 1);
			// avanzamos
			this.backtracking(estado);
			estado.setPosActual(posActual - 1);

			// Si utilizamos el arco en la solucion
			if (this.addArcoAccesible(arco) )  {
				
				estado.getParcial().add(arco);// anadimos el arco a la solucion
				estado.setKmActuales(kmActuales + arco.getEtiqueta()); // getEtiqueta nos devuelve el valor de la etiqueta (osea los km) del arco,sumamos la cantidad de km a la solucion
				estado.setPosActual(posActual + 1);
				
				this.backtracking(estado);
				
				estado.getParcial().remove(arco);
				estado.setKmActuales(kmActuales - arco.getEtiqueta());
				estado.setPosActual(posActual - 1);
			};
		}
	}

	private boolean addArcoAccesible(Arco<Integer> arco) {
		boolean destino = this.estacionesAccesibles.contains(arco.getVerticeDestino()); //
		boolean origen = this.estacionesAccesibles.contains(arco.getVerticeOrigen());
		// Si ambos ya son accesibles => redundante
		if (origen && destino) {
			return false;
		}else if (origen && !destino) {
			this.estacionesAccesibles.add(arco.getVerticeDestino());
			return true;
		}else if (!origen && destino) {
			this.estacionesAccesibles.add(arco.getVerticeOrigen());
			return true;
		}else {
			this.estacionesAccesibles.add(arco.getVerticeOrigen());
			this.estacionesAccesibles.add(arco.getVerticeDestino());
			return true;
		}
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
		return 0;
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
