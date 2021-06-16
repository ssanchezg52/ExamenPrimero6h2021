package utiles;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import modelo.Articulo;

public class CreadorArticuloTest {

	@Test
	public void test() {
		CreadorArticulo creadorArticulo = new CreadorArticulo();
		ArrayList<Articulo> articulosStock = creadorArticulo.getArticulosStock();
		System.out.println(articulosStock);
		assertFalse(creadorArticulo.getArticulosStock().isEmpty());
	}

}
