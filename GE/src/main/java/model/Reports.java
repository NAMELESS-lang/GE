package model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Reports {

	public Double lucroTotal;
	public Integer totalItens;
	public ArrayList<Product> produtosAcabando;
	@JsonIgnore
	public ReportsDAO reportsdao = new ReportsDAO();

	public void geraRelatorios(ConexaoDB conexao)throws SQLException{
		this.lucroTotal = this.reportsdao.lucroTotal(conexao);
		this.totalItens = this.reportsdao.totalCadastrado(conexao);
		this.produtosAcabando = this.reportsdao.listarProdutosAcabando(conexao);
	}
	
	public Double getLucroTotal() {
		return lucroTotal;
	}

	public Integer getTotalItens() {
		return totalItens;
	}

	public ArrayList<Product> getProdutosAcabando() {
		return produtosAcabando;
	}
	
}
