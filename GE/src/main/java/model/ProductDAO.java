package model;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import model.Product;

public class ProductDAO implements InterfaceProductDAO{
	
	@Override
	public void Cadastrar(Conexao_db conexao,Product produto) throws SQLException {
		try {
			conexao.iniciarConexao(); // inicia a conexao com o banco de dados
			conexao.getConn().setAutoCommit(false); // Cancela confirmação automática
			String query = "INSERT INTO PRODUTO (nome_produto,data_validade,marca,quantidade,peso_produto, unidade_medida_peso, valor)"
					+ "VALUES(?,?,?,?,?,?,?)";
			
			Statement state = conexao.getConn().prepareStatement(query); //Cria o statement 
			state.executeUpdate(query);
			
			conexao.getConn().commit(); // Confirma as alterações
			state.close(); // Remove os dados do statement
			conexao.getConn().close(); // Fecha a conexão
			
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println("Algum erro aconteceu!");
			System.out.print("Erro: " + e.getMessage());
		}
	}
	
	@Override
	public void Deletar(Conexao_db conexao, Product produto) throws SQLException{
		try {
			conexao.iniciarConexao();// inicia a conexao com o banco de dados
			conexao.getConn().setAutoCommit(false); // Cancela a confirmação automática
			String query = "";
			
			Statement state = conexao.getConn().prepareStatement(query);
			state.executeUpdate(query);
			
			conexao.getConn().commit(); // Confirma as alterações
			conexao.getConn().close(); // Fecha a conexão
			
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println("Algum erro aconteceu!");
			System.out.print("Erro: " + e.getMessage());
		}
	}
	
	@Override
	public void Atualizar(Conexao_db conexao, Product produto) throws SQLException{
		try {
			conexao.iniciarConexao();// inicia a conexao com o banco de dados
			conexao.getConn().setAutoCommit(false); // Cancela a confirmação automática
			String query = "";
			
			Statement state = conexao.getConn().prepareStatement(query);
			state.executeUpdate(query);
			
			conexao.getConn().commit(); // Confirma as alterações
			conexao.getConn().close(); // Fecha a conexão
			
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println("Algum erro aconteceu!");
			System.out.print("Erro: " + e.getMessage());
		}
	}
	
	@Override
	public boolean consultarCodigobarras(Conexao_db conexao, String codigo_barras) throws SQLException{
		try {
			conexao.iniciarConexao();
			String query = "SELECT codigo_barra FROM Product WHERE codigo_barra = ?";
			
			Statement state = conexao.getConn().prepareStatement(query);
			boolean resultado = state.execute(query);
			
			conexao.getConn().commit(); // Confirma as alterações
			conexao.getConn().close(); // Fecha a conexão
			return resultado;
		
	}catch(SQLException e) {
		conexao.getConn().rollback();
		System.out.println("Algum erro aconteceu!");
		System.out.print("Erro: " + e.getMessage());
		return false;
	}
	}
}
