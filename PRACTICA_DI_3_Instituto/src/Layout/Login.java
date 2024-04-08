package Layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Database.Gestion;
import Helpers.Email;
import Model.Alumno;
import Model.Profesor;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Login extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputEmail;
	private JPasswordField inputPassword;
	
	private JButton btnRegistro;
	private JButton btnLogin;
	
	private Gestion database = new Gestion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		//Configuración de la ventana
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 250);
		setLocationRelativeTo(null); //centrar la ventana
		setTitle("Aplicación: Notas instituto"); //Título de la ventana
		
		//Content Pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		
		//Layout
		GridBagLayout gbl_contentPane = new GridBagLayout();		
		gbl_contentPane.columnWidths = new int[]{350, 150};
		gbl_contentPane.rowHeights = new int[]{250};
		contentPane.setLayout(gbl_contentPane);
		
		//Izquierda
		
			//Panel
			JPanel panelIzquierda = new JPanel();
			Border bordeDerecho = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode("#BDBDBD")); // Crea un borde negro de 2 píxeles de ancho en el lado derecho
			panelIzquierda.setBorder(bordeDerecho);
			GridBagConstraints gbc_panelIzquierda = new GridBagConstraints();			
			gbc_panelIzquierda.fill = GridBagConstraints.HORIZONTAL;
			gbc_panelIzquierda.gridx = 0;
			gbc_panelIzquierda.gridy = 0;
			contentPane.add(panelIzquierda, gbc_panelIzquierda);
			
			//Layout
			GridBagLayout gbl_panelIzquierda = new GridBagLayout();			
			gbl_panelIzquierda.columnWeights = new double[]{50, 150};
			gbl_panelIzquierda.rowHeights = new int[]{40,40,40};
			panelIzquierda.setLayout(gbl_panelIzquierda);
				
				
				//Elementos
				
					JLabel labelTitle = new JLabel("INICIAR SESIÓN");
					labelTitle.setFont(new Font("Open Sans", Font.BOLD, 13));
					GridBagConstraints gbc_labelTitle = new GridBagConstraints();
					gbc_labelTitle.insets = new Insets(0, 0, 10, 0);
					gbc_labelTitle.gridx = 0;
					gbc_labelTitle.gridy = 0;
					gbc_labelTitle.gridwidth=2;
					panelIzquierda.add(labelTitle, gbc_labelTitle);				
					
					//Emails
					JLabel labelEmail = new JLabel("Email");
					labelEmail.setFont(new Font("Open Sans", Font.PLAIN, 13));
					GridBagConstraints gbc_labelEmail = new GridBagConstraints();
					gbc_labelEmail.anchor = GridBagConstraints.EAST;
					gbc_labelEmail.insets = new Insets(0, 0, 20, 20);
					gbc_labelEmail.gridx = 0;
					gbc_labelEmail.gridy = 1;
					panelIzquierda.add(labelEmail, gbc_labelEmail);
					
					inputEmail = new JTextField();
					inputEmail.setToolTipText("Email");
					inputEmail.setFont(new Font("Open Sans", Font.PLAIN, 13));
					inputEmail.setOpaque(true);
					inputEmail.setBackground(Color.decode("#FFFFFF"));
					inputEmail.setBorder(new LineBorder(Color.decode("#90A4AE"), 1, true));
					inputEmail.setPreferredSize(new Dimension(150, 25));
					GridBagConstraints gbc_inputEmail = new GridBagConstraints();
					gbc_inputEmail.insets = new Insets(0, 0, 20, 20);
					gbc_inputEmail.fill = GridBagConstraints.BOTH;
					gbc_inputEmail.gridx = 1;
					gbc_inputEmail.gridy = 1;
					panelIzquierda.add(inputEmail, gbc_inputEmail);
					inputEmail.setColumns(10);
				
					
					//Contraseña
					JLabel labelPassword = new JLabel("Contraseña");
					labelPassword.setFont(new Font("Open Sans", Font.PLAIN, 13));
					GridBagConstraints gbc_labelPassword = new GridBagConstraints();
					gbc_labelPassword.anchor = GridBagConstraints.EAST;
					gbc_labelPassword.insets = new Insets(0, 0, 20, 20);
					gbc_labelPassword.gridx = 0;
					gbc_labelPassword.gridy = 2;
					panelIzquierda.add(labelPassword, gbc_labelPassword);
					
					inputPassword = new JPasswordField();
					inputPassword.setToolTipText("Contraseña");
					inputPassword.setOpaque(true);
					inputPassword.setBackground(Color.decode("#FFFFFF"));
					inputPassword.setBorder(new LineBorder(Color.decode("#90A4AE"), 1, true));
					inputPassword.setPreferredSize(new Dimension(150, 25));
					GridBagConstraints gbc_inputPassword = new GridBagConstraints();
					gbc_inputPassword.insets = new Insets(0, 0, 20, 20);
					gbc_inputPassword.fill = GridBagConstraints.BOTH;
					gbc_inputPassword.gridx = 1;
					gbc_inputPassword.gridy = 2;
					panelIzquierda.add(inputPassword, gbc_inputPassword);
					
					
					//Btn Login
					btnLogin = new JButton("Login");
					btnLogin.addActionListener(this);
					btnLogin.setFont(new Font("Open Sans", Font.PLAIN, 13));
					btnLogin.setPreferredSize(new Dimension(100, 34));	
					btnLogin.setMargin(new Insets(10, 10, 10, 10)); // Padding del botón
					GridBagConstraints gbc_btnLogin = new GridBagConstraints();
					gbc_btnLogin.insets = new Insets(0, 0, 10, 10);
					gbc_btnLogin.gridx = 1;
					gbc_btnLogin.gridy = 3;
					panelIzquierda.add(btnLogin, gbc_btnLogin);
				
				
		
		//Derecha
			
			//Panel
			JPanel panelDerecha = new JPanel();
			GridBagConstraints gbc_panelDerecha = new GridBagConstraints();
			gbc_panelDerecha.fill = GridBagConstraints.HORIZONTAL;
			gbc_panelDerecha.gridx = 1;
			gbc_panelDerecha.gridy = 0;
			contentPane.add(panelDerecha, gbc_panelDerecha);
			
			
			//Layout
			GridBagLayout gbl_panelDerecha = new GridBagLayout();			
			gbl_panelDerecha.columnWeights = new double[]{0.0};
			gbl_panelDerecha.rowWeights = new double[]{0.0};
			panelDerecha.setLayout(gbl_panelDerecha);
			
			
			//Elementos -> Botones
			
				btnRegistro = new JButton("Registro");
				btnRegistro.addActionListener(this);
				btnRegistro.setFont(new Font("Open Sans", Font.PLAIN, 13));
				btnRegistro.setPreferredSize(new Dimension(100, 34));	
				btnRegistro.setMargin(new Insets(10, 10, 10, 10)); // Padding del botón
				GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
				gbc_btnRegistro.insets = new Insets(0, 0, 5, 0);
				gbc_btnRegistro.gridx = 0;
				gbc_btnRegistro.gridy = 0;
				panelDerecha.add(btnRegistro, gbc_btnRegistro);
				
				
	}

	
	//EVENTOS
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Botón registro -> que se abra la ventana del registro
			if(e.getSource().equals(btnRegistro)) {	
				
				Registro registro = null;
				
				try {
					registro = new Registro();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				registro.setVisible(true);
			}
			
			
			//Botón del login -> el usuario se loguea y se abre la ventana correspondiente al profesor y al alumno
			if(e.getSource().equals(btnLogin)) {
				
				String email=inputEmail.getText().toLowerCase();
				
				if((email.length()>0)&&(String.valueOf(inputPassword.getPassword()).length()>0)) { //los campos no deben estar vacíos
					
					if(new Email(email).getIsEmail()) { //el email debe cumplir el patrón correspondiente
						
						try {
							
							if(database.getIdUsuario(email)>0) { //el usuario se loguea
								
								int idUsuario=database.makeLogin(email, String.valueOf(inputPassword.getPassword()));
								
								if(idUsuario>0) {
								
									//Compruebo el rol que tiene el usuario y abro una ventana u otra
									switch(database.getIdRol(idUsuario)) {
										
										case 1: //alumno
												
											//Creo el alumno y seteo las notas
											Alumno alumno = database.getAlumno(idUsuario);
											       alumno.setNotas(database.getNotasAlumno(idUsuario, alumno.getCiclo(), alumno.getCurso()));
											       
											//Abro la ventana correspondiente
											NotasAlumno notasAlumno = new NotasAlumno(alumno);
											
											//Cierro la actual
											dispose();
											
										break;
										
										case 2: //profesor
											
											//Creo el profesor 
											Profesor profesor = database.getProfesor(idUsuario);
													 
											//Abro la ventana correspondiente
											NotasProfesor notasProfesor = new NotasProfesor(profesor);
											
											//Cierro la actual
											dispose();
											
										break;
									
									}
								}
								else {
									showMensaje("El usuario o la contraseña son incorrectos.");
								}
							}
							else {
								
								showMensaje("El usuario introducido no está registrado.");
							}
						} catch (SQLException | NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					else {
						showMensaje("El email debe cumplir el patrón: usuario@dominio.com");
					}
					
				}
				else {
					showMensaje("Error: el email y la contraseña no pueden estar vacíos.");
				}
				
			}
			
		}
		
		
		
	//AVISOS
	
			//Método para imprimir en pantalla los avisos
			protected void showMensaje(String mensaje) {
				
				JOptionPane.showConfirmDialog(null, mensaje,
		                "Message", JOptionPane.CLOSED_OPTION,
		                JOptionPane.INFORMATION_MESSAGE);
			}

}
