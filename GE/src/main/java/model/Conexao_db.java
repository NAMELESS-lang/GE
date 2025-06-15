package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;


public class Conexao_db{
	private String endereco_servidor;
	private String name;
	private String password;
	private Connection conn;
	
	
	public Conexao_db(String nome_servidor,String name, String password, String banco_de_dados) {
		this.endereco_servidor = "jdbc:mysql://"+nome_servidor+"/"+banco_de_dados;
		this.name = name;
		this.password = password;
	}
	
	// Estabelece a conexao com o banco de dados
	public void iniciarConexao() {
		try {
			Connection conexao = null;
			conexao = DriverManager.getConnection(endereco_servidor, name, password);
			if(conexao != null) { // Se a conexao der certo é armazenada na propriedade conn do objeto
				this.conn = conexao;
				System.out.println("Conexão bem-sucedida!");
			}
		}catch(SQLException e) { // Se der algum erro durante o processo, a conexao fica nula e o erro é mostrado
			this.conn = null; 
			System.out.println("Conexão falhou!");
			System.out.println("Motivo: "+e.getMessage());
		}
	}
	
	public Connection getConn() { // Quando precisar usar a conexao do banco de dados, este método é chamado
		return this.conn;
	}
	
	public boolean cloceConn() throws SQLException{
		try {
			if(conn != null) {
				this.conn.close();
				this.conn = null;
				return true;
			}
		}
	catch(SQLException e) {
		System.out.println(e.getMessage());
	}
		return false;
	}
}

