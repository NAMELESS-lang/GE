package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements InterfaceProductDAO{

	@Override
	public boolean cadastrar(ConexaoDB conexao,Product produto) throws SQLException {
			String query = "insert into product(codigo_barras, nome_produto, data_validade, marca, quantidade, peso, unidade_peso, altura, largura, comprimento, valor)"+
					"VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			
			state.setString(1, produto.getcodigoBarras());
			state.setString(2,produto.getNomeProduto());
			state.setDate(3, produto.getDataValidade());
			state.setString(4, produto.getMarca());
			state.setInt(5,produto.getQuantidade());
			state.setDouble(6, produto.getPeso().getValorPeso());
			state.setString(7, produto.getPeso().getMedida());
			state.setDouble(8, produto.getDimensoes().getAltura());
			state.setDouble(9, produto.getDimensoes().getComprimento());
			state.setDouble(10, produto.getDimensoes().getLargura());
			state.setDouble(11, produto.getValor());
			

			return state.execute();
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
		String query = "update product" + 
					   "set nome_produto = ?, data_validade = ?, marca = ?, quantidade = ?, peso = ?, unidade_peso = ?, altura = ?, largura = ?, comprimento = ?, valor = ?"+
					   "where codigo_barras = ?"; 
		
		PreparedStatement state = conexao.getConn().prepareStatement(query);
		
		state.setString(1, produto.getNomeProduto());
		state.setDate(2, produto.getDataValidade());
		state.setString(3, produto.getMarca());
		state.setInt(4, produto.getQuantidade());
		state.setDouble(5, produto.getPeso().getValorPeso());
		state.setString(6, produto.getPeso().getMedida());
		state.setDouble(7, produto.getDimensoes().getAltura());
		state.setDouble(8, produto.getDimensoes().getLargura());
		state.setDouble(9, produto.getDimensoes().getComprimento());
		state.setDouble(10, produto.getValor());
		state.setString(11,produto.getcodigoBarras());
		
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
		if(rs.next()) {
		while(rs.next()) {
			Product produto = new Product(rs);
			pd.add(produto);		
		}
			return (ArrayList<Product>) pd;
		}else {
			return null;
		}
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
