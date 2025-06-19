package model;

import java.sql.SQLException;
import model.Product;

public interface InterfaceProductDAO {
	public int Cadastrar(ConexaoDB conexao,Product produto) throws SQLException;
	
	public void Deletar(ConexaoDB conexao, Product produto) throws SQLException;
	
	public void Atualizar(ConexaoDB conexao, Product produto) throws SQLException;
	
	public  void criarCodigobarras(ConexaoDB conexao, Product produto) throws SQLException; 
}
