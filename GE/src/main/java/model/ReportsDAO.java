package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportsDAO implements InterfaceReportsDAO{


	@Override
	public Double lucroTotal(ConexaoDB conexao) throws SQLException{
		try {
			conexao.getConn().setAutoCommit(false);
			String query = "SELECT SUM(valor) FROM Product";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			ResultSet rs = state.executeQuery();
			return rs.getDouble(0);
			
		}catch(SQLException e) {
			conexao.getConn().rollback();
			System.out.println(e.getMessage());
			return null;
	}
}

	@Override
	public Integer totalCadastrado(ConexaoDB conexao) throws SQLException {
		try {
			conexao.getConn().setAutoCommit(false);
			
			String query = "SELECT COUNT(codigo_barras) FROM Product";
			
			PreparedStatement state = conexao.getConn().prepareStatement(query);
			ResultSet rs = state.executeQuery();
			return rs.getInt(0);
			
	}catch(SQLException e) {
		conexao.getConn().rollback();
		System.out.println(e.getMessage());
		return null;
	}
}
}
