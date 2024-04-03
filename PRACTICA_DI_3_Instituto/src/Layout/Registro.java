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
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import Database.Gestion;
import javax.swing.JSpinner;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputApellidos;
	private JTextField inputEmail;
	private JPasswordField inputPassword;
	private JPasswordField inputRepeatPassword;
	
	private Gestion database = new Gestion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
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
		gbl_contentPane.rowHeights = new int[]{290, 80, 80};
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
				labelTitle.setFont(new Font("Open Sans", Font.BOLD, 13));
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
				JComboBox selectRoles = makeSelectAll(panelRegistro, new String[]{"ID", "Nombre"}, "Roles", "Selecciona un rol");	
				selectRoles.setFont(new Font("Open Sans", Font.PLAIN, 13));
				selectRoles.setPreferredSize(new Dimension(0, 25));
				
				GridBagConstraints gbc_selectRoles = new GridBagConstraints();
				gbc_selectRoles.insets = new Insets(0, 0, 10, 0);
				gbc_selectRoles.fill = GridBagConstraints.BOTH;
				gbc_selectRoles.gridx = 1;
				gbc_selectRoles.gridy = 6;
				panelRegistro.add(selectRoles, gbc_selectRoles);      
				
				
				
				
		//Panel del alumno
				
			//Panel
			JPanel panelDatosUsuario = new JPanel();		
			String titlePanelDatosUsuario = "Datos usuario";
			TitledBorder borderPanelDatosUsuario = BorderFactory.createTitledBorder(titlePanelDatosUsuario);
			panelDatosUsuario.setBorder(borderPanelDatosUsuario);
			GridBagConstraints gbc_panelDatosUsuario = new GridBagConstraints();
			gbc_panelDatosUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_panelDatosUsuario.fill = GridBagConstraints.BOTH;
			gbc_panelDatosUsuario.gridx = 0;
			gbc_panelDatosUsuario.gridy = 1;
			contentPane.add(panelDatosUsuario, gbc_panelDatosUsuario);
			
			//panelAlumno.setVisible(false); //no quiero visualizar el panel del alumno
			
			
			
			//Layout
			GridBagLayout gbl_panelDatosUsuario = new GridBagLayout();			
			gbl_panelDatosUsuario.columnWidths = new int[]{150,310};
			gbl_panelDatosUsuario.rowHeights = new int[]{40, 40};				
			panelDatosUsuario.setLayout(gbl_panelDatosUsuario);
			
			
			//Elementos
				//Ciclo formativo
				JLabel lblNewLabel = new JLabel("Ciclo formativo");
				lblNewLabel.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 10, 10);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				panelDatosUsuario.add(lblNewLabel, gbc_lblNewLabel);
				
				JComboBox selectCiclo = makeSelectAll(panelRegistro, new String[]{"ID", "Nombre"}, "Ciclos", "Selecciona un ciclo");				
				selectCiclo.setPreferredSize(new Dimension(0, 25));
				selectCiclo.setFont(new Font("Open Sans", Font.PLAIN, 13));
				
				GridBagConstraints gbc_selectCiclo = new GridBagConstraints();
				gbc_selectCiclo.insets = new Insets(0, 0, 10, 0);
				gbc_selectCiclo.fill = GridBagConstraints.HORIZONTAL;
				gbc_selectCiclo.gridx = 1;
				gbc_selectCiclo.gridy = 0;
				panelDatosUsuario.add(selectCiclo, gbc_selectCiclo);
				
				
				//Curso
				JLabel labelCurso = new JLabel("Curso");
				labelCurso.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelCurso = new GridBagConstraints();
				gbc_labelCurso.anchor = GridBagConstraints.WEST;
				gbc_labelCurso.insets = new Insets(0, 0, 10, 10);
				gbc_labelCurso.gridx = 0;
				gbc_labelCurso.gridy = 1;
				labelCurso.setVisible(false);
				panelDatosUsuario.add(labelCurso, gbc_labelCurso);
				
				JComboBox selectCurso = makeSelectAll(panelRegistro, new String[]{"ID", "Nombre"}, "Cursos", "Selecciona un curso");
				selectCurso.setPreferredSize(new Dimension(0, 25));
				selectCurso.setFont(new Font("Open Sans", Font.PLAIN, 13));				
				selectCurso.setVisible(false);
				GridBagConstraints gbc_selectCurso = new GridBagConstraints();
				gbc_selectCurso.fill = GridBagConstraints.HORIZONTAL;
				gbc_selectCurso.insets = new Insets(0, 0, 10, 0);
				gbc_selectCurso.gridx = 1;
				gbc_selectCurso.gridy = 1;
				panelDatosUsuario.add(selectCurso, gbc_selectCurso);
				
				
				//Asignaturas
				JLabel labelAsignaturas = new JLabel("Asignaturas");				
				labelAsignaturas.setFont(new Font("Open Sans", Font.PLAIN, 13));
				GridBagConstraints gbc_labelAsignaturas = new GridBagConstraints();
				gbc_labelAsignaturas.anchor = GridBagConstraints.WEST;
				gbc_labelAsignaturas.insets = new Insets(0, 0, 10, 10);
				gbc_labelAsignaturas.gridx = 0;
				gbc_labelAsignaturas.gridy = 1;
				labelAsignaturas.setVisible(true);
				panelDatosUsuario.add(labelAsignaturas, gbc_labelAsignaturas);
				
				JComboBox selectAsignaturas = makeSelectAll(panelRegistro, new String[]{"ID", "Nombre"}, "Asignaturas", "Selecciona una asignatura");
				selectAsignaturas.setPreferredSize(new Dimension(0, 25));
				selectAsignaturas.setFont(new Font("Open Sans", Font.PLAIN, 13));
				selectAsignaturas.setToolTipText("Curso");
				selectAsignaturas.setVisible(true);
				GridBagConstraints gbc_selectAsignaturas = new GridBagConstraints();
				gbc_selectAsignaturas.fill = GridBagConstraints.HORIZONTAL;
				gbc_selectAsignaturas.insets = new Insets(0, 0, 10, 0);
				gbc_selectAsignaturas.gridx = 1;
				gbc_selectAsignaturas.gridy = 1;
				panelDatosUsuario.add(selectAsignaturas, gbc_selectAsignaturas);
		
			
		//Panel del alumno
				
			//Panel
			JPanel panelBotones = new JPanel();				
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
			JButton btnLimpiar = new JButton("Limpiar");
			btnLimpiar.setFont(new Font("Open Sans", Font.PLAIN, 13));
			btnLimpiar.setPreferredSize(new Dimension(100, 34));	
			btnLimpiar.setMargin(new Insets(10, 10, 10, 10)); // Padding del botón
			GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
			gbc_btnLimpiar.anchor = GridBagConstraints.EAST;
			gbc_btnLimpiar.insets = new Insets(0, 0, 0, 5);
			gbc_btnLimpiar.gridx = 0;
			gbc_btnLimpiar.gridy = 0;
			panelBotones.add(btnLimpiar, gbc_btnLimpiar);
			
			JButton btnGuardar = new JButton("Guardar");
			btnGuardar.setFont(new Font("Open Sans", Font.PLAIN, 13));
			btnGuardar.setPreferredSize(new Dimension(100, 34));	
			btnGuardar.setMargin(new Insets(10, 10, 10, 10)); // Padding del botón
			GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
			gbc_btnGuardar.anchor = GridBagConstraints.WEST;
			gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
			gbc_btnGuardar.gridx = 1;
			gbc_btnGuardar.gridy = 0;
			panelBotones.add(btnGuardar, gbc_btnGuardar);	
	
		
	}
	
	
	
	//Base de datos
		//Método que devuelve las opciones de los select
		protected JComboBox makeSelectAll(JPanel panelRegistro, String[] campos, String tabla, String defaultOption) throws SQLException {		
			
			Map<Integer, String> valores = database.getValorSelect(campos, tabla); //valores del select
			
			//Creo el select
			JComboBox comboBox = new JComboBox();
			comboBox.addItem(defaultOption);
			
			for(int i=1; i<=valores.size(); i++) {			
				
				comboBox.addItem(valores.get(i));
				
			}		
			
			return comboBox;
			
		}

}
