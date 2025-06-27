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
			System.out.print("Erro: " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean Deletar(ConexaoDB conexao, Product produto) throws SQLException{
		try {
			conexao.getConn().setAutoCommit(false); // Cancela a confirmação automática
			String query = "DELETE FROM Product WHERE codigo_barras = ?";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			state.setString(1, produto.getcodigoBarras()); // Associa o código de barras ao comando SQL
			
			int status = state.executeUpdate();
			if(status > 0) { // Caso a operação afetou alguma linha do db, então deletou o item correspondente
				conexao.getConn().commit(); // Confirma as alterações
				return true;
			}
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.print("Erro: " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean Atualizar(ConexaoDB conexao, Product produto) throws SQLException{
		try {
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
				conexao.getConn().commit(); // Confirma as alterações
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
	public ArrayList<Product> Pesquisar(ConexaoDB conexao, JsonReciver jsonreciver) throws SQLException{
		List<Product> pd = new ArrayList<>();
		
		try {
			conexao.getConn().setAutoCommit(false); // Cancela a confirmação automática
			String query = "SELECT * FROM Product WHERE "+ jsonreciver.getCategoria()+" = ?";
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			state.setString(1, jsonreciver.getInput());
			
			ResultSet rs = state.executeQuery(); // Obtém os dados do db
			
			while(rs.next()) {
				Product produto = new Product(rs.getString("codigo_barras"),rs.getString("nome_produto"),rs.getDate("data_validade"),rs.getString("marca")
						,rs.getInt("quantidade"),rs.getDouble("peso_produto"),rs.getString("unidade"),rs.getDouble("valor"));
				pd.add(produto); // Cria cada um dos objetos com os dados e os insere na ArrayList
			}
			conexao.getConn().commit();
			return (ArrayList<Product>) pd; 
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println(e.getMessage());
		}
		return (ArrayList<Product>) pd;
	}
	
	
	
	
	
	@Override
	public boolean criarCodigobarras(ConexaoDB conexao, Product product) throws SQLException{
		try {
			conexao.getConn().setAutoCommit(false);
			String query = "SELECT codigo_barras FROM Product WHERE codigo_barras = ?";
			
			StringBuilder codigo = new StringBuilder(); //Construtor do código de barras
			Random r = new Random(); // Criando um gerador de números aleatórios
			boolean repeat = true; // Variável responsável por repetir o loop de criação do código barras
			
			
			while(repeat == true) { // estrutura usada para analisar se o código já existe no db, e caso exista é criado um novo valor até que haja um inexistente
				for(int i =0; i<5;i++) { //Cria o código de barras com números aleatórios
					codigo.append(r.nextInt(10));
				}
				PreparedStatement state = conexao.getConn().prepareStatement(query);
				state.setString(1, codigo.toString());
				
				ResultSet result = state.executeQuery(); // Armazena o resultado
				if(result.next()) {
					codigo.delete(0, codigo.length());
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
