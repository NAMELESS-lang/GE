package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class Reports {
	public Double valorTotal;
	public Integer totalItens;
	public ArrayList<Product> produtosVencendo;
	public ReportsDAO reportsdao = new ReportsDAO();

	public Double getValorTotal(ConexaoDB conexao) throws SQLException { return this.reportsdao.lucroTotal(conexao); }

	public Integer getTotalItens(ConexaoDB conexao) throws SQLException{ return this.reportsdao.totalCadastrado(conexao); }

	public ArrayList<Product> getProdutosVencendo() { return produtosVencendo; }
	
	
}
