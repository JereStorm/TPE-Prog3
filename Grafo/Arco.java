package Grafo;

import java.util.Comparator;

/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Arco<T extends Comparable<T>> implements Comparable<Arco<T>> {

    private int verticeOrigen;
    private int verticeDestino;
    private T etiqueta;

	private static int cantidad;

    public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.etiqueta = etiqueta;
		this.cantidad++;
    }

    public int getVerticeOrigen() {
        return verticeOrigen;
    }

    public int getVerticeDestino() {
        return verticeDestino;
    }

	public void descontarCantidadDeArcos(){
		this.cantidad--;
	}

	public int getCantidadArcos(){
		return this.cantidad;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		if (etiqueta == null) {
			if (other.etiqueta != null)
				return false;
		} else if (!etiqueta.equals(other.etiqueta))
			return false;
		if (verticeDestino != other.verticeDestino)
			return false;
		if (verticeOrigen != other.verticeOrigen)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etiqueta == null) ? 0 : etiqueta.hashCode());
		result = prime * result + verticeDestino;
		result = prime * result + verticeOrigen;
		return result;
	}

    public T getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return "Arco{" +
                " verticeOrigen= " + verticeOrigen +
                ", verticeDestino= " + verticeDestino +
                ", etiqueta= " + etiqueta +
                '}';
    }

	@Override
	public int compareTo(Arco<T> o) {
		return this.getEtiqueta().compareTo(o.getEtiqueta());
	}
}