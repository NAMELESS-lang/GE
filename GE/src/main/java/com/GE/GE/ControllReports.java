package com.GE.GE;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import model.ConexaoDB;
import model.Reports;

@RestController
public class ControllReports {

	public ConexaoDB conexao = new ConexaoDB("localhost","matheus","1234","TESTE");
	public Reports report = new Reports();
	@PostMapping("/lucro_total")
	public Double obterLucro() throws SQLException {
		try {
			this.conexao.iniciarConexao();
			
			Double valor = this.report.getValorTotal(this.conexao);
			this.conexao.closeConn();
			
			return valor;
	}catch(SQLException e) {
		System.out.println(e.getMessage());
		return null;
	}
	}
}
