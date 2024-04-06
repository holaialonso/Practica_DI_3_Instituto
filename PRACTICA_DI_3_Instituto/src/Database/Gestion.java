package Database;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;

import com.mysql.jdbc.Statement;

import Helpers.Helpers;
import Helpers.Password;

public class Gestion {
	private Conexion conexion=new Conexion();
	private Connection con;
	private Statement st;
	private ResultSet resultado;
	
	
	
	
	//Procesos
	//SELECT
	
		//Método para obtener los valores de los roles creados en la bbdd
		public Map<Integer, String> getValorSelect(String[] campos, String tabla) throws SQLException {
			
			Map<Integer, String> aux = new HashMap<>();
			con=conexion.getConexion();
			
			
			//Query
			String query="SELECT "+Helpers.implode(campos, ", ")+" FROM "+tabla;
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				
				while (resultado.next()){		
					
					String valor = resultado.getString("Nombre");
										
					aux.put(resultado.getInt("ID"), valor);
								
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			return aux;
		}

		
	//Getters

		//Método para comprobar si el usuario existe
		public int getIdUsuario(String email) throws SQLException {
			
			con = conexion.getConexion();
			int id = 0;
				
			//Query
			String query="SELECT ID FROM Usuarios WHERE Email='"+email+"'";
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				while (resultado.next()){
					
					id=resultado.getInt("ID");
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
					
			return id;
			
		}
		
		
		//Método para obtener el id de un elemento
		protected int getIdElemento(String tabla, String elemento) throws SQLException {
			
			con = conexion.getConexion();
			int id = 0;
			
			//Query
			String query="SELECT ID FROM "+tabla+" WHERE Nombre LIKE '"+elemento+"'";
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				while (resultado.next()){
					
					id=resultado.getInt("ID");
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
					
			return id;
			
		}
		
	//CONSULTAS
		
		//INSERT
			
			//Método para insertar un usuario nuevo
			public boolean insertUsuario(String nombre, String apellidos, String email, String password, String rol) throws SQLException, NoSuchAlgorithmException {
				
				con=conexion.getConexion();
				boolean aux = false;
				
				Password pass = new Password(password);
				
				//Query
				String query="INSERT INTO Usuarios(Email, Password, Nombre, Apellidos, RefIdRol) VALUES ('"+email.toLowerCase()+"', '"+pass.getPassword()+"', '"+nombre+"', '"+apellidos+"', "+getIdElemento("Roles", rol)+")";
					
							
				//Resultado
				try {
					st=(Statement) con.createStatement();
					int confirmar = st.executeUpdate(query);
					if (confirmar == 1){
						aux = true;
					}
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return aux;
				
			}


			//Método para insertar los datos correspondientes al alumno
			public boolean insertDatosAlumno(String ciclo, String curso, String email) throws SQLException {
				
				con=conexion.getConexion();
				boolean aux = false;
				
				
				//Query
				String query="INSERT INTO Alumnos_Matricula (RefIdUsuario, RefIdCurso, RefIdCiclo) VALUES ("+getIdUsuario(email)+", "+getIdElemento("Cursos", curso)+", "+getIdElemento("Ciclos", ciclo)+")";
								
				//Resultado
				try {
					st=(Statement) con.createStatement();
					int confirmar = st.executeUpdate(query);
					if (confirmar == 1){
						aux = true;
					}
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return aux;
			}

				
			//Método para insertar los datos correspondientes al profesor
			public boolean insertDatosProfesor(DefaultListModel<String> asignaturas, String email) throws SQLException {
				
				con=conexion.getConexion();
				int aux = 1;		
				int idUsuario=getIdUsuario(email);
				
				//Recorro las asignaturas del profesor y las inserto en la BBDD
				for(int i=0; i<asignaturas.getSize(); i++) {
					
					//Query
					String query="INSERT INTO Profesores_Asignaturas(RefIdUsuario, RefIdAsignatura) VALUES ("+idUsuario+", "+getIdElemento("Asignaturas", asignaturas.get(i))+")";
					
					//Resultado
					try {
						st=(Statement) con.createStatement();
						int confirmar = st.executeUpdate(query);
						if (confirmar == 1){
							aux = aux*1;
						}
						else {
							aux= aux*0;
						}
						st.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return (aux==1) ? true : false;
				
			}
	
	//Método para comprobar si el usuario existe con los datos del usuario y la contraseña -> login
	/*public boolean selectUsuario(String email, String password) throws SQLException {
		
		con=conexion.getConexion();
		boolean aux = false;
		
		//Query
		String query="SELECT * FROM Usuarios WHERE Email='"+email+"' AND Password='"+password+"'";
		
		//Resultado
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(query);
			while (resultado.next()){
				aux = true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return aux;
		
	}*/
	
	
	//Método para comprobar si el usuario es único (con el campo del email)
	/*public boolean issetUsuario(String email) throws SQLException {
		
		con=conexion.getConexion();
		boolean aux = false;
		
		//Query
		String query="SELECT * FROM Usuarios WHERE Email='"+email+"'";
		
		//Resultado
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(query);
			while (resultado.next()){
				aux = true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return aux;
	}*/
	
	
	//Método para insertar un nuevo usuario
	/*public boolean insertUsuario(String email, String password, String nombre, String apellidos) throws SQLException {
		
		con=conexion.getConexion();
		boolean aux = false;
		
		if(!issetUsuario(email)) {
		
			//Query
			String query="INSERT INTO Usuarios(Email, Password, Nombre, Apellidos) VALUES ('"+email+"', '"+password+"', '"+nombre+"', '"+apellidos+"')";
				
			//Resultado
			try {
				st=(Statement) con.createStatement();
				int confirmar = st.executeUpdate(query);
				if (confirmar == 1){
					aux = true;
				}
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return aux;
	}*/
	
	
}