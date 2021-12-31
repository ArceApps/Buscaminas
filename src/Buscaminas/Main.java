package Buscaminas;

public class Main {
	public static void main(String[] args) {
		VPrincipal vista = new VPrincipal();
		vista.principiante();
		Casillas casilla = new Casillas(vista.getnMinas(), vista.getFilas(), vista.getColumnas());
		
		vista.reiniciar();
		vista.iniciar();
		Controlador controla = new Controlador(vista, casilla);
		vista.controlador(controla);
	}
}
