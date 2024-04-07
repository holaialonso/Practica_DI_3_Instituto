package Database;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;

import com.mysql.jdbc.Statement;

import Helpers.Helpers;
import Helpers.Password;
import Model.Alumno;

public class Gestion {
	private Conexion conexion=new Conexion();
	private Connection con;
	private Statement st;
	private ResultSet resultado;
	
	
	
	
	//Procesos
	//SELECT
	
		//Método para obtener los valores de los roles creados en la bbdd
		public ArrayList<String> getValorSelect(String[] campos, String tabla) throws SQLException {
			
			ArrayList<String> aux = new ArrayList<>();
			con=conexion.getConexion();
			
			
			//Query
			String query="SELECT "+Helpers.implode(campos, ", ")+" FROM "+tabla;
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				
				while (resultado.next()){		
					
					aux.add(resultado.getString("Nombre"));				
				
								
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			return aux;
		}

		
		//Profesor: método para obtener los ciclos en los que da clase
		public ArrayList<String> getValoresSelectCiclos_Profesor(int idUsuario) throws SQLException{
			
			ArrayList<String> aux = new ArrayList<>();
			con=conexion.getConexion();
			
			String query="SELECT distinct(c.Nombre) as Nombre FROM Asignaturas_Ciclos a INNER JOIN Profesores_Asignaturas b ON a.RefIdAsignatura=b.RefIdAsignatura INNER JOIN Ciclos c ON a.RefIdCiclo=c.ID WHERE b.RefIdUsuario="+idUsuario;
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				
				while (resultado.next()){						
					
					aux.add(resultado.getString("Nombre"));				
								
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
					
		
			return aux;
			
		}

		
		//Profesor: método para obtener los cursos en los que da clase dentro del ciclo seleccionado
		public ArrayList<String> getValoresSelectCursos_Profesor(int idUsuario, String ciclo) throws SQLException{
			
			ArrayList<String> aux = new ArrayList<>();
			con=conexion.getConexion();
			
			String query="SELECT distinct(c.Nombre) as Nombre FROM Profesores_Asignaturas a INNER JOIN Asignaturas b ON a.RefIdAsignatura=b.ID INNER JOIN Cursos c ON c.ID=b.RefIdCurso INNER JOIN Asignaturas_Ciclos d ON b.ID=d.RefIdAsignatura WHERE a.RefIdUsuario="+idUsuario+" AND d.RefIdCiclo="+getIdElemento("Ciclos", ciclo);
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				
				while (resultado.next()){						
					
					aux.add(resultado.getString("Nombre"));				
								
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
					
		
			return aux;
		}
		
		
		//Profesor: método para obtener las asignaturas en las que da clase, en base al ciclo y al curso
		public ArrayList<String> getValoresSelectAsignaturas_Profesor(int idUsuario, String ciclo, String curso) throws SQLException{
			
			ArrayList<String> aux = new ArrayList<>();
			con=conexion.getConexion();
			
			String query="SELECT b.Nombre FROM Profesores_Asignaturas a INNER JOIN Asignaturas b ON a.RefIdAsignatura=b.ID INNER JOIN Asignaturas_Ciclos c ON b.ID=c.RefIdAsignatura WHERE a.RefIdUsuario="+idUsuario+" AND b.RefIdCurso="+getIdElemento("Cursos", curso)+" AND c.RefIdCiclo="+getIdElemento("Ciclos", ciclo);	
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				
				while (resultado.next()){						
					
					aux.add(resultado.getString("Nombre"));				
								
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
		
		
		//Método para obtener un listado de alumnos en base a unos filtros establecidos
		public ArrayList<Alumno> getListadoAlumnos(String curso, String ciclo, String asignatura) throws SQLException{
			
			ArrayList <Alumno> aux = new ArrayList<>();
			con = conexion.getConexion();
			
			//-> getIdElemento("Roles", rol)
			
			//Query
			String query="SELECT c.ID, c.Nombre, c.Apellidos, d.Nota FROM Alumnos_Matricula a INNER JOIN Asignaturas_Ciclos b ON a.RefIdCiclo=b.RefIdCiclo INNER JOIN Usuarios c ON a.RefIdUsuario=c.ID LEFT JOIN Alumnos_Notas d ON c.ID=d.RefIdUsuario WHERE a.RefIdCurso="+getIdElemento("Cursos", curso)+" AND a.RefIdCiclo="+getIdElemento("Ciclos", ciclo)+" AND b.RefIdAsignatura="+getIdElemento("Asignaturas", asignatura);
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				while (resultado.next()){
					
					int id=resultado.getInt("ID");
					String nombre=resultado.getString("Nombre");
					String apellidos=resultado.getString("Apellidos");
					float nota=resultado.getFloat("Nota");
					
					aux.add(new Alumno(id, nombre, apellidos, nota));
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
					
						
			return aux;
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
	
	
	
	
}