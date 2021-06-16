package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Indicable<Integer>, Serializable{
	
	private int id;
	private ArrayList<Articulo> listaArticulos;
	
	public Pedido(int id, ArrayList<Articulo> listaArticulos) {
		super();
		this.id = id;
		this.listaArticulos = listaArticulos;
	}

	@Override
	public Integer getKey() {
		return this.id;
	}

	@Override
	public void setKey(Integer t) {
		this.id = t;
		
	}

	public ArrayList<Articulo> getListaArticulos() {
		return listaArticulos;
	}

	public void setListaArticulos(ArrayList<Articulo> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}
	
	
	
	
	
	

}
