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
			gbl_panelRegistro.columnWeights = new double[]{0.0, 1.0};
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
				JComboBox selectRoles = makeSelectRoles(panelRegistro);				
				selectRoles.setPreferredSize(new Dimension(0, 25));
				
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.insets = new Insets(0, 0, 10, 0);
				gbc_comboBox.fill = GridBagConstraints.BOTH;
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 6;
				panelRegistro.add(selectRoles, gbc_comboBox);      
				
				
				
				
		//Panel del alumno
				
			//Panel
			JPanel panelAlumno = new JPanel();		
			String titlePanelAlumno = "Alumno";
			TitledBorder borderPanelAlumno = BorderFactory.createTitledBorder(titlePanelAlumno);
			panelAlumno.setBorder(borderPanelAlumno);
			GridBagConstraints gbc_panelAlumno = new GridBagConstraints();
			gbc_panelAlumno.insets = new Insets(0, 0, 5, 5);
			gbc_panelAlumno.fill = GridBagConstraints.BOTH;
			gbc_panelAlumno.gridx = 0;
			gbc_panelAlumno.gridy = 1;
			contentPane.add(panelAlumno, gbc_panelAlumno);
			
			
			//Layout
			GridBagLayout gbl_panelAlumno = new GridBagLayout();			
			gbl_panelAlumno.columnWeights = new double[]{0.0};
			gbl_panelAlumno.rowHeights = new int[]{40, 40};			
			gbl_panelAlumno.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_panelAlumno.rowWeights = new double[]{Double.MIN_VALUE};
			panelAlumno.setLayout(gbl_panelAlumno);
			
			JLabel lblNewLabel = new JLabel("New label");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			panelAlumno.add(lblNewLabel, gbc_lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("New label");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			panelAlumno.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
			
		//Panel del profesor
			
			//Panel
			/*JPanel panelProfesor = new JPanel();
			String titlePanelProfesor= "Profesor";
			TitledBorder borderPanelProfesor = BorderFactory.createTitledBorder(titlePanelProfesor);
			panelProfesor.setBorder(borderPanelProfesor);
			GridBagConstraints gbc_panelProfesor = new GridBagConstraints();
			gbc_panelProfesor.insets = new Insets(0, 0, 5, 5);
			gbc_panelProfesor.fill = GridBagConstraints.BOTH;
			gbc_panelProfesor.gridx = 0;
			gbc_panelProfesor.gridy = 2;
			contentPane.add(panelProfesor, gbc_panelProfesor);*/
	
		
	}
	
	
	
	//Base de datos
		//Método que devuelve el combobox de los roles del usuario
		protected JComboBox makeSelectRoles(JPanel panelRegistro) throws SQLException {		
			
			String[] campos = {"ID", "Nombre"};
			String tabla = "Roles";
			
			Map<Integer, String> valores = database.getValorSelect(campos, tabla); //valores del select
			
			//Creo el select
			JComboBox comboBox = new JComboBox();
			comboBox.addItem("Selecciona un rol");
			
			for(int i=1; i<=valores.size(); i++) {			
				
				comboBox.addItem(valores.get(i));
				
			}		
			
			return comboBox;
			
		}

}
