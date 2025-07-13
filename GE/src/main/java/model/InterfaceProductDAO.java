package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfaceProductDAO {
	public boolean cadastrar(ConexaoDB conexao,Product product) throws SQLException;
	
	public boolean deletar(ConexaoDB conexao, Product product) throws SQLException;
	
	public boolean atualizar(ConexaoDB conexao, Product product) throws SQLException;
	
	public ArrayList<Product> pesquisar(ConexaoDB conexao, Category jsonreciver) throws SQLException;
	
	public boolean consultarCodigoBarras(ConexaoDB conexao, String codigoBarras) throws SQLException;
	
}
