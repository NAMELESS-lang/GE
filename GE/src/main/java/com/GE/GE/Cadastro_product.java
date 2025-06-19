package com.GE.GE;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import model.*;

@RestController // define que esta classe trabalha com requisições de API REST
public class Cadastro_product {
	
	@Autowired
	public ProductDAO productdao;
	
	@PostMapping("/cadastrar") // Caso o método seja POST este método é chamado
	public String Cadastrar(@RequestBody Product produto) throws SQLException { // Recebe como argumento o JSON da requisição http e usa automaticamento no construtor do produto
		String message = "Houve um erro no cadastro, tente novamente!";
		try {
			// Crio o objeto conexão e DAO
			ConexaoDB conexao = new ConexaoDB("localhost","matheus","1234","TESTE");
			
			// Cadastra o produto no banco de dados
			int status = productdao.Cadastrar(conexao, produto);
			if(status > 0) {
				return "Produto cadastrado com sucesso";
			}
			conexao.closeConn();
		}catch(SQLException e) {
			return message;
		}catch(Exception e) {
			return message;
		}
		return message;
	}

}
