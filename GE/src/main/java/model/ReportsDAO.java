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
		return null;
	}
}
