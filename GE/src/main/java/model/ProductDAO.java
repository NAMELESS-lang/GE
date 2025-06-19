package model;

import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import model.Product;
import java.sql.PreparedStatement;

@Repository
public class ProductDAO implements InterfaceProductDAO{

	@Override
	public int Cadastrar(ConexaoDB conexao,Product produto) throws SQLException {
		try {
			conexao.iniciarConexao(); // inicia a conexao com o banco de dados
			conexao.getConn().setAutoCommit(false); // Cancela confirmação automática
			String query = "INSERT INTO PRODUTO (nomeProduto,dataValidade,marca,quantidade,pesoProduto, Medida, valor)"
					+ "VALUES(?,?,?,?,?,?,?)";
			
			// Relaciona os parâmetros
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			state.setString(1,produto.getNomeProduto());
			state.setDate(2, produto.getDataValidade());
			state.setString(3, produto.getMarca());
			state.setInt(4,produto.getQuantidade());
			state.setDouble(5, produto.getPeso().getValorPeso());
			state.setString(6, produto.getPeso().getMedida());
			state.setDouble(7, produto.getValor());
			
			// Executa a instrução SQL
			int status = state.executeUpdate(query);
			
			conexao.getConn().commit(); // Confirma as alterações
			state.close(); // Remove os dados do statement
			conexao.getConn().close(); // Fecha a conexão
			return status;
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println("Algum erro aconteceu!");
			System.out.print("Erro: " + e.getMessage());
		}
		return 0;
	}
	
	@Override
	public void Deletar(ConexaoDB conexao, Product produto) throws SQLException{
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
	public void Atualizar(ConexaoDB conexao, Product produto) throws SQLException{
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
	public boolean criarCodigobarras(ConexaoDB conexao, Product produto) throws SQLException{
		try {
			conexao.iniciarConexao();
			String query = "SELECT codigo_barra FROM Product WHERE codigo_barra = ?";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			state.setString(1, produto);
			
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
