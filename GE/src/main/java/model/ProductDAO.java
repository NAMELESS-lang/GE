package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductDAO implements InterfaceProductDAO{

	@Override
	public boolean Cadastrar(ConexaoDB conexao,Product produto) throws SQLException {
			
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
			
			// Executa a instrução SQL
			int status = state.executeUpdate();
			if(status > 0) {
				return true;
			}
			return false;
	}
	
	@Override
	public boolean Deletar(ConexaoDB conexao, Product produto) throws SQLException{
			conexao.getConn().setAutoCommit(false); // Cancela a confirmação automática
			String query = "DELETE FROM Product WHERE codigo_barras = ?";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			state.setString(1, produto.getcodigoBarras()); // Associa o código de barras ao comando SQL
			
			int status = state.executeUpdate();
			if(status > 0) { // Caso a operação afetou alguma linha do db, então deletou o item correspondente
				conexao.getConn().commit(); // Confirma as alterações
				return true;
			}
			return false;
	}
	
	@Override
	public boolean Atualizar(ConexaoDB conexao, Product produto) throws SQLException{
			conexao.getConn().setAutoCommit(false); // Cancela a confirmação automática
			String query = "UPDATE Product "
					+ "SET nome_produto = ?, data_validade = ?, marca = ?, quantidade = ?, peso_produto =  ?, unidade = ?, valor = ?"
					+ "WHERE codigo_barras = ?";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			state.setString(1, produto.getNomeProduto());
			state.setDate(2, produto.getDataValidade());
			state.setString(3, produto.getMarca());
			state.setInt(4, produto.getQuantidade());
			state.setDouble(5, produto.getPeso().getValorPeso());
			state.setString(6, produto.getPeso().getMedida());
			state.setDouble(7, produto.getValor());
			state.setString(8,produto.getcodigoBarras());
			
			int la = state.executeUpdate(); // la = "linhas afetadas"
			if(la > 0) { // se afetou alguma linha
				return true;
			}
		return false;
	}
	
	
	@Override
	public ArrayList<Product> Pesquisar(ConexaoDB conexao, JsonReciver jsonreciver) throws SQLException{
		List<Product> pd = new ArrayList<>();
		conexao.getConn().setAutoCommit(false); // Cancela a confirmação automática
		String query = "SELECT * FROM Product WHERE "+ jsonreciver.getCategoria()+" = ?";
		PreparedStatement state = conexao.getConn().prepareStatement(query);
		state.setString(1, jsonreciver.getInput());
		
		ResultSet rs = state.executeQuery(); // Obtém os dados do db
		return (ArrayList<Product>) pd; 
	}

	@Override
	public boolean consultarCodigoBarras(ConexaoDB conexao, String codigoBarras) throws SQLException {
		String query = "SELECT * FROM Product WHERE codigo_barras = ?";
		
		PreparedStatement state = conexao.getConn().prepareStatement(query);
		state.setString(0, codigoBarras);
		
		ResultSet rs = state.executeQuery();
		return rs.next();
	}
}
