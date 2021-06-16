package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import control.fachada;
import modelo.Articulo;
import modelo.Pedido;

public class fachadaTest {
	fachada fachada;

	@Test
	public void testgetUltimoPedido() {
		fachada = new fachada();
		fachada.annadirPedido("2", new ArrayList<Articulo>());
	}

}
