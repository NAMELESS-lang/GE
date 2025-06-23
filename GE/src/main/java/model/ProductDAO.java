package model;

import java.sql.SQLException;
import java.util.Random;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import model.Product;
import java.sql.PreparedStatement;

public class ProductDAO implements InterfaceProductDAO{

	@Override
	public int Cadastrar(ConexaoDB conexao,Product produto) throws SQLException {
		try {
			this.criarCodigobarras(conexao, produto);
			conexao.iniciarConexao(); // inicia a conexao com o banco de dados
			conexao.getConn().setAutoCommit(false); // Cancela confirmação automática
			String query = "INSERT INTO Product (codigo_barras, nome_produto ,data_validade ,marca, quantidade , peso_produto, unidade, valor)"
					+ "VALUES(?,?,?,?,?,?,?,?)";
			
			// Relaciona os parâmetros
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			state.setString(1, produto.getcodigoBarras());
			state.setString(2,produto.getNomeProduto());
			state.setDate(3, produto.getDataValidade());
			state.setString(4, produto.getMarca());
			state.setInt(5,produto.getQuantidade());
			state.setDouble(6, produto.getPeso().getValorPeso());
			state.setString(7, produto.getPeso().getMedida());
			state.setDouble(8, produto.getValor());
			System.out.println("Aqui: "+produto.getcodigoBarras());
			// Executa a instrução SQL
			int status = state.executeUpdate();
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
	public void criarCodigobarras(ConexaoDB conexao, Product produto) throws SQLException{
		StringBuilder codigo = new StringBuilder();
		Random r = new Random();
		for(int i =0; i<5;i++) {
			codigo.append(r.nextInt(10));
		}
		produto.setCodigoBarras(codigo.toString());
	}
}
