package reproductor;

import java.awt.BorderLayout;




import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JList;

import java.awt.Rectangle;


import java.awt.Dimension;
import java.awt.Graphics;






public class visual extends JFrame {
	
	public void paint(Graphics g){
        Dimension tamanio = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/img/musica.png"));        
        g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);}  
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPlay;
	private int estado; // 0 play , 1 pausa
	private Reproductor cancionActual;
	private JSlider barra;

	Reproductor r = null;
	private JMenu mnCargar;
	private JMenuBar menuBar;
	private JMenuItem mntmCanciones;
	private JTextArea playlist;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visual frame = new visual();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public visual() {
		super("FASHION PLAYER");
		setBackground(Color.BLUE);
		setResizable(false);
		

		r = new Reproductor();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnCargar = new JMenu("Cargar");


		mntmCanciones = new JMenuItem("canciones");
		
		mntmCanciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();

				int respuesta = fc.showOpenDialog(mnCargar);
				if (respuesta == JFileChooser.APPROVE_OPTION) {
					File archivoElegido = fc.getSelectedFile();

					try {
						
						cancionActual = new Reproductor(archivoElegido.getPath());
						playlist.append(archivoElegido.getName()+"\n");
						
						cancionActual.setRuta(archivoElegido.getPath());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					;

				}
			}
		});
		mnCargar.add(mntmCanciones);
		menuBar.add(mnCargar);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setForeground(Color.BLUE);
		contentPane.setBackground(Color.BLACK);
		
		contentPane.setBorder(new EmptyBorder(0, 0, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnPlay = new JButton("");
		btnPlay.setBackground(Color.BLACK);
		btnPlay = new JButton(new ImageIcon(this.getClass().getResource("/img/play2.png")));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (estado == 1) {
					
						r.Continuar();
						estado = 0;
					} else
						r.AbrirFichero(cancionActual.getRuta());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPlay.setBounds(20, 73, 70, 64);
		contentPane.add(btnPlay);

		JButton btnPause = new JButton("");
		btnPause.setBounds(new Rectangle(12, 13, 44, 44));
		btnPause.setBackground(new Color(0, 0, 0));
		btnPause  = new JButton(new ImageIcon(this.getClass().getResource("/img/pause2.png")));
		btnPause.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					r.Pausa();
					estado = 1; // pausa

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPause.setBounds(20, 167, 62, 51);
		contentPane.add(btnPause);

		JButton btnStop = new JButton("");
		btnStop.setBackground(Color.BLACK);
		btnStop = new JButton(new ImageIcon(this.getClass().getResource("/img/parar2.png"))); 
		btnStop.setBounds(20, 11, 62, 51);
		contentPane.add(btnStop);
		
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					r.Stop();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
				}
			}
		});
		
		
		playlist = new JTextArea();
		playlist.setBounds(195, 11, 183, 207);
		contentPane.add(playlist);
		playlist = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(playlist); 
		playlist.setEditable(false);
	
		
		barra = new JSlider(JSlider.HORIZONTAL,0, 400, 0);
		
		barra.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent event) {
		          int value = barra.getValue();
		          if (value == 0) {
		            System.out.println("0");
		          } else if (value > 0 && value <= 30) {
		            System.out.println("value > 0 && value <= 30");
		          } else if (value > 30 && value < 80) {
		            System.out.println("value > 30 && value < 80");
		          } else {
		            System.out.println("max");
		          }
		        }
		      });
	}
}
