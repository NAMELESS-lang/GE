package model;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import model.Product;
import java.util.Random;

public class ProductDAO implements InterfaceProductDAO{

	@Override
	public boolean Cadastrar(ConexaoDB conexao,Product produto) throws SQLException {
		try {
			this.criarCodigobarras(conexao, produto);
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
			conexao.getConn().commit(); // Confirma as alterações
			if(status > 0) {
				return true;
			}
			
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println("Algum erro aconteceu!");
			System.out.print("Erro: " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public void Deletar(ConexaoDB conexao, Product produto) throws SQLException{
		try {
			conexao.getConn().setAutoCommit(false); // Cancela a confirmação automática
			String query = "";
			
			Statement state = conexao.getConn().prepareStatement(query);
			state.executeUpdate(query);
			
			conexao.getConn().commit(); // Confirma as alterações
			
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println("Algum erro aconteceu!");
			System.out.print("Erro: " + e.getMessage());
		}
	}
	
	@Override
	public void Atualizar(ConexaoDB conexao, Product produto) throws SQLException{
		try {
			conexao.getConn().setAutoCommit(false); // Cancela a confirmação automática
			String query = "";
			
			Statement state = conexao.getConn().prepareStatement(query);
			state.executeUpdate(query);
			
			conexao.getConn().commit(); // Confirma as alterações
			
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println("Algum erro aconteceu!");
			System.out.print("Erro: " + e.getMessage());
		}
	}
	
	@Override
	public boolean criarCodigobarras(ConexaoDB conexao, Product product) throws SQLException{
		try {
			conexao.getConn().setAutoCommit(false);
			String query = "SELECT codigo_barras FROM Product WHERE codigo_barras = ?";
			
			StringBuilder codigo = new StringBuilder(); //Construtor do código de barras
			Random r = new Random(); // Criando um gerador de números aleatórios
			boolean repeat = true; // Variável responsável por repetir o loop de criação do código barras
			
			
			while(repeat == true) {
				for(int i =0; i<5;i++) { //Cria o código de barras com números aleatórios
					codigo.append(r.nextInt(10));
				}
				PreparedStatement state = conexao.getConn().prepareStatement(query);
				state.setString(1, codigo.toString());
				
				ResultSet result = state.executeQuery(); // Armazena o resultado
				if(result.next()) {
					continue;
				}
				product.setCodigoBarras(codigo.toString());
				return true;
			}
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println(e.getMessage());
		}
			return false;
	}
}
