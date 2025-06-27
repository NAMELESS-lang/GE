package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfaceReportsDAO {
	
	public Double lucroTotal(ConexaoDB conexao)throws SQLException;
	
	public Integer totalCadastrado(ConexaoDB conexao)throws SQLException;
}
