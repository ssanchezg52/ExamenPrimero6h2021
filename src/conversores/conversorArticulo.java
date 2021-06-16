package conversores;

import modelo.Articulo;
import modelo.Bebida;
import modelo.Comida;
import modelo.Convertible;
import utiles.Temperatura;

public class conversorArticulo implements Convertible<Articulo>{

	@Override
	public String convertirAString(Articulo t) {
		return t.toString();
	}
	
	@Override
	public Articulo convertirAObjeto(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Articulo convertirAObjetoComida(String id,String string) {
		String palabras[] = string.split(" ");
		return new Comida(Integer.valueOf(id), palabras[0], Float.valueOf(palabras[2]), Temperatura.valueOf(palabras[0]));
	}
	
	public Articulo convertirAObjetoBebida(Integer id,String string) {
		String palabras[] = string.split(" ");
		return new Bebida(id, palabras[0], Float.valueOf(palabras[3]), Boolean.valueOf(palabras[6]));
	}

	
	

}
