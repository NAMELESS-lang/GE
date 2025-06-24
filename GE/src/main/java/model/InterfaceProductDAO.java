package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfaceProductDAO {
	public boolean Cadastrar(ConexaoDB conexao,Product product) throws SQLException;
	
	public boolean Deletar(ConexaoDB conexao, Product product) throws SQLException;
	
	public boolean Atualizar(ConexaoDB conexao, Product product) throws SQLException;
	
	public ArrayList<Product> Pesquisar(ConexaoDB conexao, String categoria,String input) throws SQLException;
	
	public  boolean criarCodigobarras(ConexaoDB conexao, Product product) throws SQLException; 
	
}
