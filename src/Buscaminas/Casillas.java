package Buscaminas;

import java.awt.Insets;
import java.util.Random;

public class Casillas {
	private int numeroMinas;
	private int nMinas;
	private int filas;
	private int columnas;
	private Random aleatorio1 = new Random();
	private Random aleatorio2 = new Random();
	private int[][] casillas;
	private boolean[][] pulsado;
	private boolean[][] bandera;

	public Casillas(int nMinas, int filas, int columnas) {
		this.nMinas = nMinas;
		this.filas = filas;
		this.columnas = columnas;

		this.casillas = new int[filas][columnas];
		this.bandera = new boolean[filas][columnas];
		this.pulsado = new boolean[filas][columnas];

	}

	public void configurarCasillas(int nMinas, int filas, int columnas) {
		this.nMinas = getnMinas();
		this.filas = getFilas();
		this.columnas = getColumnas();

		this.casillas = new int[filas][columnas];
		this.bandera = new boolean[filas][columnas];
		this.pulsado = new boolean[filas][columnas];
	}

	public int getnMinas() {
		return nMinas;
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public int[][] getCasillas() {
		return casillas;
	}

	public boolean[][] getPulsado() {
		return pulsado;
	}

	public boolean[][] getBandera() {
		return bandera;
	}

	public void setBandera(boolean[][] bandera) {
		this.bandera = bandera;
	}

	public void ponerMinas() {
		numeroMinas = 0;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				casillas[i][j] = 0;
			}
		}

		while (numeroMinas < nMinas) {
			int random1 = aleatorio1.nextInt(filas);
			int random2 = aleatorio2.nextInt(columnas);
			if (casillas[random1][random2] != 9) {
				casillas[random1][random2] = 9;
				numeroMinas++;
			}
		}
	}

	public void ponerNumeros() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (casillas[i][j] == 0) {
					for (int k = i - 1; k <= i + 1; k++) {
						for (int m = j - 1; m <= j + 1; m++) {
							if (k >= 0 && m >= 0 && k < filas && m < columnas) {
								if (casillas[k][m] == 9) {
									casillas[i][j]++;
								}
							}
						}
					}
				}
			}
		}
	}

	public void noPulsadoNoBandera() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				pulsado[i][j] = false;
				bandera[i][j] = false;
			}
		}
	}

	public void pulsar(int i, int j) {
		pulsado[i][j] = true;
	}

	/**
	 * Pulsar todo (pulsa todas las casillas del Modelo, este método se utiliza
	 * cuando se pierda la partida(bomba pulsada), se ponen todas las casillas a
	 * pulsadas y no se pueden pulsar mas casillas hasta que se inicie un nuevo
	 * juego)
	 */
	public void pulsarTodo() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				pulsado[i][j] = true;
				bandera[i][j] = true;
			}
		}

	}

	public void cambiarBandera(int i, int j) {
		if (bandera[i][j] == true) {
			bandera[i][j] = false;
		} else {
			bandera[i][j] = true;
		}

	}

	public void detectarMinas(int i, int j) {
		if (casillas[i][j] == 9 && bandera[i][j]) {
			nMinas--;
		} else if (casillas[i][j] == 9 && !bandera[i][j]) {
			nMinas++;
		}
	}
}
