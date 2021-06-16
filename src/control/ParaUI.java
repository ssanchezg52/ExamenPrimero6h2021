package control;

import vista.UI;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextField;

import control.fachada;
import modelo.Articulo;
import modelo.Habitacion;
import modelo.Pedido;

public class ParaUI extends UI {

	private fachada fachada;
	ArrayList<Articulo> articulos;

	public ParaUI() {
		super();
		this.fachada = new fachada();
		this.articulos = new ArrayList<>();
		gestionarAlta();
		gestionarConsultaPedido();
	}

	private void gestionarConsultaPedido() {
		
		getJtxtHabitacionConsulta().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO mal, por que no tiene que hacer eso. no tiene ni que dejar de ser editable ni debe decir nada de "habitacion ocupada".
				verificarHabitacion(getJtxtHabitacionConsulta(), getJtxtMensajeConsulta());
				getJtxtHabitacionConsulta().setEditable(true);
			}
		});
		
		getJtxtHabitacionConsulta().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				limpiarTodoConsulta();
				
			}
		});
		
		getJtxtpedidoConsulta().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					vaciarConsumicionesYTotalYMensaje();
					Integer idPedido = Integer.valueOf(getJtxtpedidoConsulta().getText());
					Pedido recuperarPedido = fachada.recuperarPedido(getJtxtHabitacionConsulta().getText(), idPedido);
					if (recuperarPedido != null) {
						articulos = recuperarPedido.getListaArticulos();
						for (int i = 0; i < articulos.size(); i++) {
							getJtAreaConsumicionesConsulta().setText(getJtAreaConsumicionesConsulta().getText() + articulos.get(i).toString() + "\n");
							String palabras[] = articulos.get(i).toString().split(" ");
							if (!getLblTotalConsulta().getText().isBlank()) {
								Float totalActual = Float.valueOf(getLblTotalConsulta().getText());
								Float totalAnnadido = totalActual + Float.valueOf(palabras[3]);
								getLblTotalConsulta().setText(String.valueOf(totalAnnadido));
							}else {
								getLblTotalConsulta().setText(String.valueOf(palabras[3]));
							}
						}
					}else {
						getJtxtMensajeConsulta().setText("Id del pedido no existe");
						getJtxtpedidoConsulta().setText("");
					}
				} catch (Exception e) {
					getJtxtMensajeConsulta().setText("Ingresa un numero");
					getJtxtpedidoConsulta().setText("");
				}
				
				vaciarListaArticulos();
				getJtxtpedidoConsulta().setFocusable(false);
			}

		});
		
		getJtxtpedidoConsulta().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				getJtxtpedidoConsulta().setFocusable(true);
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				vaciarPedidoYConsumicionesYTotal();
				
			}

		});
	}

	private void gestionarAlta() {


		getBtnCancelar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg) {
				limpiarTodo();

			}
		});
		getBtnFinalizarPedidoAlta().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg) {

				String separadorCadenas[] = getJtAreaConsumicionesAlta().getText().split("\n");
				String palabras[] = null;
				for (int i = 0; i < separadorCadenas.length; i++) {
					String frase = separadorCadenas[i];
					palabras = frase.split(" ");
					Articulo articulo = fachada.getArticulo(palabras[0]);
					articulos.add(articulo);
				}
				if (fachada.annadirPedido(getJtxtHabitacionAlta().getText(), articulos)) {
					limpiarTodo();
					getJtxtMensajeAlta().setText("Se ha completado el pedido correctamente");
				} else {
					limpiarTodo();
					getJtxtMensajeAlta().setText("Error, no se ha completado el pedido");
				}

			}
		});
		getJtxtHabitacionAlta().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg) {
				verificarHabitacion(getJtxtHabitacionAlta(), getJtxtMensajeAlta());

			}
		});
		getJtxtArticuloAlta().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg) {
				if (!getJtxtHabitacionAlta().getText().isBlank()) {
					
					getJtxtMensajeAlta().setText("");

					try {
						Integer idArticulo = Integer.valueOf(getJtxtArticuloAlta().getText());
						Articulo articulo = fachada.getArticulo(idArticulo);
						if (articulo != null) {
							getJtAreaConsumicionesAlta()
									.setText(getJtAreaConsumicionesAlta().getText() + articulo.toString() + "\n");

							if (!getLblTotalAlta().getText().isBlank()) {
								float total = Float.valueOf(getLblTotalAlta().getText());
								getLblTotalAlta().setText(String.valueOf(total + articulo.getPrecio()));
							} else {
								getLblTotalAlta().setText(String.valueOf(articulo.getPrecio()));
							}

							getJtxtArticuloAlta().setText("");
						} else {
							getJtxtMensajeAlta().setText("Id de Articulo incorrecto");
							getJtxtArticuloAlta().setText("");
						}

					} catch (Exception e) {
						getJtxtMensajeAlta().setText("ingresa un numero");
						getJtxtArticuloAlta().setText("");
					}
					vaciarListaArticulos();
				} else {
					getJtxtArticuloAlta().setText("");
				}
			}
		});

	}

	private void verificarHabitacion(JTextField getHabitacion,JTextField getMensaje) {
		// TODO no me gusta.. pero no se
		try {
			getJtxtMensajeAlta().setText("");
			Integer idHabitacion = Integer.valueOf(getHabitacion.getText());
			Habitacion habitacion = fachada.getHabitacion(idHabitacion);
			if (habitacion != null) {
				if (!habitacion.isOcupada()) {
					getHabitacion.setEditable(false);
				} else {
					getMensaje.setText("Habitacion ocupada");
					getHabitacion.setText("");
				}

			} else {
				getMensaje.setText("id de habitacion incorrecto");
				getHabitacion.setText("");
			}

		} catch (NumberFormatException e) {
			getMensaje.setText("ingresa un numero");
			getHabitacion.setText("");
		}
	}
	
	private void vaciarListaArticulos() {
		for (int i = 0; i < articulos.size(); i++) {
			articulos.remove(i);
		}
		
	}

	private void vaciarConsumicionesYTotalYMensaje() {
		getLblTotalConsulta().setText("");
		getJtAreaConsumicionesConsulta().setText("");
		getJtxtMensajeConsulta().setText("");
	}

	private void vaciarPedidoYConsumicionesYTotal() {
		getJtxtpedidoConsulta().setText("");
		getJtAreaConsumicionesConsulta().setText("");
		getLblTotalConsulta().setText("");
	}
}
