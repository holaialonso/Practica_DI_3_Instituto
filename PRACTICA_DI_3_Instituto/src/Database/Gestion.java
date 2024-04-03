package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Statement;

import Helpers.Helpers;

public class Gestion {
	private Conexion conexion=new Conexion();
	private Connection con;
	private Statement st;
	private ResultSet resultado;
	
	
	
	//Procesos
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
				
				aux.put(resultado.getInt("ID"), resultado.getString("Nombre"));
							
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return aux;
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