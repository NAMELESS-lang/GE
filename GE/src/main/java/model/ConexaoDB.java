package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class ConexaoDB implements AutoCloseable{
	private String url;
	private String name;
	private String password;
	private Connection conn;
	
	
	public ConexaoDB(String nome_servidor,String name, String password, String banco_de_dados) {
		this.url = "jdbc:mysql://"+nome_servidor+"/"+banco_de_dados;
		this.name = name;
		this.password = password;
	}
	
	// Estabelece a conexao com o banco de dados
	public boolean iniciarConexao() {
		try {
			Connection conexao = null;
			conexao = DriverManager.getConnection(url, name, password);
			if(conexao != null) { // Se a conexao der certo é armazenada na propriedade conn do objeto
				this.conn = conexao;
				return true;
			}else {
				throw new SQLException("Conexão com o banco de dados falhou falhou!");
			}
		}catch(SQLException e) { // Se der algum erro durante o processo, a conexao fica nula e o erro é mostrado
			this.conn = null;
			LogErros le = new LogErros();
			le.registrarError(e);
		}
		return false;
	}
	
	public Connection getConn() { // Quando precisar usar a conexao com o  banco de dados, este método é chamado
		return this.conn;
	}
	
	
	// SOBRESCREVENDO O METODO DA INTERFACE IMPLEMENTADA PARA USAR NO TRY-WITH-RESOURCES
	@Override
	public void close() throws SQLException {
		try {
			if(conn != null) {
				this.conn.close();
				this.conn = null;
			}
		}
	catch(SQLException e) {
		LogErros le = new LogErros();
		le.registrarError(e);
	}
	}
	
}

