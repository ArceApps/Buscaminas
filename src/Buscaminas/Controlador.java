package Buscaminas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.swing.JButton;

public class Controlador implements ActionListener, MouseListener {
	private VPrincipal vista;
	private Casillas modelo;

	public Controlador(VPrincipal vista, Casillas modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Principiante")) {
			vista.principiante();
			vista.reiniciar();
			vista.iniciar();
		}
		if (e.getActionCommand().equals("Intermedio")) {
			vista.intermedio();
			vista.reiniciar();
			vista.iniciar();
		}
		if (e.getActionCommand().equals("Experto")) {
			vista.experto();
			vista.reiniciar();
			vista.iniciar();
		}
		if (e.getActionCommand().equals("")) {
			vista.resetearBotones(this);
			if(vista.getNivel() == 1) vista.principiante();
			if(vista.getNivel() == 2) vista.intermedio();
			if(vista.getNivel() == 3) vista.experto();
			modelo = new Casillas(vista.getnMinas(), vista.getFilas(), vista.getColumnas());
			vista.controlador(this);
			modelo.ponerMinas();
			modelo.ponerNumeros();
			modelo.noPulsadoNoBandera();
			vista.rellenarCasillas(modelo.getCasillas());
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		JButton boton = new JButton();
		boton = (JButton) event.getSource();
		if (event.getButton() == MouseEvent.BUTTON3) {
			for (int i = 0; i < vista.getFilas(); i++) {
				for (int j = 0; j < vista.getColumnas(); j++) {
					if (vista.getBotones()[i][j] == boton && modelo.getPulsado()[i][j] == false) {
						modelo.cambiarBandera(i, j);
						vista.botonDerecho(modelo.getCasillas(), i, j, modelo.getBandera()[i][j]);
						modelo.detectarMinas(i, j);
						vista.ganador(modelo.getnMinas());
						if(modelo.getnMinas() == 0) {
							modelo.pulsarTodo();
						}
					}
				}
			}
		}
		if (event.getButton() == MouseEvent.BUTTON1) {
			for (int i = 0; i < vista.getFilas(); i++) {
				for (int j = 0; j < vista.getColumnas(); j++) {
					if (vista.getBotones()[i][j] == boton) {
						if (modelo.getPulsado()[i][j] == false && modelo.getBandera()[i][j] == false) {
							vista.busqueda(vista.getBotones(), i, j);
							modelo.pulsar(i, j);
							if(modelo.getCasillas()[i][j] == 9) {
								//vista.perder(i, j);
								vista.mostrarTodasMinas(modelo.getCasillas(), modelo.getBandera());
								modelo.pulsarTodo();
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
