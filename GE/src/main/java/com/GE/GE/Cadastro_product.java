package com.GE.GE;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import model.*;

@RestController // define que esta classe trabalha com requisições de API REST
public class Cadastro_product {
	
	@PostMapping("/cadastrar") // Caso o método seja POST este método é chamado
	public String Cadastrar(@RequestBody Product produto) throws SQLException { // Recebe como argumento o JSON da requisição http e usa automaticamento no construtor do produto
		String message = "Houve um erro no cadastro, tente novamente!";
		try {
			// Crio o objeto conexão e o inicio, assim como o DAO
			ConexaoDB conexao = new ConexaoDB("localhost","matheus","1234","TESTE");
			conexao.iniciarConexao();
			ProductDAO productdao = new ProductDAO();
			
			// Cadastra o produto no banco de dados
			boolean status = productdao.Cadastrar(conexao, produto);
			if(status == true) {
				conexao.closeConn();
				return "Produto cadastrado com sucesso";
			}
			throw new Exception("Houve um erro no processo!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return message;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return message;
		}
	}

}
