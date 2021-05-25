package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {

	/** Módulo de conexao **/
	// paramentros de conexao
	 String driver = "com.mysql.cj.jdbc.Driver";
	 String url = "jdbc:mysql://127.0.0.1:3306/cadastro?useTimezone=true&serverTimezone=UTC";
	 String user = "root";
	 String password = "123";

	// Método de conexao

	public Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// create
	public void inserirUsuario(JavaBeans usuario) {
		String create = "insert into usuario (nome,email,senha,telefone) values (?,?,?,?)";
		try {
			// abrir conexa com o banco
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setString(4, usuario.getTelefone());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	 //read
	public ArrayList<JavaBeans> listarUsuarios(){
		//objeto para acessa Java Beans
		ArrayList<JavaBeans> usuario = new ArrayList<>();
		String read = "Select id,nome,email,telefone from usuario order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//variaveis de apoio
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String email = rs.getString(3);
				String telefone = rs.getString(4);
				//arrayLista
				usuario.add(new JavaBeans(id,nome,email,telefone));
			}
			con.close();
			return usuario;
			
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	//update
	public void selecionarUsuario(JavaBeans usuario) {
		String read2 = "select *from usuario where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, usuario.getId());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				usuario.setSenha(rs.getString(4));
				usuario.setTelefone(rs.getString(5));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void updateUsuario(JavaBeans usuario) {
		String update = "update usuario set nome = ?, email=?, senha=?, telefone=? where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setString(4, usuario.getTelefone());
			pst.setInt(5, usuario.getId());
			pst.executeUpdate();
			con.close();
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	public void deleteUsuario(JavaBeans usuario) {
		String delete = "delete from usuario where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, usuario.getId());
		    pst.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public  boolean validate(JavaBeans usuario){
		boolean status = false;

		try{
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement("select *from usuario where email =? and senha=");
			pst.setString(1, usuario.getEmail());
			pst.setString(2, usuario.getSenha());
			
			ResultSet rs = pst.executeQuery();
			status=rs.next();
			
		}catch(Exception e ) {
			System.out.println(e);
		}
		
		return status;
		
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	

}
