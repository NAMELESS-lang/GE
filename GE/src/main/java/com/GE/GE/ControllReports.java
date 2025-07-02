package com.GE.GE;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import model.ConexaoDB;
import model.Reports;

@RestController
public class ControllReports {

	public ConexaoDB conexao = new ConexaoDB("localhost","matheus","1234","TESTE");
	public Reports report = new Reports();
	@PostMapping("/gerar_relatorios")
	public Reports gerarRelatorios(){
		try {
			this.conexao.iniciarConexao();
			
			this.report.geraRelatorios(this.conexao);
			this.conexao.closeConn();

			return this.report;
	}catch(Exception e) {
		System.out.println(e.getMessage());
		return null;
	}
	}
}
