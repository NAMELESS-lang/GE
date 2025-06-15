package com.GE.GE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import model.*;

@RestController // define que esta classe trabalha com requisições de API REST
public class Cadastro_product {
	
	@PostMapping("/cadastrar") // Caso o método seja POST este método é chamado
	public String Cadastrar(@RequestBody Product produto) { // Recebe como argumento o JSON da requisição http e usa automaticamento no construtor do produto
		return "deu certo";
	}

}
