package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO implements InterfaceProductDAO{

	@Override
	public int cadastrar(ConexaoDB conexao,Product produto) throws SQLException {
		
		
			String query = "insert into product(codigo_barras, nome_produto, data_validade, marca, categoria, quantidade, peso, unidade_peso, altura, largura, comprimento, valor)"+
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			
			state.setString(1, produto.getcodigoBarras());
			state.setString(2,produto.getNomeProduto());
			state.setDate(3, produto.getDataValidade());
			state.setString(4, produto.getMarca());
			state.setString(5,produto.getCategoria());
			state.setInt(6,produto.getQuantidade());
			state.setDouble(7, produto.getPeso().getValorPeso());
			state.setString(8, produto.getPeso().getMedida());
			state.setDouble(9, produto.getDimensoes().getAltura());
			state.setDouble(10, produto.getDimensoes().getComprimento());
			state.setDouble(11, produto.getDimensoes().getLargura());
			state.setDouble(12, produto.getValor());
			
			return state.executeUpdate();
			
	}
	
	@Override
	public boolean deletar(ConexaoDB conexao, Product produto) throws SQLException{
			String query = "DELETE FROM product WHERE codigo_barras = ?";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			state.setString(1, produto.getcodigoBarras()); 
			
			int status = state.executeUpdate();
			return status > 0 ? true : false;
	}
	
	@Override
	public boolean atualizar(ConexaoDB conexao, Product produto) throws SQLException{
		String query = "update product " + 
					   "set nome_produto = ?, data_validade = ?, marca = ?, categoria = ?, quantidade = ?, peso = ?, unidade_peso = ?, altura = ?, largura = ?, comprimento = ?, valor = ? "+
					   "where codigo_barras = ?"; 
		
		PreparedStatement state = conexao.getConn().prepareStatement(query);
		
		state.setString(1, produto.getNomeProduto());
		state.setDate(2, produto.getDataValidade());
		state.setString(3, produto.getMarca());
		state.setString(4, produto.getCategoria());
		state.setInt(5, produto.getQuantidade());
		state.setDouble(6, produto.getPeso().getValorPeso());
		state.setString(7, produto.getPeso().getMedida());
		state.setDouble(8, produto.getDimensoes().getAltura());
		state.setDouble(9, produto.getDimensoes().getLargura());
		state.setDouble(10, produto.getDimensoes().getComprimento());
		state.setDouble(11, produto.getValor());
		state.setString(12,produto.getcodigoBarras());
		
		int la = state.executeUpdate();
		return la > 0 ?  true : false;
	}
	
	
	@Override
	public ArrayList<Product> pesquisar(ConexaoDB conexao, Category category) throws SQLException{
		List<Product> pd = new ArrayList<>();
		String query = "SELECT * FROM product WHERE "+ category.getCategoria()+" = ?";
		PreparedStatement state = conexao.getConn().prepareStatement(query);
		state.setString(1, category.getInput());
		
		ResultSet rs = state.executeQuery();
		while(rs.next()) {
			Product produto = new Product(rs);
			pd.add(produto);		
		
			return (ArrayList<Product>) pd;
		}
		return null;
	}
	
	@Override
	public boolean consultarCodigoBarras(ConexaoDB conexao, String codigoBarras) throws SQLException {
		String query = "SELECT * FROM product WHERE codigo_barras = ?";
		
		PreparedStatement state = conexao.getConn().prepareStatement(query);
		state.setString(1, codigoBarras);
		ResultSet rs = state.executeQuery();
		return rs.next();
	}

	@Override
	public Map<String, String> pesquisarProdutos(ConexaoDB conexao) throws SQLException {
		Map<String, String> pd = new HashMap<>();
		
		String query = "SELECT codigo_barras, nome_produto FROM product";
		
		PreparedStatement state = conexao.getConn().prepareStatement(query);
		
		ResultSet rs = state.executeQuery();
		while(rs.next()) {
			pd.put(rs.getString("codigo_barras"),rs.getString("nome_produto"));
		}
		return pd;
	}
}
