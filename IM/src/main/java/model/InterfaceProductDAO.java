package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface InterfaceProductDAO {
	public int cadastrar(ConexaoDB conexao,Product product) throws SQLException;
	
	public boolean deletar(ConexaoDB conexao, String codigo_barras) throws SQLException;
	
	public boolean atualizar(ConexaoDB conexao, Product product) throws SQLException;
	
	public Map<String, String> pesquisarProdutos(ConexaoDB conexao) throws SQLException;
	
	public boolean pesquisarProductExistente(ConexaoDB conexao, Product product) throws SQLException;
	
	public ArrayList<Product> pesquisar(ConexaoDB conexao, Category jsonreciver) throws SQLException;
	
	public boolean consultarCodigoBarras(ConexaoDB conexao, String codigoBarras) throws SQLException;
	
}
