package Layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpinnerListModel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import Database.Gestion;
import Helpers.Email;
import Helpers.Helpers;

import javax.swing.JSpinner;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.NoSuchAlgorithmException;

public class Registro extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputApellidos;
	private JTextField inputEmail;
	private JPasswordField inputPassword;
	private JPasswordField inputRepeatPassword;
	
	private JComboBox selectRoles;
	private JPanel panelDatosUsuario;
	private JPanel panelBotones;
	
	private JLabel labelCiclo;
	private JComboBox selectCiclo;	
	private JLabel labelCurso;
	private JComboBox selectCurso;
	private JLabel labelAsignaturas;
	private JComboBox selectAsignaturas;
	
	private DefaultListModel<String> asignaturasLista = new DefaultListModel<>();
	private JList listaAsignaturas;
	
	private JButton btnRegistro;
	private JButton btnLimpiar;
	
	private Gestion database = new Gestion();

	
	public Registro() throws SQLException {
		//Configuración de la ventana
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null); //centrar la ventana
		setTitle("Aplicación: Notas instituto"); //Título de la ventana
		
		//Content Pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 20, 10, 20));
		setContentPane(contentPane);
		
		//Layout
		GridBagLayout gbl_contentPane = new GridBagLayout();		
		gbl_contentPane.columnWidths = new int[]{480};
		gbl_contentPane.rowHeights = new int[]{290, 100, 40};
		contentPane.setLayout(gbl_contentPane);
		
		
		//Panel de registro
			//Panel
			JPanel panelRegistro = new JPanel();
			GridBagConstraints gbc_panelRegistro = new GridBagConstraints();
			gbc_panelRegistro.insets = new Insets(0, 0, 5, 5);
			gbc_panelRegistro.fill = GridBagConstraints.BOTH;
			gbc_panelRegistro.gridx = 0;
			gbc_panelRegistro.gridy = 0;
			contentPane.add(panelRegistro, gbc_panelRegistro);
			
			//Layout
			GridBagLayout gbl_panelRegistro = new GridBagLayout();			
			gbl_panelRegistro.columnWidths = new int[]{150,310};
			gbl_panelRegistro.rowHeights = new int[]{40, 40, 40, 40, 40, 40, 40};		
			panelRegistro.setLayout(gbl_panelRegistro);
			
			
			//Elementos
				//Título
				JLabel labelTitle = new JLabel("REGISTRO");
				labelTitle.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
				GridBagConstraints gbc_labelTitle = new GridBagConstraints();
				gbc_labelTitle.insets = new Insets(0, 0, 10, 0);
				gbc_labelTitle.gridx = 0;
				gbc_labelTitle.gridy = 0;
				gbc_labelTitle.gridwidth=2;
				panelRegistro.add(labelTitle, gbc_labelTitle);
				
				//Nombre
				JLabel labelNombre = new JLabel("Nombre");
				labelNombre.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelNombre = new GridBagConstraints();
				gbc_labelNombre.fill = GridBagConstraints.VERTICAL;
				gbc_labelNombre.anchor = GridBagConstraints.WEST;
				gbc_labelNombre.insets = new Insets(0, 0, 10, 10);
				gbc_labelNombre.gridx = 0;
				gbc_labelNombre.gridy = 1;
				panelRegistro.add(labelNombre, gbc_labelNombre);
				
				inputNombre = new JTextField();
				inputNombre.setToolTipText("Nombre");
				inputNombre.setFont(new Font("Open Sans", Font.PLAIN, 13));
				inputNombre.setOpaque(true);
				inputNombre.setBackground(Color.decode("#FFFFFF"));
				inputNombre.setBorder(new LineBorder(Color.decode("#90A4AE"), 1, true));
				inputNombre.setPreferredSize(new Dimension(150, 25));
				GridBagConstraints gbc_inputNombre = new GridBagConstraints();
				gbc_inputNombre.insets = new Insets(0, 0, 10, 0);
				gbc_inputNombre.fill = GridBagConstraints.BOTH;
				gbc_inputNombre.gridx = 1;
				gbc_inputNombre.gridy = 1;
				panelRegistro.add(inputNombre, gbc_inputNombre);
				inputNombre.setColumns(10);
				
				
				//Apellidos
				JLabel labelApellidos = new JLabel("Apellidos");
				labelApellidos.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelApellidos = new GridBagConstraints();
				gbc_labelApellidos.fill = GridBagConstraints.VERTICAL;
				gbc_labelApellidos.anchor = GridBagConstraints.WEST;
				gbc_labelApellidos.insets = new Insets(0, 0, 10, 10);
				gbc_labelApellidos.gridx = 0;
				gbc_labelApellidos.gridy = 2;
				panelRegistro.add(labelApellidos, gbc_labelApellidos);
				
				inputApellidos = new JTextField();
				inputApellidos.setToolTipText("Apellidos");
				inputApellidos.setFont(new Font("Open Sans", Font.PLAIN, 13));
				inputApellidos.setOpaque(true);
				inputApellidos.setBackground(Color.decode("#FFFFFF"));
				inputApellidos.setBorder(new LineBorder(Color.decode("#90A4AE"), 1, true));
				inputApellidos.setPreferredSize(new Dimension(150, 25));
				GridBagConstraints gbc_inputApellidos = new GridBagConstraints();
				gbc_inputApellidos.insets = new Insets(0, 0, 10, 0);
				gbc_inputApellidos.fill = GridBagConstraints.BOTH;
				gbc_inputApellidos.gridx = 1;
				gbc_inputApellidos.gridy = 2;
				panelRegistro.add(inputApellidos, gbc_inputApellidos);
				inputApellidos.setColumns(10);
				
				
				//Email
				JLabel labelEmail = new JLabel("Email");
				labelEmail.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelEmail = new GridBagConstraints();
				gbc_labelEmail.anchor = GridBagConstraints.WEST;
				gbc_labelEmail.insets = new Insets(0, 0, 10, 10);
				gbc_labelEmail.gridx = 0;
				gbc_labelEmail.gridy = 3;
				panelRegistro.add(labelEmail, gbc_labelEmail);
				
				inputEmail = new JTextField();
				inputEmail.setFont(new Font("Open Sans", Font.PLAIN, 13));
				inputEmail.setOpaque(true);
				inputEmail.setBackground(Color.decode("#FFFFFF"));
				inputEmail.setBorder(new LineBorder(Color.decode("#90A4AE"), 1, true));
				inputEmail.setPreferredSize(new Dimension(150, 25));
				GridBagConstraints gbc_inputEmail = new GridBagConstraints();
				gbc_inputEmail.insets = new Insets(0, 0, 10, 0);
				gbc_inputEmail.fill = GridBagConstraints.BOTH;
				gbc_inputEmail.gridx = 1;
				gbc_inputEmail.gridy = 3;
				panelRegistro.add(inputEmail, gbc_inputEmail);
				inputEmail.setColumns(10);
				
				
				//Contraseña
				JLabel labelPassword = new JLabel("Contraseña");
				labelPassword.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelPassword = new GridBagConstraints();
				gbc_labelPassword.anchor = GridBagConstraints.WEST;
				gbc_labelPassword.insets = new Insets(0, 0, 10, 10);
				gbc_labelPassword.gridx = 0;
				gbc_labelPassword.gridy = 4;
				panelRegistro.add(labelPassword, gbc_labelPassword);
				
				inputPassword = new JPasswordField();
				inputPassword.setToolTipText("Contraseña");	
				inputPassword.setOpaque(true);
				inputPassword.setBackground(Color.decode("#FFFFFF"));
				inputPassword.setBorder(new LineBorder(Color.decode("#90A4AE"), 1, true));
				inputPassword.setPreferredSize(new Dimension(150, 25));
				GridBagConstraints gbc_inputPassword = new GridBagConstraints();
				gbc_inputPassword.insets = new Insets(0, 0, 10, 0);
				gbc_inputPassword.fill = GridBagConstraints.BOTH;
				gbc_inputPassword.gridx = 1;
				gbc_inputPassword.gridy = 4;
				panelRegistro.add(inputPassword, gbc_inputPassword);
				
				
				//Repite contraseña
				JLabel labelRepeatPassword = new JLabel("Repite contraseña");
				labelRepeatPassword.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelRepeatPassword = new GridBagConstraints();
				gbc_labelRepeatPassword.anchor = GridBagConstraints.WEST;
				gbc_labelRepeatPassword.insets = new Insets(0, 0, 10, 10);
				gbc_labelRepeatPassword.gridx = 0;
				gbc_labelRepeatPassword.gridy = 5;
				panelRegistro.add(labelRepeatPassword, gbc_labelRepeatPassword);
				
				inputRepeatPassword = new JPasswordField();
				inputRepeatPassword.setToolTipText("Repite contraseña");
				inputRepeatPassword.setOpaque(true);
				inputRepeatPassword.setBackground(Color.decode("#FFFFFF"));
				inputRepeatPassword.setBorder(new LineBorder(Color.decode("#90A4AE"), 1, true));
				inputRepeatPassword.setPreferredSize(new Dimension(150, 25));
				GridBagConstraints gbc_inputRepeatPassword = new GridBagConstraints();
				gbc_inputRepeatPassword.insets = new Insets(0, 0, 10, 0);
				gbc_inputRepeatPassword.fill = GridBagConstraints.BOTH;
				gbc_inputRepeatPassword.gridx = 1;
				gbc_inputRepeatPassword.gridy = 5;
				panelRegistro.add(inputRepeatPassword, gbc_inputRepeatPassword);
				
				JLabel labelRol = new JLabel("Rol");
				labelRol.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelRol = new GridBagConstraints();
				gbc_labelRol.anchor = GridBagConstraints.WEST;
				gbc_labelRol.insets = new Insets(0, 0, 10, 10);
				gbc_labelRol.gridx = 0;
				gbc_labelRol.gridy = 6;
				panelRegistro.add(labelRol, gbc_labelRol);				
				
				//Select para los roles
				selectRoles = makeSelect(new String[]{"ID", "Nombre"}, "Roles", "Selecciona un rol");	
				selectRoles.setFont(new Font("Open Sans", Font.PLAIN, 13));
				selectRoles.setPreferredSize(new Dimension(0, 25));		
				selectRoles.addActionListener(this);
				GridBagConstraints gbc_selectRoles = new GridBagConstraints();
				gbc_selectRoles.insets = new Insets(0, 0, 10, 0);
				gbc_selectRoles.fill = GridBagConstraints.BOTH;
				gbc_selectRoles.gridx = 1;
				gbc_selectRoles.gridy = 6;
				panelRegistro.add(selectRoles, gbc_selectRoles);      
				
				
				
				
		//Panel del alumno
				
			//Panel
			panelDatosUsuario = new JPanel();		
			String titlePanelDatosUsuario = "Datos usuario";
			TitledBorder borderPanelDatosUsuario = BorderFactory.createTitledBorder(titlePanelDatosUsuario);
			panelDatosUsuario.setBorder(borderPanelDatosUsuario);
			panelDatosUsuario.setVisible(false); //en una primera carga de la ventana no quiero que este contenido esté visible
			GridBagConstraints gbc_panelDatosUsuario = new GridBagConstraints();
			gbc_panelDatosUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_panelDatosUsuario.fill = GridBagConstraints.BOTH;
			gbc_panelDatosUsuario.gridx = 0;
			gbc_panelDatosUsuario.gridy = 1;
			contentPane.add(panelDatosUsuario, gbc_panelDatosUsuario);		
			
			
			//Layout
			GridBagLayout gbl_panelDatosUsuario = new GridBagLayout();			
			gbl_panelDatosUsuario.columnWidths = new int[]{150,310};
			gbl_panelDatosUsuario.rowHeights = new int[]{40, 40, 10};				
			panelDatosUsuario.setLayout(gbl_panelDatosUsuario);
			
			
			//Elementos
				//Ciclo formativo
				labelCiclo = new JLabel("Ciclo formativo");
				labelCiclo.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelCiclo = new GridBagConstraints();
				gbc_labelCiclo.anchor = GridBagConstraints.WEST;
				gbc_labelCiclo.insets = new Insets(0, 0, 10, 10);
				gbc_labelCiclo.gridx = 0;
				gbc_labelCiclo.gridy = 0;
				labelCiclo.setVisible(false);
				panelDatosUsuario.add(labelCiclo, gbc_labelCiclo);
				
				selectCiclo = makeSelect(new String[]{"ID", "Nombre"}, "Ciclos", "Selecciona un ciclo");				
				selectCiclo.setPreferredSize(new Dimension(0, 25));
				selectCiclo.setFont(new Font("Open Sans", Font.PLAIN, 13));				
				GridBagConstraints gbc_selectCiclo = new GridBagConstraints();
				gbc_selectCiclo.insets = new Insets(0, 0, 10, 0);
				gbc_selectCiclo.fill = GridBagConstraints.HORIZONTAL;
				gbc_selectCiclo.gridx = 1;
				gbc_selectCiclo.gridy = 0;
				selectCiclo.setVisible(false);
				panelDatosUsuario.add(selectCiclo, gbc_selectCiclo);
				
				
				//Curso
				labelCurso = new JLabel("Curso");
				labelCurso.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelCurso = new GridBagConstraints();
				gbc_labelCurso.anchor = GridBagConstraints.WEST;
				gbc_labelCurso.insets = new Insets(0, 0, 10, 10);
				gbc_labelCurso.gridx = 0;
				gbc_labelCurso.gridy = 1;	
				labelCurso.setVisible(false);
				panelDatosUsuario.add(labelCurso, gbc_labelCurso);
				
				selectCurso = makeSelect(new String[]{"ID", "Nombre"}, "Cursos", "Selecciona un curso");
				selectCurso.setPreferredSize(new Dimension(0, 25));
				selectCurso.setFont(new Font("Open Sans", Font.PLAIN, 13));				
				selectCurso.setVisible(true);
				GridBagConstraints gbc_selectCurso = new GridBagConstraints();
				gbc_selectCurso.fill = GridBagConstraints.HORIZONTAL;
				gbc_selectCurso.insets = new Insets(0, 0, 10, 0);
				gbc_selectCurso.gridx = 1;
				gbc_selectCurso.gridy = 1;
				panelDatosUsuario.add(selectCurso, gbc_selectCurso);
				
				
				//Asignaturas
				labelAsignaturas = new JLabel("Asignaturas");				
				labelAsignaturas.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelAsignaturas = new GridBagConstraints();
				gbc_labelAsignaturas.anchor = GridBagConstraints.WEST;
				gbc_labelAsignaturas.insets = new Insets(0, 0, 10, 10);
				gbc_labelAsignaturas.gridx = 0;
				gbc_labelAsignaturas.gridy = 0;
				labelAsignaturas.setVisible(false);
				panelDatosUsuario.add(labelAsignaturas, gbc_labelAsignaturas);
								
				
				selectAsignaturas = makeSelect(new String[]{"ID", "Nombre"}, "Asignaturas", "Selecciona una asignatura");
				selectAsignaturas.setFont(new Font("Open Sans", Font.PLAIN, 13));
				selectAsignaturas.setToolTipText("Curso");
				selectAsignaturas.setPrototypeDisplayValue("Selecciona una asignatura"); //ajustar el ancho del comboBox al largo de la cadena
				selectAsignaturas.setVisible(true);
				selectAsignaturas.addActionListener(this);
				GridBagConstraints gbc_selectAsignaturas = new GridBagConstraints();
				gbc_selectAsignaturas.fill = GridBagConstraints.BOTH;
				gbc_selectAsignaturas.insets = new Insets(0, 0, 10, 0);
				gbc_selectAsignaturas.gridx = 1;
				gbc_selectAsignaturas.gridy = 0;
				selectAsignaturas.setVisible(false);
				panelDatosUsuario.add(selectAsignaturas, gbc_selectAsignaturas);
				
				
				
				//Asignaturas que hemos seleccionado de la lista	            
				listaAsignaturas = new JList<>(asignaturasLista);			
				listaAsignaturas.addMouseListener(this);
				listaAsignaturas.setBackground(new Color(238, 238, 238));
				listaAsignaturas.setPrototypeCellValue("Selecciona una asignatura"); //ajusto el ancho de la lista al largo de la cadena
				            
				GridBagConstraints gbc_listaAsignaturas = new GridBagConstraints();
				gbc_listaAsignaturas.fill = GridBagConstraints.BOTH;
				gbc_listaAsignaturas.gridx = 1;
				gbc_listaAsignaturas.gridy = 1;	
				gbc_listaAsignaturas.gridheight=2;
				listaAsignaturas.setVisible(false);
				panelDatosUsuario.add(listaAsignaturas, gbc_listaAsignaturas);
								
		
			
		//Panel del alumno
				
			//Panel
			panelBotones = new JPanel();	
			panelBotones.setVisible(false);
			GridBagConstraints gbc_panelBotones = new GridBagConstraints();
			gbc_panelBotones.insets = new Insets(0, 0, 5, 5);
			gbc_panelBotones.fill = GridBagConstraints.BOTH;
			gbc_panelBotones.gridx = 0;
			gbc_panelBotones.gridy = 2;
			contentPane.add(panelBotones, gbc_panelBotones);
			
			
			//Layout
			GridBagLayout gbl_panelBotones = new GridBagLayout();			
			gbl_panelBotones.columnWidths = new int[]{230, 230};
			gbl_panelBotones.rowHeights = new int[]{40};				
			panelBotones.setLayout(gbl_panelBotones);
			
			
			//Elementos			
			
			btnLimpiar = new JButton("Limpiar");
			btnLimpiar.addActionListener(this);
			btnLimpiar.setFont(new Font("Open Sans", Font.PLAIN, 13));
			btnLimpiar.setPreferredSize(new Dimension(100, 34));	
			btnLimpiar.setMargin(new Insets(10, 10, 10, 10)); // Padding del botón
			GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
			gbc_btnLimpiar.anchor = GridBagConstraints.EAST;
			gbc_btnLimpiar.insets = new Insets(0, 0, 0, 5);
			gbc_btnLimpiar.gridx = 0;
			gbc_btnLimpiar.gridy = 0;
			panelBotones.add(btnLimpiar, gbc_btnLimpiar);	
			
			
			btnRegistro = new JButton("Registrar");
			btnRegistro.addActionListener(this);
			btnRegistro.setFont(new Font("Open Sans", Font.PLAIN, 13));
			btnRegistro.setPreferredSize(new Dimension(100, 34));	
			btnRegistro.setMargin(new Insets(10, 10, 10, 10)); // Padding del botón
			GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
			gbc_btnGuardar.anchor = GridBagConstraints.WEST;
			gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
			gbc_btnGuardar.gridx = 1;
			gbc_btnGuardar.gridy = 0;
			panelBotones.add(btnRegistro, gbc_btnGuardar);	
			
			
	
		
	}
	
	
	
	//Base de datos
		//Método que devuelve las opciones de los select
		protected JComboBox makeSelect(String[] campos, String tabla, String defaultOption) throws SQLException {		
			
			ArrayList<String> valores = database.getValorSelect(campos, tabla); //valores del select
			
			//Creo el select
			JComboBox comboBox = new JComboBox();
			comboBox.addItem(defaultOption);
			
			for (int i=0; i<valores.size(); i++) {			
				
				comboBox.addItem(valores.get(i));
				
			}		
			
			return comboBox;
			
		}

		
		
	//Eventos
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//onChange
				//Select Roles
				if(e.getSource().equals(selectRoles)) {				
					
					int selectedIndex = selectRoles.getSelectedIndex();
					
					//Todas estas acciones las hago únicamente cuando el select tiene algo seleccionado
					if(selectedIndex>0) {
						
						String valor = (String) selectRoles.getSelectedItem();
					
						//Panel de los datos del usuario
						panelDatosUsuario.setBorder(BorderFactory.createTitledBorder(valor)); // Cambiar el título del panel
						
						//Mostrar las diferentes configuraciones que tengo para cada rol en la vista
						switch(valor) {
						
							case "Alumno":
								
								labelCiclo.setVisible(true);
								selectCiclo.setVisible(true);
								labelCurso.setVisible(true);
								selectCurso.setVisible(true);
								
								labelAsignaturas.setVisible(false);
								selectAsignaturas.setVisible(false);
								listaAsignaturas.setVisible(false);
							
								
							break;
							
							case "Profesor":
								
								labelCiclo.setVisible(false);
								selectCiclo.setVisible(false);
								labelCurso.setVisible(false);
								selectCurso.setVisible(false);
								
								labelAsignaturas.setVisible(true);
								selectAsignaturas.setVisible(true);
								listaAsignaturas.setVisible(true);
								
							break;
						}
						
						
						//Muestro el panel de los datos del usuario
						panelDatosUsuario.setVisible(true);
						panelBotones.setVisible(true);
					}
					else {
						
						panelDatosUsuario.setVisible(false);
						panelBotones.setVisible(false);
						
					}
				}
							
				
				//Select de asignaturas
				if(e.getSource().equals(selectAsignaturas)) {
					
					String valor = (String) selectAsignaturas.getSelectedItem();
					
					//Añadir el valor al JList de asignaturas
					if(selectAsignaturas.getSelectedIndex()>0) {
						if(asignaturasLista.getSize()<3) {
							if((valor != null)&&(!valor.isEmpty())&&(checkListValor(valor))) {
			                    // Agregar el nuevo elemento al modelo de la lista
								asignaturasLista.addElement(valor);
			                }
						}
						else {
							showMensaje("El profesor solamente puede tener asignadas 3 asignaturas.");
						}	
					}
					
				}
				
				//Botón para limpiar el formulario
				if(e.getSource().equals(btnLimpiar)) {
					
					clearFormulario();
				}
				
				//Botón registro -> guarda el usuario
				if(e.getSource().equals(btnRegistro)) {	
					
					//Valido el campo del email, de la contraseña, nombre y apellidos
					try {
						if((checkEmail())&&(checkPassword())&&(checkNombre())&&(checkApellidos())) {
							
							//Dependiendo del rol que vaya a tener el usuario validaremos los campos correspondientes
							switch(selectRoles.getSelectedIndex()) {
							
								//Alumno: ciclo formativo + curso
								case 1: 
									
									if(checkAlumno()) {
										
										boolean usuario=database.insertUsuario(inputNombre.getText(), inputApellidos.getText(),inputEmail.getText(), String.valueOf(inputPassword.getPassword()), (String) selectRoles.getSelectedItem());
										boolean alumno=database.insertDatosAlumno((String) selectCiclo.getSelectedItem(), (String) selectCurso.getSelectedItem(), inputEmail.getText());
										
										if((usuario)&&(alumno)) {
											
											showMensaje("El alumno ha sido creado correctamente.");
											dispose(); //cierro la ventana
																						
										}
									}
									
								break;
									
								//Profesor: asignaturas
								case 2:
									
									if(checkProfesor()) {
										
										boolean usuario=database.insertUsuario(inputNombre.getText(), inputApellidos.getText(),inputEmail.getText(), String.valueOf(inputPassword.getPassword()), (String) selectRoles.getSelectedItem());
										boolean profesor=database.insertDatosProfesor(asignaturasLista, inputEmail.getText());
										
										if((usuario)&&(profesor)) {
											
											showMensaje("El profesor ha sido creado correctamente.");											
											dispose(); //cierro la ventana
										}
									}
								break;
							}
							
						}
					} catch (SQLException | NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
		}
	
			
		//Mouse
		@Override
		public void mouseClicked(MouseEvent e) {
			
			//Lista de asignaturas -> cuando se selecciona un elemento -> que dé la opción de quitarlo
			if(e.getSource().equals(listaAsignaturas)) {
				
				String valor = (String) listaAsignaturas.getSelectedValue(); //Literal seleccionado
				
				//Saco en pantalla un mensaje de confirmación
				int accion = JOptionPane.showConfirmDialog(null, "¿Quieres eliminar la asignatura "+valor+"?", "Confirma la acción", JOptionPane.OK_CANCEL_OPTION);
				
				
				//Si el usuario confirma que lo que quiere hacer es borrar la asignatura -> procedo al borrado
				if (accion == JOptionPane.OK_OPTION) {
				
					int selectedIndex = listaAsignaturas.getSelectedIndex(); //índice seleccionado
					
		            if (selectedIndex != -1) {                  
		                 asignaturasLista.remove(selectedIndex); //eliminarlo
		            }
		            
				}
				
			}
		}
		
	
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	//AVISOS
		
		//Método para imprimir en pantalla los avisos
		protected void showMensaje(String mensaje) {
			
			JOptionPane.showConfirmDialog(null, mensaje,
	                "Message", JOptionPane.CLOSED_OPTION,
	                JOptionPane.INFORMATION_MESSAGE);
		}
	
	
	//VALIDACIONES: Métodos que validan los diferentes campos del formulario
		//Método para comprobar que el campo del nombre no lo tenemos vacío
		protected boolean checkNombre() {
			
			boolean aux = true;
			
			if(inputNombre.getText().isEmpty()) {
				
				showMensaje("ERROR: introduce el nombre del usuario.");			
				
				aux = false;
			}
			
			return aux;
		}
		
		
		//Método para comprobar el campo de los apellidos
		protected boolean checkApellidos() {
			
			boolean aux = true; 
			
			if(inputApellidos.getText().isEmpty()) {
				
				showMensaje("ERROR: introduce los apellidos del usuario.");		
				
				aux = false;
				
			}
			
			return aux;
		}
	
		//Método para comprobar que el email sigue la cadena: usuario@servidor.com y que no esté registrado en la bbdd
		protected boolean checkEmail() throws SQLException {
			
			boolean aux = true;
			
			//Compruebo que el email cumple el patrón correspondiente
			if(!new Email(inputEmail.getText()).getIsEmail()) {
				
				showMensaje("ERROR: debes introducir un email válido \"usuario@servidor.com\".");			
				
				aux = false;
			}
			
			//Compruebo que no haya un usuario registrado en la base de datos con el mismo email
			if(database.getIdUsuario(inputEmail.getText())>0) {
				
				showMensaje("ERROR: ya hay un usuario registrado con ese email");				
			
				aux = false;
			}
			
			return aux;
		}
		
		
		//Método para comprobar que las dos contraseñas son iguales
		protected boolean checkPassword() {
			
			boolean aux = true;
			String password1=String.valueOf(inputPassword.getPassword());
			String password2=String.valueOf(inputRepeatPassword.getPassword());
			
			//Las contraseñas no pueden estar vacías
			if((password1.isEmpty())||(password2.isEmpty())) {
				
				showMensaje("ERROR: las contraseñas no pueden estar vacías.");			
				
				aux=false;
			}
			
			//Las contraseñas deben coincidir
			if((!password1.equals(password2))&&(!password1.isEmpty())&&(!password2.isEmpty())) {
				
				showMensaje("ERROR: las contraseñas no coinciden.");			
				
				aux=false;
			}
			
			return aux;
		}
		
		
		//Método para validar los campos que son necesarios para registrar al alumno
		protected boolean checkAlumno() {
			
			boolean aux = true;		
			
			//Ciclo
			if(selectCiclo.getSelectedIndex()==0) {
				
				showMensaje("ERROR: debes seleccionar un ciclo para el alumno.");
			
				aux=false;
				
			}
			
			
			//Curso
			if(selectCurso.getSelectedIndex()==0) {
				
				showMensaje("ERROR: debes seleccionar un curso para el alumno.");			
				
				aux=false;
				
			}
			
			return aux;
		}
		
		
		//Método para validar los campos que son necesarios para registrar al profesor
		protected boolean checkProfesor() {
			
			boolean aux = true;
			
			if(asignaturasLista.size()==0) {
				
				showMensaje("ERROR: debes seleccionar al menos una asignatura para el profesor");			
				
				aux=false;
				
			}
			
			return aux;
		}
	
		//Método para comprobar si el elemento ya está en la lista, en caso de que ya esté en la lista no debe dejar volver a añadirlo
		protected boolean checkListValor(String valor) {
			
			boolean aux = true;
			
			for(int i=0; i<asignaturasLista.size(); i++) {			
				
				if(valor==asignaturasLista.get(i)) {
					
					showMensaje("ERROR: esta asignatura ya ha sido seleccionada.");
					
					aux=false;
					
				}
			}
			
			return aux;
			
		}

	
	//LIMPIAR
		
		//Método para limpiar el formulario
		protected void clearFormulario() {
			
			inputNombre.setText("");
			inputApellidos.setText("");
			inputEmail.setText("");
			inputPassword.setText("");
			inputRepeatPassword.setText("");
			selectRoles.setSelectedIndex(0);
			
			selectCiclo.setSelectedIndex(0);
			selectCurso.setSelectedIndex(0);
			
			asignaturasLista.clear();
			selectAsignaturas.setSelectedIndex(0);
			
            
			
		}
			
		
		
		
}
