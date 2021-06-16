package modelo;

public class Bebida extends Articulo {

	private boolean isalcohol;

	public Bebida(int id, String nombre, float precio, boolean isalcohol) {
		super(id, nombre, precio);
		this.isalcohol = isalcohol;
	}

	public boolean isIsalcohol() {
		return isalcohol;
	}

	public void setIsalcohol(boolean isalcohol) {
		this.isalcohol = isalcohol;
	}

	public String tieneAlcohol() {
		if (isIsalcohol()) {
			return "si";
		} else {
			return "no";
		}
	}

	@Override
	public String toString() {
		return super.getNombre() + " con precio " + super.getPrecio() + " y que " + tieneAlcohol() + " tiene alcohol";
	}

}
