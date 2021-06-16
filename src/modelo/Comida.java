package modelo;

import utiles.Temperatura;

public class Comida extends Articulo {

	private Temperatura temperatura;

	public Comida(int id, String nombre, float precio, Temperatura temperatura) {
		super(id, nombre, precio);
		this.temperatura = temperatura;
	}

	public Temperatura getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Temperatura temperatura) {
		this.temperatura = temperatura;
	}

	@Override
	public String toString() {
		return super.getNombre() + " con precio " + super.getPrecio() + " y que se sirve " + getTemperatura();
	}

}
