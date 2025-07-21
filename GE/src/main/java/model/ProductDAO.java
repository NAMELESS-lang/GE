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
	public int cadastrar(ConexaoDB conexao,Product product) throws SQLException {
		
		
			String query = "insert into product(codigo_barras, nome_produto, data_validade, marca, categoria, quantidade, peso, unidade_peso, altura, largura, comprimento, valor)"+
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			
			state.setString(1, product.getcodigoBarras());
			state.setString(2,product.getNomeProduto());
			state.setDate(3, product.getDataValidade());
			state.setString(4, product.getMarca());
			state.setString(5,product.getCategoria());
			state.setInt(6,product.getQuantidade());
			state.setDouble(7, product.getPeso().getValorPeso());
			state.setString(8, product.getPeso().getMedida());
			state.setDouble(9, product.getDimensoes().getAltura());
			state.setDouble(10, product.getDimensoes().getComprimento());
			state.setDouble(11, product.getDimensoes().getLargura());
			state.setDouble(12, product.getValor());
			
			return state.executeUpdate();
			
	}
	
	@Override
	public boolean deletar(ConexaoDB conexao, String codigo_barras) throws SQLException{
			String query = "DELETE FROM product WHERE codigo_barras = ?";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			state.setString(1, codigo_barras); 
			
			int status = state.executeUpdate();
			return status > 0 ? true : false;
	}
	
	@Override
	public boolean atualizar(ConexaoDB conexao, Product product) throws SQLException{
		String query = "update product " + 
					   "set nome_produto = ?, data_validade = ?, marca = ?, categoria = ?, quantidade = ?, peso = ?, unidade_peso = ?, altura = ?, largura = ?, comprimento = ?, valor = ? "+
					   "where codigo_barras = ?"; 
		
		PreparedStatement state = conexao.getConn().prepareStatement(query);
		
		state.setString(1, product.getNomeProduto());
		state.setDate(2, product.getDataValidade());
		state.setString(3, product.getMarca());
		state.setString(4, product.getCategoria());
		state.setInt(5, product.getQuantidade());
		state.setDouble(6, product.getPeso().getValorPeso());
		state.setString(7, product.getPeso().getMedida());
		state.setDouble(8, product.getDimensoes().getAltura());
		state.setDouble(9, product.getDimensoes().getLargura());
		state.setDouble(10, product.getDimensoes().getComprimento());
		state.setDouble(11, product.getValor());
		state.setString(12,product.getcodigoBarras());
		
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
		}
		return (ArrayList<Product>) pd;
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
