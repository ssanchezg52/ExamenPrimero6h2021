package control;

import java.util.ArrayList;

import dao.base.DAOMultiObjetosGenerica;
import dao.concretas.DaoLista;
import dao.concretas.DaoUnitario;
import dao.serializado.GrabadorSerializado;
import dao.serializado.RecuperadorIndexadoSerializado;
import modelo.Articulo;
import modelo.Habitacion;
import modelo.Pedido;
import utiles.CreadorArticulo;
import utiles.CreadorHabitaciones;

public class fachada {
	
	public DaoLista<ArrayList<Habitacion>, Habitacion, Integer> daoHabitacion = new DaoLista<>("habitaciones.hab", new CreadorHabitaciones().getHabitaciones());
	public DaoLista<ArrayList<Articulo>, Articulo, Integer> daoArticulo = new DaoLista<>("articulos.art", new CreadorArticulo().getArticulosStock());
	public DaoUnitario<Pedido, Integer> daoPedido;
	
	public Habitacion getHabitacion(Integer pos) {
		return this.daoHabitacion.get(pos);
	}
	
	public Articulo getArticulo(Integer pos) {
		return this.daoArticulo.get(pos);
	}
	
	public Articulo getArticulo(String string) {
		return this.daoArticulo.getArticuloPorNombre(string);
	}
	
	public Pedido getPedido(Integer pos) {
		return this.daoPedido.buscar(pos);
	}
	
	public Integer getUltimoPedido() {
		return this.daoPedido.getUltimoPedido();
	}
	
	public boolean addPedido(Pedido pedido) {
		return this.daoPedido.add(pedido);
	}
	
	public boolean annadirPedido(String idHabitacion,ArrayList<Articulo> listaArticulos) {
		String path = idHabitacion + ".hab";
		this.daoPedido = new DaoUnitario<>(path, 
				new DAOMultiObjetosGenerica<>(path, new GrabadorSerializado<Pedido>(), new RecuperadorIndexadoSerializado<Pedido,Integer>()));
		return addPedido(new Pedido(this.daoPedido.getUltimoPedido()+1, listaArticulos));
		
	}
	
	public Pedido recuperarPedido(String idHabitacion,Integer idPedido) {
		String path = idHabitacion + ".hab";
		this.daoPedido = new DaoUnitario<>(path, 
				new DAOMultiObjetosGenerica<>(path, new GrabadorSerializado<Pedido>(), new RecuperadorIndexadoSerializado<Pedido,Integer>()));
		return getPedido(idPedido);
	}

}
