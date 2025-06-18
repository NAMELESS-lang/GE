package model;

import java.sql.SQLException;
import model.Product;

public interface InterfaceProductDAO {
	public Product Cadastrar(ConexaoDB conexao,Product produto) throws SQLException;
	
	public void Deletar(ConexaoDB conexao, Product produto) throws SQLException;
	
	public void Atualizar(ConexaoDB conexao, Product produto) throws SQLException;
	
	public  boolean consultarCodigobarras(ConexaoDB conexao, String codigo_barras) throws SQLException; 
}
