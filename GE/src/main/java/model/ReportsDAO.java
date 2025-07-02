package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportsDAO implements InterfaceReportsDAO{


	@Override
	public Double lucroTotal(ConexaoDB conexao) throws SQLException{
		try {
			conexao.getConn().setAutoCommit(false);
			String query = "SELECT SUM(valor) as valor FROM Product";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			ResultSet rs = state.executeQuery();
			while(rs.next()) {
				return rs.getDouble("valor");
			}
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println(e.getMessage());
	}
		return null;
}

	@Override
	public Integer totalCadastrado(ConexaoDB conexao) throws SQLException {
		try {
			conexao.getConn().setAutoCommit(false);
			
			String query = "SELECT COUNT(codigo_barras) as codigo_barra FROM Product";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			ResultSet rs = state.executeQuery();
			while(rs.next()) {
				return rs.getInt("codigo_barra");
			}
	}catch(SQLException e) {
		conexao.getConn().rollback();
		System.out.println(e.getMessage());
	}
		return null;

}
	
	
	@Override 
	public ArrayList<Product> listarProdutosAcabando(ConexaoDB conexao) throws SQLException{
		try {
			List<Product> lpd = new ArrayList<>();
			conexao.getConn().setAutoCommit(false);
			String query = "SELECT * FROM Product WHERE quantidade < 50";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			ResultSet rs = state.executeQuery();
			while(rs.next()) {
				Product produto = new Product(rs.getString("codigo_barras"),rs.getString("nome_produto"),rs.getDate("data_validade"),rs.getString("Marca"),
						rs.getInt("quantidade"),rs.getDouble("peso_produto"),rs.getString("unidade"),rs.getDouble("valor"));
				lpd.add(produto);
			}
			return (ArrayList<Product>)lpd;
		}catch(SQLException e) {
			conexao.getConn().rollback();
			conexao.closeConn();
			return null;
		}
	}
}
