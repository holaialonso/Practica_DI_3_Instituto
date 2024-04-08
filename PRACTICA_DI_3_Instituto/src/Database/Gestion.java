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
import Model.Asignatura;
import Model.Profesor;

public class Gestion {
	private Conexion conexion=new Conexion();
	private Connection con;
	private Statement st;
	private ResultSet resultado;
	
	
	
	
	//Procesos

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

		//Profesor: Método para obtener un listado de alumnos en base a unos filtros establecidos
		public ArrayList<Alumno> getListadoAlumnos(String curso, String ciclo, String asignatura) throws SQLException{
			
			ArrayList <Alumno> aux = new ArrayList<>();
			con = conexion.getConexion();			
						
			//Query
			String query="SELECT f.ID, f.Nombre, f.Apellidos, g.Nota, a.Nombre as Asignatura "
						+ " FROM Asignaturas a INNER JOIN Cursos b ON a.RefIdCurso=b.ID "
						+ " INNER JOIN Asignaturas_Ciclos c ON a.ID=c.RefIdAsignatura "
						+ " INNER JOIN Ciclos d ON c.RefIdCiclo=d.ID "
						+ " INNER JOIN Alumnos_Matricula e ON e.RefIdCurso=b.ID AND e.RefIdCiclo=d.ID "
						+ " INNER JOIN Usuarios f ON e.RefIdUsuario=f.ID "
						+ " LEFT JOIN Alumnos_Notas g ON g.RefIdUsuario=f.ID AND g.RefIdAsignatura=a.ID "
						+ " WHERE b.ID="+getIdElemento("Cursos", curso)+" AND a.ID="+getIdElemento("Asignaturas", asignatura)+" AND d.ID="+getIdElemento("Ciclos", ciclo);
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				while (resultado.next()){
					
					int id=resultado.getInt("ID");
					String nombre=resultado.getString("Nombre");
					String apellidos=resultado.getString("Apellidos");
					
					ArrayList <Asignatura> notas = new ArrayList<>();
									       notas.add(new Asignatura(resultado.getString("Asignatura"), resultado.getDouble("Nota")));
									       
					aux.add(new Alumno(id, nombre, apellidos, notas));
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
					
						
			return aux;
		}
	
		//Login: Método para obtener el rol del usuario
		public int getIdRol(int idUsuario) throws SQLException {
			
			int aux = 0;
			con = conexion.getConexion();	
			
			String query="SELECT RefIdRol FROM Usuarios WHERE ID="+idUsuario;
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				while (resultado.next()){
					
					aux = resultado.getInt("RefIdRol");
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}				
		
			return aux;
		}
		
		
		//Login: Método para crear el alumno
		public Alumno getAlumno(int idUsuario) throws SQLException {
			
			con = conexion.getConexion();
			Alumno aux = new Alumno();
			
			String query="SELECT a.ID, a.Nombre, a.Apellidos, c.Nombre as Curso, d.Nombre as Ciclo"
						+ " FROM Usuarios a INNER JOIN Alumnos_Matricula b ON a.ID=b.RefIdUsuario"
						+ " INNER JOIN Cursos c ON c.ID=b.RefIdCurso"
						+ " INNER JOIN Ciclos d ON d.ID=b.RefIdCiclo"
						+ " WHERE a.ID="+idUsuario;
			
			System.out.println(query);
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				while (resultado.next()){
					
					aux.setId(resultado.getInt("ID"));
					aux.setNombre(resultado.getString("Nombre"));
					aux.setApellidos(resultado.getString("Apellidos"));
					aux.setCiclo(resultado.getString("Ciclo"));
					aux.setCurso(resultado.getString("Curso"));
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}				
			
			return aux;
			
		}

		
		//Login - Alumno: Método para obtener las notas de un alumno
		public ArrayList<Asignatura> getNotasAlumno(int idUsuario, String ciclo, String curso) throws SQLException{
			
			ArrayList<Asignatura> aux = new ArrayList<>();
			con = conexion.getConexion();
			
			String query="SELECT a.Nombre, e.Nota, e.RefIdUsuario"
					+ " FROM Asignaturas a INNER JOIN Cursos b ON a.RefIdCurso=b.ID"
					+ " INNER JOIN Asignaturas_Ciclos c ON c.RefIdAsignatura=a.ID"
					+ " INNER JOIN Ciclos d ON c.RefIdCiclo=d.ID"
					+ " LEFT JOIN Alumnos_Notas e ON e.RefIdAsignatura=a.ID AND e.RefIdCiclo=d.ID"
					+ " WHERE c.RefIdCiclo="+getIdElemento("Ciclos", ciclo)+" AND a.RefIdCurso="+getIdElemento("Cursos", curso);
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				while (resultado.next()){
					
					if((resultado.getInt("RefIdUsuario")==idUsuario)||(resultado.getInt("RefIdUsuario")==0)) {
						
						aux.add(new Asignatura(resultado.getString("Nombre"), resultado.getDouble("Nota")));
					}
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}			
			
			return aux;
			
		}
		
		
		//Login: Método para crear el profesor
		public Profesor getProfesor(int idUsuario) throws SQLException {			
				
			con = conexion.getConexion();
			Profesor aux = new Profesor();
			
			String query="SELECT ID, Nombre, Apellidos FROM Usuarios WHERE ID="+idUsuario;
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				while (resultado.next()){
					
					aux.setId(resultado.getInt("ID"));
					aux.setNombre(resultado.getString("Nombre"));
					aux.setApellidos(resultado.getString("Apellidos"));
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
	
	
			//Profesor: Método para guardar las notas de los alumnos
			public int saveNotaAlumno(int idAlumno, double nota, String ciclo, String asignatura) throws SQLException {
				
				int aux = 1;
				
				//Obengo los id's correspondientes para el ciclo y la asignatura
				int idCiclo=getIdElemento("Ciclos", ciclo);
				int idAsignatura=getIdElemento("Asignaturas", asignatura);
							
				//Compruebo si existe el registro en la tabla para el usuario y dependiendo de eso, hago un insert o un update
				String query="";		
				
				if(issetNotaAlumno(idAlumno, idCiclo, idAsignatura)) {
					
					query="UPDATE Alumnos_Notas SET Nota="+nota+" WHERE RefIdUsuario="+idAlumno+" AND RefIdAsignatura="+idAsignatura+" AND RefIdCiclo="+idCiclo;
					
				}
				else {
					
					if(nota>0) {
					
						query="INSERT INTO Alumnos_Notas (RefIdUsuario, RefIdAsignatura, RefIdCiclo, Nota) VALUES ("+idAlumno+", "+idAsignatura+", "+idCiclo+", "+nota+")";
					
					}
				}
				
							
				//Resultado
				if(query.length()>0) {
					try{
						st=(Statement) con.createStatement();				
						int confirmar = st.executeUpdate(query);				
					}
					catch (SQLException e) {
						e.printStackTrace();
						aux=0;
					}
				}
				
				
				return aux;
			}
			
			
				//Método para comprobar si existe la nota del alumno en la tabla Alumnos_Notas
				public boolean issetNotaAlumno(int idAlumno, int idCiclo, int idAsignatura) throws SQLException {
			
			boolean aux = false;
			con = conexion.getConexion();
			
			//Query
			String query="SELECT * FROM Alumnos_Notas WHERE RefIdUsuario="+idAlumno+" AND RefIdAsignatura="+idAsignatura+" AND RefIdCiclo="+idCiclo;
			
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
			
			
		}

				
	//LOGIN
				
		//Método para loguear un usuario
		public int makeLogin(String email, String password) throws SQLException, NoSuchAlgorithmException {
			
			int aux = 0;
			con=conexion.getConexion();
			
			password = new Password(password).getPassword(); //codificación en md5			
			
			String query="SELECT ID FROM Usuarios WHERE Email='"+email+"' AND Password='"+password+"'";
			
			//Resultado
			try{
				st=(Statement) con.createStatement();
				resultado= st.executeQuery(query);
				while (resultado.next()){
					
					aux = resultado.getInt("ID");
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return aux;
		}
}