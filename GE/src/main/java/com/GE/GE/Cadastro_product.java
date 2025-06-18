package com.GE.GE;

import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import model.*;

@RestController // define que esta classe trabalha com requisições de API REST
public class Cadastro_product {
	
	@PostMapping("/cadastrar") // Caso o método seja POST este método é chamado
	public String Cadastrar(@RequestBody Product produto) throws SQLException { // Recebe como argumento o JSON da requisição http e usa automaticamento no construtor do produto
		try {
			
			// Crio o objeto conexão e DAO
			ConexaoDB conexao = new ConexaoDB("localhost","matheus","1234","TESTE");
			ProductDAO productdao = new ProductDAO();
			
			
			// Modificar o date do produto para inserí-lo no banco de dados, pois o método não aceita string(que é o que retorna no momento), 
			//mas objetos do tipo date
			produto.criarCodigobarra();
			
			
			while(productdao.consultarCodigobarras(conexao, produto.getcodigoBarras())) {
				produto.criarCodigobarra();
			}
			productdao.Cadastrar(conexao, produto);
			
			
			return "Produto cadastrado com sucesso";
			
		}catch(SQLException e) {
			return "Houve um erro no cadastro, tente novamente!";
		}catch(Exception e) {
			return "Houve um erro no cadastro, tente novamente!";
		}
	}

}
