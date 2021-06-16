package utiles;

import java.util.ArrayList;

import dao.concretas.DaoLista;
import modelo.Articulo;
import modelo.Bebida;
import modelo.Comida;
import modelo.Habitacion;

public class CreadorArticulo {
	
	private ArrayList<Articulo> articulosStock;

	public CreadorArticulo() {
		super();
		this.articulosStock = new ArrayList<>();
		rellenarVector();
	
		
	}

	public ArrayList<Articulo> getArticulosStock() {
		return articulosStock;
	}

	private void rellenarVector() {
		DatadorArticulos datadorArticulos = new DatadorArticulos();
		for (int i = 0; i < datadorArticulos.nombre.length/2; i++) {
			articulosStock.add(new Comida(i,datadorArticulos.nombre[i], datadorArticulos.precios[i], datadorArticulos.temperatura[i]));
		}
		for (int i = datadorArticulos.nombre.length/2, j = 0; i < datadorArticulos.nombre.length; i++,j++) {
			articulosStock.add(new Bebida(i,datadorArticulos.nombre[i], datadorArticulos.precios[i], datadorArticulos.alcocholicas[j]));
		}
	}

	public static void main(String[] args) {
		new CreadorArticulo();
	}

}
