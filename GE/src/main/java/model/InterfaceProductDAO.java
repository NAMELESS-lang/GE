package model;

import java.sql.SQLException;
import model.Product;

public interface InterfaceProductDAO {
	public boolean Cadastrar(ConexaoDB conexao,Product product) throws SQLException;
	
	public void Deletar(ConexaoDB conexao, Product product) throws SQLException;
	
	public boolean Atualizar(ConexaoDB conexao, Product product) throws SQLException;
	
	public  boolean criarCodigobarras(ConexaoDB conexao, Product product) throws SQLException; 
}
