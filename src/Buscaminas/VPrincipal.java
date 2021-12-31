
package Buscaminas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 * @author programacion
 *
 */
public class VPrincipal extends JFrame {
	// Marcos y dimensiones
	private int ancho;
	private int alto;
	private int filas = 9;
	private int columnas = 9;
	private int nMinas;

	private GridLayout layoutCasillas;
	private JFrame frame;
	private JPanel panelBotones = new JPanel();
	private JPanel panelJuego = new JPanel();
	private JPanel vista = new JPanel();
	private JPanel kk = new JPanel();
 	private JButton jugar = new JButton();
	private JTextField tiempo = new JTextField("08:32:59");
	private JTextField minas = new JTextField(nMinas);
	private JLabel labelMinas = new JLabel();
	private JLabel labelReloj = new JLabel();
	private JButton[][] botones;
	private int nivel;
	private Fuentes f = new Fuentes();
	private Font fuente = new Font("Arial", Font.BOLD, 24);
	private Font fuenteBotones = new Font("Arial", Font.BOLD, 22);
	private Color color = new Color(0, 0, 0, 0);
	private Color colorAmarillo = new Color(255,0,127);

	/**
	 * Convertimos mina en una imagen. creamos una imagen nueva dándole las
	 * dimensiones que queramos a la antigua. Lo mismo con las demás imágenes.
	 */
	private ImageIcon mina = new ImageIcon("src/images/mine.png");
	private Image imagenMina = mina.getImage();
	private Image minaImagen = imagenMina.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private ImageIcon minaBuena = new ImageIcon(minaImagen);
	
	private ImageIcon minaMala = new ImageIcon("src/images/minemala.png");
	private Image imagenMinaMala = minaMala.getImage();
	private Image minaMalaImagen = imagenMinaMala.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private ImageIcon minaMalaBuena = new ImageIcon(minaMalaImagen);

	private ImageIcon flag = new ImageIcon("src/images/flag.png");
	private Image imagenFlag = flag.getImage();
	private Image flagImagen = imagenFlag.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private ImageIcon flagBuena = new ImageIcon(flagImagen);

	private ImageIcon inicio = new ImageIcon("src/images/playinicio.png");
	private Image imagenPlayInicio = inicio.getImage();
	private Image iconPlayInicio = imagenPlayInicio.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
	private ImageIcon playInicio = new ImageIcon(iconPlayInicio);

	private ImageIcon iconoJugar = new ImageIcon("src/images/playjugar.png");
	private Image imagenJugar = iconoJugar.getImage();
	private Image iconPlayJugar = imagenJugar.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
	private ImageIcon playJugar = new ImageIcon(iconPlayJugar);

	private ImageIcon iconoPerder = new ImageIcon("src/images/playperder.png");
	private Image imagenPlayPerder = iconoPerder.getImage();
	private Image playImagenPerder = imagenPlayPerder.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
	private ImageIcon playPerder = new ImageIcon(playImagenPerder);

	private ImageIcon iconoMinasLabel = new ImageIcon("src/images/minaslabel.png");
	private Image imagenMinasLabel = iconoMinasLabel.getImage();
	private Image minasLabelImagen = imagenMinasLabel.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
	private ImageIcon minasLabel = new ImageIcon(minasLabelImagen);

	private ImageIcon iconoReloj = new ImageIcon("src/images/reloj.png");
	private Image imagenIconoReloj = iconoReloj.getImage();
	private Image RelojImagen = imagenIconoReloj.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
	private ImageIcon reloj = new ImageIcon(RelojImagen);

	private JMenuBar barraMenu;
	/* Declaro los JMenu */
	private JMenu menuVer, menuEdicion, menuAyuda, menuEstadisticas;
	/* Declaro todos los JMenuItem */
	private JMenuItem itmPrincipiante, itmIntermedio, itmExperto, itmHistorialEditar, itmHistorialCancelar,
			itmHistorialBorrar, itmAyudaVer, itmAyudaAcerca;

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public int getnMinas() {
		return nMinas;
	}

	public JButton[][] getBotones() {
		return botones;
	}

	// Constructor
	public VPrincipal() {
		super("Buscaminas");
		this.setLayout(new BorderLayout());
		this.setIconImage(iconoMinasLabel.getImage());
		this.setJMenuBar(menu());
	}

	public JMenuBar menu() {
		this.barraMenu = new JMenuBar();
		this.menuVer = new JMenu("Ver");
		this.menuEdicion = new JMenu("Edición");
		this.menuAyuda = new JMenu("Ayuda");
		this.menuEstadisticas = new JMenu("Estadísticas");

		this.itmPrincipiante = new JMenuItem("Principiante");
		this.itmIntermedio = new JMenuItem("Intermedio");
		this.itmExperto = new JMenuItem("Experto");

		this.barraMenu.add(menuVer);
		this.barraMenu.add(menuEdicion);
		this.barraMenu.add(menuAyuda);
		this.barraMenu.add(menuEstadisticas);
		this.menuVer.add(itmPrincipiante);
		this.menuVer.add(itmIntermedio);
		this.menuVer.add(itmExperto);

		return barraMenu;

	}

	public void reiniciar() {
		this.getContentPane().removeAll();
		// this.validate();
		// this.panelJuego.updateUI();
		this.add(panelBotones(), BorderLayout.NORTH);
		this.add(panelJuego(), BorderLayout.CENTER);
	}

	public JPanel iniciar() {
		JPanel vista = new JPanel();
		this.setSize(ancho, alto);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		return vista;
	}

	public void controlador(Controlador c) {
		itmPrincipiante.addActionListener(c);
		itmIntermedio.addActionListener(c);
		itmExperto.addActionListener(c);
		jugar.addActionListener(c);
		for (int i = 0; i < getFilas(); i++) {
			for (int j = 0; j < getColumnas(); j++) {
				botones[i][j].addMouseListener(c);
			}
		}
	}

	public JPanel panelBotones() {
		panelBotones.setLayout(new FlowLayout());
		// panelBotones.setBackground(Color.lightGray);
		panelBotones.add(labelReloj);
		panelBotones.add(tiempo);
		panelBotones.add(jugar);
		panelBotones.add(minas);
		panelBotones.add(labelMinas);
		labelReloj.setIcon(reloj);
		labelMinas.setIcon(minasLabel);
		jugar.setMargin(new Insets(2, 2, 2, 2));
		jugar.setIcon(playInicio);
		jugar.setBorder(BorderFactory.createBevelBorder(0));
		minas.setSize(60, 24);
		minas.setPreferredSize(minas.getSize());
		minas.setFont(f.MyFont(1, 20));
		tiempo.setSize(60, 24);
		tiempo.setPreferredSize(tiempo.getSize());
		tiempo.setFont(f.MyFont(0, 16));
		tiempo.setForeground(Color.blue);
		tiempo.setBackground(Color.black);
		minas.setForeground(Color.blue);
		minas.setBackground(Color.black);
		tiempo.setHorizontalAlignment(SwingConstants.RIGHT);
		minas.setHorizontalAlignment(SwingConstants.RIGHT);
		tiempo.setEditable(false);
		minas.setEditable(false);
		panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		return panelBotones;
	}

	public JPanel panelJuego() {
		panelJuego = new JPanel();
		layoutCasillas = new GridLayout(filas, columnas, 0, 0);
		panelJuego.setLayout(layoutCasillas);
		panelJuego.setBorder(BorderFactory.createEmptyBorder(3, 10, 10, 10));
		// panelJuego.setBackground(Color.lightGray);
		botones = new JButton[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				botones[i][j] = new JButton();
				panelJuego.add(botones[i][j]);
				botones[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
			}
		}
		return panelJuego;
	}

	public void rellenarCasillas(int[][] casillas) {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				botones[i][j].setText(String.valueOf(casillas[i][j]));
				botones[i][j].setMargin(new Insets(0, 0, 0, 0));
				botones[i][j].setFont(fuenteBotones);
				botones[i][j].setFocusPainted(false);
				botones[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
			}
		}
	}

	public void busquedaRecursiva(int i, int j) {
		if (i < 0)
			return;
		if (j < 0)
			return;
		if (i >= filas)
			return;
		if (j >= columnas)
			return;

		for (i = 0; i < filas; i++) {
			for (j = 0; j < columnas; j++) {
				if (botones[i][j].getBackground().equals(Color.lightGray)) {
					for (int k = i - 1; k <= i + 1; k++) {
						for (int m = j - 1; m <= j + 1; m++) {
							if (k >= 0 && m >= 0 && k < filas && m < columnas
									&& !botones[k][m].getText().equals(null)) {
								if (botones[k][m].getText().equals("0")
										|| botones[k][m].getBackground().equals(Color.lightGray)) {
									botones[k][m].setBackground(Color.lightGray);
									botones[k][m].setBorder(BorderFactory.createEtchedBorder());
									botones[k][m].setEnabled(false);
								} else if (!botones[k][m].getText().equals("0")
										&& !botones[k][m].getText().equals("9")) {
									buscarNumero(k, m);
								}
							}
						}
					}
				}
			}
		}

	}

	public void busqueda(JButton[][] matriz, int i, int j) {
		if (botones[i][j].getText().equals("0")) {
			botones[i][j].setBackground(Color.lightGray);
			botones[i][j].setBorder(BorderFactory.createEtchedBorder());
			botones[i][j].setEnabled(false);
			busquedaRecursiva(i, j);
			busquedaRecursiva(i, j - 1);
			busquedaRecursiva(i, j + 1);
			busquedaRecursiva(i - 1, j);
			busquedaRecursiva(i + 1, j);
			busquedaRecursiva(i + 1, j + 1);
			busquedaRecursiva(i - 1, j - 1);
			busquedaRecursiva(i + 1, j - 1);
			busquedaRecursiva(i - 1, j + 1);
		}
		buscarNumero(i, j);
	}

	public void buscarNumero(int i, int j) {
		if (botones[i][j].getText().equals("1")) {
			botones[i][j].setForeground(Color.black);
		} else if (botones[i][j].getText().equals("2")) {
			botones[i][j].setForeground(Color.blue);
		} else if (botones[i][j].getText().equals("3")) {
			botones[i][j].setForeground(Color.green);
		} else if (botones[i][j].getText().equals("4")) {
			botones[i][j].setForeground(Color.ORANGE);
		} else if (botones[i][j].getText().equals("5")) {
			botones[i][j].setForeground(colorAmarillo);
		} else if (botones[i][j].getText().equals("6") || botones[i][j].getText().equals("7")) {
			botones[i][j].setForeground(Color.magenta);
		} else if (botones[i][j].getText().equals("8")) {
			botones[i][j].setForeground(Color.red);
		} else if (botones[i][j].getText().equals("9")) {
			perder(i, j);
		}
	}

	/**
	 * Resetea los botones cada vez que se inicia una nueva partida.
	 * 
	 */
	public void resetearBotones(Controlador c) {
		jugar.setIcon(playJugar);
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				// Quito el controlador de los botones. para añadirlos de nuevo con
				// los nuevos valores de minas, filas y columnas.
				botones[i][j].removeMouseListener(c);
				botones[i][j].setIcon(null);
				botones[i][j].setBackground(jugar.getBackground());
				botones[i][j].setEnabled(true);
				botones[i][j].setBorder(jugar.getBorder());
				botones[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
				botones[i][j].setForeground(Color.black);
				botones[i][j].setForeground(color);
			}
		}
	}

	public void perder(int i, int j) {
		jugar.setIcon(playPerder);
		botones[i][j].setText("");
		botones[i][j].setIcon(minaBuena);
		botones[i][j].setBackground(Color.red);
		JOptionPane.showMessageDialog(null, "¡¡HA PERDIDO!!");
	}

	public void mostrarTodasMinas(int[][] casillas, boolean[][] bandera) {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (casillas[i][j] == 9 && bandera[i][j] == false) {
					botones[i][j].setText("");
					botones[i][j].setIcon(minaBuena);
				} else if (casillas[i][j] == 9 && bandera[i][j] == true) {
					botones[i][j].setText("");
					botones[i][j].setIcon(flagBuena);
				} else if (casillas[i][j] != 9 && bandera[i][j] == true) {
					botones[i][j].setText("");
					botones[i][j].setIcon(minaMalaBuena);
				} else if (casillas[i][j] == 0 && bandera[i][j] == false) {
					botones[i][j].setBackground(Color.lightGray);
					botones[i][j].setBorder(BorderFactory.createEtchedBorder());
					botones[i][j].setEnabled(false);
				}
				buscarNumero(i, j);
			}
		}
	}

	public void ganador(int nMinas) {
		if (nMinas == 0) {
			JOptionPane.showMessageDialog(null, "¡¡BIEN, HAS GANADO!!");
		}
	}

	public void botonDerecho(int[][] matriz, int i, int j, boolean bandera) {
		if (bandera == false) {
			botones[i][j].setIcon(null);
			botones[i][j].setText(String.valueOf(matriz[i][j]));
			nMinas++;
			minas.setText(String.valueOf(String.format("%02d", nMinas)));
		} else if (bandera == true) {
			botones[i][j].setText("");
			botones[i][j].setIcon(flagBuena);
			nMinas--;
			minas.setText(String.valueOf(String.format("%02d", nMinas)));
		}
	}

	public void principiante() {
		nivel = 1;
		ancho = 260;
		alto = 360;
		filas = 9;
		columnas = 9;
		nMinas = 10;
		minas.setText(String.valueOf(String.format("%02d", nMinas)));
	}

	public void intermedio() {
		nivel = 2;
		ancho = 440;
		alto = 520;
		filas = 16;
		columnas = 16;
		nMinas = 40;
		minas.setText(String.valueOf(String.format("%02d", nMinas)));
	}

	public void experto() {
		nivel = 3;
		ancho = 780;
		alto = 520;
		filas = 16;
		columnas = 30;
		nMinas = 99;
		minas.setText(String.valueOf(String.format("%02d", nMinas)));
	}

}
